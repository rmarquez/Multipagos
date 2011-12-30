package com.metropolitana.multipagos.forms.arqueo;

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

import com.metropolitana.multipagos.ArqueoCantidad;
import com.metropolitana.multipagos.ArqueoCheque;
import com.metropolitana.multipagos.ArqueoPagos;
import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.DetalleVisitas;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.banco.BancoHandler;
import com.metropolitana.multipagos.forms.cantidades.CantidadHandler;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;

public class ArqueoHandler {
	
	/**
	 * Inserta un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final ArqueoPagos bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
					Integer.valueOf(1), null, broker));
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
	public void update(final ArqueoPagos bean, Integer usrId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
						Integer.valueOf(2), null, broker));
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
	
	public void autorizarArqueo(final ArqueoPagos bean, Integer usrId, AuthUser usrAutoriza) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
					Integer.valueOf(3), usrAutoriza, broker));
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
	 * Obtiene un visita.
	 * 
	 * @param arqueoId
	 *            Identificador del visita a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del visita
	 */
	public static ArqueoPagos retrieve(final Integer arqueoId,
			final PersistenceBroker broker) {
		ArqueoPagos criterio = new ArqueoPagos();
		criterio.setArqueoId(arqueoId);
		Query query = new QueryByIdentity(criterio);
		return (ArqueoPagos) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param arqueo
	 *            Bean de objeto pago
	 * @param broker
	 */
	private void actualizarReferencias(ArqueoPagos arqueo, PersistenceBroker broker) {
		arqueo.setUsrIdRef(Auth_userHandler.retrieve(arqueo.getUsrId(), broker));
		linkChilds(arqueo, broker);
	}

	private void linkChilds(final ArqueoPagos bean, final PersistenceBroker broker) {
		Iterator cantidad = bean.getArqueoCantidadList().iterator();
		while (cantidad.hasNext()) {
			ArqueoCantidad cant = (ArqueoCantidad) cantidad.next();
			cant.setCantidadIdRef(CantidadHandler.retrieve(cant.getCantidadId(), broker));
			cant.setArqueoIdRef(bean);
		}
		Iterator cheque = bean.getArqueoChequeList().iterator();
		while (cheque.hasNext()) {
			ArqueoCheque ck = (ArqueoCheque) cheque.next();
			ck.setBancoIdRef(BancoHandler.retrieve(ck.getBancoId(), broker));			
			ck.setArqueoIdRef(bean);
		}
	}

	/**
	 * Obtiene un pago.
	 * 
	 * @param arqueoId
	 *            Identificador del pago a obtener
	 * @return bean que contiene los datos del pago
	 */
	public static ArqueoPagos retrieve(final Integer arqueoId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(arqueoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de pagos.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de pagos
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(ArqueoPagos.class,
					criterio);
			query.addOrderBy("pagoFecha", true);
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
	public final Collection<ArqueoPagos> getResultadosXPagina(
			final Date fecha, final Integer colectorId,
			final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return getResultadosXPagina(
					ArqueoPagos.class,
					getCriterio(fecha, colectorId), null, pagina,
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
			final Integer colectorId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(ArqueoPagos.class,
					getCriterio(fecha, colectorId));
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
			final Integer colectorId) {
		Criteria criterio = new Criteria();
		if (fecha != null) {
			criterio.addEqualTo("pagoFecha", fecha);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
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
	
	public static boolean colectorArqueado(Date fecha, Integer colectorId) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (fecha != null) {
                    criterio.addEqualTo("pagoFecha", fecha);
            }
            if (colectorId != null) {
                    criterio.addEqualTo("colectorId", colectorId);
            }
            List lst = getColectorArqueadoList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	private static List getColectorArqueadoList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(ArqueoPagos.class, criterio);
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
