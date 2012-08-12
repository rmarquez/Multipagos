package com.metropolitana.multipagos.forms.cartera;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.AsignacionClaro;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.pagos.PagosHandler;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;


public class CarteraXDepartamentoHandler {

	/**
	 * <code>CARTERA_ID</code> Identificador de la cartera
	 */
	public static final String CARTERA_ID = "carteraId";

	/** Nombre del campo para la búsqueda de Localidades. */
	//private static final String CAMPO_BUSQUEDA = "localidadNombre";

	/**
	 * Inserta un Localidad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final CarteraXDepartamento bean, Integer usrId, Integer colectorId, Integer recibo,
			BigDecimal montoPagado, String horaRegistro) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			if(montoPagado != null) {
				Pagos pago = PagosHandler.nuevoPago(bean, usrId, colectorId, recibo,
						montoPagado, horaRegistro);			
				broker.store(pago);
			}
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
	 * Actualiza los datos de un Localidad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final CarteraXDepartamento bean, Integer usrId)	throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);			
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
	
	public void revertirPago(final Integer carteraId, final Integer usrId) throws Exception {		
		
		CarteraXDepartamento cartera = retrieve(carteraId);
		if (cartera != null){
			cartera.setContrato(cartera.getContrato());
			cartera.setSuscriptor(cartera.getSuscriptor());
			cartera.setNit(cartera.getNit());
			cartera.setDireccion(cartera.getDireccion());
			cartera.setFacturaInterna(cartera.getFacturaInterna());
			cartera.setNumeroFiscal(cartera.getNumeroFiscal());
			cartera.setAnio(cartera.getAnio());
			cartera.setMes(cartera.getMes());
			cartera.setSaldo(cartera.getSaldo());
			cartera.setCupon(cartera.getCupon());
			cartera.setTelefono(cartera.getTelefono());
			cartera.setPagado(Boolean.FALSE);
			cartera.setFechaPago(null);
			cartera.setDepartamentoId(cartera.getDepartamentoId());
			cartera.setLocalidadId(cartera.getLocalidadId());
			cartera.setBarrioId(cartera.getBarrioId());
			cartera.setServicioId(cartera.getServicioId());
			cartera.setEstadoId(cartera.getEstadoId());
			cartera.setFechaIngreso(cartera.getFechaIngreso());
		}
		update(cartera, usrId);
	}

	/**
	 * Obtiene un cliente de la cartera de cliente en mora.
	 * 
	 * @param carteraId
	 *            Identificador del cartera a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos de la Cartera
	 */
	public static CarteraXDepartamento retrieve(final Integer carteraId,
			final PersistenceBroker broker) {
		CarteraXDepartamento criterio = new CarteraXDepartamento();
		criterio.setCarteraId(carteraId);
		Query query = new QueryByIdentity(criterio);
		return (CarteraXDepartamento) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param cartera
	 *            Bean de objeto cartera x departamento
	 * @param broker
	 */
	private void actualizarReferencias(CarteraXDepartamento cartera,
			PersistenceBroker broker) {
		cartera.setDepartamentoIdRef(DepartamentosHandler.retrieve(
				cartera.getDepartamentoId(), broker));
		cartera.setServicioIdRef(ServicioHandler.retrieve(
				cartera.getServicioId(), broker));
		cartera.setBarrioIdRef(BarrioHandler.retrieve(
				cartera.getBarrioId(), broker));
		cartera.setLocalidadIdRef(LocalidadHandler.retrieve(
				cartera.getLocalidadId(), broker));
		cartera.setEstadoIdRef(EstadoCorteHandler.retrieve(
				cartera.getEstadoId(), broker));
	}

	/**
	 * Obtiene un cliente de la cartera x departamento.
	 * 
	 * @param carteraId
	 *            Identificador del cliente en mora a obtener
	 * @return bean que contiene los datos de la cartera
	 */
	public static CarteraXDepartamento retrieve(final Integer carteraId)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(carteraId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	public static List getAnioAsignacion(final Integer numAsignacion) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (numAsignacion != null) {
				criterio.addEqualTo("numAsignacion", numAsignacion);
			}
			//ReportQueryByCriteria query = new ReportQueryByCriteria(CarteraXDepartamento.class, criterio);
			ReportQueryByCriteria query = new ReportQueryByCriteria(AsignacionClaro.class, criterio);
			query.setAttributes(new String[] { "fechaIngreso"});
			query.addGroupBy(new String[] { "fechaIngreso"});
			
			query.addOrderByAscending("fechaIngreso");
			return IteratorUtils.toList(broker.getReportQueryIteratorByQuery(query));
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getAAsignacion() throws Exception {
		try {
			return getAAsignacion(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Collection getAAsignacion(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(AsignacionClaro.class,
					criterio);
			query.addOrderBy("anioAsignacion", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getList(final String contrato) throws Exception {
			PersistenceBroker broker = null;
			try {
				broker = PersistenceBrokerFactory.defaultPersistenceBroker();
				Criteria criterio = new Criteria();
				criterio.addLike("upper(contrato)", "*"
						+ contrato.toUpperCase(Locale.getDefault())
						+ "*");
				QueryByCriteria query = new QueryByCriteria(CarteraXDepartamento.class,
						criterio);
				query.addOrderByAscending("contrato");
				return broker.getCollectionByQuery(query);
			} catch (Exception e) {
				throw e;
			} finally {
				if (broker != null && !broker.isClosed()) {
					broker.close();
				}
			}
		}
	
	public static CarteraXDepartamento carteraXFactura(final String factura) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXFactura(factura, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	private static CarteraXDepartamento carteraXFactura(final String factura, final PersistenceBroker broker) {
		CarteraXDepartamento criterio = new CarteraXDepartamento();
		criterio.setFacturaInterna(factura);
		Query query = new QueryByCriteria(criterio);
		return (CarteraXDepartamento) broker.getObjectByQuery(query);
	}
	
	
	public static CarteraXDepartamento carteraXContrato(final String contrato)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXContrato(contrato, broker);
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static CarteraXDepartamento carteraXContrato(final String contrato, final PersistenceBroker broker) {
		System.out.println("+++ llega contraro = " + contrato);
		CarteraXDepartamento criterio = new CarteraXDepartamento();
		criterio.setContrato(contrato);
		Query query = new QueryByCriteria(criterio);
		return (CarteraXDepartamento) broker.getObjectByQuery(query);
	}
	
	public Collection getCarteraXContrato(final String contrato) throws Exception {
		PersistenceBroker broker = null;
		
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (contrato != null) {
				criterio.addEqualTo("contrato", contrato);
			}
			QueryByCriteria query = new QueryByCriteria(CarteraXDepartamento.class, criterio);
			query.addOrderByAscending("contrato");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	public static CarteraXDepartamento getMesSaldoMora(final String contrato,
			final String facturaInterna, final String numeroFiscal, PersistenceBroker pb) {
        Criteria criterio = new Criteria();
        criterio.addEqualTo("contrato", contrato);
        criterio.addEqualTo("facturaInterna", facturaInterna);
        criterio.addEqualTo("numeroFiscal", numeroFiscal);
        QueryByCriteria query = new QueryByCriteria(CarteraXDepartamento.class, criterio);
        Iterator iter = pb.getIteratorByQuery(query);
        if (iter.hasNext()) {
            return  (CarteraXDepartamento) pb.getObjectByQuery(query);
        } else {
            return null;
        }
    }

    public static CarteraXDepartamento getMesSaldoMora(final String contrato,
			final String facturaInterna, final String numeroFiscal) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return getMesSaldoMora(contrato, facturaInterna, numeroFiscal, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	
	
	
	/**
	 * Obtener la lista de localidades a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param localidadNombre
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
	//@SuppressWarnings("unchecked")
	/*public final Collection<Localidad> getResultadosXPagina(
			final String localidadNombre, final Integer departamentoId,
			final Integer filtrar, final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					Localidad.class,
					getCriterio(localidadNombre, departamentoId, filtrar,
							CAMPO_BUSQUEDA), CAMPO_BUSQUEDA, pagina,
					registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}*/

	/**
	 * Obtiene la cantidad de Localidads que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param localidadNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	/*public final int getContador(final String localidadNombre,
			final Integer departamentoId, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Localidad.class,
					getCriterio(localidadNombre, departamentoId, filtrar,
							CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}*/

	/**
	 * Contruir el criterio de búsqueda para la consulta en el formulario de
	 * localidad.
	 * 
	 * @param localidadNombre
	 *            Criterio de busqueda.
	 * @param departamentoId
	 *            Identificador del departamento.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	/*public static final Criteria getCriterio(final String localidadNombre,
			final Integer departamentoId, final Integer filtrar,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		if (localidadNombre != null && localidadNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					localidadNombre.toUpperCase(Locale.getDefault()) + "*");
		}
		if (departamentoId != null) {
			criterio.addEqualTo("departamentoId", departamentoId);
		}
		if (filtrar == Integer.valueOf(1)) {
			criterio.addEqualTo("inactivo", Boolean.FALSE);
		}
		if (filtrar == Integer.valueOf(2)) {
			criterio.addEqualTo("inactivo", Boolean.TRUE);
		}
		return criterio;
	}*/

	/**
	 * Genera el listado de todos los contratos de clientes en mora.
	 * 
	 * @return Collection o null Listao de contratos
	 * @throws Exception
	 */
	public Collection getList() throws PBFactoryException {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					CarteraXDepartamento.class, new Criteria());
			query.setAttributes(new String[] { "carteraId",
					"contrato" });
			query.addOrderBy("contrato", true);
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
	
	public Collection getCartera() throws Exception {
		try {
			return getCartera(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Collection getCartera(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(CarteraXDepartamento.class,
					criterio);
			query.addOrderBy("carteraId", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	public static boolean existeContrato(String contrato) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (contrato != null && contrato.length() > 0) {
				criterio.addLike("upper(contrato)",
						contrato.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(CarteraXDepartamento.class, criterio);
            query.addOrderBy("contrato", true);
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
