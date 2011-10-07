package com.metropolitana.multipagos.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.AuthUser;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * @author rmarquez
 *
 */
public class AuthHandler {

    /**
     * Lista de usuarios.
     *
     * @param usrLogin
     *            Filtro para el nombre de acceso de un usuario
     * @return Lista(Collection) de usuarios
     * @throws Exception
     */
    public static Collection getList(final String usrLogin) throws Exception {
        PersistenceBroker broker = null;

        try {
            // 1. Get the PersistenceBroker
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            //2. Define criterio
            Criteria criterio = new Criteria();
            if (usrLogin.length() > 0) {
                criterio.addLike("usrLogin", usrLogin + "*");
            }
            // 3. Define query
            QueryByCriteria query = new QueryByCriteria(AuthUser.class, criterio);
            query.addOrderBy("usrLogin", true);
            // 4. Execute Query
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
     * Determinar si un usuario esta autorizado a utilizar un recurso.<br>
     *
     * @param usrId
     *            Identificador del usuario.
     * @param resKey
     *            Recurso a verificar.
     * @return true si el usuario puede acceder al recurso, false en caso
     *         contrato.
     * @throws Exception
     *             Exception
     */
    public static boolean autorizado(final Integer usrId, final String resKey) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            // 1. Clear cache
            broker.clearCache();
            // 2. Define query
            Criteria crit = new Criteria();
            crit.addEqualTo("usrId", usrId);
            crit.addEqualTo("authUserRoleList.rolIdRef.authPermissionList.resIdRef.resKey", resKey);
            QueryByCriteria q = new QueryByCriteria(AuthUser.class, crit);
            // 3. Execute Query
            return !broker.getCollectionByQuery(q).isEmpty();
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Obtener una lista de los usuarios a los que enviaremos correos
     *
     * @return correos de los usuarios con la opcion de notificar ofertas
     */
    public static List getListCorreos(String tipoNotificacion) throws Exception {
    	PersistenceBroker broker = null;
    	List<Object> lista = new ArrayList<Object>();
    	try {
    		broker = PersistenceBrokerFactory.defaultPersistenceBroker();

    		Criteria crit = new Criteria();
    		if (tipoNotificacion.equals("ofertascreadas")) {
    			crit.addEqualTo("usrNotificarOfertas", Boolean.TRUE);
    		}
    		ReportQueryByCriteria query = new ReportQueryByCriteria(AuthUser.class, crit);
    		query.setAttributes(new String[]{"usrEmail"});
    		for (Iterator iter = broker.getReportQueryIteratorByQuery(query); iter.hasNext();) {
                Object[] item = (Object[]) iter.next();
	            lista.add(item[0]);
    		}
    		return lista;
    	} catch (Exception e) {
    		throw e;
    	} finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
}
