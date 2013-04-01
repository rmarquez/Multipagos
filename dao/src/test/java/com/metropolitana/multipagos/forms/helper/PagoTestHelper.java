package com.metropolitana.multipagos.forms.helper;

import java.math.BigDecimal;
import java.util.Date;

import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.pagos.PagosHandler;

public class PagoTestHelper {
	
	public final Pagos insertPagos(AuthUser user, Date fecha,
			DetallePagos detalle1, DetallePagos detalle2,
			DetallePagos detalle3, DetallePagos detalle4) throws Exception {
		
		Pagos pago = new Pagos();
		PagosHandler handler = new PagosHandler();
		pago.setUsrId(user.getUsrId());
		pago.setUsrIdRef(user);
		pago.setFecha(fecha);
		
		
		if(detalle1 != null){
			pago.addDetallePagos(detalle1);
		}
		if(detalle2 != null){
			pago.addDetallePagos(detalle2);
		}
		if(detalle3 != null){
			pago.addDetallePagos(detalle3);
		}
		if(detalle4 != null){
			pago.addDetallePagos(detalle4);
		}
		handler.insert(pago, user.getUsrId());
		return pago;		

	}
	
	public final DetallePagos insertDetallePago(CarteraXDepartamento cartera,
			Localidad localidad, Colector colector,
			Servicio servicio, String numContrato, String facturaInterna,
			BigDecimal montoPago, String horaRegistro) throws Exception {
		
		DetallePagos detalle = new DetallePagos();
		detalle.setCarteraId(cartera.getCarteraId());
		detalle.setCarteraIdRef(cartera);
		detalle.setLocalidadId(localidad.getLocalidadId());
		detalle.setLocalidadIdRef(localidad);
		detalle.setColectorId(colector.getColectorId());
		detalle.setColectorIdRef(colector);
		detalle.setServicioId(servicio.getServicioId());
		detalle.setServicioIdRef(servicio);
		detalle.setNumeroContrato(numContrato);
		detalle.setFacturaInterna(facturaInterna);
		detalle.setSalgoPagar(montoPago);
		detalle.setHoraRegistro(horaRegistro);
		return detalle;

	}

}
