package com.metropolitana.multipagos.forms;


import java.math.BigDecimal;
import java.util.Date;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;
import com.metropolitana.multipagos.forms.helper.AuthUserTestHelper;
import com.metropolitana.multipagos.forms.helper.BarrioTestHelper;
import com.metropolitana.multipagos.forms.helper.CarteraTestHelper;
import com.metropolitana.multipagos.forms.helper.ColectorTestHelper;
import com.metropolitana.multipagos.forms.helper.DepartamentoTestHelper;
import com.metropolitana.multipagos.forms.helper.EstadoCorteTestHelper;
import com.metropolitana.multipagos.forms.helper.LocalidadTestHelper;
import com.metropolitana.multipagos.forms.helper.PagoTestHelper;
import com.metropolitana.multipagos.forms.helper.ServicioTestHelper;
import com.metropolitana.multipagos.junit.PBTestBase;

public class TestPagos extends PBTestBase {
	
	private PagoTestHelper testPagoHelper;
	private ColectorTestHelper testColectorHelper;
	private ServicioTestHelper testServicioHelper;
    private AuthUserTestHelper testUserHelper;
    private DepartamentoTestHelper testDepHelper;
	private LocalidadTestHelper testLocalidadHelper;
	private BarrioTestHelper testBarrioHelper;
	private BarrioHandler handlerBarrio;
	private CarteraTestHelper testCarteraHelper;
	private EstadoCorteTestHelper testEstadoHelper;

    public void setUp() throws Exception {
        super.setUp();
        testPagoHelper = new PagoTestHelper();
        testColectorHelper = new ColectorTestHelper(); 
        testServicioHelper = new ServicioTestHelper();
        testDepHelper = new DepartamentoTestHelper();
        testBarrioHelper = new BarrioTestHelper();
        testLocalidadHelper = new LocalidadTestHelper();
        handlerBarrio = new BarrioHandler();
        testUserHelper = new AuthUserTestHelper();
        testCarteraHelper = new CarteraTestHelper();
        testEstadoHelper = new EstadoCorteTestHelper();
       
    }
	
	public void testInsertPagos() throws Exception {
		Date hoy = new Date();
		
		AuthUser user = testUserHelper.insertUsuario(AuthUserTestHelper.USER_LOGIN_1, AuthUserTestHelper.USER_PASS_1, AuthUserTestHelper.USER_CARGO_1);
        assertNotNull("El usuario del sistema no se creó.", user);
        
        Departamento departamento = testDepHelper.insertDepartamento(DepartamentoTestHelper.NOMBRE_DEP_1, user.getUsrId());
        assertNotNull("El departamento '" + DepartamentoTestHelper.NOMBRE_DEP_1 + "' no se guardo.", departamento);

        Localidad localidad = testLocalidadHelper.insertLocalidad(LocalidadTestHelper.NOMBRE_LOCALIDAD_1, departamento, user.getUsrId());
        assertNotNull("La Localidad '" + LocalidadTestHelper.NOMBRE_LOCALIDAD_1 + "' no se guardo.", localidad);
        
        Barrio barrio = testBarrioHelper.insertBarrio(BarrioTestHelper.NOMBRE_BARRIO_1, localidad, user.getUsrId());
        assertNotNull("El Barrio '" + BarrioTestHelper.NOMBRE_BARRIO_1 + "' no se guardo.", barrio);
        
        Servicio servicio = testServicioHelper.insertServicio(ServicioTestHelper.NOMBRE_SERVICIO_1, user.getUsrId());
        assertNotNull("El servicio '" + ServicioTestHelper.NOMBRE_SERVICIO_1 + "' no se guardo.", servicio);
        
        Colector colector = testColectorHelper.insertColector(Integer.valueOf(1), ColectorTestHelper.NOMBRE_COLECTOR_1, ColectorTestHelper.APELLIDO_COLECTOR_1, user.getUsrId());
        assertNotNull("El colector '" + ColectorTestHelper.NOMBRE_COLECTOR_1 + "' no se guardo.", colector);
        
        EstadoCorte estado = testEstadoHelper.insertEstado(EstadoCorteTestHelper.NOMBRE_ESTADO_1, user.getUsrId());
        assertNotNull("El estado '" + EstadoCorteTestHelper.NOMBRE_ESTADO_1 + "' no se guardo.", estado);
        
		CarteraXDepartamento cartera1 = testCarteraHelper.insertCartera("0001",
				"Pedro Jose Martinez", "887", "Algun lado de Managua", "A001",
				"N001", "2011", "7", BigDecimal.valueOf(400), "AFR", "22222222", Boolean.FALSE,
				departamento, localidad, barrio, servicio, estado,hoy, user.getUsrId());
		
		CarteraXDepartamento cartera2 = testCarteraHelper.insertCartera("0001",
				"Pedro Jose Martinez", "887", "Algun lado de Managua", "A002",
				"N002", "2011", "8", BigDecimal.valueOf(402), "AFR", "22222222", Boolean.FALSE,
				departamento, localidad, barrio, servicio, estado,hoy, user.getUsrId());
		
		CarteraXDepartamento cartera3 = testCarteraHelper.insertCartera("0001",
				"Pedro Jose Martinez", "887", "Algun lado de Managua", "A003",
				"N003", "2011", "9", BigDecimal.valueOf(404), "AFR", "22222222", Boolean.FALSE,
				departamento, localidad, barrio, servicio, estado,hoy, user.getUsrId());
		
		CarteraXDepartamento cartera4 = testCarteraHelper.insertCartera("0001",
				"Pedro Jose Martinez", "887", "Algun lado de Managua", "A004",
				"N004", "2011", "10", BigDecimal.valueOf(406), "AFR", "22222222", Boolean.FALSE,
				departamento, localidad, barrio, servicio, estado, hoy, user.getUsrId());
		
		DetallePagos detalle1 = testPagoHelper.insertDetallePago(cartera1, localidad, colector, servicio, "0001", "A001", BigDecimal.valueOf(400), "00:00");
		DetallePagos detalle2 = testPagoHelper.insertDetallePago(cartera2, localidad, colector, servicio, "0001", "A002", BigDecimal.valueOf(402), "00:00");
		DetallePagos detalle3 = testPagoHelper.insertDetallePago(cartera3, localidad, colector, servicio, "0001", "A003", BigDecimal.valueOf(404), "00:00");
		DetallePagos detalle4 = testPagoHelper.insertDetallePago(cartera4, localidad, colector, servicio, "0001", "A004", BigDecimal.valueOf(406), "00:00");
		
		/*Pagos pago = testPagoHelper.insertPagos(user, hoy, detalle1, detalle2, detalle3, detalle4);
		assertNotNull("El Pago '" + pago.getFecha() + "' no se guardo.", pago);*/
	}
	
	
	
	
	 protected void cleanUpDB() throws Exception {
		 	removeAll(Pagos.class);
		 	removeAll(CarteraXDepartamento.class);
		 	removeAll(EstadoCorte.class);
		 	removeAll(Colector.class);
		 	removeAll(Servicio.class);
		 	removeAll(Barrio.class);
		 	removeAll(Localidad.class);
		 	removeAll(Departamento.class);
	        removeAll(AuthUser.class, AuthUserTestHelper.REMOVE_CRITERIO);
	    }

}
