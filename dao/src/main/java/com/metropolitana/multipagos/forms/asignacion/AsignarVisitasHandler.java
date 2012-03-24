package com.metropolitana.multipagos.forms.asignacion;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.AsignarBarrio;
import com.metropolitana.multipagos.AsignarColector;
import com.metropolitana.multipagos.AsignarVisitas;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.DetalleColectores;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;

import com.metropolitana.multipagos.forms.logs.LogsHandler;
import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class AsignarVisitasHandler {
	
	public void insert(final AsignarVisitas bean, Integer usrId) throws Exception {
		org.apache.ojb.broker.PersistenceBroker broker = null;
		
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(bean);
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
	 * Actualiza los datos de un asignacion.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 *//*
	public void update(final AsignarColector bean, Integer usrId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
						Integer.valueOf(2), broker));
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
	
	*//**
	 * Obtiene un asignacion.
	 * 
	 * @param asignacionId
	 *            Identificador del asignacion a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del asignacion
	 *//*
	public static AsignarColector retrieve(final Integer asignarcId,
			final PersistenceBroker broker) {
		AsignarColector criterio = new AsignarColector();
		criterio.setAsignarcId(asignarcId);
		Query query = new QueryByIdentity(criterio);
		return (AsignarColector) broker.getObjectByQuery(query);
	}

	*//**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param asignar
	 *            Bean de objeto asignacion
	 * @param broker
	 *//*
	private void actualizarReferencias(AsignarColector asignar, PersistenceBroker broker) {		
		asignar.setUsrIdRef(Auth_userHandler.retrieve(asignar.getUsrId(), broker));
		linkChilds(asignar, broker);
	}

	private void linkChilds(final AsignarColector bean, final PersistenceBroker broker) {
		Iterator iterDetalle = bean.getDetalleColectoresList().iterator();
		while (iterDetalle.hasNext()) {
			DetalleColectores det = (DetalleColectores) iterDetalle.next();			
			det.setColectorIdRef(ColectorHandler.retrieve(det.getColectorId(), broker));			
			det.setAsignarcIdRef(bean);
		}
	}

	*//**
	 * Obtiene una asignacion.
	 * 
	 * @param asignarcId
	 *            Identificador del asignacion a obtener
	 * @return bean que contiene los datos del asignacion
	 *//*
	public static AsignarColector retrieve(final Integer asignarcId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(asignarcId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	*//**
	 * Genera listado de asignaciones.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de asignaciones
	 * @throws Exception
	 *//*
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(AsignarColector.class,
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

	

	*//**
	 * Genera el listado de todas los asignaciones.
	 * 
	 * @return Collection o null Listado de asignaciones
	 * @throws Exception
	 *//*
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List getListAsignaciones(final Integer usrId) throws Exception {
	      
		PersistenceBroker broker = null;

	       try {
	           broker = PersistenceBrokerFactory.defaultPersistenceBroker();

	           Criteria criterio = new Criteria();
	           if (usrId != null) {
	               criterio.addEqualTo("usrId", usrId);
	           }
	           
	           ReportQueryByCriteria query = new ReportQueryByCriteria(AsignarColector.class, criterio);
	           query.setAttributes(new String[] {"asignarcId",
	                                             "usrIdRef.usrFullName"});
	           
	           query.addGroupBy(new String[] {"asignarcId",
	           								  "usrIdRef.usrFullName"});
	           
	           query.addOrderBy("asignarcId", true);
	           return IteratorUtils.toList(broker.getReportQueryIteratorByQuery(query));
	       } catch (Exception e) {
	           throw e;
	       } finally {
	           if (broker != null && !broker.isClosed()) {
	               broker.close();
	           }
	       }
	   }
	
	public static boolean asignacionColectorUsr(Integer usrId) throws Exception {
        try {
            Criteria criterio = new Criteria();
            
            if (usrId != null) {
                    criterio.addEqualTo("usrId", usrId);
            }
            List lst = getAsignacionColectorList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	private static List getAsignacionColectorList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(AsignarColector.class, criterio);
            return (List)broker.getCollectionByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	
	public static List getColectoresAsignados(final Integer usrId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (usrId != null) {
				criterio.addEqualTo("asignarcIdRef.usrId", usrId);
			}
			ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleColectores.class, criterio);
			query.setAttributes(new String[] { "colectorId", "colectorIdRef.primerNombre","colectorIdRef.primerApellido" });
			query.addGroupBy(new String[] { "colectorId", "colectorIdRef.primerNombre","colectorIdRef.primerApellido" });
			
			query.addOrderByAscending("colectorId");
			return IteratorUtils.toList(broker.getReportQueryIteratorByQuery(query));
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}*/
}
