package com.metropolitana.multipagos.forms.cantidades;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.CantidadMonedas;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
/**
 * @author Rafael Márquez
 */
public class CantidadHandler {

	/** Nombre del campo para la búsqueda de cantidads. */
	private static final String CAMPO_BUSQUEDA = "cantidadNombre";

	/**
	 * Inserta un cantidad
	 * 
	 * @param cantidad
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(CantidadMonedas cantidad, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(cantidad);
			broker.store(LogsHandler.setLogsDelSistema(cantidad, fecha, usrId,
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
	 * Actualiza los datos de un cantidad
	 * 
	 * @param cantidad
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(CantidadMonedas cantidad, Integer usrId, Boolean inactivo) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(cantidad);
			if(inactivo.booleanValue()== Boolean.FALSE) {
				broker.store(LogsHandler.setLogsDelSistema(cantidad, fecha, usrId,
						Integer.valueOf(2), broker));
			} else {
				broker.store(LogsHandler.setLogsDelSistema(cantidad, fecha, usrId,
						Integer.valueOf(3), broker));
			}
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
	 * Obtiene un cantidad
	 * 
	 * @param cantidadId
	 *            Identificador del cantidad a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del cantidad
	 */
	public static CantidadMonedas retrieve(Integer cantidadId, PersistenceBroker broker) {
		CantidadMonedas criterio = new CantidadMonedas();
		criterio.setCantidadId(cantidadId);
		Query query = new QueryByIdentity(criterio);
		return (CantidadMonedas) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un cantidad
	 * 
	 * @param cantidadId
	 *            Identificador del cantidad a obtener
	 * @return bean que contiene los datos del cantidad
	 */
	public static CantidadMonedas retrieve(Integer cantidadId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(cantidadId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de cantidads
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de cantidads
	 * @throws Exception
	 */
	private Collection getList(Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(CantidadMonedas.class, criterio);
			query.addOrderBy("cantidadNombre", true);
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
	 * Genera listado de cantidads
	 * 
	 * @param cantidadNombre
	 *            nombre o parte del nombre de un cantidad
	 * @return Collection o null listado de cantidads
	 * @throws Exception
	 */
	public Collection getList(String cantidadNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (cantidadNombre != null && cantidadNombre.length() > 0) {
				criterio.addLike("upper(cantidadNombre)",
						cantidadNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Genera el listado de todos los cantidads
	 * 
	 * @return Collection o null Listao de cantidads
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getList(Boolean dolares) throws Exception {
		try {
			Criteria criterio = new Criteria();
			criterio.addEqualTo("dolares", dolares);
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de cantidad a presentar en los resultados de las búsquedas
	 * por página.
	 * 
	 * @param cantidadNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de cantidads por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             cantidads.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<CantidadMonedas> getResultadosXPagina(
			final String cantidadNombre, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(CantidadMonedas.class,
					getCriterio(cantidadNombre, CAMPO_BUSQUEDA),
					CAMPO_BUSQUEDA, pagina, registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static final Criteria getCriterio(final String criterioBusqueda, final String campoBusqueda) {
        Criteria criterio = new Criteria();
        if (criterioBusqueda != null && criterioBusqueda.length() > 0) {
            criterio.addLike("upper(" + campoBusqueda + ")",
                    criterioBusqueda.toUpperCase(Locale.getDefault()) + "*");
        }
        /*if(filtrar == Integer.valueOf(1)){
        	criterio.addEqualTo("inactivo", Boolean.FALSE);
        }
        if(filtrar == Integer.valueOf(2)){
        	criterio.addEqualTo("inactivo", Boolean.TRUE);
        }*/
        return criterio;
    }

	/**
	 * Obtiene la cantidad de cantidads que coinciden con el filtro de búsqueda.
	 * 
	 * @param cantidadNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String cantidadNombre) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(CantidadMonedas.class,
					getCriterio(cantidadNombre, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
}
