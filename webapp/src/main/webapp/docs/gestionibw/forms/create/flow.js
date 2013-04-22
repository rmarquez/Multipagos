importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraIbwHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloIbwHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function createform(form) {
    //if (autorizar("registro_visitas")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
		var bean = new Packages.com.metropolitana.multipagos.GestionIbw();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.gestion_ibw.GestionIbwHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());
		
		form.showForm("create-form-display");
        
        form.save(bean);
        	
        handlerBean.insert(bean, auth_getUserID());
        	
        dialogosino("Gestion Ibw", "Registro de gestiones procesado con éxito",
                        "¿Desea procesar una nueva gestion?","create", "/bienvenidos");
    //}
}

function validarForm(form) {
	var widgetMensaje = form.getChild("mensajes de error");
	var handlerSimbolo = new SimboloIbwHandler();
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.gestion_ibw.GestionIbwHandler();
	var detalle = form.getChild("detalle");
	
	for (var i = 0; i<detalle.size; i++) {
	    var row = detalle.getRow(i);
	    
	 // Validar si la tarjeta no fue registrada 2 veces el mismo dia.
	    var codigo = row.getChild("codigo").getValue();
	    var gestionLlamada = row.getChild("gestionLlamada").getValue();
	    var fechaGestion = row.getChild("fechaGestion").getValue();
	    	if(codigo != null && fechaGestion != null) {
	    		if(gestionLlamada.booleanValue()==true){
			    	if(handlerBean.getCodigoXFecha(true, codigo, fechaGestion)==true) {   
			    		var mensajeN = "El cliente codigo " + codigo + " ya fue registrado el dia de hoy, en una llamada, no puede registrarse 2 veces."
		    			form.getChild("mensajes de error").addMessage(mensajeN);
		    			return false;  	
			    	} 
		    } else {
		    	if(handlerBean.getCodigoXFecha(false, codigo, fechaGestion)==true) {   
		    		var mensajeN = "El cliente codigo " + codigo + " ya fue registrado el dia de hoy, no puede registrarse 2 veces."
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
	    		    c++;
			    row2 = null ;
			}
	    }
	    if ( c > 1)	{
	    	var mensajeN = "El cliente codigo " + codigo + " se repite "  + c + " veces, favor eliminar elementos repetidos"
			form.getChild("mensajes de error").addMessage(mensajeN);
			return false; 

	    }
    }
    return true;
}