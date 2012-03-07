importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
/**
    Este formulario contiene un control upload para subir archivos,
    con botones de Adjuntar
    Parámetros:
    	Este formulario almacena los archivos en la carpeta
    	resources/files para luego tomar el xls y migrar los datos
    	a postgres
*/
cocoon.load("resource://org/apache/cocoon/forms/flow/javascript/Form.js");

function createform(form) {
	
	var usrId = auth_getUserID();
	var handlerUser = new Auth_userHandler();
	var usuario = handlerUser.retrieve(usrId);
	if((usuario.getUsrEditdb()).equals(Boolean.TRUE)){
	
        form.showForm("create-form-display");
        
        var formUpload = new Form("forms/create/definition.xml");
        
	    cocoon.sendPage("migrar");  
	}
	cocoon.sendPage("/noautorizado");
}

function validarForm(form) {
	var widgetMensaje = form.getChild("mensajes de error");
	
	var pagado = form.getChild("pagado").getValue();
	var pendiente = form.getChild("pendiente").getValue();	
	
	if(pagado.equals(Boolean.TRUE) && pendiente.equals(Boolean.TRUE)){ 		
		form.getChild("mensajes de error").addMessage("No pueden seleccionar los conceptos pagados y pendientes al mismo tiempo, seleccionar solo uno");
		return false;	    	
	} 
			  
	return true;
}

