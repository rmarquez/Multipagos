package com.metropolitana.multipagos.forms;

import java.util.List;


import com.metropolitana.multipagos.AuthRole;

import com.metropolitana.multipagos.forms.auth_resource.Auth_resourceHandler;
import com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler;
import com.metropolitana.multipagos.forms.helper.AuthRoleTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestAuthRole extends PBTestBase {

    private AuthRoleTestHelper authRoleTestHelper;
    private Auth_roleHandler authRoleHandler;
    private Auth_resourceHandler authResourceHandler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        authRoleTestHelper = new AuthRoleTestHelper();
        authRoleHandler = new Auth_roleHandler();
        authResourceHandler = new Auth_resourceHandler();
    }

    public void testInsertRetrieveBroker() throws Exception {
        // Verificar que se guardo en la base de datos.
        AuthRole authRole = authRoleTestHelper.insertAuthRole(AuthRoleTestHelper.ROLE_NAME1, Boolean.TRUE, (List)authResourceHandler.getList());
        assertNotNull("AuthRole '" + AuthRoleTestHelper.ROLE_NAME1 + "' no se guardo.", authRole);

        // Verificar si se recupera de la base de datos.
        AuthRole authRoleRetrieved = Auth_roleHandler.retrieve(authRole.getRolId());
        assertNotNull("AuthRole '" + AuthRoleTestHelper.ROLE_NAME1 + "' no existe.", authRoleRetrieved);
        assertEquals("Los nombres de AuthRole no coincide", AuthRoleTestHelper.ROLE_NAME1, authRoleRetrieved.getRolName());

        // Verificamos si se actualizan los datos.
        authRole.setRolName(AuthRoleTestHelper.ROLE_NAME2);
        authRoleHandler.update(authRole);

        authRoleRetrieved = Auth_roleHandler.retrieve(authRole.getRolId());
        assertNotNull("AuthRole '" + AuthRoleTestHelper.ROLE_NAME2 + "' no existe.", authRoleRetrieved);
        assertEquals("Los nombres de AuthRoles no coinciden", AuthRoleTestHelper.ROLE_NAME2, authRoleRetrieved.getRolName());

        // Verificando si se esta eliminado.
        authRoleHandler.remove(authRole.getRolId());
        authRoleRetrieved = Auth_roleHandler.retrieve(authRole.getRolId());
        assertNull("AuthRole '" + AuthRoleTestHelper.ROLE_NAME2 + "' no se eliminó.", authRoleRetrieved);
    }

    public void testGetListBroker() throws Exception {
        // Insertamos los tres objetos para verificar las funciones de lista.
        authRoleTestHelper.insertAuthRole(AuthRoleTestHelper.ROLE_NAME1, Boolean.TRUE, (List)authResourceHandler.getList());
        authRoleTestHelper.insertAuthRole(AuthRoleTestHelper.ROLE_NAME2, Boolean.FALSE, (List)authResourceHandler.getList());
        authRoleTestHelper.insertAuthRole(AuthRoleTestHelper.ROLE_NAME3, Boolean.TRUE, (List)authResourceHandler.getList());

        List list = (List) authRoleHandler.getList(AuthRoleTestHelper.ROLE_NAME1);
        assertNotNull("La lista de AuthRoles es nula.", list);
        assertFalse("La lista de AuthRoles está vacía." + list.size(), list.isEmpty());

        list = (List) authRoleHandler.getList(AuthRoleTestHelper.STR_SEARCH);
        assertNotNull("La lista de AuthRoles es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List) authRoleHandler.getList("");
        assertNotNull("La lista de AuthRoles es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 4, list.size());

        list = (List) authRoleHandler.getList(null);
        assertNotNull("La lista de AuthRoles es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 4, list.size());
    }

    @Override
    protected void cleanUpDB() throws Exception {
        removeAll(AuthRole.class, AuthRoleTestHelper.REMOVE_CRITERIO);
    }
}
