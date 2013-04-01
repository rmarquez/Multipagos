package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthRole;
import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.AuthUserRole;
import com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestAuthUser extends PBTestBase {

	private AuthUserTestHelper testAuthUserHelper;
    private Auth_userHandler userHandler;

    public void setUp() throws Exception {
        super.setUp();
        testAuthUserHelper = new AuthUserTestHelper();
        userHandler = new Auth_userHandler();
    }

    public void testInsertRetrieveBroker() throws Exception {

        // Verificar que se guarda en la base de datos.
        AuthUser usuario = testAuthUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1,
                                                             AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        assertNotNull("El usuario '" + AuthUserTestHelper.USER_LOGIN_1 + "' no se guardo.", usuario);
        assertEquals("El usuario " + AuthUserTestHelper.USER_LOGIN_1 + "no se guardo",
                1, AuthHandler.getList(AuthUserTestHelper.USER_LOGIN_1).size());

        MenuUser menuresources = new MenuUser(usuario.getUsrId());
        assertFalse("No existe el recurso \"factura_elaborar\" en el Menu del usuario", menuresources.getResources().contains(AuthUserTestHelper.USER_RESKEY_1));
        assertFalse("No existe el recurso \"carga_ruta_elaborar\" en el Menu del usuario", menuresources.getResources().contains(AuthUserTestHelper.USER_RESKEY_2));
        assertFalse("No existe el recurso \"cata\" en el Menu del usuario", menuresources.getResources().contains("cata"));

        //Revisando que el usuario no tenga recursos asignados
        /*boolean autorizado = AuthHandler.autorizado(usuario.getUsrId(), AuthUserTestHelper.USER_RESKEY_3);
        assertFalse("El recurso" + AuthUserTestHelper.USER_RESKEY_3 +
                "no fue agregado en el rol del usuario", autorizado);*/

        // Insertando los roles
        AuthRole rol = Auth_roleHandler.retrieve(Integer.valueOf(2));
        assertNotNull("El rol de Adimistrador no existe", rol);
        AuthUserRole roles = testAuthUserHelper.insertRoles(usuario, rol);
        assertNotNull("El rol del usuario '" + AuthUserTestHelper.USER_LOGIN_1 + "' no se guardo.", roles);

        // Verificar si se recupera de la base de datos.
        AuthUser userRetrieve =  Auth_userHandler.retrieve(usuario.getUsrId());
        assertNotNull("El Ususario " + AuthUserTestHelper.USER_LOGIN_1 + " no existe.", userRetrieve);
        assertEquals("Los nombres de usuario no coinciden. ", AuthUserTestHelper.USER_LOGIN_1, userRetrieve.getUsrLogin());
        assertNotNull("El detalle de roles del usuario no existe.", userRetrieve.getAuthUserRoleList());
        assertEquals("El detalle de las comisiones no contiene la cantidad correcta.", 1, userRetrieve.getAuthUserRoleList().size());

        // Verificamos si se actualizan los datos.
        usuario.setUsrLogin(AuthUserTestHelper.USER_LOGIN_2);
        usuario.setUsrEmail("correo@agssa.net");
        userHandler.update(usuario);
        userRetrieve = Auth_userHandler.retrieve(usuario.getUsrId());
        assertNotNull("El login del usuario '" + AuthUserTestHelper.USER_LOGIN_2 + "' no existe.", userRetrieve);
        assertEquals("Los nombres de usuario no coinciden", AuthUserTestHelper.USER_LOGIN_2, userRetrieve.getUsrLogin());

        boolean actualizar = userHandler.updatePassword(usuario.getUsrId(), AuthUserTestHelper.USER_PASS_2);
        userRetrieve = Auth_userHandler.retrieve(usuario.getUsrId());
        assertTrue("No se pudo actualizar la contraseña", actualizar);
        assertEquals("El password de usuario no coincide", AuthUserTestHelper.USER_PASS_2, userRetrieve.getUsrPassword());

        //Verificamos que regrese los usuarios actuales
        assertEquals("El usuario " + AuthUserTestHelper.USER_LOGIN_1 + "no se guardo",
                2, AuthHandler.getList("").size());

        // Verificar los getList
        List list = (List)userHandler.getList("");
        assertNotNull("La lista de usuarios es nula.", list);
        assertFalse("La lista de usuarios esta vacía." + list.size(), list.isEmpty());

        list = (List)userHandler.getList(null);
        assertNotNull("La lista de usuarios es nula.", list);
        assertFalse("La lista de usuarios esta vacía." + list.size(), list.isEmpty());

        list = (List)userHandler.getList(usuario.getUsrLogin());
        assertNotNull("La lista de usuarios es nula.", list);
        assertFalse("La lista de usuarios esta vacía." + list.size(), list.isEmpty());

        list = (List)userHandler.getListAll(usuario.getUsrLogin());
        assertNotNull("La lista de usuarios es nula.", list);
        assertFalse("La lista de usuarios esta vacía." + list.size(), list.isEmpty());

        list = (List)userHandler.getListAll("");
        assertNotNull("La lista de usuarios es nula.", list);
        assertFalse("La lista de usuarios esta vacía." + list.size(), list.isEmpty());

        //Verificamos los recursos del menú del usuario
        menuresources = new MenuUser(userRetrieve.getUsrId());
        assertTrue("No existe el recurso \"cata\" en el Menu del usuario", menuresources.getResources().contains("cata"));

        // Verificando si se esta eliminado.
        userHandler.remove(usuario.getUsrId());
        userRetrieve = Auth_userHandler.retrieve(usuario.getUsrId());
        assertNull("El Usuario '" + AuthUserTestHelper.USER_LOGIN_2 + "' no se eliminó.", userRetrieve);
    }

    protected void cleanUpDB() throws Exception {
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }
}
