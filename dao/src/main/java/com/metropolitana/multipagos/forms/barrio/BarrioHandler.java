package com.metropolitana.multipagos.forms.barrio;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

import com.metropolitana.multipagos.Banco;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class BarrioHandler {

	/**
	 * <code>BARRIO_ID</code> Identificador del Barrio
	 */
	public static final String BARRIO_ID = "barrioId";

	/** Nombre del campo para la búsqueda de Barrios. */
	private static final String CAMPO_BUSQUEDA = "barrioNombre";

	/**
	 * Inserta un Barrio.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(final Barrio bean, Integer usrId) throws Exception {
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
	 * Actualiza los datos de un Barrio.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(final Barrio bean, Integer usrId, Boolean inactivo)
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
	 * Obtiene un Barrio.
	 * 
	 * @param BarrioId
	 *            Identificador del Barrio a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del Barrio
	 */
	public static Barrio retrieve(final Integer BarrioId,
			final PersistenceBroker broker) {
		Barrio criterio = new Barrio();
		criterio.setBarrioId(BarrioId);
		Query query = new QueryByIdentity(criterio);
		return (Barrio) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param barrio
	 *            Bean de objeto Barrio
	 * @param broker
	 */
	private void actualizarReferencias(Barrio barrio,
			PersistenceBroker broker) {
		barrio.setLocalidadIdRef(LocalidadHandler.retrieve(
				barrio.getLocalidadId(), broker));
	}

	/**
	 * Obtiene un Barrio.
	 * 
	 * @param barrioId
	 *            Identificador del Barrio a obtener
	 * @return bean que contiene los datos del Barrio
	 */
	public static Barrio retrieve(final Integer barrioId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(barrioId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de Barrios.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de Barrios
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Barrio.class,
					criterio);
			query.addOrderBy("barrioNombre", true);
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
	 * Genera listado de Barrios.
	 * 
	 * @param BarrioNombre
	 *            nombre o parte del nombre de un Barrio
	 * @return Collection o null listado de Barrios
	 * @throws Exception
	 */
	public Collection getList(final String BarrioNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (BarrioNombre != null && BarrioNombre.length() > 0) {
				criterio.addLike("upper(barrioNombre)",
						BarrioNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de Barrioes a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param barrioNombre
	 *            Filtro de búsqueda.
	 * @param localidadId
	 *            Idetinficador de la localidad.
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
	public final Collection<Barrio> getResultadosXPagina(
			final String barrioNombre, final Integer localidadId,
			final Integer filtrar, final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					Barrio.class,
					getCriterio(barrioNombre, localidadId, filtrar,
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
	 * Obtiene la cantidad de Barrios que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param barrioNombre
	 *            Filtro de búsqueda.
	 * @param localidadId
	 *            Identificador del objeto localidad.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String barrioNombre,
			final Integer localidadId, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Barrio.class,
					getCriterio(barrioNombre, localidadId, filtrar,
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
	 * Barrio.
	 * 
	 * @param barrioNombre
	 *            Criterio de busqueda.
	 * @param localidadId
	 *            Identificador del objeto localidad.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final String barrioNombre,
			final Integer localidadId, final Integer filtrar,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		if (barrioNombre != null && barrioNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					barrioNombre.toUpperCase(Locale.getDefault()) + "*");
		}
		if (localidadId != null) {
			criterio.addEqualTo("localidadId", localidadId);
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
	 * Genera el listado de todos los Barrios.
	 * 
	 * @return Collection o null Listao de Barrios
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
	
	public static boolean existeBarrio(String barrioNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (barrioNombre != null && barrioNombre.length() > 0) {
				criterio.addLike("upper(barrioNombre)",
						barrioNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(Barrio.class, criterio);
            query.addOrderBy("barrioNombre", true);
            return (List)broker.getCollectionByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
	
	public Collection getBarrioXLocalidad(final Integer localidadId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (localidadId != null) {
				criterio.addEqualTo("localidadId", localidadId);
			}
			QueryByCriteria query = new QueryByCriteria(Barrio.class, criterio);
			query.addOrderByAscending("barrioNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getBarrioXCartera(final Integer carteraId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (carteraId != null) {
				criterio.addEqualTo("carteraXDepartamentoList.carteraId", carteraId);
			}
			QueryByCriteria query = new QueryByCriteria(Barrio.class, criterio);
			query.addOrderByAscending("barrioNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
}
