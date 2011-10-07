importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);

function alSeleccionarUsuario(event) {
    var usrId = event.source.value;
    var fecha = event.source.parent.getChild("fecha");
    fecha.setValue(null);
    if (usrId != null) {
        event.source.parent.getChild("fecha").setValue(new Packages.java.util.Date());
    }
}

function alCambiar(event) {
    var contador = event.source.value;
    var cantidadPagos = event.source.parent.getParent().getParent().getChild("cantidadPagos");
    cantidadPagos.setValue(null);
    
    var cantidadPagosUs = event.source.parent.getParent().getParent().getChild("cantidadPagosUs");
    cantidadPagosUs.setValue(null);
    
    var cont = 0;
    var contUs = 0;
	var detalleVisitas = event.source.parent.getParent().getParent().getChild("detalle");
	for (var i = 0; i<detalleVisitas.size; i++) {
	    var row = detalleVisitas.getRow(i);
	    var dolares = row.getChild("dolares").getValue();
	    var contador = row.getChild("contador").getValue();
	    if(dolares.booleanValue()== true) {
	    	contUs += parseInt(contador);
	    }
	    if(dolares.booleanValue()== false) {
	    	cont += parseInt(contador);
	    }
    }	
    if (contador != null) {
        cantidadPagos.setValue(Integer.valueOf(cont));
        cantidadPagosUs.setValue(Integer.valueOf(contUs));
    }
}


function alSeleccionarContrato(event) {
	var numContrato = event.source.value;
	//var numContrato = event.source.parent.getChild("numContrato").getValue();
	var carteraId = event.source.parent.getChild("carteraId");
    carteraId.setValue(null);
    var suscriptor = event.source.parent.getChild("suscriptor");
    suscriptor.setValue(null);
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    var servicioId = event.source.parent.getChild("servicioId");
    servicioId.setValue(null);
    var fechaPago = event.source.parent.getChild("fechaPago");
    fechaPago.setValue(null);
    var handlerCartera = new CarteraXDepartamentoHandler();
	if(numContrato != null){
		var cartera = handlerCartera.carteraXContrato(numContrato);
		if(cartera != null){
			carteraId.setValue(cartera.getCarteraId());
			suscriptor.setValue(cartera.getSuscriptor());
			localidadId.setValue(cartera.getLocalidadId());
			servicioId.setValue(cartera.getServicioId());
			fechaPago.setValue(new Packages.java.util.Date());
		}		
	} 
	
}

function alSeleccionarNumero(event) {
    var numeroFiscal = event.source.value;
    var facturaInterna = event.source.parent.getChild("facturaInterna").getValue();
    var numContrato = event.source.parent.getChild("numContrato").getValue();
    var year = event.source.parent.getChild("year");
    year.setValue(null);
    var mes = event.source.parent.getChild("mes");
    mes.setValue(null);
    var montoPago = event.source.parent.getChild("montoPago");
    montoPago.setValue(null);
    var handlerCartera = new CarteraXDepartamentoHandler();
    if (numeroFiscal != null) {
    	var cartera = handlerCartera.getMesSaldoMora(numContrato, facturaInterna, numeroFiscal);
    	if(cartera != null) {
    		year.setValue(cartera.getAno());
    		mes.setValue(cartera.getMes());
    		montoPago.setValue(cartera.getSaldo()); 
    	}       
    }
}

function alSeleccionarColector(event) {
	var colector = event.source.value;
	var util = new Util();
	var hora = util.getFechaString(new Packages.java.util.Date());
    var colectorId = event.source.parent.getChild("colectorId");
    colectorId.setValue(null);
    var colectorNombre = event.source.parent.getChild("colectorNombre");
    colectorNombre.setValue(null);
    var horaRegistro = event.source.parent.getChild("horaRegistro");
    horaRegistro.setValue(null);
    var contador = event.source.parent.getChild("contador");
    contador.setValue(null);
    var handlerColector = new ColectorHandler();
    if (colectorId != null) {
    	var colector = handlerColector.colectorXNumero(colector);
    	if(colector != null) {
    		colectorId.setValue(colector.getColectorId()); 
        	colectorNombre.setValue(colector.getPrimerNombre()+ " "+colector.getPrimerApellido());
        	horaRegistro.setValue(hora);
        	contador.setValue(Integer.valueOf(1));
        	
    	}        
    }
}

function pagoEnDolares(event) {
    var dolares = event.source.value;
    var montoPago = event.source.parent.getChild("montoPago");
    var montoPagoUs = event.source.parent.getChild("montoPagoUs");
    montoPago.setValue(null);
    montoPagoUs.setValue(null);
    if (dolares.booleanValue()== true) {
        montoPago.setValue(null);
        montoPago.setState(WidgetState.INVISIBLE);
        montoPagoUs.setState(WidgetState.ACTIVE);        
    } else {
    	montoPago.setState(WidgetState.ACTIVE);
    	montoPagoUs.setState(WidgetState.INVISIBLE);
    }
}
