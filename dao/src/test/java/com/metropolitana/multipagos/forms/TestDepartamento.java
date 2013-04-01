package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestDepartamento extends PBTestBase {

    private DepartamentoTestHelper testDepHelper;
    private DepartamentosHandler depHandler;
    private AuthUserTestHelper testUserHelper;

    public void setUp() throws Exception {
        super.setUp();
        testDepHelper = new DepartamentoTestHelper();
        depHandler = new DepartamentosHandler();
        testUserHelper = new AuthUserTestHelper();
    }

    public void testInsertRetrieveBroker() throws Exception {
        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        // Verificar si se recupera de la base de datos.
        Departamento depRetrieved = DepartamentosHandler.retrieve(departamento.getDepartamentoId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no existe.", depRetrieved);
        assertEquals("Los nombres de departamento no coincide", DepartamentoTestHelper.NOMBRE_DEP_1, depRetrieved.getDepartamentoNombre());

        // Verificamos si se actualizan los datos.
        departamento.setDepartamentoNombre(DepartamentoTestHelper.NOMBRE_DEP_2);
        depHandler.update(departamento, user.getUsrId(), Boolean.FALSE);

        depRetrieved = DepartamentosHandler.retrieve(departamento.getDepartamentoId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_2 + "' no existe.", depRetrieved);
        assertEquals("Los nombres de departamento no coinciden", DepartamentoTestHelper.NOMBRE_DEP_2, depRetrieved.getDepartamentoNombre());
        
    }

    public void testGetListBroker() throws Exception {
    	AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);

        int contador = depHandler.getContador(null, null);
        assertEquals("La cantidad de depaartamentos regresada no es correcta", 0, contador);

        // Insertamos los tres objetos para verificar las funciones de lista.
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_2, user.getUsrId());
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_3, user.getUsrId());
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_4, user.getUsrId());
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_5, user.getUsrId());
        testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_6, user.getUsrId());

        contador = depHandler.getContador(DepartamentoTestHelper.STR_SEARCH, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 6, contador);

        contador = depHandler.getContador("", null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 6, contador);

        List list = (List) depHandler.getList();
        assertNotNull("La lista de departamentos es nula.", list);
        assertFalse("La lista de departamentos está vacía." + list.size(), list.isEmpty());

        list = (List) depHandler.getList(DepartamentoTestHelper.STR_SEARCH);
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List) depHandler.getList("");
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List) depHandler.getList(null);
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List<Departamento>) depHandler.getResultadosXPagina(DepartamentoTestHelper.STR_SEARCH, 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Departamento>) depHandler.getResultadosXPagina(DepartamentoTestHelper.STR_SEARCH, 1, 2, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Departamento>) depHandler.getResultadosXPagina("", 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Departamento>) depHandler.getResultadosXPagina(null, 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Departamento>) depHandler.getResultadosXPagina(null, 1, 1, 0);
        assertNull("La lista de resultados no es nula", list);

        list = (List<Departamento>) depHandler.getResultadosXPagina(null, 1, 0, 1);
        assertNull("La lista de resultados no es nula", list);

        Util util = new Util();
        assertNotNull("Existe el objeto util", util);

    }

    protected void cleanUpDB() throws Exception {
        removeAll(Departamento.class);
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }
}
