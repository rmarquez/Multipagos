package com.metropolitana.multipagos.forms.helper;

import java.math.BigDecimal;
import java.util.Date;

import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;

public class CarteraTestHelper {
	
	public final static String NOMBRE_SUSCRIPTOR_1 = "NOMBRE_SUSCRIPTOR_1";
    public final static String NOMBRE_SUSCRIPTOR_2 = "NOMBRE_SUSCRIPTOR_2";
    public final static String NOMBRE_SUSCRIPTOR_3 = "NOMBRE_SUSCRIPTOR_3";
    public final static String NOMBRE_SUSCRIPTOR_4 = "NOMBRE_SUSCRIPTOR_4";
    public final static String STR_SEARCH = "NOMBRE_SUSCRIPTOR";
    
	public final CarteraXDepartamento insertCartera(final String contrato,
			final String suscriptor, final String nit, final String direccion,
			final String facturaInterna, final String numFiscal,
			final String anio, final String mes, final BigDecimal saldo,
			final String cupon, final String telefono, final Boolean pagado,
			final Departamento departamento, final Localidad localidad,
			final Barrio barrio, final Servicio servicio, final EstadoCorte estado, 
			final Date fechaIngreso, final Integer usrId) throws Exception {

		CarteraXDepartamentoHandler handler = new CarteraXDepartamentoHandler();
		CarteraXDepartamento cartera = new CarteraXDepartamento();
		cartera.setContrato(contrato);
		cartera.setSuscriptor(suscriptor);
		cartera.setNit(nit);
		cartera.setDireccion(direccion);
		cartera.setFacturaInterna(facturaInterna);
		cartera.setNumeroFiscal(numFiscal);
		cartera.setAnio(anio);
		cartera.setMes(mes);
		cartera.setSaldo(saldo);
		cartera.setCupon(cupon);
		cartera.setTelefono(telefono);
		cartera.setPagado(pagado);
		cartera.setDepartamentoId(departamento.getDepartamentoId());
		cartera.setDepartamentoIdRef(departamento);
		cartera.setLocalidadId(localidad.getLocalidadId());
		cartera.setLocalidadIdRef(localidad);
		cartera.setBarrioId(barrio.getBarrioId());
		cartera.setBarrioIdRef(barrio);
		cartera.setServicioId(servicio.getServicioId());
		cartera.setServicioIdRef(servicio);
		cartera.setEstadoId(estado.getEstadoId());
		cartera.setEstadoIdRef(estado);
		cartera.setFechaIngreso(fechaIngreso);
		cartera.setPagadoClaro(false);
		handler.insert(cartera, usrId, null, null, null, null);

		return cartera;

	}

}
