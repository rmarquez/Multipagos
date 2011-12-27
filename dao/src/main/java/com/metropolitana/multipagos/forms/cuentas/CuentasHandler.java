package com.metropolitana.multipagos.forms.cuentas;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import com.metropolitana.multipagos.Cuentas;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

public class CuentasHandler {
	
	/** Nombre del campo para la búsqueda de cuentas. */
	private static final String CAMPO_BUSQUEDA = "cuentaEmpresa";

	/**
	 * Inserta una cuenta
	 * 
	 * @param cuenta
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(Cuentas cuenta, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(cuenta);
			broker.store(LogsHandler.setLogsDelSistema(cuenta, fecha, usrId,
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
	 * Actualiza los datos de una cuenta
	 * 
	 * @param cuenta
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(Cuentas cuenta, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(cuenta);
			/**if(inactivo.booleanValue()== Boolean.FALSE) {
				broker.store(LogsHandler.setLogsDelSistema(cuenta, fecha, usrId,
						Integer.valueOf(2), broker));
			} else {
				broker.store(LogsHandler.setLogsDelSistema(cuenta, fecha, usrId,
						Integer.valueOf(3), broker));
			}**/
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
	 * Obtiene una cuenta
	 * 
	 * @param cuentaId
	 *            Identificador de la cuenta a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos de la cuenta
	 */
	public static Cuentas retrieve(Integer cuentaId, PersistenceBroker broker) {
		Cuentas criterio = new Cuentas();
		criterio.setCuentaId(cuentaId);
		Query query = new QueryByIdentity(criterio);
		return (Cuentas) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene una cuenta
	 * 
	 * @param cuentaId
	 *            Identificador de la cuenta a obtener
	 * @return bean que contiene los datos de la cuenta
	 */
	public static Cuentas retrieve(Integer cuentaId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(cuentaId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de cuentas
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de cuentas
	 * @throws Exception
	 */
	private Collection getList(Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Cuentas.class, criterio);
			query.addOrderBy("cuentaEmpresa", true);
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
	 * Genera listado de cuentas
	 * 
	 * @param cuentaEmpresa
	 *            nombre o parte del nombre de la empresa a quien pertenece la cuenta
	 * @return Collection o null listado de cuentas
	 * @throws Exception
	 */
	public Collection getList(String cuentaEmpresa) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (cuentaEmpresa != null && cuentaEmpresa.length() > 0) {
				criterio.addLike("upper(cuentaEmpresa)",
						cuentaEmpresa.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Genera el listado de todos las cuentas
	 * 
	 * @return Collection o null Listado de cuentas
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de cuentas a presentar en los resultados de las búsquedas
	 * por página.
	 * 
	 * @param cuentaEmpresa
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de cuentas por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             bancos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<Cuentas> getResultadosXPagina(
			final String cuentaEmpresa, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(Cuentas.class,
					getCriterio(cuentaEmpresa, CAMPO_BUSQUEDA),
					CAMPO_BUSQUEDA, pagina, registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Obtiene la cantidad de cuentas que coinciden con el filtro de búsqueda.
	 * 
	 * @param cuentaEmpresa
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String cuentaEmpresa) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Cuentas.class,
					getCriterio(cuentaEmpresa, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static final Criteria getCriterio(final String criterioBusqueda,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		if (criterioBusqueda != null && criterioBusqueda.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					criterioBusqueda.toUpperCase(Locale.getDefault()) + "*");
		}

		return criterio;
	}

}
