package com.metropolitana.multipagos.forms.barrio;

import com.metropolitana.multipagos.IBarrio;
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

import com.metropolitana.multipagos.IBarrio;
import com.metropolitana.multipagos.Municipio;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.municipio.MunicipioHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class IBarrioHandler {

	/**
	 * <code>BARRIO_ID</code> Identificador del IBarrio
	 */
	public static final String BARRIO_ID = "barrioId";

	/** Nombre del campo para la búsqueda de IBarrios. */
	private static final String CAMPO_BUSQUEDA = "barrioNombre";

	
	/**
	 * Obtiene un IBarrio.
	 * 
	 * @param barrioId
	 *            Identificador del IBarrio a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del IBarrio
	 */
	public static IBarrio retrieve(final Integer barrioId,
			final PersistenceBroker broker) {
		IBarrio criterio = new IBarrio();
		criterio.setBarrioId(barrioId);
		Query query = new QueryByIdentity(criterio);
		return (IBarrio) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param barrio
	 *            Bean de objeto IBarrio
	 * @param broker
	 */
	private void actualizarReferencias(IBarrio barrio,
			PersistenceBroker broker) {
		barrio.setMunicipioIdRef(MunicipioHandler.retrieve(
				barrio.getMunicipioId(), broker));
	}

	/**
	 * Obtiene un IBarrio.
	 * 
	 * @param barrioId
	 *            Identificador del IBarrio a obtener
	 * @return bean que contiene los datos del IBarrio
	 */
	public static IBarrio retrieve(final Integer barrioId)
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
	 * Genera listado de IBarrios.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de IBarrios
	 * @throws Exception
	 */
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(IBarrio.class,
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
	 * Genera listado de IBarrios.
	 * 
	 * @param barrioNombre
	 *            nombre o parte del nombre de un IBarrio
	 * @return Collection o null listado de IBarrios
	 * @throws Exception
	 */
	public Collection getList(final String barrioNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (barrioNombre != null && barrioNombre.length() > 0) {
				criterio.addLike("upper(barrioNombre)",
						barrioNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Obtener la lista de IBarrioes a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param barrioNombre
	 *            Filtro de búsqueda.
	 * @param municipioId
	 *            Idetinficador de la municipio.
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
	public final Collection<IBarrio> getResultadosXPagina(
			final String barrioNombre, final Integer municipioId,
			final Integer filtrar, final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					IBarrio.class,
					getCriterio(barrioNombre, municipioId, filtrar,
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
	 * Obtiene la cantidad de IBarrios que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param barrioNombre
	 *            Filtro de búsqueda.
	 * @param municipioId
	 *            Identificador del objeto municipio.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String barrioNombre,
			final Integer municipioId, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(IBarrio.class,
					getCriterio(barrioNombre, municipioId, filtrar,
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
	 * IBarrio.
	 * 
	 * @param barrioNombre
	 *            Criterio de busqueda.
	 * @param municipioId
	 *            Identificador del objeto municipio.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final String barrioNombre,
			final Integer municipioId, final Integer filtrar,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		if (barrioNombre != null && barrioNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					barrioNombre.toUpperCase(Locale.getDefault()) + "*");
		}
		if (municipioId != null) {
			criterio.addEqualTo("municipioId", municipioId);
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
	 * Genera el listado de todos los IBarrios.
	 * 
	 * @return Collection o null Listao de IBarrios
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
	
	public static boolean existeIBarrio(String barrioNombre) throws Exception {
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
            QueryByCriteria query = new QueryByCriteria(IBarrio.class, criterio);
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
	
	public Collection getIBarrioXMunicipio(final Integer municipioId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (municipioId != null) {
				criterio.addEqualTo("municipioId", municipioId);
			}
			QueryByCriteria query = new QueryByCriteria(IBarrio.class, criterio);
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
				criterio.addEqualTo("carteraIbwList.carteraId", carteraId);
			}
			QueryByCriteria query = new QueryByCriteria(IBarrio.class, criterio);
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
