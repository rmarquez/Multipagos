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

import com.metropolitana.multipagos.CarteraAvon;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.pagos.PagosHandler;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;


public class CarteraAvonHandler {

	/**
	 * <code>CARTERA_ID</code> Identificador de la cartera
	 */
	public static final String CARTERA_ID = "carteraId";

	/** Nombre del campo para la búsqueda de Localidades. */
	//private static final String CAMPO_BUSQUEDA = "localidadNombre";

	/**
	 * Obtiene un cliente de la cartera de cliente en mora.
	 * 
	 * @param carteraId
	 *            Identificador del cartera a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos de la Cartera
	 */
	public static CarteraAvon retrieve(final Integer cavonId,
			final PersistenceBroker broker) {
		CarteraAvon criterio = new CarteraAvon();
		criterio.setCavonId(cavonId);
		Query query = new QueryByIdentity(criterio);
		return (CarteraAvon) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param cartera
	 *            Bean de objeto cartera x departamento
	 * @param broker
	 */
	private void actualizarReferencias(CarteraAvon cartera,
			PersistenceBroker broker) {
		cartera.setDepartamentoIdRef(DepartamentosHandler.retrieve(
				cartera.getDepartamentoId(), broker));
		cartera.setBarrioIdRef(BarrioHandler.retrieve(
				cartera.getBarrioId(), broker));
		cartera.setLocalidadIdRef(LocalidadHandler.retrieve(
				cartera.getLocalidadId(), broker));
	}

	/**
	 * Obtiene un cliente de la cartera x departamento.
	 * 
	 * @param carteraId
	 *            Identificador del cliente en mora a obtener
	 * @return bean que contiene los datos de la cartera
	 */
	public static CarteraAvon retrieve(final Integer cavonId)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(cavonId, broker);
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
				QueryByCriteria query = new QueryByCriteria(CarteraAvon.class,
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
	
	public static CarteraAvon carteraXFactura(final String factura) throws Exception {
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
	
	
	private static CarteraAvon carteraXFactura(final String factura, final PersistenceBroker broker) {
		CarteraAvon criterio = new CarteraAvon();
		criterio.setFactura(factura);
		Query query = new QueryByCriteria(criterio);
		return (CarteraAvon) broker.getObjectByQuery(query);
	}
	
	
	public static CarteraAvon carteraXCodigo(final String codigo)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXCodigo(codigo, broker);
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static CarteraAvon carteraXCodigo(final String codigo, final PersistenceBroker broker) {
		CarteraAvon criterio = new CarteraAvon();
		criterio.setCodigo(codigo);
		Query query = new QueryByCriteria(criterio);
		return (CarteraAvon) broker.getObjectByQuery(query);
	}
	
	public Collection getCarteraXCodigo(final String codigo) throws Exception {
		PersistenceBroker broker = null;
		
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (codigo != null) {
				criterio.addEqualTo("codigo", codigo);
			}
			QueryByCriteria query = new QueryByCriteria(CarteraAvon.class, criterio);
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

	
	public static CarteraAvon getMesSaldoMora(final String codigo,
			final String factura, PersistenceBroker pb) {
        Criteria criterio = new Criteria();
        criterio.addEqualTo("contrato", codigo);
        criterio.addEqualTo("factura", factura);
        QueryByCriteria query = new QueryByCriteria(CarteraAvon.class, criterio);
        Iterator iter = pb.getIteratorByQuery(query);
        if (iter.hasNext()) {
            return  (CarteraAvon) pb.getObjectByQuery(query);
        } else {
            return null;
        }
    }

    public static CarteraAvon getMesSaldoMora(final String codigo,
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
					CarteraAvon.class, new Criteria());
			query.setAttributes(new String[] { "cavonId",
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
			QueryByCriteria query = new QueryByCriteria(CarteraAvon.class,
					criterio);
			query.addOrderBy("cavonId", true);
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
            QueryByCriteria query = new QueryByCriteria(CarteraAvon.class, criterio);
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