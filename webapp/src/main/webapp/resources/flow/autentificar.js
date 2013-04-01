cocoon.load("context://resources/flow/flow.js");
/*
    Objetivo: Autentificación de usuario
*/

/* Carga los usuarios de la base de datos */
function autentificar() {
    var criteria = cocoon.request.name;
    var handler = new Packages.com.metropolitana.multipagos.forms.AuthHandler();
    var bean = handler.getList(criteria);
    cocoon.sendPage("/usuarios", {"bean": bean});
}

/* Verifica si el usuario tiene permiso para el recurso solicitado */
function autorizar(recurso) {
    var handler = "authhandler";
    var authMgr = null;
    var autorizado = false;
    try {
        authMgr = cocoon.getComponent(Packages.org.apache.cocoon.webapps.authentication.AuthenticationManager.ROLE);

        // autentificación
        if (authMgr.checkAuthentication(null, handler, null)) {
            // Usuario autenticado, tomamos el userID
            var userId = authMgr.getState().getHandler().getUserId();
            // Autorizacion (desde la DB)
            var handler = new Packages.com.metropolitana.multipagos.forms.AuthHandler();
            autorizado = handler.autorizado(parseInt(userId), recurso);
            if (!autorizado)
                cocoon.sendPage("/noautorizado");
        } else
            cocoon.sendPage("/noauthenticado");
    } finally {
        cocoon.releaseComponent(authMgr);
    }
    return autorizado;
}

/* Retorna el userID del usuario de la session */
function auth_getUserID() {
    var handler = "authhandler";
    var authMgr = null;
    try {
        authMgr = cocoon.getComponent(Packages.org.apache.cocoon.webapps.authentication.AuthenticationManager.ROLE);

        // autentificación
        if (authMgr.checkAuthentication(null, handler, null)) {
            // Usuario autenticado, tomamos el userID
            var userId = authMgr.getState().getHandler().getUserId();
            return parseInt(userId);
        }
    } finally {
        cocoon.releaseComponent(authMgr);
    }
    return null;
}
