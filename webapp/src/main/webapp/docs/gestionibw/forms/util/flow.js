importClass(Packages.java.util.Date);
importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.java.util.Calendar);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.java.text.DateFormat);
importClass(Packages.java.text.SimpleDateFormat);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler);
importClass(Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloIbwHandler);
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
    var cont = 0;
	var detalleGestion = event.source.parent.getParent().getParent().getChild("detalle");
	for (var i = 0; i<detalleGestion.size; i++) {
	    var row = detalleGestion.getRow(i);
	    var contador = row.getChild("contador").getValue();
	    cont += parseInt(contador);
    }	
   
}

function alSeleccionarCodigo(event) {

	var codigo = event.source.value;
	var carteraId = event.source.parent.getChild("carteraId");
	//carteraId.setValue(null);
	var cliente = event.source.parent.getChild("cliente");
	cliente.setValue(null);
    var municipioId = event.source.parent.getChild("municipioId");
    municipioId.setValue(null);
    var barrioId = event.source.parent.getChild("barrioId");
    barrioId.setValue(null);
    var fechaGestion = event.source.parent.getChild("fechaGestion");
    fechaGestion.setValue(null);
    var handlerCartera = new CarteraIbwHandler();   
    var handlerGestion = new Packages.com.metropolitana.multipagos.forms.gestion_ibw.GestionIbwHandler();
 	if(codigo != null){
		var cartera = handlerCartera.carteraXcodCliente(codigo);
		if(cartera != null){
			carteraId.setValue(cartera.getCarteraId()); 
			cliente.setValue(cartera.getNombre()+ " "+cartera.getApellido());
			municipioId.setValue(cartera.getMunicipioId());
			barrioId.setValue(cartera.getBarrioId());
			fechaGestion.setValue(new Packages.java.util.Date());			
		}  else { 
			 event.source.parent.getChild("codigo").setValidationError(new ValidationError("Codigo NO encontrado"));				
    	}
	} 	
}

function alSeleccionarSimbolo(event) {
	
    var simbolo = event.source.value;
    var simboloId = event.source.parent.getChild("simboloId");
    simboloId.setValue(null);
    var simboloNombre = event.source.parent.getChild("simboloNombre");
    simboloNombre.setValue(null);
    var fprogCobro = event.source.parent.getChild("fprogCobro");
    fprogCobro.setValue(null);
    var handlerSimbolo = new SimboloIbwHandler();
    if (simbolo != null) {
    	var simbolo2 = handlerSimbolo.simboloXNumero(simbolo);
    	if(simbolo2 != null){
	    	simboloNombre.setValue(simbolo2.getSimboloNombre());    	
	    	simboloId.setValue(simbolo2.getSimboloId()); 
	    	//  RSJ 201020412 if(simbolo2.getSimboloNumero()=="30" || simbolo2.getSimboloNumero()=="35") {   
	    	if(simbolo2.getSimboloNumero()=="710" || simbolo2.getSimboloNumero()=="711" || simbolo2.getSimboloNumero()=="712") {   
	    		fprogCobro.setState(WidgetState.ACTIVE);        	
	    	} else {
	    		fprogCobro.setState(WidgetState.INVISIBLE); 
	    	}
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