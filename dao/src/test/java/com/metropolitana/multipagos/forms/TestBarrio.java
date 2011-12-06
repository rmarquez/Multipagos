package com.metropolitana.multipagos.forms;

import java.util.List;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.BarrioTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.forms.helper.LocalidadTestHelper;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestBarrio extends PBTestBase {
	
	private DepartamentoTestHelper testDepHelper;
	private LocalidadTestHelper testLocalidadHelper;
	private BarrioTestHelper testBarrioHelper;
	private BarrioHandler handlerBarrio;
	private AuthUserTestHelper testUserHelper;
	
	public void setUp() throws Exception {
        super.setUp();
        testDepHelper = new DepartamentoTestHelper();
        testBarrioHelper = new BarrioTestHelper();
        testLocalidadHelper = new LocalidadTestHelper();
        handlerBarrio = new BarrioHandler();
        testUserHelper = new AuthUserTestHelper();
    }
	
	public void testInsertRetrieveBroker() throws Exception {
        AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        Localidad localidad = testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_1, departamento, user.getUsrId());
        assertNotNull("La Localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_1 + "' no se guardo.", localidad);
        
        Barrio barrio = testBarrioHelper.insertBarrio(BarrioTestHelper.NOMBRE_BARRIO_1, localidad, user.getUsrId());
        assertNotNull("El Barrio '" + BarrioTestHelper.NOMBRE_BARRIO_1 + "' no se guardo.", barrio);
        
     // Verificar si se recupera de la base de datos.
        Barrio retrieved = BarrioHandler.retrieve(barrio.getBarrioId());
        assertNotNull("El Barrio '" + BarrioTestHelper.NOMBRE_BARRIO_1 + "' no existe.", retrieved);
        assertEquals("Los nombres de barrios no coincide", BarrioTestHelper.NOMBRE_BARRIO_1, retrieved.getBarrioNombre());

        // Verificamos si se actualizan los datos.
        barrio.setBarrioNombre(BarrioTestHelper.NOMBRE_BARRIO_2);
        handlerBarrio.update(barrio, user.getUsrId(), Boolean.FALSE);

        retrieved = BarrioHandler.retrieve(barrio.getBarrioId());
        assertNotNull("El Barrio '" + BarrioTestHelper.NOMBRE_BARRIO_2 + "' no existe.", retrieved);
        assertEquals("Los nombres de barrios no coincide", BarrioTestHelper.NOMBRE_BARRIO_2, retrieved.getBarrioNombre());
        
    }
	
	public void testGetListBroker() throws Exception {
		AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_CARGO_1, AuthUserTestHelper.USER_PASS_1);
        // Verificar que se guardo en la base de datos.
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        Localidad localidad = testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_1, departamento, user.getUsrId());
        assertNotNull("La Localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_1 + "' no se guardo.", localidad);
        
        int contador = handlerBarrio.getContador(null, null, null);
        assertEquals("La cantidad de localidades regresada no es correcta", 0, contador);
        
        // Insertamos los tres objetos para verificar las funciones de lista.
        testBarrioHelper.insertBarrio(BarrioTestHelper.NOMBRE_BARRIO_1, localidad, user.getUsrId());
        testBarrioHelper.insertBarrio(BarrioTestHelper.NOMBRE_BARRIO_2, localidad, user.getUsrId());
        testBarrioHelper.insertBarrio(BarrioTestHelper.NOMBRE_BARRIO_3, localidad, user.getUsrId());

        contador = handlerBarrio.getContador(BarrioTestHelper.STR_SEARCH, null, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 3, contador);

        contador = handlerBarrio.getContador("", null, null);
        assertEquals("La cantidad de departamentos regresada no es correcta", 3, contador);

        List list = (List) handlerBarrio.getList();
        assertNotNull("La lista de barrios es nula.", list);
        assertFalse("La lista de barrios está vacía." + list.size(), list.isEmpty());

        list = (List) handlerBarrio.getList(BarrioTestHelper.STR_SEARCH);
        assertNotNull("La lista de barrios es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List) handlerBarrio.getList("");
        assertNotNull("La lista de barrios es nula.", list);
        assertEquals("La cantidad de elementos no es correcta.", 3, list.size());

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina(BarrioTestHelper.STR_SEARCH, localidad.getLocalidadId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina(BarrioTestHelper.STR_SEARCH, localidad.getLocalidadId(), 1, 2, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 0, list.size());

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina("", localidad.getLocalidadId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina(null, localidad.getLocalidadId(), 1, 1, 3);
        assertNotNull("La lista de resultados para la pagina 1 es nula", list);
        assertEquals("La lista de resultados para la pagina 1 es incorrecta", 3, list.size());

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina(null, localidad.getLocalidadId(), 1, 1, 0);
        assertNull("La lista de resultados no es nula", list);

        list = (List<Barrio>) handlerBarrio.getResultadosXPagina(null, localidad.getLocalidadId(), 1, 0, 1);
        assertNull("La lista de resultados no es nula", list);

        Util util = new Util();
        assertNotNull("Existe el objeto util", util);

    }
	
	protected void cleanUpDB() throws Exception {
		removeAll(Barrio.class);
		removeAll(Localidad.class);
		removeAll(Departamento.class);
        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
    }

}
