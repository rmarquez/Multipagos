package com.metropolitana.multipagos.forms.arreglo_pago;

import com.metropolitana.multipagos.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import com.metropolitana.multipagos.forms.simbolo.SimboloHandler;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

public class ArregloPagoHandler {
	
	/** Nombre del campo para la búsqueda de ArregloPago. */
	private static final String CAMPO_BUSQUEDA = "codCliente";

	/**
	 * Inserta un arreglo de pago
	 * 
	 * @param arreglo
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(ArregloPago arreglo, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(arreglo, broker);
			broker.store(arreglo);
			//broker.store(LogsHandler.setLogsDelSistema(arreglo, fecha, usrId,
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
	 * Actualiza los datos de un arreglo
	 * 
	 * @param arreglo
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(ArregloPago arreglo, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
                        actualizarReferencias(arreglo, broker);
			broker.store(arreglo);
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
	 * Obtiene un arreglo de pago
	 * 
	 * @param arregloId
	 *            Identificador del arreglo de pago a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del arreglo
	 */
	public static ArregloPago retrieve(Integer arregloId, PersistenceBroker broker) {
		ArregloPago criterio = new ArregloPago();
		criterio.setArregloId(arregloId);
		Query query = new QueryByIdentity(criterio);
		return (ArregloPago) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene una cuenta
	 * 
	 * @param arregloId
	 *            Identificador de la cuenta a obtener
	 * @return bean que contiene los datos de la cuenta
	 */
	public static ArregloPago retrieve(Integer arregloId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(arregloId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private void actualizarReferencias(ArregloPago arreglo, PersistenceBroker broker) {
		linkDeuda(arreglo, broker);
		//linkCalendario(arreglo, broker);
	}

	private void linkDeuda(final ArregloPago bean, final PersistenceBroker broker) {
		Iterator iterDetalle = bean.getArregloDeudaList().iterator();
		while (iterDetalle.hasNext()) {
			ArregloDeuda det = (ArregloDeuda) iterDetalle.next();
                        det.setTmpIdRef(CarteraBanproHandler.retrieve(det.getTmpId(), broker));
                       det.setArregloIdRef(bean);
		}
	}
        
//        private void linkCalendario(final ArregloPago bean, final PersistenceBroker broker) {
//		Iterator iterDetalle = bean.getArregloCalendarioList().iterator();
//		while (iterDetalle.hasNext()) {
//			ArregloDeuda det = (ArregloDeuda) iterDetalle.next();
//                        det.setTmpIdRef(CarteraBanproHandler.retrieve(det.getTmpId(), broker));
//                       det.setArregloIdRef(bean);
//		}
//	}

	/**
	 * Genera listado de ArregloPago
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de ArregloPago
	 * @throws Exception
	 */
	private Collection getList(Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(ArregloPago.class, criterio);
			query.addOrderBy("tarjeta", true);
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
	 * Genera listado de ArregloPago
	 * 
	 * @param codCliente
	 *            codigo cliente a quien pertenece el arreglo de pago.
	 * @return Collection o null listado de ArregloPago
	 * @throws Exception
	 */
	public Collection getList(String codCliente) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (codCliente != null && codCliente.length() > 0) {
				criterio.addLike("upper(codCliente)",
						codCliente.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Genera el listado de todos las ArregloPago
	 * 
	 * @return Collection o null Listado de ArregloPago
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de ArregloPago a presentar en los resultados de las búsquedas
	 * por página.
	 * 
	 * @param codCliente
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de ArregloPago por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             bancos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<ArregloPago> getResultadosXPagina(
			final String codCliente, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(ArregloPago.class,
					Util.getCriterio(codCliente, null, CAMPO_BUSQUEDA),
					CAMPO_BUSQUEDA, pagina, registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Obtiene la cantidad de ArregloPago que coinciden con el filtro de búsqueda.
	 * 
	 * @param codCliente
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String codCliente) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(ArregloPago.class,
					getCriterio(codCliente, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static final Criteria getCriterio(final String criterioBusqueda,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		if (criterioBusqueda != null && criterioBusqueda.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					criterioBusqueda.toUpperCase(Locale.getDefault()) + "*");
		}

		return criterio;
	}
	
	

}
