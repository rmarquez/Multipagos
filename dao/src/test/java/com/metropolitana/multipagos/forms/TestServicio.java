package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.forms.helper.ServicioTestHelper;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestServicio extends PBTestBase {
		
		private ServicioTestHelper testServicioHelper;
	    private ServicioHandler servicioHandler;
	    private AuthUserTestHelper testUserHelper;

	    public void setUp() throws Exception {
	        super.setUp();
	        testServicioHelper = new ServicioTestHelper();
	        servicioHandler = new ServicioHandler();
	        testUserHelper = new AuthUserTestHelper();
	    }
	    
	    public void testInsertRetrieveBroker() throws Exception {
	        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
	        // Verificar que se guardo en la base de datos.
	        Servicio servicio = testServicioHelper.insertServicio(ServicioTestHelper.NOMBRE_SERVICIO_1, user.getUsrId());
	        assertNotNull("El servicio '" + ServicioTestHelper.NOMBRE_SERVICIO_1 + "' no se guardo.", servicio);

	        // Verificar si se recupera de la base de datos.
	        Servicio servicioRetrieved = ServicioHandler.retrieve(servicio.getServicioId());
	        assertNotNull("El servicio '" + ServicioTestHelper.NOMBRE_SERVICIO_1 + "' no existe.", servicioRetrieved);
	        assertEquals("Los nombres de servicio no coincide", ServicioTestHelper.NOMBRE_SERVICIO_1, servicioRetrieved.getServicioNombre());

	        // Verificamos si se actualizan los datos.
	        servicio.setServicioNombre(ServicioTestHelper.NOMBRE_SERVICIO_2);
	        servicioHandler.update(servicio, user.getUsrId(), Boolean.FALSE);

	        servicioRetrieved = ServicioHandler.retrieve(servicio.getServicioId());
	        assertNotNull("El servicio '" + ServicioTestHelper.NOMBRE_SERVICIO_2 + "' no existe.", servicioRetrieved);
	        assertEquals("Los nombres de servicios no coinciden", ServicioTestHelper.NOMBRE_SERVICIO_2, servicioRetrieved.getServicioNombre());
	        
	    }
	    
	    public void testGetListBroker() throws Exception {
	    	AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);

	        int contador = servicioHandler.getContador(null, null);
	        assertEquals("La cantidad de servicios regresada no es correcta", 0, contador);

	        // Insertamos los tres objetos para verificar las funciones de lista.
	        testServicioHelper.insertServicio(ServicioTestHelper.NOMBRE_SERVICIO_1, user.getUsrId());
	        testServicioHelper.insertServicio(ServicioTestHelper.NOMBRE_SERVICIO_2, user.getUsrId());
	        testServicioHelper.insertServicio(ServicioTestHelper.NOMBRE_SERVICIO_3, user.getUsrId());

	        contador = servicioHandler.getContador(ServicioTestHelper.STR_SEARCH, null);
	        assertEquals("La cantidad de servicios regresada no es correcta", 3, contador);

	        contador = servicioHandler.getContador("", null);
	        assertEquals("La cantidad de servicios regresada no es correcta", 3, contador);

	        List list = (List) servicioHandler.getServicioList();
	        assertNotNull("La lista de servicios es nula.", list);
	        assertFalse("La lista de departamentos está vacía." + list.size(), list.isEmpty());

	        list = (List) servicioHandler.getList(ServicioTestHelper.STR_SEARCH);
	        assertNotNull("La lista de servicios es nula.", list);
	        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

	        list = (List) servicioHandler.getList("");
	        assertNotNull("La lista de servicios es nula.", list);
	        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina(ServicioTestHelper.STR_SEARCH, 1, 1, 3);
	        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
	        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina(ServicioTestHelper.STR_SEARCH, 1, 2, 3);
	        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
	        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 0, list.size());

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina("", 1, 1, 3);
	        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
	        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina(null, 1, 1, 3);
	        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
	        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina(null, 1, 1, 0);
	        assertNull("La lista de resultados no es nula", list);

	        list = (List<Servicio>) servicioHandler.getResultadosXPagina(null, 1, 0, 1);
	        assertNull("La lista de resultados no es nula", list);

	        Util util = new Util();
	        assertNotNull("Existe el objeto util", util);

	    }

	    protected void cleanUpDB() throws Exception {
	        removeAll(Servicio.class);
	        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
	    }
}
