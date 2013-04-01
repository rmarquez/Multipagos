importClass(Packages.java.util.Date);
importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.java.util.Calendar);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.java.text.DateFormat);
importClass(Packages.java.text.SimpleDateFormat);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler);
importClass(Packages.com.metropolitana.multipagos.forms.ciudad.CiudadHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloBanproHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
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
    //var cantidadVisitas = event.source.parent.getParent().getParent().getChild("cantidadVisitas");
    //cantidadVisitas.setValue(null);    
    var cont = 0;
	var detalleGestion = event.source.parent.getParent().getParent().getChild("detalle");
	for (var i = 0; i<detalleGestion.size; i++) {
	    var row = detalleGestion.getRow(i);
	    var contador = row.getChild("contador").getValue();
	    cont += parseInt(contador);
    }	
    /**if (contador != null) {
        cantidadVisitas.setValue(Integer.valueOf(cont));
    }**/
}

function alSeleccionarTarjeta(event) {

	var tarjeta = event.source.value;
	var codCliente = event.source.parent.getChild("codigo")
	codCliente.setValue(null);
	var cuenta = event.source.parent.getChild("cuenta")
	cuenta.setValue(null);
	var tmpId = event.source.parent.getChild("tmpId");
	//carteraId.setValue(null);
	var nombre = event.source.parent.getChild("nombre");
	nombre.setValue(null);
	var ciudadCId = event.source.parent.getChild("ciudadCId");
	ciudadCId.setValue(null);
	var departamentoId = event.source.parent.getChild("departamentoId");
	departamentoId.setValue(null);
    var fechaGestion = event.source.parent.getChild("fechaGestion");
    fechaGestion.setValue(null);
    var handlerCartera = new CarteraBanproHandler();   
    if(tarjeta != null){
 		var cartera = handlerCartera.carteraXTarjeta(tarjeta);
		if(cartera != null){
			codCliente.setValue(cartera.getCodCliente());
			cuenta.setValue(cartera.getCuenta());
			tmpId.setValue(cartera.getTmpId()); 
			nombre.setValue(cartera.getNombre());
			ciudadCId.setValue(cartera.getCiudadC());
			departamentoId.setValue(cartera.getDepartamentoC());
			fechaGestion.setValue(new Packages.java.util.Date());			
		}  else { 
			 event.source.parent.getChild("tarjeta").setValidationError(new ValidationError("La tarjeta NO encontrada, verifique el numero."));				
    	}
	} 	
}

function alSeleccionarSimbolo(event) {
	
    var simbolo = event.source.value;
    var simboloId = event.source.parent.getChild("simboloId");
    simboloId.setValue(null);
    var simboloNombre = event.source.parent.getChild("simboloNombre");
    simboloNombre.setValue(null);
    var simboloCorto = event.source.parent.getChild("simboloCorto");
    simboloCorto.setValue(null);
    var handlerSimbolo = new SimboloBanproHandler();
    if (simbolo != null) {
    	var simbolo2 = handlerSimbolo.simboloXNumero(simbolo);
    	if(simbolo2 != null){
	    	simboloNombre.setValue(simbolo2.getSimboloNombre());   
	    	simboloCorto.setValue(simbolo2.getSimboloCorto());   
	    	simboloId.setValue(simbolo2.getSimboloId()); 
	    	
    	}else {
    		event.source.parent.getChild("simbolo").setValidationError(new ValidationError("Simbolo claro no encontrado, verifique si el # es correcto."));
    	} 
    }
}



function alSeleccionarColector(event) {
	var colectorNumero = event.source.value;
	var util = new Util();
	var hora = util.getFechaString(new Packages.java.util.Date());
    var colectorId = event.source.parent.getChild("colectorId");
    colectorId.setValue(null);
    var colectorNombre = event.source.parent.getChild("colectorNombre");
    colectorNombre.setValue(null);
    var horaRegistro = event.source.parent.getChild("horaRegistro");
    horaRegistro.setValue(null);
    var contador = event.source.parent.getChild("contador");    
    var handlerColector = new ColectorHandler();
    
	var colector = handlerColector.colectorXNumero(colectorNumero);
	if(colector != null) {
		colectorId.setValue(colector.getColectorId()); 
    	colectorNombre.setValue(colector.getPrimerNombre()+ " "+colector.getPrimerApellido());
    	horaRegistro.setValue(hora);
    	contador.setValue(Integer.valueOf(1));
    	
	} else {
		event.source.parent.getChild("colectorNumero").setValidationError(new ValidationError("Colector no encontrado, verifique si el # es correcto."));
	}       
  
}