package com.metropolitana.multipagos.forms.visitas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.DetalleVisitas;
import com.metropolitana.multipagos.Visitas;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.simbolo.SimboloHandler;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class VisitasHandler {

	/**
	 * <code>VISITA_ID</code> Identificador del Control de visitas
	 */
	public static final String VISITA_ID = "visitaId";

	/** Nombre del campo para la búsqueda de visitaes. */
	//private static final String CAMPO_BUSQUEDA = "visitaNombre";

	/**
	 * Inserta un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Visitas bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
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
	 * Actualiza los datos de un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Visitas bean, Integer usrId)
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

	/**
	 * Obtiene un visita.
	 * 
	 * @param visitaId
	 *            Identificador del visita a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del visita
	 */
	public static Visitas retrieve(final Integer visitaId,
			final PersistenceBroker broker) {
		Visitas criterio = new Visitas();
		criterio.setVisitaId(visitaId);
		Query query = new QueryByIdentity(criterio);
		return (Visitas) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param visita
	 *            Bean de objeto visita
	 * @param broker
	 */
	private void actualizarReferencias(Visitas visita, PersistenceBroker broker) {
		visita.setUsrIdRef(Auth_userHandler.retrieve(visita.getUsrId(), broker));
		linkChilds(visita, broker);
	}

	private void linkChilds(final Visitas bean, final PersistenceBroker broker) {
		Iterator iterDetalle = bean.getDetalleVisitasList().iterator();
		while (iterDetalle.hasNext()) {
			DetalleVisitas det = (DetalleVisitas) iterDetalle.next();
			det.setCarteraIdRef(CarteraXDepartamentoHandler.retrieve(
					det.getCarteraId(), broker));
			det.setSimboloIdRef(SimboloHandler.retrieve(
					det.getSimboloId(), broker));
			det.setColectorIdRef(ColectorHandler.retrieve(
					det.getColectorId(), broker));
			det.setVisitaIdRef(bean);
		}
	}

	/**
	 * Obtiene un visita.
	 * 
	 * @param visitaId
	 *            Identificador del visita a obtener
	 * @return bean que contiene los datos del visita
	 */
	public static Visitas retrieve(final Integer visitaId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(visitaId, broker);
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
			QueryByCriteria query = new QueryByCriteria(Visitas.class,
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

	/**
	 * Genera listado de visitas.
	 * 
	 * @param visitaNombre
	 *            nombre o parte del nombre de un visita
	 * @return Collection o null listado de visitas
	 * @throws Exception
	 */
	public Collection getList(final String visitaNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (visitaNombre != null && visitaNombre.length() > 0) {
				criterio.addLike("upper(visitaNombre)",
						visitaNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getList(Integer carteraId) throws Exception {
        try {
            Criteria criterios = new Criteria();
            if (carteraId != null) {
                criterios.addEqualTo("carteraXDepartamentoList.carteraId", carteraId);
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
	public final Collection<Visitas> getResultadosXPagina(
			final Date fecha, final Integer usrId,
			final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return getResultadosXPagina(
					DetalleVisitas.class,
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
	 * Obtiene la cantidad de visitas que coinciden con el filtro de
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
			QueryByCriteria query = new QueryByCriteria(DetalleVisitas.class,
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
			criterio.addEqualTo("visitaIdRef.fecha", fecha);
		}
		if (usrId != null) {
			criterio.addEqualTo("visitaIdRef.usrId", usrId);
		}
		
		return criterio;
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
	
	
	public static boolean existeContrato(String numeroContrato) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (numeroContrato != null && numeroContrato.length() > 0) {
            	criterio.addEqualTo("numeroContrato", numeroContrato);
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
            QueryByCriteria query = new QueryByCriteria(DetalleVisitas.class, criterio);
            query.addOrderBy("numeroContrato", true);
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
	public Date getUltimaVisita(String contrato) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();         

            Criteria criterio = new Criteria();
            if (contrato != null) {
                criterio.addEqualTo("numeroContrato", contrato);
            }            
            ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleVisitas.class, criterio);
            query.setAttributes(new String[] {"max(fechaVisita)" });
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
	
}
