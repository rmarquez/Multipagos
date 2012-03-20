package com.metropolitana.multipagos.forms.simbolo;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.Simbolo;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
//import com.metropolitana.multipagos.forms.contabilidad.CuentaContableHandler;
//import com.metropolitana.multipagos.forms.logs.LogsHandler;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PBFactoryException;
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
public class SimboloHandler {

	/**
	 * <code>SIMBOLO_ID</code> Identificador del simbolo
	 */
	public static final String SIMBOLO_ID = "simboloId";

	/** Nombre del campo para la búsqueda de simbolos. */
	private static final String CAMPO_BUSQUEDA = "simboloNombre";

	/**
	 * Inserta un simbolo.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Simbolo bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
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
	 * Actualiza los datos de un simbolo.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Simbolo bean, Integer usrId, Boolean inactivo)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(bean);
			if (inactivo.booleanValue() == Boolean.FALSE) {
				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
						Integer.valueOf(2), broker));
			} else {
				broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
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
	 * Obtiene un simbolo.
	 * 
	 * @param simboloId
	 *            Identificador del simbolo a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del simbolo
	 */
	public static Simbolo retrieve(final Integer simboloId,
			final PersistenceBroker broker) {
		Simbolo criterio = new Simbolo();
		criterio.setSimboloId(simboloId);
		Query query = new QueryByIdentity(criterio);
		return (Simbolo) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un simbolo.
	 * 
	 * @param simboloId
	 *            Identificador del simbolo a obtener
	 * @return bean que contiene los datos del simbolo
	 */
	public static Simbolo retrieve(final Integer simboloId) throws Exception {
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
			QueryByCriteria query = new QueryByCriteria(Simbolo.class, criterio);
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
					Simbolo.class, criterio);
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
	public final Collection<Simbolo> getResultadosXPagina(
			final String simboloNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(Simbolo.class,
					Util.getCriterio(simboloNombre, filtrar, CAMPO_BUSQUEDA),
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
			QueryByCriteria query = new QueryByCriteria(Simbolo.class,
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

	/**
	 * Genera el listado de todos los simbolos.
	 * 
	 * @return Collection o null Listao de simbolos
	 * @throws Exception
	 */
	public Collection getList() throws PBFactoryException {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					Simbolo.class, new Criteria());
			query.setAttributes(new String[] { "simboloId",
					"simboloNumero" });
			query.addOrderBy("simboloNumero", true);
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
	
	public static Simbolo simboloXNumero(final Integer simboloNumero)
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
	
	public static Simbolo simboloXNumero(final Integer simboloNumero,
			final PersistenceBroker broker) {
		Simbolo criterio = new Simbolo();
		criterio.setSimboloNumero(simboloNumero);
		Query query = new QueryByCriteria(criterio);
		return (Simbolo) broker.getObjectByQuery(query);
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
			QueryByCriteria query = new QueryByCriteria(Simbolo.class,
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
            QueryByCriteria query = new QueryByCriteria(Simbolo.class, criterio);
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
