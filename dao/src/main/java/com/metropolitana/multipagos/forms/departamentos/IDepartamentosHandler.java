package com.metropolitana.multipagos.forms.departamentos;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.IDepartamento;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

/**
 * @author Rafael Márquez
 */
public class IDepartamentosHandler {

	/**
	 * <code>DEP_ID</code> Identificador del IDepartamento
	 */
	public static final String DEP_ID = "iDepartamentoId";

	/** Nombre del campo para la búsqueda de IDepartamentos. */
	private static final String CAMPO_BUSQUEDA = "iDepartamentoNombre";

	/**
	 * Obtiene un IDepartamento.
	 * 
	 * @param IDepartamentoId
	 *            Identificador del IDepartamento a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del IDepartamento
	 */
	public static IDepartamento retrieve(final Integer IDepartamentoId,
			final PersistenceBroker broker) {
		IDepartamento criterio = new IDepartamento();
		criterio.setIDepartamentoId(IDepartamentoId);
		Query query = new QueryByIdentity(criterio);
		return (IDepartamento) broker.getObjectByQuery(query);
	}
	
	/**
	 * Obtiene un IDepartamento.
	 * 
	 * @param IDepartamentoId
	 *            Identificador del IDepartamento a obtener
	 * @return bean que contiene los datos del IDepartamento
	 */
	public static IDepartamento retrieve(final Integer IDepartamentoId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(IDepartamentoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de IDepartamentos.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de IDepartamentos
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(IDepartamento.class,
					criterio);
			query.addOrderBy("IDepartamentoNombre", true);
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
	 * Genera listado de IDepartamentos.
	 * 
	 * @param IDepartamentoNombre
	 *            nombre o parte del nombre de un IDepartamento
	 * @return Collection o null listado de IDepartamentos
	 * @throws Exception
	 */
	public Collection getList(final String IDepartamentoNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (IDepartamentoNombre != null && IDepartamentoNombre.length() > 0) {
				criterio.addLike("upper(IDepartamentoNombre)",
						IDepartamentoNombre.toUpperCase(Locale.getDefault())
								+ "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de IDepartamentos a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param IDepartamentoNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de IDepartamentos por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             IDepartamentos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<IDepartamento> getResultadosXPagina(
			final String IDepartamentoNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(IDepartamento.class,
					Util.getCriterio(IDepartamentoNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de IDepartamentos que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param IDepartamentoNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String IDepartamentoNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(IDepartamento.class,
					Util.getCriterio(IDepartamentoNombre, filtrar, CAMPO_BUSQUEDA));
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
	 * Genera el listado de todos los IDepartamentos.
	 * 
	 * @return Collection o null Listao de IDepartamentos
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("inactivo", Boolean.FALSE);
		try {
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean existeIDepartamento(String IDepartamentoNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (IDepartamentoNombre != null && IDepartamentoNombre.length() > 0) {
				criterio.addLike("upper(IDepartamentoNombre)",
						IDepartamentoNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(IDepartamento.class, criterio);
            query.addOrderBy("IDepartamentoNombre", true);
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
