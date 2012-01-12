importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
//importClass(Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);

function pagoEnDolares(event) {
    var dolares = event.source.value;
    var montoCs = event.source.parent.getChild("montoCs");
    var montoUs = event.source.parent.getChild("montoUs");
    if (dolares.booleanValue()== true) {
        montoCs.setValue(null);
        montoCs.setState(WidgetState.INVISIBLE);
        montoUs.setState(WidgetState.ACTIVE);        
    } else {
    	montoUs.setValue(null);
    	montoCs.setState(WidgetState.ACTIVE);
    	montoUs.setState(WidgetState.INVISIBLE);
    }
}

function depositoEnDolares(event) {
    var dolares = event.source.value;
    var montoCs = event.source.parent.getChild("montoCs");
    var montoUs = event.source.parent.getChild("montoUs");
    var cuentaCs = event.source.parent.getChild("cuentaCs");
    var cuentaUs = event.source.parent.getChild("cuentaUs");
    if (dolares.booleanValue()== true) {
    	cuentaCs.setValue(null);
        montoCs.setValue(null);
        montoCs.setState(WidgetState.INVISIBLE);
        montoUs.setState(WidgetState.ACTIVE);
        cuentaCs.setState(WidgetState.INVISIBLE);
        cuentaUs.setState(WidgetState.ACTIVE); 
    } else {
    	cuentaUs.setValue(null);
    	montoUs.setValue(null);
    	montoCs.setState(WidgetState.ACTIVE);
    	montoUs.setState(WidgetState.INVISIBLE);
    	cuentaCs.setState(WidgetState.ACTIVE);
    	cuentaUs.setState(WidgetState.INVISIBLE);
    }
}

function alSeleccionarColector(event) {
	var colectorId = event.source.value;
    var pagoFecha = event.source.parent.getChild("pagoFecha").getValue();
    var detalle = event.source.parent.getChild("detalle")
    var handlerArqueo = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
    if (colectorId != null) {
    	if(handlerArqueo.colectorArqueado(pagoFecha, colectorId)==true) {
    		event.source.parent.getChild("colectorId").setValidationError(new ValidationError("Este colector ya fue arqueado este dia."));
    	} else {
	    	var handler = new Packages.com.metropolitana.multipagos.forms.pagos.PagosXColector();
	    	var bean = handler.getListPagosXColector(pagoFecha, colectorId);
	    	//java.lang.System.out.println("Cantidad en list  = " + bean.size());
	        for (var i = 0; i < bean.size(); i++) {
	        	var rowGuardar = bean.get(i);
	        	 detalle.addRow();	
	        	 var row = detalle.getRow(i);
	        	 row.getChild("recibo").setValue(rowGuardar[0]);   
	    	     row.getChild("facturaInterna").setValue(rowGuardar[1]);
	    	     row.getChild("numContrato").setValue(rowGuardar[2]);
	    	     row.getChild("fecha").setValue(rowGuardar[3]);
	    	     row.getChild("monto").setValue(rowGuardar[4]);
	       
	        }
    	}
    }
}

function alCambiarMontoUs(event) {
    var montoUs = event.source.value;
    var pagoFecha = event.source.parent.parent.parent.getChild("pagoFecha").getValue();
    var conversion = event.source.parent.getChild("conversion");
    conversion.setValue(null);
    if (montoUs != null) {
    	var tasaHandler = Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();
    	var tasa = tasaHandler.retrieve(pagoFecha);
    	if (tasa != null) {
    		conversion.setValue(montoUs.multiply(tasa.getTasaCambioMes()).setScale(2, java.math.BigDecimal.ROUND_HALF_UP));
    	} else {
    		event.source.parent.parent.parent.getChild("mensajes de error").addMessage("Se necesita la tasa de cambio del mes para la conversion.");             
    	}
        
    }
}

function alSeleccionarCantidad(event) {
    var cantidadId = event.source.value;
    var cantidadValor = event.source.parent.getChild("cantidadValor");
    cantidadValor.setValue(null);
    if (cantidadId != null) {
    	var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var cantd = handler.retrieve(cantidadId);
    	cantidadValor.setValue(cantd.getCantidadValor());
    }
}

function alSeleccionarCantidadUs(event) {
    var cantidadIdUs = event.source.value;
    var cantidadValorUs = event.source.parent.getChild("cantidadValorUs");
    cantidadValorUs.setValue(null);
    if (cantidadIdUs != null) {
    	var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var cantd = handler.retrieve(cantidadIdUs);
    	cantidadValorUs.setValue(cantd.getCantidadValor());
    }
}

function alSeleccionarValor(event) {
    var cantidad = event.source.value;
    var cantidadValor = event.source.parent.getChild("cantidadValor").getValue();
    var total = event.source.parent.getChild("total");
    total.setValue(null);
    if (cantidad != null) {
    	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    	total.setValue(handlerUtil.calcularTotalCantidad(cantidadValor, cantidad));
    }
}

function alSeleccionarValorUs(event) {
    var cantidadUs = event.source.value;
    var cantidadValorUs = event.source.parent.getChild("cantidadValorUs").getValue();
    var totalUs = event.source.parent.getChild("totalUs");
    totalUs.setValue(null);
    if (cantidadUs != null) {
    	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    	totalUs.setValue(handlerUtil.calcularTotalCantidad(cantidadValorUs, cantidadUs));
    }
}

function alSeleccionarFactura(event) {
    var facturaInterna = event.source.value;
	var suscriptor = event.source.parent.getChild("suscriptor");
    suscriptor.setValue(null);    
    var handlerCartera = new CarteraXDepartamentoHandler();	
    if(facturaInterna != null){
		var cartera = handlerCartera.carteraXFactura(facturaInterna);
		if(cartera != null){
			suscriptor.setValue(cartera.getSuscriptor());	
		}		
	} 
}

function alSeleccionarContrato(event) {
	var numContrato = event.source.value;
	var facturaInterna = event.source.parent.getChild("facturaInterna").getValue();
	var suscriptor = event.source.parent.getChild("suscriptor");    
	//suscriptor.setValue(null);
    var handlerCartera = new CarteraXDepartamentoHandler(); 
    if(numContrato != null && facturaInterna == null){
		var cartera = handlerCartera.carteraXContrato(numContrato, null);
		if(cartera != null){
			suscriptor.setValue(cartera.getSuscriptor());	
			
		}
	}	
}

function alSeleccionarAceptar(event) {
    var aceptar = event.source.value;    
    var autorizar = event.source.parent.getChild("autorizar");
    autorizar.setValue(null);
    if (aceptar != null) {
    	autorizar.setValue(Boolean.TRUE);
    }
}
