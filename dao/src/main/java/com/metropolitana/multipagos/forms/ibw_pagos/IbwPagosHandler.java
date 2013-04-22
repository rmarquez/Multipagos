package com.metropolitana.multipagos.forms.ibw_pagos;

import com.metropolitana.multipagos.IbwPagos;
import com.metropolitana.multipagos.CarteraIbw;
import com.metropolitana.multipagos.DetalleIbwPagos;
import java.math.BigDecimal;
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


import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.cartera.CarteraIbwHandler;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.municipio.MunicipioHandler;

public class IbwPagosHandler {

	/**
	 * <code>PAGO_ID</code> Identificador del Control de pagos
	 */
	public static final String PAGO_ID = "pagoId";
	
	public static IbwPagos nuevoPago(final CarteraIbw bean,
			Integer usrId, Integer colectorId, Integer recibo,
			BigDecimal montoPagado, String horaRegistro) throws Exception {
		IbwPagos pago = new IbwPagos();

		pago.setUsrId(usrId);
		Date fecha = Calendar.getInstance().getTime();
		pago.setFecha(fecha);
		pago.setMontoTotal(montoPagado);
		pago.setCantidadPagos(Integer.valueOf(1));

		DetalleIbwPagos detalle = crearDetallePago(bean, colectorId, recibo,
				montoPagado, horaRegistro);
		pago.addDetalleIbwPagos(detalle);		
		return pago;
	}

	private static DetalleIbwPagos crearDetallePago(CarteraIbw cartera,
			Integer colectorId, Integer recibo, BigDecimal montoPagado, String horaRegistro) {

		if (cartera != null) {

			DetalleIbwPagos detalle = new DetalleIbwPagos();
			detalle.setCarteraIdRef(cartera);

			detalle.setMunicipioId(cartera.getMunicipioId());
			detalle.setCodigo(cartera.getCodCliente());
			detalle.setFactura(cartera.getFacturaIbw());
                        detalle.setFechaPago(Calendar.getInstance().getTime());
			detalle.setSalgoPagar(cartera.getSaldoDol());
			// datos pago
			detalle.setColectorId(colectorId);
			detalle.setMontoPago(montoPagado);
			detalle.setRecibo(recibo);
			detalle.setHoraRegistro(horaRegistro);

			return detalle;
		} else {
			throw new NullPointerException("El Objeto cartera no existe.");
		}
	}
	
