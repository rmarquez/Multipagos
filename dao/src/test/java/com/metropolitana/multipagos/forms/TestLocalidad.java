package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.forms.helper.LocalidadTestHelper;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestLocalidad extends PBTestBase {
	
	private DepartamentoTestHelper testDepHelper;
	private LocalidadTestHelper testLocalidadHelper;
	private LocalidadHandler handlerLocalidad;
	private AuthUserTestHelper testUserHelper;
	
	public void setUp() throws Exception {
        super.setUp();
        testDepHelper = new DepartamentoTestHelper();
        testLocalidadHelper = new LocalidadTestHelper();
        handlerLocalidad = new LocalidadHandler();
        testUserHelper = new AuthUserTestHelper();
    }
	
	public void testInsertRetrieveBroker() throws Exception {
        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        Localidad localidad = testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_1, departamento, user.getUsrId());
        assertNotNull("La Localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_1 + "' no se guardo.", localidad);
        
     // Verificar si se recupera de la base de datos.
        Localidad retrieved = LocalidadHandler.retrieve(localidad.getLocalidadId());
        assertNotNull("La localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_1 + "' no existe.", retrieved);
        assertEquals("Los nombres de localidades no coincide", LocalidadTestHelper.NOMBRE_LOCALIDAD_1, retrieved.getLocalidadNombre());

        // Verificamos si se actualizan los datos.
        localidad.setLocalidadNombre(LocalidadTestHelper.NOMBRE_LOCALIDAD_2);
        handlerLocalidad.update(localidad, user.getUsrId(), Boolean.FALSE);

        retrieved = LocalidadHandler.retrieve(localidad.getLocalidadId());
        assertNotNull("La localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_2 + "' no existe.", retrieved);
        assertEquals("Los nombres de localidades no coincide", LocalidadTestHelper.NOMBRE_LOCALIDAD_2, retrieved.getLocalidadNombre());
        
    }
	
	public void testGetListBroker() throws Exception {
    	AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);

        int contador = handlerLocalidad.getContador(null, null, null);
        assertEquals("La cantidad de localidades regresada no es correcta", 0, contador);
        
        // Verificar que se guardo en la base de datos.
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        // Insertamos los tres objetos para verificar las funciones de lista.
        testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_1, departamento, user.getUsrId());
        testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_2, departamento, user.getUsrId());
        testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_3, departamento, user.getUsrId());

        contador = handlerLocalidad.getContador(LocalidadTestHelper.STR_SEARCH, null, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 3, contador);

        contador = handlerLocalidad.getContador("", null, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 3, contador);

        List list = (List) handlerLocalidad.getLocalidadList();
        assertNotNull("La lista de departamentos es nula.", list);
        assertFalse("La lista de departamentos está vacía." + list.size(), list.isEmpty());

        list = (List) handlerLocalidad.getList(LocalidadTestHelper.STR_SEARCH);
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List) handlerLocalidad.getList("");
        assertNotNull("La lista de departamentos es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina(LocalidadTestHelper.STR_SEARCH, departamento.getDepartamentoId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina(LocalidadTestHelper.STR_SEARCH, departamento.getDepartamentoId(), 1, 2, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 0, list.size());

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina("", departamento.getDepartamentoId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina(null, departamento.getDepartamentoId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina(null, departamento.getDepartamentoId(), 1, 1, 0);
        assertNull("La lista de resultados no es nula", list);

        list = (List<Localidad>) handlerLocalidad.getResultadosXPagina(null, departamento.getDepartamentoId(), 1, 0, 1);
        assertNull("La lista de resultados no es nula", list);

        Util util = new Util();
        assertNotNull("Existe el objeto util", util);

    }
	
	protected void cleanUpDB() throws Exception {
		removeAll(Localidad.class);
		removeAll(Departamento.class);
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }

}
