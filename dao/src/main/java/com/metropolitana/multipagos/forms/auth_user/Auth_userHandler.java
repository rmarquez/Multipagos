/*
 * Archivo: Auth_userHandler.java
 * Autor: Rafael Marquez
 * Creado el 10-06-2010
 *
 */
package com.metropolitana.multipagos.forms.auth_user;

import java.util.Collection;
import java.util.Iterator;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.AuthUserRole;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler;

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
public class Auth_userHandler {

    /**
     * Inserta un usuario
     *
     * @param authUser
     *            Usuario a insertar
     * @throws Exception
     */
    public void insert(AuthUser authUser) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            linkChilds(authUser, broker);
            broker.store(authUser);
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
     * Actualiza un usuario
     *
     * @param authUser
     *            Usuario a actualizar
     * @throws Exception
     */
    public void update(AuthUser authUser) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            linkChilds(authUser, broker);
            broker.store(authUser);
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
     * Actualiza la contraseña de un usuario
     *
     * @param usrID
     *            Identificador del usuario
     * @param usrPassWd
     *            Nueva contraseña
     * @return true si la contraseña es actualizada
     * @throws Exception
     */
    public boolean updatePassword(Integer usrID, String usrPassWd) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            AuthUser authUser = retrieve(usrID, broker);
            authUser.setUsrPassword(usrPassWd);
            broker.store(authUser);
            broker.commitTransaction();
            broker.clearCache();
            return true;
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
     * Actualiza las referencias de los hijos
     *
     * @param authUser
     *            Usuario
     * @param broker
     */
    private void linkChilds(AuthUser authUser, PersistenceBroker broker) {
        Iterator iter = authUser.getAuthUserRoleList().iterator();
        while (iter.hasNext()) {
            AuthUserRole temp = (AuthUserRole) iter.next();
            // Referencia al rol
            temp.setRolIdRef(Auth_roleHandler.retrieve(temp.getRolId(), broker));
            // Referencia al usuario
            temp.setUsrIdRef(authUser);
        }
    }

    /**
     * Genera el listado de los usuarios, el usuario administrador es excluido
     * del listado
     *
     * @param usrLogin
     *            Nombre o parte del nombre de un usuario
     * @return Collection o null
     * @throws Exception
     */
    public Collection getList(String usrLogin) throws Exception {
        PersistenceBroker broker = null;

        try {
            // 1. Get the PersistenceBroker
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            //2. Define criterio
            Criteria criterio = new Criteria();
            if (usrLogin != null && usrLogin.length() > 0) {
                criterio.addLike("usrLogin", usrLogin + "*");
            }
            // Excluir el administrador de la lista
            criterio.addNotEqualTo("usrLogin", "admin");
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
     * Genera el listado de usuarios
     *
     * @param usrLogin
     *            Nombre exacto del usuario a buscar
     * @return Collection o Null
     * @throws Exception
     */
    public Collection getListAll(String usrLogin) throws Exception {
        PersistenceBroker broker = null;

        try {
            // 1. Get the PersistenceBroker
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            //2. Define criterio
            Criteria criterio = new Criteria();
            if (usrLogin.length() > 0) {
                criterio.addEqualTo("usrLogin", usrLogin);
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
     * Recupera un usuario
     *
     * @param usrId
     *            Identificador del usuario a recuperar
     * @param broker
     * @return AuthUser Usuario
     */
    public static AuthUser retrieve(Integer usrId, PersistenceBroker broker) {
        AuthUser criterio = new AuthUser();
        criterio.setUsrId(usrId);
        Query query = new QueryByIdentity(criterio);
        return (AuthUser) broker.getObjectByQuery(query);
    }

    /**
     * Recupera un usuario
     *
     * @param usrId
     *            Identificador del usuario
     * @return AuthUser Usuario
     * @throws Exception
     */
    public static AuthUser retrieve(Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return retrieve(usrId, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Elimina un usuario
     *
     * @param usrId
     *            Identificador del usuario a eliminar
     * @throws Exception
     */
    public void remove(Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            AuthUser authUser = retrieve(usrId, broker);
            broker.delete(authUser);
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
	 * Genera el listado de todos los Usuarios.
	 * 
	 * @return Collection o null Listao de usuarios
	 * @throws Exception
	 */
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(AuthUser.class,
					criterio);
			query.addOrderBy("usrFullName", true);
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