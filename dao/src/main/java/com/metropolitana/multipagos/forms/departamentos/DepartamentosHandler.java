package com.metropolitana.multipagos.forms.departamentos;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.Departamento;
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
public class DepartamentosHandler {

	/**
	 * <code>DEP_ID</code> Identificador del departamento
	 */
	public static final String DEP_ID = "departamentoId";

	/** Nombre del campo para la búsqueda de departamentos. */
	private static final String CAMPO_BUSQUEDA = "departamentoNombre";

	/**
	 * Inserta un departamento.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Departamento bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			// actualizarReferencias(bean, broker);
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
	 * Actualiza los datos de un departamento.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Departamento bean, Integer usrId, Boolean inactivo) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			// actualizarReferencias(bean, broker);
			broker.store(bean);
			if(inactivo.booleanValue()== Boolean.FALSE) {
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
	 * Obtiene un departamento.
	 * 
	 * @param departamentoId
	 *            Identificador del departamento a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del departamento
	 */
	public static Departamento retrieve(final Integer departamentoId,
			final PersistenceBroker broker) {
		Departamento criterio = new Departamento();
		criterio.setDepartamentoId(departamentoId);
		Query query = new QueryByIdentity(criterio);
		return (Departamento) broker.getObjectByQuery(query);
	}
	
	/**
	 * Obtiene un departamento.
	 * 
	 * @param departamentoId
	 *            Identificador del departamento a obtener
	 * @return bean que contiene los datos del departamento
	 */
	public static Departamento retrieve(final Integer departamentoId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(departamentoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de departamentos.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de departamentos
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Departamento.class,
					criterio);
			query.addOrderBy("departamentoNombre", true);
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
	 * Genera listado de departamentos.
	 * 
	 * @param departamentoNombre
	 *            nombre o parte del nombre de un departamento
	 * @return Collection o null listado de departamentos
	 * @throws Exception
	 */
	public Collection getList(final String departamentoNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (departamentoNombre != null && departamentoNombre.length() > 0) {
				criterio.addLike("upper(departamentoNombre)",
						departamentoNombre.toUpperCase(Locale.getDefault())
								+ "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de departamentos a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param departamentoNombre
	 *            Filtro de búsqueda.
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
	public final Collection<Departamento> getResultadosXPagina(
			final String departamentoNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(Departamento.class,
					Util.getCriterio(departamentoNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de departamentos que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param departamentoNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String departamentoNombre, final Integer filtrar)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Departamento.class,
					Util.getCriterio(departamentoNombre, filtrar, CAMPO_BUSQUEDA));
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
	 * Genera el listado de todos los departamentos.
	 * 
	 * @return Collection o null Listao de departamentos
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
	
	public static boolean existeDepartamento(String departamentoNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (departamentoNombre != null && departamentoNombre.length() > 0) {
				criterio.addLike("upper(departamentoNombre)",
						departamentoNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(Departamento.class, criterio);
            query.addOrderBy("departamentoNombre", true);
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
