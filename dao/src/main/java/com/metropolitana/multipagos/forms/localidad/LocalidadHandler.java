package com.metropolitana.multipagos.forms.localidad;

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

import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;

public class LocalidadHandler {

	/**
	 * <code>LOCALIDAD_ID</code> Identificador del Localidad
	 */
	public static final String LOCALIDAD_ID = "localidadId";

	/** Nombre del campo para la búsqueda de Localidades. */
	private static final String CAMPO_BUSQUEDA = "localidadNombre";

	/**
	 * Inserta un Localidad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Localidad bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
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
	 * Actualiza los datos de un Localidad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Localidad bean, Integer usrId, Boolean inactivo)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
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
	 * Obtiene un Localidad.
	 * 
	 * @param LocalidadId
	 *            Identificador del Localidad a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del Localidad
	 */
	public static Localidad retrieve(final Integer LocalidadId,
			final PersistenceBroker broker) {
		Localidad criterio = new Localidad();
		criterio.setLocalidadId(LocalidadId);
		Query query = new QueryByIdentity(criterio);
		return (Localidad) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param localidad
	 *            Bean de objeto localidad
	 * @param broker
	 */
	private void actualizarReferencias(Localidad localidad,
			PersistenceBroker broker) {
		localidad.setDepartamentoIdRef(DepartamentosHandler.retrieve(
				localidad.getDepartamentoId(), broker));
	}

	/**
	 * Obtiene un Localidad.
	 * 
	 * @param LocalidadId
	 *            Identificador del Localidad a obtener
	 * @return bean que contiene los datos del Localidad
	 */
	public static Localidad retrieve(final Integer LocalidadId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(LocalidadId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de Localidads.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de Localidads
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Localidad.class,
					criterio);
			query.addOrderBy("localidadNombre", true);
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
	 * Genera listado de Localidads.
	 * 
	 * @param localidadNombre
	 *            nombre o parte del nombre de un Localidad
	 * @return Collection o null listado de Localidads
	 * @throws Exception
	 */
	public Collection getList(final String localidadNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (localidadNombre != null && localidadNombre.length() > 0) {
				criterio.addLike("upper(localidadNombre)",
						localidadNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getList(Integer carteraId) throws Exception {
        try {
            Criteria criterios = new Criteria();
            if (carteraId != null) {
                criterios.addEqualTo("carteraXDepartamentoList.carteraId", carteraId);
            }
            return getList(criterios);
        } catch (Exception e) {
            throw e;
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
	@SuppressWarnings("unchecked")
	public final Collection<Localidad> getResultadosXPagina(
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
	}

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
	public final int getContador(final String localidadNombre,
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
	}

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
	public static final Criteria getCriterio(final String localidadNombre,
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
	}

	/**
	 * Genera el listado de todos los Localidads.
	 * 
	 * @return Collection o null Listao de Localidads
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}

}
