package com.metropolitana.multipagos.forms.cartera;

import com.metropolitana.multipagos.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.ciudad.CiudadHandler;
import com.metropolitana.multipagos.forms.departamentos.BDepartamentosHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.pagos.PagosHandler;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;
import com.metropolitana.multipagos.forms.tasafija.TasafijaHandler;


public class CarteraBanproHandler {

	/**
	 * <code>CARTERA_ID</code> Identificador de la cartera
	 */
	public static final String TMP_ID = "tmpId";

	/**
	 * Obtiene un cliente de la cartera de cliente en mora.
	 * 
	 * @param carteraId
	 *            Identificador del cartera a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos de la Cartera
	 */
	public static CarteraBanpro retrieve(final Integer tmpId, final PersistenceBroker broker) {
		CarteraBanpro criterio = new CarteraBanpro();
		criterio.setTmpId(tmpId);
		Query query = new QueryByIdentity(criterio);
		return (CarteraBanpro) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param cartera
	 *            Bean de objeto cartera x departamento
	 * @param broker
	 */
	private void actualizarReferencias(CarteraBanpro cartera,
			PersistenceBroker broker) {
		cartera.setDepartamentoCRef(BDepartamentosHandler.retrieve(cartera.getDepartamentoC(), broker));
		cartera.setCiudadCRef(CiudadHandler.retrieve(cartera.getCiudadC(), broker));
                cartera.setDepartamentoTRef(BDepartamentosHandler.retrieve(cartera.getDepartamentoT(), broker));
		cartera.setCiudadTRef(CiudadHandler.retrieve(cartera.getCiudadT(), broker));
		
		
		
	}

	/**
	 * Obtiene un cliente de la cartera x departamento.
	 * 
	 * @param carteraId
	 *            Identificador del cliente en mora a obtener
	 * @return bean que contiene los datos de la cartera
	 */
	public static CarteraBanpro retrieve(final Integer tmpId)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(tmpId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	public Collection getList(final String codigo) throws Exception {
			PersistenceBroker broker = null;
			try {
				broker = PersistenceBrokerFactory.defaultPersistenceBroker();
				Criteria criterio = new Criteria();
				criterio.addLike("upper(codigo)", "*"
						+ codigo.toUpperCase(Locale.getDefault())
						+ "*");
				QueryByCriteria query = new QueryByCriteria(CarteraBanpro.class,
						criterio);
				query.addOrderByAscending("codigo");
				return broker.getCollectionByQuery(query);
			} catch (Exception e) {
				throw e;
			} finally {
				if (broker != null && !broker.isClosed()) {
					broker.close();
				}
			}
		}
	
	public static CarteraBanpro carteraXTarjeta(final String tarjeta) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXTarjeta(tarjeta, broker);

		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static CarteraBanpro carteraXTarjeta(final String tarjeta, final PersistenceBroker broker) {
		CarteraBanpro criterio = new CarteraBanpro();
		criterio.setTarjeta(tarjeta);
		Query query = new QueryByCriteria(criterio);
		return (CarteraBanpro) broker.getObjectByQuery(query);
	}
	
	public Collection getCarteraXCodigo(final String codigo) throws Exception {
		PersistenceBroker broker = null;
		
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (codigo != null) {
				criterio.addEqualTo("codigo", codigo);
			}
			QueryByCriteria query = new QueryByCriteria(CarteraBanpro.class, criterio);
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

	
	public static CarteraBanpro getMesSaldoMora(final String codigo,
			final String factura, PersistenceBroker pb) {
        Criteria criterio = new Criteria();
        criterio.addEqualTo("contrato", codigo);
        criterio.addEqualTo("factura", factura);
        QueryByCriteria query = new QueryByCriteria(CarteraBanpro.class, criterio);
        Iterator iter = pb.getIteratorByQuery(query);
        if (iter.hasNext()) {
            return  (CarteraBanpro) pb.getObjectByQuery(query);
        } else {
            return null;
        }
    }

    public static CarteraBanpro getMesSaldoMora(final String codigo,
			final String factura) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return getMesSaldoMora(codigo, factura, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	
	

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
					CarteraBanpro.class, new Criteria());
			query.setAttributes(new String[] { "tmpId",
					"codCliente" });
			query.addOrderBy("codCliente", true);
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
			QueryByCriteria query = new QueryByCriteria(CarteraBanpro.class,
					criterio);
			query.addOrderBy("tmpId", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	public static boolean existeCodigo(String codigo) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (codigo != null && codigo.length() > 0) {
				criterio.addLike("upper(contrato)",
						codigo.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(CarteraBanpro.class, criterio);
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
	
	public static BigDecimal getPagosCordobas(final BigDecimal monto, final String cuenta) throws Exception {
        PersistenceBroker broker = null;
        try {
           broker = PersistenceBrokerFactory.defaultPersistenceBroker();
           Criteria criterio = new Criteria();
           if (cuenta !=null) {
               criterio.addEqualTo("cuenta", cuenta);
           }
           ReportQueryByCriteria query = new ReportQueryByCriteria(PagosBanpro.class, criterio);
           query.setAttributes(new String[]{"sum(pagoCor)"});
           Iterator iter = broker.getReportQueryIteratorByQuery(query);
           BigDecimal saldo = BigDecimal.ZERO;
           if (iter.hasNext()) {
               Object[] item = (Object[])iter.next();
               if (item[0] != null) {
                   saldo = saldo.add((BigDecimal)item[0]);
               }
           }
           return monto.subtract(saldo);

        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
   }
	public static BigDecimal getPagosDolares(final String cuenta) throws Exception {
        PersistenceBroker broker = null;
        try {
           broker = PersistenceBrokerFactory.defaultPersistenceBroker();
           Criteria criterio = new Criteria();
           if (cuenta !=null) {
               criterio.addEqualTo("cuenta", cuenta);
           }
           ReportQueryByCriteria query = new ReportQueryByCriteria(PagosBanpro.class, criterio);
           query.setAttributes(new String[]{"sum(pagoDol)"});
           Iterator iter = broker.getReportQueryIteratorByQuery(query);
           BigDecimal saldo = BigDecimal.ZERO;
           if (iter.hasNext()) {
               Object[] item = (Object[])iter.next();
               if (item[0] != null) {
                   saldo = saldo.add((BigDecimal)item[0]);
               }
           }
           return saldo;

        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
   }
        
	public static BigDecimal getTotalDeuda(BigDecimal montoC,
			BigDecimal montoD, Date fecha) throws Exception {
		BigDecimal tasaCambio = TasafijaHandler.getTasaFija(fecha);
		BigDecimal conversion = montoC.divide(tasaCambio, 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal total = montoD.add(conversion);
		return total;

	}
	
	public static List getListDeudaXCliente(final String codCliente)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			List<Object[]> deuda = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker.getReportQueryIteratorByQuery(queryDeudaXCliente(codCliente)); iter.hasNext();) {

				Object[] detalle = (Object[]) iter.next();
				deuda.add(detalle);
			}
			
			return deuda;
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static ReportQueryByCriteria queryDeudaXCliente(
			final String codCliente) {

		Criteria criterio = new Criteria();

		if (codCliente != null) {
			criterio.addEqualTo("codCliente", codCliente);
		}
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				CarteraBanpro.class, criterio);
		query.setAttributes(new String[] { "tmpId", "nombre", "producto", "tarjeta",
				"cuenta", "deudaCor", "deudaDol", "totalDeudaDol" });
		query.addGroupBy(new String[] { "tmpId", "nombre", "producto", "tarjeta",
				"cuenta", "deudaCor", "deudaDol", "totalDeudaDol" });

		query.addOrderBy("tmpId", true);
		return query;
	}
}