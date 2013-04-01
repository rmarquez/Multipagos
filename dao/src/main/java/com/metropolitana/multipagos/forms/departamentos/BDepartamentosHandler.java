package com.metropolitana.multipagos.forms.departamentos;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.BDepartamento;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

/**
 * @author Rafael Márquez
 */
public class BDepartamentosHandler {

	/**
	 * <code>DEP_ID</code> Identificador del BDepartamento
	 */
	public static final String DEP_ID = "bDepartamentoId";

	/** Nombre del campo para la búsqueda de BDepartamentos. */
	private static final String CAMPO_BUSQUEDA = "bDepartamentoNombre";

	/**
	 * Inserta un BDepartamento.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
//	public void insert(final BDepartamento bean, Integer usrId) throws Exception {
//		PersistenceBroker broker = null;
//
//		try {
//			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
//			broker.beginTransaction();
//			Date fecha = Calendar.getInstance().getTime();
//			// actualizarReferencias(bean, broker);
//			broker.store(bean);
////			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
////					Integer.valueOf(1), broker));
//			broker.commitTransaction();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			if (broker != null && !broker.isClosed()) {
//				if (broker.isInTransaction()) {
//					broker.abortTransaction();
//				}
//				broker.close();
//			}
//		}
//	}

	/**
	 * Actualiza los datos de un BDepartamento.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
//	public void update(final BDepartamento bean, Integer usrId, Boolean inactivo) throws Exception {
//		PersistenceBroker broker = null;
//
//		try {
//			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
//			broker.beginTransaction();
//			Date fecha = Calendar.getInstance().getTime();
//			// actualizarReferencias(bean, broker);
//			broker.store(bean);
//			if(inactivo.booleanValue()== Boolean.FALSE) {
//				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
//						Integer.valueOf(2), broker));
//			} else {
//				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
//						Integer.valueOf(3), broker));
//			}			
//			broker.commitTransaction();
//			broker.clearCache();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			if (broker != null && !broker.isClosed()) {
//				if (broker.isInTransaction()) {
//					broker.abortTransaction();
//				}
//				broker.close();
//			}
//		}
//	}

	/**
	 * Obtiene un BDepartamento.
	 * 
	 * @param bDepartamentoId
	 *            Identificador del BDepartamento a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del BDepartamento
	 */
	public static BDepartamento retrieve(final Integer bDepartamentoId,
			final PersistenceBroker broker) {
		BDepartamento criterio = new BDepartamento();
		criterio.setBDepartamentoId(bDepartamentoId);
		Query query = new QueryByIdentity(criterio);
		return (BDepartamento) broker.getObjectByQuery(query);
	}
	
	/**
	 * Obtiene un BDepartamento.
	 * 
	 * @param bDepartamentoId
	 *            Identificador del BDepartamento a obtener
	 * @return bean que contiene los datos del BDepartamento
	 */
	public static BDepartamento retrieve(final Integer bDepartamentoId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(bDepartamentoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de BDepartamentos.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de BDepartamentos
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(BDepartamento.class,
					criterio);
			query.addOrderBy("bDepartamentoNombre", true);
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
	 * Genera listado de BDepartamentos.
	 * 
	 * @param bDepartamentoNombre
	 *            nombre o parte del nombre de un BDepartamento
	 * @return Collection o null listado de BDepartamentos
	 * @throws Exception
	 */
	public Collection getList(final String bDepartamentoNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (bDepartamentoNombre != null && bDepartamentoNombre.length() > 0) {
				criterio.addLike("upper(bDepartamentoNombre)",
						bDepartamentoNombre.toUpperCase(Locale.getDefault())
								+ "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de BDepartamentos a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param bDepartamentoNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de BDepartamentos por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             BDepartamentos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<BDepartamento> getResultadosXPagina(
			final String bDepartamentoNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(BDepartamento.class,
					Util.getCriterio(bDepartamentoNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de BDepartamentos que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param bDepartamentoNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String bDepartamentoNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(BDepartamento.class,
					Util.getCriterio(bDepartamentoNombre, filtrar, CAMPO_BUSQUEDA));
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
	 * Genera el listado de todos los BDepartamentos.
	 * 
	 * @return Collection o null Listao de BDepartamentos
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("inactivo", Boolean.FALSE);
		try {
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean existeBDepartamento(String bDepartamentoNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (bDepartamentoNombre != null && bDepartamentoNombre.length() > 0) {
				criterio.addLike("upper(bDepartamentoNombre)",
						bDepartamentoNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(BDepartamento.class, criterio);
            query.addOrderBy("bDepartamentoNombre", true);
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
