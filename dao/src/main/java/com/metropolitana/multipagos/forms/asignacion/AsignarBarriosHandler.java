package com.metropolitana.multipagos.forms.asignacion;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.AsignarBarrio;
import com.metropolitana.multipagos.DetalleBarrios;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class AsignarBarriosHandler {
	
	public void insert(final AsignarBarrio bean, Integer usrId) throws Exception {
		org.apache.ojb.broker.PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			//actualizarReferencias(bean, broker);
			broker.store(bean);
			//broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
			//		Integer.valueOf(1), broker));
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
	 * Actualiza los datos de un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final AsignarBarrio bean, Integer usrId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			//actualizarReferencias(bean, broker);
			broker.store(bean);
			//broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
			//			Integer.valueOf(2), broker));
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
	public void remove(Integer visitaId, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            Date fecha = Calendar.getInstance().getTime();
            Visitas bean = retrieve(visitaId, broker);
            broker.delete(bean);
            //broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId, Integer.valueOf(3), broker));
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
**/
	/**
	 * Obtiene un visita.
	 * 
	 * @param visitaId
	 *            Identificador del visita a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del visita
	 */
	public static AsignarBarrio retrieve(final Integer asignarbId,
			final PersistenceBroker broker) {
		AsignarBarrio criterio = new AsignarBarrio();
		criterio.setAsignarbId(asignarbId);
		Query query = new QueryByIdentity(criterio);
		return (AsignarBarrio) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param asignar
	 *            Bean de objeto visita
	 * @param broker
	 */
	private void actualizarReferencias(AsignarBarrio asignar, PersistenceBroker broker) {
		asignar.setColectorIdRef(ColectorHandler.retrieve(asignar.getColectorId(), broker));
		asignar.setDepartamentoIdRef(DepartamentosHandler.retrieve(asignar.getDepartamentoId(), broker));
		asignar.setLocalidadIdRef(LocalidadHandler.retrieve(asignar.getLocalidadId(), broker));
		linkChilds(asignar, broker);
	}

	private void linkChilds(final AsignarBarrio bean, final PersistenceBroker broker) {
		Iterator iterDetalle = bean.getDetalleBarriosList().iterator();
		while (iterDetalle.hasNext()) {
			DetalleBarrios det = (DetalleBarrios) iterDetalle.next();
			det.setBarrioIdRef(BarrioHandler.retrieve(det.getBarrioId(), broker));
			det.setAsignarbIdRef(bean);
		}
	}

	/**
	 * Obtiene un visita.
	 * 
	 * @param asignarbId
	 *            Identificador del visita a obtener
	 * @return bean que contiene los datos del visita
	 */
	public static AsignarBarrio retrieve(final Integer asignarbId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(asignarbId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de visitas.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de visitas
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(AsignarBarrio.class,
					criterio);
			query.addOrderBy("asignarbId", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	public List getListAsignaciones(final Integer colectorId, final Integer departamentoId, final Integer localidadId) throws Exception {
	      
		PersistenceBroker broker = null;

	       try {
	           broker = PersistenceBrokerFactory.defaultPersistenceBroker();

	           Criteria criterio = new Criteria();
	           if (colectorId != null) {
	               criterio.addEqualTo("colectorId", colectorId);
	           }
	           if (departamentoId != null) {
	               criterio.addEqualTo("departamentoId", departamentoId);
	           }
	           if (localidadId != null) {
	               criterio.addEqualTo("localidadId", localidadId);
	           }
	           ReportQueryByCriteria query = new ReportQueryByCriteria(AsignarBarrio.class, criterio);
	           query.setAttributes(new String[] {"asignarbId",
	                                             "colectorIdRef.primerNombre",
	                                             "colectorIdRef.primerApellido",
	                                             "departamentoIdRef.departamentoNombre",
	                                             "localidadIdRef.localidadNombre"});
	           query.addGroupBy(new String[] {"asignarbId",
						                       "colectorIdRef.primerNombre",
						                       "colectorIdRef.primerApellido",
						                       "departamentoIdRef.departamentoNombre",
						                       "localidadIdRef.localidadNombre"});
	           query.addOrderBy("asignarbId", true);
	           return IteratorUtils.toList(broker.getReportQueryIteratorByQuery(query));
	       } catch (Exception e) {
	           throw e;
	       } finally {
	           if (broker != null && !broker.isClosed()) {
	               broker.close();
	           }
	       }
	   }

	/**
	 * Genera el listado de todos los visitas.
	 * 
	 * @return Collection o null Listao de visitas
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}	
}
