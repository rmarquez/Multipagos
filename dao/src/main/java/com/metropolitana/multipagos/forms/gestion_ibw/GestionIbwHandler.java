package com.metropolitana.multipagos.forms.gestion_ibw;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.DetalleGIbw;
import com.metropolitana.multipagos.GestionIbw;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.cartera.CarteraIbwHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.simbolo.SimboloIbwHandler;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class GestionIbwHandler {

	/**
	 * <code>GESTION_ID</code> Identificador del Control de GestionIbw
	 */
	public static final String GESTION_ID = "gestionId";

	

	/**
	 * Inserta un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final GestionIbw bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			//Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
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
	public void update(final GestionIbw bean, Integer usrId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			//Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
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
	
	public void remove(Integer gestionId, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            //Date fecha = Calendar.getInstance().getTime();
            GestionIbw bean = retrieve(gestionId, broker);
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

	/**
	 * Obtiene un visita.
	 * 
	 * @param visitaId
	 *            Identificador del visita a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del visita
	 */
	public static GestionIbw retrieve(final Integer gestionId,
			final PersistenceBroker broker) {
		GestionIbw criterio = new GestionIbw();
		criterio.setGestionId(gestionId);
		Query query = new QueryByIdentity(criterio);
		return (GestionIbw) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param visita
	 *            Bean de objeto visita
	 * @param broker
	 */
	private void actualizarReferencias(GestionIbw gestion, PersistenceBroker broker) {
		gestion.setUsrIdRef(Auth_userHandler.retrieve(gestion.getUsrId(), broker));
		linkChilds(gestion, broker);
	}

	private void linkChilds(final GestionIbw bean, final PersistenceBroker broker) {
		Iterator iterDetalle = bean.getDetalleGIbwList().iterator();
		while (iterDetalle.hasNext()) {
			DetalleGIbw det = (DetalleGIbw) iterDetalle.next();
			
			det.setCarteraIdRef(CarteraIbwHandler.retrieve(
					det.getCarteraId(), broker));
			det.setSimboloIdRef(SimboloIbwHandler.retrieve(
					det.getSimboloId(), broker));
			det.setColectorIdRef(ColectorHandler.retrieve(
					det.getColectorId(), broker));
			det.setGestionIdRef(bean);
		}
	}

	/**
	 * Obtiene un visita.
	 * 
	 * @param visitaId
	 *            Identificador del visita a obtener
	 * @return bean que contiene los datos del visita
	 */
	public static GestionIbw retrieve(final Integer gestionId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(gestionId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de GestionIbw.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de GestionIbw
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(GestionIbw.class,
					criterio);
			query.addOrderBy("fecha", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	
	public Collection getList(Integer gestionId) throws Exception {
        try {
            Criteria criterios = new Criteria();
            if (gestionId != null) {
                criterios.addEqualTo("carteraAvonList.carteraId", gestionId);
            }
            return getList(criterios);
        } catch (Exception e) {
            throw e;
        }
    }

	/**
	 * Obtener la lista de visitaes a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param visitaNombre
	 *            Filtro de búsqueda.
	 * @param departamentoId
	 *            Idetinficador del departamento.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de departamentos por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             departamentos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<GestionIbw> getResultadosXPagina(
			final Date fecha, final Integer usrId,
			final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return getResultadosXPagina(
					GestionIbw.class,
					getCriterio(fecha, usrId), null, pagina,
					registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
    public static final Collection getResultadosXPagina(final Class clase,
            final Criteria criterio, final String criterioOrden,
            final int pagina, final int registrosPorPagina,
            final PersistenceBroker broker) throws Exception {
        if (pagina >= 1  && registrosPorPagina >= 1) {
            int inicio = 1;
            if (pagina != 1) {
                inicio = (registrosPorPagina * (pagina - 1)) + 1;
            }
            QueryByCriteria query = new QueryByCriteria(clase, criterio);
            if (criterioOrden != null) {
            	query.addOrderBy(criterioOrden, true);
            }            
            query.setStartAtIndex(inicio);
            query.setEndAtIndex(inicio + registrosPorPagina - 1);
            return broker.getCollectionByQuery(query);
        }
        return null;
    }

	/**
	 * Obtiene la cantidad de GestionIbw que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param visitaNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final Date fecha,
			final Integer usrId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(GestionIbw.class,
					getCriterio(fecha, usrId));
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
	 * Contruir el criterio de búsqueda para la consulta en el formulario de
	 * visita.
	 * 
	 * @param visitaNombre
	 *            Criterio de busqueda.
	 * @param departamentoId
	 *            Identificador del departamento.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final Date fecha,
			final Integer usrId) {
		Criteria criterio = new Criteria();
		if (fecha != null) {
			criterio.addEqualTo("fecha", fecha);
		}
		if (usrId != null) {
			criterio.addEqualTo("usrId", usrId);
		}
		
		return criterio;
	}

	/**
	 * Genera el listado de todos los GestionIbw.
	 * 
	 * @return Collection o null Listao de GestionIbw
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static boolean existeContrato(String codigo) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (codigo != null && codigo.length() > 0) {
            	criterio.addEqualTo("codigo", codigo);
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
            QueryByCriteria query = new QueryByCriteria(DetalleGIbw.class, criterio);
            query.addOrderBy("codigo", true);
            return (List)broker.getCollectionByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	/**
	 * Retorna la fecha de la ultima visita.
	 * @param contrato
	 * 		Numero de contrato
	 * @return
	 * @throws Exception
	 */
	public Date getUltimaVisita(String codigo) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();         

            Criteria criterio = new Criteria();
            if (codigo != null) {
                criterio.addEqualTo("codigo", codigo);
            }            
            ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleGIbw.class, criterio);
            query.setAttributes(new String[] {"max(fecha)" });
            Iterator iter = broker.getReportQueryIteratorByQuery(query);
            if (iter.hasNext()) {
                Object fecha = ((Object[]) iter.next())[0];
                if (fecha != null) {                	
                    return (Date)fecha;
                }
            }
            
            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	
	public static boolean getCodigoXFecha(final Boolean gestionLlamada,
			final String codigo, final Date fechaGestion)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (gestionLlamada != null ){
				criterio.addEqualTo("gestionLlamada", gestionLlamada);
			} else {
				criterio.addEqualTo("gestionLlamada", false);
			}
			if (codigo != null && fechaGestion != null) {
				criterio.addEqualTo("codigo", codigo);
				criterio.addEqualTo("fechaGestion", fechaGestion);
				
			}
			List lst = getCodigoXFechaList(criterio);
			if (lst.isEmpty()) {
				return Boolean.FALSE.booleanValue();
			} else {
				return Boolean.TRUE.booleanValue();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}

	}
	
	private static List getCodigoXFechaList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(DetalleGIbw.class, criterio);
            query.addOrderBy("codigo", true);
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