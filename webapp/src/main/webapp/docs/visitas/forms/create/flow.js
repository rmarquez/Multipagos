importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function createform(form) {
    //if (autorizar("registro_visitas")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
		var bean = new Packages.com.metropolitana.multipagos.Visitas();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());
		
		form.showForm("create-form-display");
        
        form.save(bean);
        	
        handlerBean.insert(bean, auth_getUserID());
        	
        dialogosino("Visitas", "Registro de visitas procesado con éxito",
                        "¿Desea procesar un nuevo registro de visitas?","create", "/bienvenidos");
    //}
}

function validarForm(form) {
	var cantidadVisitas = form.getChild("cantidadVisitas").getValue();
	var widgetMensaje = form.getChild("mensajes de error");
	var handlerSimbolo = new SimboloHandler();
	var handlerVisitas = new VisitasHandler();
	var detalle = form.getChild("detalle");
	
	for (var i = 0; i<detalle.size; i++) {
	    var row = detalle.getRow(i);
	    var simboloId = row.getChild("simboloId").getValue();
	    if(simboloId != null){
	    //  RSJ 201020413 - cambiado  var simbolo = handlerSimbolo.simboloXNumero(simboloId);
	    	var simbolo = handlerSimbolo.retrieve(simboloId);
	       	//  RSJ 201020412 - cambiado if(simbolo.getSimboloNumero()!="34") {
	    	if(simbolo.getSimboloNumero()!="34" || simbolo.getSimboloNumero()!="606" || simbolo.getSimboloNumero()!="607") {   
	    		var avisoCobro = row.getChild("avisoCobro").getValue();
	    		if (avisoCobro == null){
	    			form.getChild("mensajes de error").addMessage("El campo aviso cobro no puede ser nulo. ");
	    			return false;
	    		}	    		      	
	    	}
	    }
	    // Validar si el contrato no fue registrado 2 veces el mismo dia.
	    var contrato = row.getChild("numContrato").getValue();
	    var fechaVisita = row.getChild("fechaVisita").getValue();
	    if(contrato != null && fechaVisita != null){
		    	if(handlerVisitas.getContratoXFecha(contrato, fechaVisita)==true) {   
		    		var mensajeN = "El contrato " + contrato + " ya fue registrado el dia de hoy, no puede registrarse 2 veces."
	    			form.getChild("mensajes de error").addMessage(mensajeN);
	    			return false;  	
		    	} 
		    }
	    // Validar que el contrato no se repida en el detalle
	    var c = 1;
	    for (var l = i + 1 ; l<detalle.size; l++) {
	    	var row2 = detalle.getRow(l);
	    	var contrato2 = row2.getChild("numContrato").getValue();
			if(contrato.equals(contrato2)) {
	    	//if (detalle.get(k).equals(detalle.get(l))) {
				java.lang.System.out.println ("***** Dentro del equals ******* ");
			    c++;
			    row2 = null ;
			}
	    }
	    if ( c > 1)	{
	    	var mensajeN = "El contrato " + contrato + " se repite "  + c + " veces, favor eliminar elementos repetidos"
			form.getChild("mensajes de error").addMessage(mensajeN);
			return false; 

	    }
	    //************************************************
    }
	
	if(cantidadVisitas==null) {
		form.getChild("mensajes de error").addMessage("¡ No se han registrado visitas !, verifique sus datos, puede contener algun error. ");
		return false;
	}	  
	return true;
}