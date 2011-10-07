/*
  Objetivo: Simple formulario de inicio de Sesión
*/

function entradaform(form) {
	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
	var redirect = cocoon.parameters["protected-redirect"];
    form.showForm("entrada-vista");
    var passwd = form.getChild("auth_passwd").getValue();
    var md5 = encriptar.encriptarMD5(passwd);
    cocoon.sendPage("do-login?resource=" + redirect + "&username=" + form.getChild("auth_user").getValue() + "&passwd=" + md5);
}
