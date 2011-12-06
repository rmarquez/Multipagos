importClass(Packages.java.lang.Integer);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
/*
  Objetivo: Simple formulario de inicio de Sesión
*/

function entradaform(form) {
	var handlerUser = new Auth_userHandler();
	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
	var redirect = cocoon.parameters["protected-redirect"];
    form.showForm("entrada-vista");
    var passwd = form.getChild("auth_passwd").getValue();
    var md5 = encriptar.encriptarMD5(passwd);
    /**var usuario = handlerUser.getUsuario(form.getChild("auth_user").getValue(), md5);
    if(usuario == null){
    	form.getChild("mensajes de error").addMessage("Usuario o password incorrecto, intente nuevamente.");
    } else {**/
    	cocoon.sendPage("do-login?resource=" + redirect + "&username=" + form.getChild("auth_user").getValue() + "&passwd=" + md5);
    //}
}
