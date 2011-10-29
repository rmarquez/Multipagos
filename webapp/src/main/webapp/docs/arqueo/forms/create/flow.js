importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.MenuUser);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	form.getChild("usrId").setValue(usuario.getUsrId());
    	form.getChild("autorizado").setValue(Boolean.FALSE);
    	form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.ArqueoPagos();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();        
        
        form.save(bean);
        var autorizado = form.getChild("autorizado").getValue();
        if(autorizado.booleanValue()==false) {
        	handlerBean.insert(bean, auth_getUserID());
        } else if(autorizado.booleanValue()==true){
        	var passwd = form.getChild("auth_passwd").getValue();
    	    var auth_user = form.getChild("auth_user").getValue();
    	  	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
	      	var md5 = encriptar.encriptarMD5(passwd);  
	      	var usuario = handlerUser.getUsuario(auth_user, md5); 
	      	var permisos = new MenuUser(parseInt(usuario.getUsrId()));
	      	if (permisos.getResources().contains('arqueo')) {
	      		handlerBean.autorizarArqueo(bean, auth_getUserID(),usuario);
	      	} 
        }
        dialogosino("Arqueo", "Arqueo procesado con éxito",
                        "¿Desea procesar una nuevo arqueo?","create", "/bienvenidos");
    }
}


function validarForm(form) {
	var diferencia = BigDecimal.ZERO;
	var autorizado = form.getChild("autorizado").getValue();
	
	if(autorizado.booleanValue()==true) {
	  return true;
	}
	if(autorizado.booleanValue()==false) {
		diferencia = form.getChild("diferencia").getValue();
		var widgetMensaje = form.getChild("mensajes de error");    
	 	var auth_user = form.getChild("auth_user");
	 	var auth_passwd = form.getChild("auth_passwd");
	 	var aceptar = form.getChild("aceptar");
	 	    
	     
 	    if (diferencia.compareTo(BigDecimal.ZERO) == -1) {
 	    	form.getChild("mensajes de error").addMessage("Existe una diferencia negativa, ¿ desea autorizar el proceso ?");
 	        auth_user.setState(WidgetState.ACTIVE);
 	        auth_passwd.setState(WidgetState.ACTIVE);
 	        aceptar.setState(WidgetState.ACTIVE);
 	        form.getChild("autorizado").setValue(true);
 	        return false;
 	    } 		
	}	      
	return true;
}

function validarUsuarioForm(form) {
	var autorizado = form.getChild("autorizado").getValue();
	if(autorizado.booleanValue()==true) {
		var passwd = form.getChild("auth_passwd").getValue();
	    var auth_user = form.getChild("auth_user").getValue();
	    var widgetMensaje = form.getChild("mensajes de error");
	    var handlerUser = new Auth_userHandler();
	  	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
	  	var md5 = encriptar.encriptarMD5(passwd);  
	  	if(auth_user != null && passwd != null){
	  		var usuario = handlerUser.getUsuario(auth_user, md5); 
	  	  	var permisos = new MenuUser(parseInt(usuario.getUsrId()));
	  	  if (permisos.getResources().contains('arqueo')) {  
	  		  return true;
	  	  } else {
	  		form.getChild("mensajes de error").addMessage("El Usuario no tiene persimos para esta operaciòn !");
	  		return false;
	  	  }
	  	} else {
	  		form.getChild("mensajes de error").addMessage("Los campos login y password no debe ser nulos");
	  		return false;
	  	}
	}
	return true;
}