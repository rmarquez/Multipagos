importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

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
    var cont = 0;
	var detalleVisitas = event.source.parent.getParent().getParent().getChild("detalle");
	for (var i = 0; i<detalleVisitas.size; i++) {
	    var row = detalleVisitas.getRow(i);
	    var contador = row.getChild("contador").getValue();
	    cont += parseInt(contador);	    
    }	
    if (contador != null) {
        cantidadPagos.setValue(Integer.valueOf(cont));
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

function alSeleccionarFactura(event) {
    var facturaInterna = event.source.value;
	var numContrato = event.source.parent.getChild("numContrato");
	numContrato.setValue(null);	
	var numeroFiscal = event.source.parent.getChild("numeroFiscal");
	numeroFiscal.setValue(null);	
	var cupon = event.source.parent.getChild("cupon");
	cupon.setValue(null);	
	var carteraId = event.source.parent.getChild("carteraId");
    carteraId.setValue(null);
    var suscriptor = event.source.parent.getChild("suscriptor");
    suscriptor.setValue(null);
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    var servicioId = event.source.parent.getChild("servicioId");
    servicioId.setValue(null);
    var year = event.source.parent.getChild("year");
    year.setValue(null);
    var mes = event.source.parent.getChild("mes");
    mes.setValue(null);
    var montoPago = event.source.parent.getChild("montoPago");
    montoPago.setValue(null);  
    
    var handlerCartera = new CarteraXDepartamentoHandler();
	
    if(facturaInterna != null){
		var cartera = handlerCartera.carteraXFactura(facturaInterna);
		if(cartera != null){
			carteraId.setValue(cartera.getCarteraId());
			suscriptor.setValue(cartera.getSuscriptor());
			localidadId.setValue(cartera.getLocalidadId());
			servicioId.setValue(cartera.getServicioId());
			year.setValue(cartera.getAno());
    		mes.setValue(cartera.getMes());
    		montoPago.setValue(cartera.getSaldo()); 
    		numContrato.setValue(cartera.getContrato());
    		numeroFiscal.setValue(cartera.getNumeroFiscal());
    		cupon.setValue(cartera.getCupon());
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