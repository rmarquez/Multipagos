package com.metropolitana.multipagos.forms.estado_corte;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.Cuentas;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
/**
 * 
 * @author Rafael Márquez
 * 22/09/2011
 *
 */
public class EstadoCorteHandler {

	/**
	 * <code>ESTADO_ID</code> Identificador del estado
	 */
	public static final String ESTADO_ID = "estadoId";

	/** Nombre del campo para la búsqueda de estados. */
	private static final String CAMPO_BUSQUEDA = "estadoNombre";

	/**
	 * Inserta un estado.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final EstadoCorte bean, Integer usrId) throws Exception {
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
	 * Actualiza los datos de un estado.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final EstadoCorte bean, Integer usrId, Boolean inactivo)
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
	 * Obtiene un estado.
	 * 
	 * @param estadoId
	 *            Identificador del estado a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del estado
	 */
	public static EstadoCorte retrieve(final Integer estadoId,
			final PersistenceBroker broker) {
		EstadoCorte criterio = new EstadoCorte();
		criterio.setEstadoId(estadoId);
		Query query = new QueryByIdentity(criterio);
		return (EstadoCorte) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un estado.
	 * 
	 * @param estadoId
	 *            Identificador del estado a obtener
	 * @return bean que contiene los datos del estado
	 */
	public static EstadoCorte retrieve(final Integer estadoId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(estadoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de estados.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de estados
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(EstadoCorte.class,
					criterio);
			query.addOrderBy("estadoNombre", true);
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
	 * Genera listado de estados.
	 * 
	 * @param estadoNombre
	 *            nombre o parte del nombre de un estado
	 * @return Collection o null listado de estados
	 * @throws Exception
	 */
	public Collection getList(final String estadoNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (estadoNombre != null && estadoNombre.length() > 0) {
				criterio.addLike("upper(estadoNombre)",
						estadoNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de estados a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param estadoNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de estados por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             estados.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<EstadoCorte> getResultadosXPagina(
			final String estadoNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(EstadoCorte.class,
					Util.getCriterio(estadoNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de estados que coinciden con el filtro de búsqueda.
	 * 
	 * @param estadoNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String estadoNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(EstadoCorte.class,
					Util.getCriterio(estadoNombre, filtrar, CAMPO_BUSQUEDA));
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
	 * Genera el listado de todos los estados.
	 * 
	 * @return Collection o null Listado de estados
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean validarEstado(String estadoNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            
            if (estadoNombre != null) {
                    criterio.addEqualTo("estadoNombre", estadoNombre);
                    //criterio.addLike("upper(estadoNombre)",
                    //		estadoNombre.toUpperCase(Locale.getDefault()) + "*");
            }
            List lst = getEstadoList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	private static List getEstadoList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(EstadoCorte.class, criterio);
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
