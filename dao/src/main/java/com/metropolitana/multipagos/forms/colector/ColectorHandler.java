package com.metropolitana.multipagos.forms.colector;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class ColectorHandler {

	/**
	 * <code>COLECTOR_ID</code> Identificador del colector
	 */
	public static final String COLECTOR_ID = "colectorId";

	/** Primer campo para la búsqueda de colectores. */
	private static final String CAMPO_BUSQUEDA1 = "primerNombre";
	
	/** Segundo campo para la búsqueda de colectores. */
	private static final String CAMPO_BUSQUEDA2 = "primerApellido";
	
	/** Tercer campo para la búsqueda de colectores. */
	private static final String CAMPO_BUSQUEDA3 = "cedula";

	/**
	 * Inserta un colector.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Colector bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(bean);
			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
					Integer.valueOf(1), broker));
			broker.commitTransaction();
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				if (broker.isInTransaction()) {
					broker.abortTransaction();
				}
				broker.close();
			}
		}
	}

	/**
	 * Actualiza los datos de un colector.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Colector bean, Integer usrId, Boolean inactivo) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(bean);
			if(inactivo.booleanValue()== Boolean.FALSE) {
				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
						Integer.valueOf(2), broker));
			} else {
				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
						Integer.valueOf(3), broker));
			}			
			broker.commitTransaction();
			broker.clearCache();
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				if (broker.isInTransaction()) {
					broker.abortTransaction();
				}
				broker.close();
			}
		}
	}

	/**
	 * Obtiene un colector.
	 * 
	 * @param colectorId
	 *            Identificador del colector a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del colector
	 */
	public static Colector retrieve(final Integer colectorId,
			final PersistenceBroker broker) {
		Colector criterio = new Colector();
		criterio.setColectorId(colectorId);
		Query query = new QueryByIdentity(criterio);
		return (Colector) broker.getObjectByQuery(query);
	}
	
	/**
	 * Obtiene un colector.
	 * 
	 * @param colectorId
	 *            Identificador del colector a obtener
	 * @return bean que contiene los datos del colector
	 */
	public static Colector retrieve(final Integer colectorId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(colectorId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de colectors.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de colectors
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			criterio.addEqualTo("inactivo", false);
			QueryByCriteria query = new QueryByCriteria(Colector.class,
					criterio);
			query.addOrderBy("primerNombre", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de colectores.
	 * 
	 * @param primerNombre
	 *            nombre o parte del nombre de un colector
	 * @return Collection o null listado de colectors
	 * @throws Exception
	 */
	public Collection getList(final String primerNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (primerNombre != null && primerNombre.length() > 0) {
				criterio.addLike("upper(primerNombre)",
						primerNombre.toUpperCase(Locale.getDefault())
								+ "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de colectors a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param primerNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de colectors por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             colectors.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<Colector> getResultadosXPagina(
			final String primerNombre, final String primerApellido,
			final String cedula, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					Colector.class,
					getCriterio(primerNombre, primerApellido, cedula, filtrar,
							CAMPO_BUSQUEDA1, CAMPO_BUSQUEDA2, CAMPO_BUSQUEDA3),
					CAMPO_BUSQUEDA1, pagina, registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Obtiene la cantidad de colectors que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param primerNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String primerNombre,
			final String primerApellido, final String cedula,
			final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Colector.class,
					getCriterio(primerNombre, primerApellido, cedula, filtrar,
							CAMPO_BUSQUEDA1, CAMPO_BUSQUEDA2, CAMPO_BUSQUEDA3));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private final Criteria getCriterio(final String primerNombre,
			final String primerApellido, final String cedula,
			final Integer filtrar, final String campoBusqueda1,
			final String campoBusqueda2, final String campoBusqueda3) {
		Criteria criterio = new Criteria();

		if (primerNombre != null && primerNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda1 + ")",
					primerNombre.toUpperCase(Locale.getDefault()) + "*");
		}
		if (primerApellido != null && primerApellido.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda2 + ")",
					primerApellido.toUpperCase(Locale.getDefault()) + "*");
		}
		if (cedula != null && cedula.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda3 + ")",
					cedula.toUpperCase(Locale.getDefault()) + "*");
		}
		if (filtrar == Integer.valueOf(1)) {
			criterio.addEqualTo("inactivo", Boolean.FALSE);
		}
		if (filtrar == Integer.valueOf(2)) {
			criterio.addEqualTo("inactivo", Boolean.TRUE);
		}
		return criterio;
	}

	/**
	 * Genera el listado de todos los colectores.
	 * 
	 * @return Collection o null Listao de colectores
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getListColector() throws PBFactoryException {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					Colector.class, new Criteria());
			query.setAttributes(new String[] { "colectorId",
					"primerNombre", "primerApellido" });
			query.addOrderBy("primerNombre", true);
			return IteratorUtils.toList(broker
					.getReportQueryIteratorByQuery(query));
		} catch (PBFactoryException e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getListColector(final String primerNombre)
			throws Exception {
			PersistenceBroker broker = null;
			try {
				broker = PersistenceBrokerFactory.defaultPersistenceBroker();
				Criteria criterio = new Criteria();
				criterio.addLike("upper(primerNombre)", "*"
						+ primerNombre.toUpperCase(Locale.getDefault())
						+ "*");
				ReportQueryByCriteria query = new ReportQueryByCriteria(
						Colector.class, criterio);
				query.setAttributes(new String[] { "colectorId",
						"primerNombre", "primerApellido" });
				query.addOrderByAscending("primerNombre");
				return IteratorUtils.toList(broker
						.getReportQueryIteratorByQuery(query));
			} catch (Exception e) {
				throw e;
			} finally {
				if (broker != null && !broker.isClosed()) {
					broker.close();
				}
			}
		}
	
	public static Colector colectorXNumero(final Integer colectorNumero)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return colectorXNumero(colectorNumero, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static Colector colectorXNumero(final Integer colectorNumero,
			final PersistenceBroker broker) {
		Colector criterio = new Colector();
		criterio.setColectorNumero(colectorNumero);
		Query query = new QueryByCriteria(criterio);
		return (Colector) broker.getObjectByQuery(query);
	}
}
