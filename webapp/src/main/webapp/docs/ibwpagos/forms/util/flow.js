importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraIbwHandler);
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
	    var fechaFactura = row.getChild("fechaFactura").getValue();
	    if(fechaFactura == null){
	    	form.getChild("mensajes de error").addMessage("La fecha de la factura no puede ser vacia, favor verifique los datos. ");
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
	var carteraId = event.source.parent.getChild("carteraId");
	carteraId.setValue(null);
    var cliente = event.source.parent.getChild("cliente");
    cliente.setValue(null);
    var municipioId = event.source.parent.getChild("municipioId");
    municipioId.setValue(null);
    var barrioId = event.source.parent.getChild("barrioId");
    barrioId.setValue(null);
    var fechaFactura = event.source.parent.getChild("fechaFactura");
    fechaFactura.setValue(null);
    var mes = event.source.parent.getChild("mes");
    mes.setValue(null);
    var montoPago = event.source.parent.getChild("montoPago");
    montoPago.setValue(null);    
    var saldoPagar = event.source.parent.getChild("salgoPagar");
    saldoPagar.setValue(null); 
    var fechaPago = event.source.parent.getChild("fechaPago");
    //fechaPago.setValue(null);
    var editar = event.source.parent.getParent().getParent().getChild("editar").getValue();
    var handlerCartera = new CarteraIbwHandler();
    var handlerPagos = new Packages.com.metropolitana.multipagos.forms.ibw_pagos.IbwPagosHandler();
	var cartera = handlerCartera.carteraXfacturaIbw(factura);
	var util = new Util();
	if(cartera != null){
		carteraId.setValue(cartera.getCarteraId());
		cliente.setValue(cartera.getNombre()+ " "+cartera.getApellido());
		municipioId.setValue(cartera.getMunicipioId());
		barrioId.setValue(cartera.getBarrioId());
		fechaFactura.setValue(cartera.getFechaFactura());
		mes.setValue(util.getMesfecha(cartera.getFechaFactura()));
		saldoPagar.setValue(cartera.getSaldoDol()); 
		montoPago.setValue(cartera.getSaldoDol()); 
		codigo.setValue(cartera.getCodCliente());
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
	var carteraId = event.source.parent.getChild("carteraId");
	var cliente = event.source.parent.getChild("cliente");
    var municipioId = event.source.parent.getChild("municipioId");
    var barrioId = event.source.parent.getChild("barrioId");
    var saldoPagar = event.source.parent.getChild("salgoPagar");
    
    var handlerCartera = new CarteraIbwHandler(); 
    if(codigo != null && factura == null){
		var cartera = handlerCartera.carteraXcodCliente( codigo );
		if(cartera != null){
			carteraId.setValue(cartera.getCarteraId()); 
			cliente.setValue(cartera.getNombre()+ " "+cartera.getApellido());
			municipioId.setValue(cartera.getMunicipioId());
			barrioId.setValue(cartera.getBarrioId());
			saldoPagar.setValue(cartera.getSaldo());
			
		}
		
	}	
}

function validarFechaPago(event) {
    var fechaPago = event.source.value;
    var util = new Util();
    if (fechaPago != null) {
    	if (util.validarAnioFecha(fechaPago)==true) {
    		event.source.parent.getChild("fechaPago").setValidationError(new ValidationError("Fecha incorrecta, el año no puede ser menor o mayor al actual."));
    	}
    }
}

function pagoConCK(event) {
    var pago = event.source.value;
    var numero = event.source.parent.getChild("numeroCk");
    if (pago.booleanValue()== true) {
        numero.setState(WidgetState.ACTIVE);        
    } else {
    	numero.setState(WidgetState.INVISIBLE);
    }
}