	/**
	 * Inserta un visita.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final IbwPagos bean, Integer usrId) throws Exception {
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
			//broker.clearCache();
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
	public void update(final IbwPagos bean, Integer usrId)
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
	
	public void remove(Integer pagoId, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            //Date fecha = Calendar.getInstance().getTime();
            IbwPagos bean = retrieve(pagoId, broker);
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
	
	
	 public void revertirPagos(final Integer pagoId, final Integer usrId)  throws Exception {
		 IbwPagos pago = retrieve(pagoId);
		 CarteraIbwHandler cartHandler = new CarteraIbwHandler();
		 Iterator iterDetalle = pago.getDetalleIbwPagosList().iterator();
		 while (iterDetalle.hasNext()) {
			DetalleIbwPagos det = (DetalleIbwPagos) iterDetalle.next();
			//cartHandler.revertirPago(det.getCavonId(), usrId);				
		 }        
    }	

	/**
	 * Obtiene un visita.
	 * 
	 * @param pagoId
	 *            Identificador del visita a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del visita
	 */
	public static IbwPagos retrieve(final Integer pagoId,
			final PersistenceBroker broker) {
		IbwPagos criterio = new IbwPagos();
		criterio.setPagoId(pagoId);
		Query query = new QueryByIdentity(criterio);
		return (IbwPagos) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param pago
	 *            Bean de objeto pago
	 * @param broker
	 */
	private void actualizarReferencias(IbwPagos pago, PersistenceBroker broker) throws Exception {
		pago.setUsrIdRef(Auth_userHandler.retrieve(pago.getUsrId(), broker));
		linkChilds(pago, broker);
	}

	private void linkChilds(final IbwPagos bean, final PersistenceBroker broker) throws Exception {
		Iterator iterDetalle = bean.getDetalleIbwPagosList().iterator();
		while (iterDetalle.hasNext()) {
			DetalleIbwPagos det = (DetalleIbwPagos) iterDetalle.next();
			det.setCarteraIdRef(CarteraIbwHandler.retrieve(det.getCarteraId(), broker));
			det.setColectorIdRef(ColectorHandler.retrieve(det.getColectorId(), broker));
			det.setMunicipioIdRef(MunicipioHandler.retrieve(det.getMunicipioId(), broker));
            BigDecimal montoPago = det.getMontoPago();
            BigDecimal saldoPagar = det.getSalgoPagar();
            // Se cambiara el estado solo si el monto pagado es igual o mayor al monto pendiente.
			if(montoPago.compareTo(saldoPagar)==0 || montoPago.compareTo(saldoPagar)==1){
				cambiarEstadoCartera(det.getCarteraId(), det.getFechaPago(), broker);
			}				
			
			det.setPagoIdRef(bean);
			
		}
	}
	
	private void cambiarEstadoCartera(Integer carteraId, Date fechaPago, PersistenceBroker broker) throws Exception {
		CarteraIbw cartera = CarteraIbwHandler.retrieve(carteraId);
		if(cartera.getPagado()==false){
			cartera.setPagado(Boolean.TRUE);
			cartera.setFechaPago(fechaPago);
			broker.store(cartera);
		}
	}
	
	

	/**
	 * Obtiene un pago.
	 * 
	 * @param pagoId
	 *            Identificador del pago a obtener
	 * @return bean que contiene los datos del pago
	 */
	public static IbwPagos retrieve(final Integer pagoId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(pagoId, broker);
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
			QueryByCriteria query = new QueryByCriteria(IbwPagos.class,
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
	public final Collection<IbwPagos> getResultadosXPagina(
			final Date fecha, final Integer usrId,
			final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			//return Util.getResultadosXPagina(Pagos.class, getCriterio(fecha, usrId), null, pagina,registrosXPagina, broker);
			return getResultadosXPagina(fecha, usrId, pagina,registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
    public static final Collection getResultadosXPagina(final Date fecha, final Integer usrId,
            final int pagina, final int registrosPorPagina,
            final PersistenceBroker broker) throws Exception {
        if (pagina >= 1  && registrosPorPagina >= 1) {
            int inicio = 1;
            if (pagina != 1) {
                inicio = (registrosPorPagina * (pagina - 1)) + 1;
            }
            Criteria criterio = new Criteria();
    		if (usrId != null) {
    			criterio.addEqualTo("usrId", usrId);
    		}
    		if (fecha != null) {
    			criterio.addEqualTo("fecha", fecha);
    		}
            QueryByCriteria query = new QueryByCriteria(IbwPagos.class, criterio);
            query.addOrderBy("usrId", true);            
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
			QueryByCriteria query = new QueryByCriteria(IbwPagos.class,
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
	 * pagos.
	 * 
	 * @param fecha
	 *            Criterio de busqueda.
	 * @param usrId
	 *            Identificador del usuario.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final Date fecha,
			final Integer usrId) {
		Criteria criterio = new Criteria();
		if (usrId != null) {
			criterio.addEqualTo("usrId", usrId);
		}
		if (fecha != null) {
			criterio.addEqualTo("fecha", fecha);
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
	
	public static boolean existeFactura(String factura) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (factura != null && factura.length() > 0) {
            	criterio.addEqualTo("factura", factura);
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
            QueryByCriteria query = new QueryByCriteria(DetalleIbwPagos.class, criterio);
            query.addOrderBy("factura", true);
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
