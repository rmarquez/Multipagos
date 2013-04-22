package com.metropolitana.multipagos.forms.simbolo;

import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.SimboloIbw;
import com.metropolitana.multipagos.forms.Util;
import java.util.Collection;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * @author Rafael Márquez
 */
public class SimboloIbwHandler {

	/**
	 * <code>SIMBOLO_ID</code> Identificador del simbolo
	 */
	public static final String SIMBOLO_ID = "simboloId";

	/** Nombre del campo para la búsqueda de simbolos. */
	private static final String CAMPO_BUSQUEDA = "simboloNombre";

	
	/**
	 * Obtiene un simbolo.
	 * 
	 * @param simboloId
	 *            Identificador del simbolo a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del simbolo
	 */
	public static SimboloIbw retrieve(final Integer simboloId,
			final PersistenceBroker broker) {
		SimboloIbw criterio = new SimboloIbw();
		criterio.setSimboloId(simboloId);
		Query query = new QueryByIdentity(criterio);
		return (SimboloIbw) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un simbolo.
	 * 
	 * @param simboloId
	 *            Identificador del simbolo a obtener
	 * @return bean que contiene los datos del simbolo
	 */
	public static SimboloIbw retrieve(final Integer simboloId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(simboloId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de simbolos.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de simbolos
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(SimboloIbw.class, criterio);
			query.addOrderBy("simboloNumero", true);
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
	 * Genera listado de simbolos.
	 * 
	 * @param simboloNombre
	 *            nombre o parte del nombre de un simbolo
	 * @return Collection o null listado de simbolos
	 * @throws Exception
	 */
	public Collection getList(final String simboloNumero) throws Exception {
			PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			criterio.addLike("upper(simboloNumero)", "*"
					+ simboloNumero.toString().toUpperCase(Locale.getDefault())
					+ "*");
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					SimboloIbw.class, criterio);
			query.setAttributes(new String[] { "simboloId",
					"simboloNumero" });
			query.addOrderByAscending("simboloNumero");
			return IteratorUtils.toList(broker
					.getReportQueryIteratorByQuery(query));
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Obtener la lista de simbolos a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param simboloNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de simbolos por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             simbolos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<SimboloIbw> getResultadosXPagina(
			final String simboloNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(SimboloIbw.class,
					Util.getCriterio(simboloNombre, filtrar, CAMPO_BUSQUEDA),
					SIMBOLO_ID, pagina, registrosXPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Obtiene la cantidad de simbolos que coinciden con el filtro de búsqueda.
	 * 
	 * @param simboloNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String simboloNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(SimboloIbw.class,
					Util.getCriterio(simboloNombre, filtrar, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	// RSJ 20120413 - cambio
	public Collection getList() throws Exception {
        Criteria criterio = new Criteria();
        criterio.addEqualTo("inactivo", Boolean.FALSE);
        try {
            return getList(criterio);
        } catch (Exception e) {
            throw e;
        }
    }
	
	public static SimboloIbw simboloXNumero(final Integer simboloNumero)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return simboloXNumero(simboloNumero, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static SimboloIbw simboloXNumero(final Integer simboloNumero,
			final PersistenceBroker broker) {
		SimboloIbw criterio = new SimboloIbw();
		criterio.setSimboloNumero(simboloNumero);
		Query query = new QueryByCriteria(criterio);
		return (SimboloIbw) broker.getObjectByQuery(query);
	}
	
	public Collection simboloXNumeroList() throws Exception {
		try {
			return simboloXNumeroList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Collection simboloXNumeroList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(SimboloIbw.class,
					criterio);
			query.addOrderBy("simboloId", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static boolean validarNumero(Integer simboloNumero) throws Exception {
        try {
            Criteria criterio = new Criteria();
            
            if (simboloNumero != null) {
                    criterio.addEqualTo("simboloNumero", simboloNumero);
                    
            }
            List lst = getSimboloList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	public static boolean validarNombre(String simboloNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            
            if (simboloNombre != null) {
                    //criterio.addEqualTo("numeroCuenta", numeroCuenta);
                    criterio.addLike("upper(simboloNombre)",
                    		simboloNombre.toUpperCase(Locale.getDefault()) + "*");
            }
            List lst = getSimboloList(criterio);
            if (lst.isEmpty()) {
                return Boolean.FALSE.booleanValue();
            } else {
                return Boolean.TRUE.booleanValue();
            }
        } catch (Exception e) {
            throw e;
        }
    }
	
	private static List getSimboloList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(SimboloIbw.class, criterio);
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
