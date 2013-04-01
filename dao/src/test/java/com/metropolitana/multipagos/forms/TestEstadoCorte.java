package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.forms.helper.EstadoCorteTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestEstadoCorte extends PBTestBase {
	
	private EstadoCorteTestHelper testEstadoHelper;
	private EstadoCorteHandler estadoHandler;
	private AuthUserTestHelper testUserHelper;
	
	public void setUp() throws Exception {
        super.setUp();
        
        testEstadoHelper = new EstadoCorteTestHelper();
        estadoHandler = new EstadoCorteHandler();
        testUserHelper = new AuthUserTestHelper();
	}
	
	public void testInsertRetrieveBroker() throws Exception {
        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        EstadoCorte estado = testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_1, user.getUsrId());
        assertNotNull("El estado '" + EstadoCorteTestHelper.NOMBRE_ESTADO_1 + "' no se guardo.", estado);

        // Verificar si se recupera de la base de datos.
        EstadoCorte retrieved = EstadoCorteHandler.retrieve(estado.getEstadoId());
        assertNotNull("El estado '" + EstadoCorteTestHelper.NOMBRE_ESTADO_1 + "' no existe.", retrieved);
        assertEquals("Los nombres de estados no coincide", EstadoCorteTestHelper.NOMBRE_ESTADO_1, retrieved.getEstadoNombre());

        // Verificamos si se actualizan los datos.
        estado.setEstadoNombre(EstadoCorteTestHelper.NOMBRE_ESTADO_2);
        estadoHandler.update(estado, user.getUsrId(), Boolean.FALSE);

        retrieved = EstadoCorteHandler.retrieve(estado.getEstadoId());
        assertNotNull("El estado '" + EstadoCorteTestHelper.NOMBRE_ESTADO_2 + "' no existe.", retrieved);
        assertEquals("Los nombres de estados no coinciden", EstadoCorteTestHelper.NOMBRE_ESTADO_2, retrieved.getEstadoNombre());
        
    }
	
	public void testGetListBroker() throws Exception {
    	AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);

        int contador = estadoHandler.getContador(null, null);
        assertEquals("La cantidad de depaartamentos regresada no es correcta", 0, contador);

        // Insertamos los tres objetos para verificar las funciones de lista.
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_1, user.getUsrId());
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_2, user.getUsrId());
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_3, user.getUsrId());
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_4, user.getUsrId());
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_5, user.getUsrId());
        testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_6, user.getUsrId());

        contador = estadoHandler.getContador(EstadoCorteTestHelper.STR_SEARCH, null);
        assertEquals("La cantidad de estados regresada no es correcta", 6, contador);

        contador = estadoHandler.getContador("", null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 6, contador);

        List list = (List) estadoHandler.getList();
        assertNotNull("La lista de estados es nula.", list);
        assertFalse("La lista de estados está vacía." + list.size(), list.isEmpty());

        list = (List) estadoHandler.getList(EstadoCorteTestHelper.STR_SEARCH);
        assertNotNull("La lista de estados es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List) estadoHandler.getList("");
        assertNotNull("La lista de estados es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List) estadoHandler.getList(null);
        assertNotNull("La lista de estados es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 6, list.size());

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina(EstadoCorteTestHelper.STR_SEARCH, 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina(EstadoCorteTestHelper.STR_SEARCH, 1, 2, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina("", 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina(null, 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina(null, 1, 1, 0);
        assertNull("La lista de resultados no es nula", list);

        list = (List<EstadoCorte>) estadoHandler.getResultadosXPagina(null, 1, 0, 1);
        assertNull("La lista de resultados no es nula", list);

        Util util = new Util();
        assertNotNull("Existe el objeto util", util);

    }

    protected void cleanUpDB() throws Exception {
        removeAll(EstadoCorte.class);
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }

}
