function auth_roleCombo() {
    if (autorizar("auth")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler();
        var bean = handler.getList(new String);
        cocoon.sendPage("auth_roleCombo-data", {"bean": bean});
    }
}

function auth_userCombo() {
    if (autorizar("auth")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getListAll(cocoon.request.usrLogin);
        cocoon.sendPage("auth_userCombo-data", {"bean": bean});
    }
}

/* Cuando el usuario intente cambiar su contraseña, unicamente se le permite
   ver en la lista su usuario */
function solo_userCombo() {
	/* Creamos el bean basado en los parametros */
	var usuario = new Packages.com.metropolitana.multipagos.AuthUser();
	usuario.setUsrId(parseInt(cocoon.request.usrId));
	usuario.setUsrLogin(cocoon.request.usrLogin);

	/* Creamos la lista para almacenar el único elemento */
	var bean = new Packages.java.util.ArrayList();
	bean.add(usuario);

	cocoon.sendPage("auth_userCombo-data", {"bean": bean});
}
