importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.java.lang.Boolean);
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
	var editarDb = usuario.getUsrEditdb();
	//if(editarDb.equals(Boolean.TRUE)){
	
        form.showForm("create-form-display");
        
        var formUpload = new Form("forms/create/definition.xml");
        //var numAsignacion = form.getChild("numAsignacion").getValue();
        //cocoon.sendPage("migrar?numAsignacion="+numAsignacion);
        cocoon.sendPage("migrar");
	//}
	//cocoon.sendPage("/noautorizado");
}

