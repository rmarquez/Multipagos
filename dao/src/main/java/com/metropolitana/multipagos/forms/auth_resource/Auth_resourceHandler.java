/*
 * Archivo: Auth_resourceHandler.java
 * Autor: Rafael Marquez
 * Creado el 10-06-2010
 *
 */
package com.metropolitana.multipagos.forms.auth_resource;

import java.util.Collection;

import com.metropolitana.multipagos.AuthResource;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;

/**
 * @author rmarquez
 *
 */
public class Auth_resourceHandler {

    /**
     * Genera el listado de recursos
     *
     * @param criterio
     *            Criterio de filtrado de la lista
     * @return Collection o null
     * @throws Exception
     */
    private Collection getList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(AuthResource.class, criterio);
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
     * Genera el listado de los recursos
     *
     * @param filtro
     *            Nombre o parte del nombre del recurso
     * @return Collection o null
     * @throws Exception
     */
    public Collection getList(String filtro) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (filtro.length() > 0) {
                criterio.addLike("resName", filtro + "*");
            }
            return getList(criterio);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Genera el listado de todos los recursos
     *
     * @return Collection o null
     * @throws Exception
     */
    public Collection getList() throws Exception {
        try {
            return getList(new Criteria());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Recupera un recurso
     *
     * @param resId
     *            Identificador del recurso a recuperar
     * @param pb
     * @return AuthResource recurso
     */
    public static AuthResource retrieve(Integer resId, PersistenceBroker pb) {
        AuthResource criterio = new AuthResource();
        criterio.setResId(resId);
        Query query = new QueryByIdentity(criterio);
        return (AuthResource) pb.getObjectByQuery(query);
    }

    /**
     *
     * @param resId
     * @return
     * @throws Exception
     */
    public static AuthResource retrieve(Integer resId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return retrieve(resId, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
}
