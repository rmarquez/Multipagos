importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloAvonHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function createform(form) {
    //if (autorizar("registro_visitas")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
		var bean = new Packages.com.metropolitana.multipagos.GestionAvon();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.gestion.GestionHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());
		
		form.showForm("create-form-display");
        
        form.save(bean);
        	
        handlerBean.insert(bean, auth_getUserID());
        	
        dialogosino("Gestion Avon", "Registro de gestiones procesado con éxito",
                        "¿Desea procesar una nueva gestion?","create", "/bienvenidos");
    //}
}

function validarForm(form) {
	//var cantidadVisitas = form.getChild("cantidadVisitas").getValue();
	var widgetMensaje = form.getChild("mensajes de error");
	var handlerSimbolo = new SimboloAvonHandler();
	var handlerGestion = new Packages.com.metropolitana.multipagos.forms.gestion.GestionHandler();
	var detalle = form.getChild("detalle");
	
	for (var i = 0; i<detalle.size; i++) {
	    var row = detalle.getRow(i);
	    var simboloId = row.getChild("simboloId").getValue();
	    if(simboloId != null){
	    //  RSJ 201020413 - cambiado  var simbolo = handlerSimbolo.simboloXNumero(simboloId);
	    	var simbolo = handlerSimbolo.retrieve(simboloId);
	       	//  RSJ 201020412 - cambiado if(simbolo.getSimboloNumero()!="34") {
	    	if(simbolo.getSimboloNumero()!="210" || simbolo.getSimboloNumero()!="211" || simbolo.getSimboloNumero()!="212") {   
	    		var observaciones = row.getChild("observaciones").getValue();
	    		if (observaciones == null){
	    			form.getChild("mensajes de error").addMessage("El campo observaciones no puede ser nulo. ");
	    			return false;
	    		}	    		      	
	    	}
	    }
	 // Validar si el codigo no fue registrado 2 veces el mismo dia.
	    var codigo = row.getChild("codigo").getValue();
	    var gestionLlamada = row.getChild("gestionLlamada").getValue();
	    var fechaGestion = row.getChild("fechaGestion").getValue();
	    //if(gestionLlamada != null){
	    	if(codigo != null && fechaGestion != null) {
	    		if(gestionLlamada.booleanValue()==true){
			    	if(handlerGestion.getCodigoXFecha(true, codigo, fechaGestion)==true) {   
			    		var mensajeN = "El codigo " + codigo + " ya fue registrado el dia de hoy, en una llamada, no puede registrarse 2 veces."
		    			form.getChild("mensajes de error").addMessage(mensajeN);
		    			return false;  	
			    	} 
		    } else {
		    	if(handlerGestion.getCodigoXFecha(false, codigo, fechaGestion)==true) {   
		    		var mensajeN = "El codigo " + codigo + " ya fue registrado el dia de hoy, no puede registrarse 2 veces."
	    			form.getChild("mensajes de error").addMessage(mensajeN);
	    			return false;  	
		    	} 
		    }
	    	
    	}
	    // Validar que el contrato no se repida en el detalle
	    var c = 1;
	    for (var l = i + 1 ; l<detalle.size; l++) {
	    	var row2 = detalle.getRow(l);
	    	var codigo2 = row2.getChild("codigo").getValue();
			if(codigo.equals(codigo2)) {
	    	//if (detalle.get(k).equals(detalle.get(l))) {
				java.lang.System.out.println ("***** Dentro del equals ******* ");
			    c++;
			    row2 = null ;
			}
	    }
	    if ( c > 1)	{
	    	var mensajeN = "El codigo " + codigo + " se repite "  + c + " veces, favor eliminar elementos repetidos"
			form.getChild("mensajes de error").addMessage(mensajeN);
			return false; 

	    }
    }
	
	/**if(cantidadVisitas==null) {
		form.getChild("mensajes de error").addMessage("¡ No se han registrado visitas !, verifique sus datos, puede contener algun error. ");
		return false;
	}**/	  
	return true;
}