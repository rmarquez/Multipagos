/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitana.multipagos.forms.municipio;

import com.metropolitana.multipagos.Municipio;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.departamentos.IDepartamentosHandler;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
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

/**
 *
 * @author rmarquez
 */
public class MunicipioHandler {
    
    /**
	 * <code>MUNICIPIO_ID</code> Identificador del Municipio
	 */
	public static final String MUNICIPIO_ID = "municipioId";

	/** Nombre del campo para la búsqueda de Municipios. */
	private static final String CAMPO_BUSQUEDA = "municipioNombre";

	
	/**
	 * Obtiene un Municipio.
	 * 
	 * @param MunicipioId
	 *            Identificador del Municipio a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del Municipio
	 */
	public static Municipio retrieve(final Integer MunicipioId,
			final PersistenceBroker broker) {
		Municipio criterio = new Municipio();
		criterio.setMunicipioId(MunicipioId);
		Query query = new QueryByIdentity(criterio);
		return (Municipio) broker.getObjectByQuery(query);
	}

	/**
	 * Metodo que actualiza referencias a llaves extranjeras
	 * 
	 * @param municipio
	 *            Bean de objeto municipio
	 * @param broker
	 */
	private void actualizarReferencias(Municipio municipio,
			PersistenceBroker broker) {
		municipio.setDepartamentoIdRef(IDepartamentosHandler.retrieve(
				municipio.getDepartamentoId(), broker));
	}

	/**
	 * Obtiene un Municipio.
	 * 
	 * @param MunicipioId
	 *            Identificador del Municipio a obtener
	 * @return bean que contiene los datos del Municipio
	 */
	public static Municipio retrieve(final Integer municipioId)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(municipioId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de Municipios.
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de Municipios
	 * @throws Exception
	 */
	private Collection getMunicipioList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			criterio.addEqualTo("inactivo", false);
			QueryByCriteria query = new QueryByCriteria(Municipio.class,
					criterio);
			query.addOrderBy("municipioId", true);
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
	 * Genera listado de Municipios.
	 * 
	 * @param municipioNombre
	 *            nombre o parte del nombre de un Municipio
	 * @return Collection o null listado de Municipios
	 * @throws Exception
	 */
	public Collection getList(final String municipioNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (municipioNombre != null && municipioNombre.length() > 0) {
				criterio.addLike("upper(municipioNombre)",
						municipioNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getMunicipioList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection getList(Integer carteraId) throws Exception {
        try {
            Criteria criterios = new Criteria();
            if (carteraId != null) {
                criterios.addEqualTo("carteraIbwList.carteraId", carteraId);
            }
            return getMunicipioList(criterios);
        } catch (Exception e) {
            throw e;
        }
    }
	
	public Collection getMunicipioXCartera(final Integer carteraId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (carteraId != null) {
				criterio.addEqualTo("carteraIbwList.carteraId", carteraId);
			}
			QueryByCriteria query = new QueryByCriteria(Municipio.class, criterio);
			query.addOrderByAscending("municipioNombre");
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	public Collection getMunicipioXDepartamento(final Integer departamentoId) throws Exception {
		
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			if (departamentoId != null) {
				criterio.addEqualTo("departamentoId", departamentoId);
			}
			QueryByCriteria query = new QueryByCriteria(Municipio.class, criterio);
			query.addOrderByAscending("municipioNombre");
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
	 * Obtener la lista de municipios a presentar en los resultados de las
	 * búsquedas por página.
	 * 
	 * @param municipioNombre
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
	public final Collection<Municipio> getResultadosXPagina(
			final String municipioNombre, final Integer departamentoId,
			final Integer filtrar, final int pagina, final int registrosXPagina)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(
					Municipio.class,
					getCriterio(municipioNombre, departamentoId, filtrar,
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
	 * Obtiene la cantidad de Municipios que coinciden con el filtro de
	 * búsqueda.
	 * 
	 * @param municipioNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String municipioNombre,
			final Integer departamentoId, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Municipio.class,
					getCriterio(municipioNombre, departamentoId, filtrar,
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
	 * municipio.
	 * 
	 * @param municipioNombre
	 *            Criterio de busqueda.
	 * @param departamentoId
	 *            Identificador del departamento.
	 * @param campoBusqueda
	 *            Nombre del campo en el cual se debe buscar.
	 * @return Criteria Criterio para la búsqueda.
	 */
	public static final Criteria getCriterio(final String municipioNombre,
			final Integer departamentoId, final Integer filtrar,
			final String campoBusqueda) {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("departamentoIdRef.inactivo", Boolean.FALSE);
		if (municipioNombre != null && municipioNombre.length() > 0) {
			criterio.addLike("upper(" + campoBusqueda + ")",
					municipioNombre.toUpperCase(Locale.getDefault()) + "*");
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
	 * Genera el listado de todos los Municipios.
	 * 
	 * @return Collection o null Listao de Municipios
	 * @throws Exception
	 */
	public Collection getMunicipioList() throws Exception {
		Criteria criterio = new Criteria();
		criterio.addEqualTo("inactivo", Boolean.FALSE);
		try {
			return getMunicipioList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean existeMunicipio(String municipioNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (municipioNombre != null && municipioNombre.length() > 0) {
				criterio.addLike("upper(municipioNombre)",
						municipioNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(Municipio.class, criterio);
            query.addOrderBy("municipioNombre", true);
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
