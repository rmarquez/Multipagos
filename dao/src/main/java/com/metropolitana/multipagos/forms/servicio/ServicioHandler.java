package com.metropolitana.multipagos.forms.servicio;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.Simbolo;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
//import com.metropolitana.multipagos.forms.logs.LogsHandler;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * @author Rafael Márquez
 */
public class ServicioHandler {

	/**
	 * <code>SERVICIO_ID</code> Identificador del servicio
	 */
	public static final String SERVICIO_ID = "servicioId";

	/** Nombre del campo para la búsqueda de servicios. */
	private static final String CAMPO_BUSQUEDA = "servicioNombre";

	/**
	 * Inserta un servicio.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Servicio bean, Integer usrId) throws Exception {
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
	 * Actualiza los datos de un servicio.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Servicio bean, Integer usrId, Boolean inactivo)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(bean);
			if (inactivo.booleanValue() == Boolean.FALSE) {
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
	 * Obtiene un servicio.
	 * 
	 * @param servicioId
	 *            Identificador del servicio a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del servicio
	 */
	public static Servicio retrieve(final Integer servicioId,
			final PersistenceBroker broker) {
		Servicio criterio = new Servicio();
		criterio.setServicioId(servicioId);
		Query query = new QueryByIdentity(criterio);
		return (Servicio) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un servicio.
	 * 
	 * @param servicioId
	 *            Identificador del servicio a obtener
	 * @return bean que contiene los datos del servicio
	 */
	public static Servicio retrieve(final Integer servicioId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(servicioId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de servicios.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de servicios
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Servicio.class,
					criterio);
			query.addOrderBy("servicioNombre", true);
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
	 * Genera listado de servicios.
	 * 
	 * @param servicioNombre
	 *            nombre o parte del nombre de un servicio
	 * @return Collection o null listado de servicios
	 * @throws Exception
	 */
	public Collection getList(final String servicioNombre) throws Exception {
		PersistenceBroker broker = null;
	try {
		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Criteria criterio = new Criteria();
		criterio.addLike("upper(servicioNombre)", "*"
				+ servicioNombre.toString().toUpperCase(Locale.getDefault())
				+ "*");
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				Servicio.class, criterio);
		query.setAttributes(new String[] { "servicioId",
				"servicioNombre" });
		query.addOrderByAscending("servicioNombre");
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

	/**
	 * Obtener la lista de servicios a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param servicioNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de servicios por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             servicios.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<Servicio> getResultadosXPagina(
			final String servicioNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(Servicio.class,
					Util.getCriterio(servicioNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de servicios que coinciden con el filtro de búsqueda.
	 * 
	 * @param servicioNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String servicioNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Servicio.class,
					Util.getCriterio(servicioNombre, filtrar, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera el listado de todos los servicios.
	 * 
	 * @return Collection o null Listao de servicios
	 * @throws Exception
	 */
	public Collection getList() throws PBFactoryException {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					Servicio.class, new Criteria());
			query.setAttributes(new String[] { "servicioId",
					"servicioNombre" });
			query.addOrderBy("servicioNombre", true);
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
	
	public Collection getServicioList() throws Exception {
		try {
			return getServicioList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Collection getServicioList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Servicio.class,
					criterio);
			query.addOrderBy("servicioNombre", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getServicioXCartera(final Integer carteraId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (carteraId != null) {
				criterio.addEqualTo("carteraXDepartamentoList.carteraId", carteraId);
			}
			QueryByCriteria query = new QueryByCriteria(Servicio.class, criterio);
			query.addOrderByAscending("servicioNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static boolean existeServicio(String servicioNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (servicioNombre != null && servicioNombre.length() > 0) {
				criterio.addLike("upper(servicioNombre)",
						servicioNombre.toUpperCase(Locale.getDefault()) + "*");
			}
            List lst = getNombreList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	private static List getNombreList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(Servicio.class, criterio);
            query.addOrderBy("servicioNombre", true);
            return (List)broker.getCollectionByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
}
