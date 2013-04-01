/*
 * Archivo: Auth_resourceHandler.java
 * Autor: Rafael Marquez
 * Creado el 10-06-2010
 *
 */
package com.metropolitana.multipagos.forms.auth_role;

import java.util.Collection;
import java.util.Iterator;

import com.metropolitana.multipagos.AuthPermission;
import com.metropolitana.multipagos.AuthRole;
import com.metropolitana.multipagos.forms.auth_resource.Auth_resourceHandler;

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
public class Auth_roleHandler {

    /**
     * Inserta un rol
     *
     * @param authRole
     *            Rol a insertar
     * @throws Exception
     */
    public void insert(AuthRole authRole) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            linkChilds(authRole, broker);
            broker.store(authRole);
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
     * Actualiza un rol
     *
     * @param authRole
     *            Rol a actualizar
     * @throws Exception
     */
    public void update(AuthRole authRole) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            linkChilds(authRole, broker);
            broker.store(authRole);
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
     * Actualiza los hijos de los roles
     *
     * @param authRole
     *            Rol
     * @param broker
     */
    private void linkChilds(AuthRole authRole, PersistenceBroker broker) {
        // recorre el listado de permisos que tiene un rol
        Iterator iterPermission = authRole.getAuthPermissionList().iterator();
        while (iterPermission.hasNext()) {
            AuthPermission temp = (AuthPermission) iterPermission.next();
            // Actualiza referencia al recurso
            temp.setResIdRef(Auth_resourceHandler.retrieve(temp.getResId(), broker));
            // Actualiza referencia al rol
            temp.setRolIdRef(authRole);
        }
    }

    /**
     * recupera un rol
     *
     * @param rolId
     *            Identificador del rol
     * @param broker
     * @return AuthRole Rol
     */
    public static AuthRole retrieve(Integer rolId, PersistenceBroker broker) {
        AuthRole criterio = new AuthRole();
        criterio.setRolId(rolId);
        Query query = new QueryByIdentity(criterio);
        return (AuthRole) broker.getObjectByQuery(query);
    }

    /**
     * Recupera un rol
     *
     * @param rolId
     *            Identificador del rol
     * @return AuthRole Rol
     * @throws Exception
     */
    public static AuthRole retrieve(Integer rolId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return retrieve(rolId, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Elimina un rol
     *
     * @param rolId
     *            Identificador del rol
     * @throws Exception
     */
    public void remove(Integer rolId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            AuthRole authRole = retrieve(rolId, broker);
            broker.delete(authRole);
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
     * Genera el listado de los roles. El listado excluye el rol principal del
     * administrador
     *
     * @param rolName
     *            Nombre o parte del nombre del rol
     * @return Collection o null
     * @throws Exception
     */
    public Collection getList(String rolName) throws Exception {
        PersistenceBroker broker = null;

        try {
            // 1. Get the PersistenceBroker
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            //2. Define criterio
            Criteria criterio = new Criteria();
            if (rolName != null && rolName.length() > 0) {
                criterio.addLike("rolName", rolName + "*");
            }
            // Eliminar el rol del administrador de la lista
            criterio.addNotEqualTo("rolName", "admin");
            // 3. Define query
            QueryByCriteria query = new QueryByCriteria(AuthRole.class, criterio);
            query.addOrderBy("rolName", true);
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

}