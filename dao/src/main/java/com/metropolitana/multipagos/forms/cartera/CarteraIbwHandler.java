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

import com.metropolitana.multipagos.CarteraIbw;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.barrio.IBarrioHandler;
import com.metropolitana.multipagos.forms.departamentos.IDepartamentosHandler;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.municipio.MunicipioHandler;
import com.metropolitana.multipagos.forms.pagos.PagosHandler;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;


public class CarteraIbwHandler {

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
	public static CarteraIbw retrieve(final Integer carteraId,
			final PersistenceBroker broker) {
		CarteraIbw criterio = new CarteraIbw();
		criterio.setCarteraId(carteraId);
		Query query = new QueryByIdentity(criterio);
		return (CarteraIbw) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param cartera
	 *            Bean de objeto cartera x departamento
	 * @param broker
	 */
	private void actualizarReferencias(CarteraIbw cartera,
			PersistenceBroker broker) {
		cartera.setDepartamentoIdRef(IDepartamentosHandler.retrieve(
				cartera.getDepartamentoId(), broker));
		cartera.setBarrioIdRef(IBarrioHandler.retrieve(
				cartera.getBarrioId(), broker));
		cartera.setMunicipioIdRef(MunicipioHandler.retrieve(
				cartera.getMunicipioId(), broker));
	}

	/**
	 * Obtiene un cliente de la cartera x departamento.
	 * 
	 * @param carteraId
	 *            Identificador del cliente en mora a obtener
	 * @return bean que contiene los datos de la cartera
	 */
	public static CarteraIbw retrieve(final Integer carteraId)
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

	
	public Collection getList(final String codCliente) throws Exception {
			PersistenceBroker broker = null;
			try {
				broker = PersistenceBrokerFactory.defaultPersistenceBroker();
				Criteria criterio = new Criteria();
				criterio.addLike("upper(codCliente)", "*"
						+ codCliente.toUpperCase(Locale.getDefault())
						+ "*");
				QueryByCriteria query = new QueryByCriteria(CarteraIbw.class,
						criterio);
				query.addOrderByAscending("codCliente");
				return broker.getCollectionByQuery(query);
			} catch (Exception e) {
				throw e;
			} finally {
				if (broker != null && !broker.isClosed()) {
					broker.close();
				}
			}
		}
	
	public static CarteraIbw carteraXfacturaIbw(final String facturaIbw) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXfacturaIbw(facturaIbw, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	private static CarteraIbw carteraXfacturaIbw(final String facturaIbw, final PersistenceBroker broker) {
		CarteraIbw criterio = new CarteraIbw();
		criterio.setFacturaIbw(facturaIbw);
		Query query = new QueryByCriteria(criterio);
		return (CarteraIbw) broker.getObjectByQuery(query);
	}
	
	
	public static CarteraIbw carteraXcodCliente(final String codCliente)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXcodCliente(codCliente, broker);
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static CarteraIbw carteraXcodCliente(final String codCliente, final PersistenceBroker broker) {
		CarteraIbw criterio = new CarteraIbw();
		criterio.setCodCliente(codCliente);
		Query query = new QueryByCriteria(criterio);
		return (CarteraIbw) broker.getObjectByQuery(query);
	}
	
	public Collection getCarteraXcodCliente(final String codCliente) throws Exception {
		PersistenceBroker broker = null;
		
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (codCliente != null) {
				criterio.addEqualTo("codCliente", codCliente);
			}
			QueryByCriteria query = new QueryByCriteria(CarteraIbw.class, criterio);
			query.addOrderByAscending("codCliente");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	public static CarteraIbw getMesSaldoMora(final String codCliente,
			final String facturaIbw, PersistenceBroker pb) {
        Criteria criterio = new Criteria();
        criterio.addEqualTo("codCliente", codCliente);
        criterio.addEqualTo("facturaIbw", facturaIbw);
        QueryByCriteria query = new QueryByCriteria(CarteraIbw.class, criterio);
        Iterator iter = pb.getIteratorByQuery(query);
        if (iter.hasNext()) {
            return  (CarteraIbw) pb.getObjectByQuery(query);
        } else {
            return null;
        }
    }

    public static CarteraIbw getMesSaldoMora(final String codCliente,
			final String facturaIbw) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return getMesSaldoMora(codCliente, facturaIbw, broker);
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
					CarteraIbw.class, new Criteria());
			query.setAttributes(new String[] { "carteraId",
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
			QueryByCriteria query = new QueryByCriteria(CarteraIbw.class,
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
	
	
	public static boolean existecodCliente(String codCliente) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (codCliente != null && codCliente.length() > 0) {
				criterio.addLike("upper(codCliente)",
						codCliente.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(CarteraIbw.class, criterio);
            query.addOrderBy("codCliente", true);
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