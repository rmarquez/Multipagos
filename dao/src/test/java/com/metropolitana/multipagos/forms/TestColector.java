package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.ColectorTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestColector extends PBTestBase {
	
	private ColectorTestHelper testColectorHelper;
	private ColectorHandler handlerColector;
	private AuthUserTestHelper testUserHelper;
	
	public void setUp() throws Exception {
        super.setUp();
        testColectorHelper = new ColectorTestHelper(); 
        handlerColector = new ColectorHandler();
        testUserHelper = new AuthUserTestHelper();
	}
	
	public void testInsertRetrieveBroker() throws Exception {
        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        Colector colector = testColectorHelper.insertColector(Integer.valueOf(1), ColectorTestHelper.NOMBRE_COLECTOR_1, ColectorTestHelper.APELLIDO_COLECTOR_1, user.getUsrId());
        assertNotNull("El colector '" + ColectorTestHelper.NOMBRE_COLECTOR_1 + "' no se guardo.", colector);

        // Verificar si se recupera de la base de datos.
        Colector colectorRetrieved = ColectorHandler.retrieve(colector.getColectorId());
        assertNotNull("El colector '" + ColectorTestHelper.NOMBRE_COLECTOR_1 + "' no existe.", colectorRetrieved);
        assertEquals("Los nombres de colectores no coincide", ColectorTestHelper.NOMBRE_COLECTOR_1, colectorRetrieved.getPrimerNombre());

        // Verificamos si se actualizan los datos.
        colector.setPrimerNombre(ColectorTestHelper.NOMBRE_COLECTOR_2);
        handlerColector.update(colector, user.getUsrId(), Boolean.FALSE);

        colectorRetrieved = ColectorHandler.retrieve(colector.getColectorId());
        assertNotNull("El colector '" + ColectorTestHelper.NOMBRE_COLECTOR_2 + "' no existe.", colectorRetrieved);
        assertEquals("Los nombres de colector no coinciden", ColectorTestHelper.NOMBRE_COLECTOR_2, colectorRetrieved.getPrimerNombre());
        
    }
	
	public void testGetListBroker() throws Exception {
    	AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);

        int contador = handlerColector.getContador(null, null,null, null);
        assertEquals("La cantidad de colectores regresada no es correcta", 0, contador);

        // Insertamos los tres objetos para verificar las funciones de lista.
        testColectorHelper.insertColector(Integer.valueOf(1), ColectorTestHelper.NOMBRE_COLECTOR_1, ColectorTestHelper.APELLIDO_COLECTOR_1, user.getUsrId());
        testColectorHelper.insertColector(Integer.valueOf(2), ColectorTestHelper.NOMBRE_COLECTOR_2, ColectorTestHelper.APELLIDO_COLECTOR_2, user.getUsrId());
        testColectorHelper.insertColector(Integer.valueOf(3), ColectorTestHelper.NOMBRE_COLECTOR_3, ColectorTestHelper.APELLIDO_COLECTOR_3, user.getUsrId());

        contador = handlerColector.getContador(ColectorTestHelper.STR_SEARCH, null,null, null);
        assertEquals("La cantidad de colectores regresada no es correcta", 3, contador);

        contador = handlerColector.getContador("", "", null, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 3, contador);

        List list = (List) handlerColector.getList();
        assertNotNull("La lista de colectores es nula.", list);
        assertFalse("La lista de colectores está vacía." + list.size(), list.isEmpty());

        list = (List) handlerColector.getList(ColectorTestHelper.STR_SEARCH);
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List) handlerColector.getList("");
        assertNotNull("La lista de colector es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List) handlerColector.getList(null);
        assertNotNull("La lista de colector es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List<Colector>) handlerColector.getResultadosXPagina(ColectorTestHelper.STR_SEARCH, "", "", 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Colector>) handlerColector.getResultadosXPagina(ColectorTestHelper.STR_SEARCH, "", "", 1, 2, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 0, list.size());

        list = (List<Colector>) handlerColector.getResultadosXPagina("", "", "", 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Colector>) handlerColector.getResultadosXPagina(null, "", "", 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Colector>) handlerColector.getResultadosXPagina(null, "", "", 1, 1, 0);
        assertNull("La lista de resultados no es nula", list);

        list = (List<Colector>) handlerColector.getResultadosXPagina(null, "", "", 1, 0, 1);
        assertNull("La lista de resultados no es nula", list);

        Util util = new Util();
        assertNotNull("Existe el objeto util", util);

    }

    protected void cleanUpDB() throws Exception {
        removeAll(Colector.class);
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }

}
