package com.metropolitana.multipagos.forms.banco;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.Banco;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.forms.Util;
import com.metropolitana.multipagos.forms.logs.LogsHandler;
//import com.metropolitana.multipagos.forms.logs.LogsHandler;


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
public class BancoHandler {

	/** Nombre del campo para la búsqueda de bancos. */
	private static final String CAMPO_BUSQUEDA = "bancoNombre";

	/**
	 * Inserta un banco
	 * 
	 * @param banco
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void insert(Banco banco, Integer usrId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(banco);
			broker.store(LogsHandler.setLogsDelSistema(banco, fecha, usrId,
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
	 * Actualiza los datos de un banco
	 * 
	 * @param banco
	 *            bean que contiene los datos
	 * @throws Exception
	 */
	public void update(Banco banco, Integer usrId, Boolean inactivo) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			broker.beginTransaction();
			Date fecha = Calendar.getInstance().getTime();
			broker.store(banco);
			if(inactivo.booleanValue()== Boolean.FALSE) {
				broker.store(LogsHandler.setLogsDelSistema(banco, fecha, usrId,
						Integer.valueOf(2), broker));
			} else {
				broker.store(LogsHandler.setLogsDelSistema(banco, fecha, usrId,
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
	 * Obtiene un banco
	 * 
	 * @param bancoId
	 *            Identificador del banco a obtener
	 * @param broker
	 *            PersistenceBroker
	 * @return bean que contiene los datos del banco
	 */
	public static Banco retrieve(Integer bancoId, PersistenceBroker broker) {
		Banco criterio = new Banco();
		criterio.setBancoId(bancoId);
		Query query = new QueryByIdentity(criterio);
		return (Banco) broker.getObjectByQuery(query);
	}

	/**
	 * Obtiene un banco
	 * 
	 * @param bancoId
	 *            Identificador del banco a obtener
	 * @return bean que contiene los datos del banco
	 */
	public static Banco retrieve(Integer bancoId) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(bancoId, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Genera listado de bancos
	 * 
	 * @param criterio
	 *            Criterio de filtrado de la lista
	 * @return Collection o null listado de bancos
	 * @throws Exception
	 */
	private Collection getList(Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Banco.class, criterio);
			query.addOrderBy("bancoNombre", true);
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
	 * Genera listado de bancos
	 * 
	 * @param bancoNombre
	 *            nombre o parte del nombre de un banco
	 * @return Collection o null listado de bancos
	 * @throws Exception
	 */
	public Collection getList(String bancoNombre) throws Exception {
		try {
			Criteria criterio = new Criteria();
			if (bancoNombre != null && bancoNombre.length() > 0) {
				criterio.addLike("upper(bancoNombre)",
						bancoNombre.toUpperCase(Locale.getDefault()) + "*");
			}
			return getList(criterio);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Genera el listado de todos los bancos
	 * 
	 * @return Collection o null Listao de bancos
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

	/**
	 * Obtener la lista de banco a presentar en los resultados de las búsquedas
	 * por página.
	 * 
	 * @param bancoNombre
	 *            Filtro de búsqueda.
	 * @param pagina
	 *            Página.
	 * @param registrosXPagina
	 *            Cantidad de registros por página.
	 * @return Lista de bancos por página.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al recuperar la lista de
	 *             bancos.
	 */
	@SuppressWarnings("unchecked")
	public final Collection<Banco> getResultadosXPagina(
			final String bancoNombre, final Integer filtrar, final int pagina,
			final int registrosXPagina) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return Util.getResultadosXPagina(Banco.class,
					Util.getCriterio(bancoNombre, filtrar, CAMPO_BUSQUEDA),
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
	 * Obtiene la cantidad de bancos que coinciden con el filtro de búsqueda.
	 * 
	 * @param bancoNombre
	 *            Filtro de búsqueda.
	 * @return Cantidad de registro resultados.
	 * @throws Exception
	 *             Indica que ha ocurrido un error al buscar la cantidad de
	 *             resultados.
	 */
	public final int getContador(final String bancoNombre, final Integer filtrar) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(Banco.class,
					Util.getCriterio(bancoNombre, filtrar, CAMPO_BUSQUEDA));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	public static boolean existeBanco(String bancoNombre) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (bancoNombre != null && bancoNombre.length() > 0) {
				criterio.addLike("upper(bancoNombre)",
						bancoNombre.toUpperCase(Locale.getDefault()) + "*");
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
            QueryByCriteria query = new QueryByCriteria(Banco.class, criterio);
            query.addOrderBy("bancoNombre", true);
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