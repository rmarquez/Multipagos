importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloAvonHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);

function validarForm(form) {
	var cantidadPagos = form.getChild("cantidadPagos").getValue();
	var widgetMensaje = form.getChild("mensajes de error");
	var detalle = form.getChild("detalle");
	
	for (var i = 0; i<detalle.size; i++) {
	    var row = detalle.getRow(i);
	    var montoPago = row.getChild("montoPago").getValue();
	    if(montoPago == null){
	    	form.getChild("mensajes de error").addMessage("El monto del pago no puede ser nulo, favor verifique los datos. ");
	    	return false;
	    }
	    var year = row.getChild("year").getValue();
	    if(year == null){
	    	form.getChild("mensajes de error").addMessage("El Año no puede ser vacio, favor verifique los datos. ");
	    	return false;
	    }
	    var mes = row.getChild("mes").getValue();
	    if(mes == null){
	    	form.getChild("mensajes de error").addMessage("El Mes no puede ser vacio, favor verifique los datos. ");
	    	return false;
	    }
	    if(cantidadPagos==null) {
			form.getChild("mensajes de error").addMessage("¡ No se han registrado pagos !, verifique sus datos, puede contener algun error. ");
			return false;
		}
	    
	    //cantidadPagos
    }		  
	return true;
}


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

function alSeleccionarFactura(event) {
    var factura = event.source.value;
	var codigo = event.source.parent.getChild("codigo");
	codigo.setValue(null);	
	var cavonId = event.source.parent.getChild("cavonId");
	cavonId.setValue(null);
    var consejero = event.source.parent.getChild("consejero");
    consejero.setValue(null);
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    var barrioId = event.source.parent.getChild("barrioId");
    barrioId.setValue(null);
    var year = event.source.parent.getChild("year");
    year.setValue(null);
    var mes = event.source.parent.getChild("mes");
    mes.setValue(null);
    var montoPago = event.source.parent.getChild("montoPago");
    montoPago.setValue(null);    
    var saldoPagar = event.source.parent.getChild("salgoPagar");
    saldoPagar.setValue(null); 
    var fechaPago = event.source.parent.getChild("fechaPago");
    //fechaPago.setValue(null);
    var editar = event.source.parent.getParent().getParent().getChild("editar").getValue();
    var handlerCartera = new CarteraAvonHandler();
    var handlerPagos = new Packages.com.metropolitana.multipagos.forms.avon_pagos.AvonPagosHandler();
	var cartera = handlerCartera.carteraXFactura(factura);
	
	if(cartera != null){
		cavonId.setValue(cartera.getCavonId());
		consejero.setValue(cartera.getConsejero());
		localidadId.setValue(cartera.getLocalidadId());
		barrioId.setValue(cartera.getBarrioId());
		year.setValue(cartera.getAnio());
		mes.setValue(cartera.getMes());
		saldoPagar.setValue(cartera.getSaldo()); 
		montoPago.setValue(cartera.getSaldo()); 
		codigo.setValue(cartera.getCodigo());
	}
}

function alSeleccionarNumColector(event) {
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

function alSeleccionarCodigo(event) {
	var codigo = event.source.value;
	var factura = event.source.parent.getChild("factura").getValue();
	var cavonId = event.source.parent.getChild("cavonId");
	var consejero = event.source.parent.getChild("consejero");
    var localidad = event.source.parent.getChild("localidadId");
    var barrioId = event.source.parent.getChild("barrioId");
    var saldoPagar = event.source.parent.getChild("salgoPagar");
    //var year = event.source.parent.getChild("year");
    //var mes = event.source.parent.getChild("mes");
    
    var handlerCartera = new CarteraAvonHandler(); 
     //var handlerVisita = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
	if(codigo != null && factura == null){
		// RSJ 201020505 - comentado por que se quito el 2do parametros
		// var cartera = handlerCartera.carteraXContrato(numContrato, null);
		var cartera = handlerCartera.carteraXCodigo( codigo );
		if(cartera != null){
			cavonId.setValue(cartera.getCavonId()); 
			consejero.setValue(cartera.getConsejero());
			localidad.setValue(cartera.getLocalidadId());
			barrioId.setValue(cartera.getBarrioId());
			saldoPagar.setValue(cartera.getSaldo());
			
		}
		
	}	
}

function validarFechaPago(event) {
    var fechaPago = event.source.value;
    var util = new Util();
    //var fecha = event.source.parent.getChild("fecha");
    //fecha.setValue(null);
    if (fechaPago != null) {
    	if (util.validarAnioFecha(fechaPago)==true) {
    		event.source.parent.getChild("fechaPago").setValidationError(new ValidationError("Fecha incorrecta, el año no puede ser menor o mayor al actual."));
    	}
    }
}

function revertirPago(event) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
	var detalle = event.source.parent.getChild("detalle");
	for (var i = 0; i<detalle.size; i++) {
	    var row = detalle.getRow(i);
	    var marcar = row.getChild("marcar").getValue().booleanValue();
	    
	    if(marcar==true){
	    	var carteraId = row.getChild("carteraId").getValue();
	    	handlerBean.revertirPago(carteraId, auth_getUserID());
	    }	    
    }   
}
