package com.metropolitana.multipagos.forms.ciudad;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;


import com.metropolitana.multipagos.Ciudad;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;
import com.metropolitana.multipagos.forms.departamentos.BDepartamentosHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;

public class CiudadHandler {

	/**
	 * <code>CIUDAD_ID</code> Identificador de la ciudad
	 */
	public static final String CIUDAD_ID = "ciudadId";

	/** Nombre del campo para la búsqueda de Ciudades. */
	private static final String CAMPO_BUSQUEDA = "ciudadNombre";

	/**
	 * Inserta un Ciudad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	
	public void insert(final Ciudad bean, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			actualizarReferencias(bean, broker);
			broker.store(bean);
			//broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId,
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
	} */

	/**
	 * Actualiza los datos de un Ciudad.
	 * 
	 * @param bean
	 *            bean que contiene los datos
	 * @throws Exception
	
	public void update(final Ciudad bean, Integer usrId, Boolean inactivo)
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
	} */

	/**
	 * Obtiene un Ciudad.
	 * 
	 * @param CiudadId
	 *            Identificador del Ciudad a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del Ciudad
	 */
	public static Ciudad retrieve(final Integer CiudadId,
			final PersistenceBroker broker) {
		Ciudad criterio = new Ciudad();
		criterio.setCiudadId(CiudadId);
		Query query = new QueryByIdentity(criterio);
		return (Ciudad) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param Ciudad
	 *            Bean de objeto Ciudad
	 * @param broker
	
	private static void actualizarReferencias(Ciudad ciudad,
			PersistenceBroker broker) {
		Ciudad.setDepartamentoIdRef(BDepartamentosHandler.retrieve(ciudad.getDepartamentoId(), broker));
	}
 */
	/**
	 * Obtiene un Ciudad.
	 * 
	 * @param CiudadId
	 *            Identificador del Ciudad a obtener
	 * @return bean que contiene los datos del Ciudad
	 */
	public static Ciudad retrieve(final Integer CiudadId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(CiudadId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de Ciudads.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de Ciudads
	 * @throws Exception
	 */
	private Collection getCiudadList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			criterio.addEqualTo("inactivo", false);
			QueryByCriteria query = new QueryByCriteria(Ciudad.class,
					criterio);
			query.addOrderBy("CiudadId", true);
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
	 * Genera listado de Ciudads.
	 * 
	 * @param CiudadNombre
	 *            nombre o parte del nombre de un Ciudad
	 * @return Collection o null listado de Ciudads
	 * @throws Exception
	 */
	public Collection getList(final String CiudadNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (CiudadNombre != null && CiudadNombre.length() > 0) {
				criterio.addLike("upper(CiudadNombre)",
						CiudadNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getCiudadList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getList(Integer tmpId) throws Exception {
        try {
            Criteria criterios = new Criteria();
            if (tmpId != null) {
                criterios.addEqualTo("carteraXDepartamentoList.tmpId", tmpId);
            }
            return getCiudadList(criterios);
        } catch (Exception e) {
            throw e;
        }
    }
	
	public Collection getCiudadCXCartera(final Integer tmpId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (tmpId != null) {
				criterio.addEqualTo("ciudadCCarteraBanproList.tmpId", tmpId);
			}
			QueryByCriteria query = new QueryByCriteria(Ciudad.class, criterio);
			query.addOrderByAscending("ciudadNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getCiudadTXCartera(final Integer tmpId) throws Exception {
			
			PersistenceBroker broker = null;
			try {
				broker = PersistenceBrokerFactory.defaultPersistenceBroker();
				Criteria criterio = new Criteria();
				if (tmpId != null) {
					criterio.addEqualTo("ciudadTCarteraBanproList.tmpId", tmpId);
				}
				QueryByCriteria query = new QueryByCriteria(Ciudad.class, criterio);
				query.addOrderByAscending("ciudadNombre");
				return broker.getCollectionByQuery(query);
			} catch (Exception e) {
				throw e;
			} finally {
				if (broker != null && !broker.isClosed()) {
					broker.close();
				}
			}
		}
	
	public Collection getCiudadXCarteraAvon(final Integer carteraId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (carteraId != null) {
				criterio.addEqualTo("carteraAvonList.carteraId", carteraId);
			}
			QueryByCriteria query = new QueryByCriteria(Ciudad.class, criterio);
			query.addOrderByAscending("ciudadNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getCiudadXDepartamento(final Integer departamentoId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (departamentoId != null) {
				criterio.addEqualTo("departamentoId", departamentoId);
			}
			QueryByCriteria query = new QueryByCriteria(Ciudad.class, criterio);
			query.addOrderByAscending("ciudadNombre");
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
	 * Obtener la lista de Ciudades a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param CiudadNombre
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
	public final Collection<Ciudad> getResultadosXPagina(
			final String CiudadNombre, final Integer departamentoId,
			final Integer filtrar, final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					Ciudad.class,
					getCriterio(CiudadNombre, departamentoId, filtrar,
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
	 * Obtiene la cantidad de Ciudads que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param CiudadNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String CiudadNombre,
			final Integer departamentoId, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Ciudad.class,
					getCriterio(CiudadNombre, departamentoId, filtrar,
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
	 * Ciudad.
	 * 
	 * @param CiudadNombre
	 *            Criterio de busqueda.
	 * @param departamentoId
	 *            Identificador del departamento.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final String CiudadNombre,
			final Integer departamentoId, final Integer filtrar,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("departamentoIdRef.inactivo", Boolean.FALSE);
		if (CiudadNombre != null && CiudadNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					CiudadNombre.toUpperCase(Locale.getDefault()) + "*");
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
	 * Genera el listado de todos los Ciudads.
	 * 
	 * @return Collection o null Listao de Ciudads
	 * @throws Exception
	 */
	public Collection getCiudadList() throws Exception {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("inactivo", Boolean.FALSE);
		try {
			return getCiudadList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean existeCiudad(String CiudadNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (CiudadNombre != null && CiudadNombre.length() > 0) {
				criterio.addLike("upper(CiudadNombre)",
						CiudadNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(Ciudad.class, criterio);
            query.addOrderBy("ciudadNombre", true);
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
