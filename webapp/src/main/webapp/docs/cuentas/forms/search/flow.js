importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
        var cuentaEmpresa = form.getChild("criteria").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("cuentas.list?cuentaEmpresa="+cuentaEmpresa+"&pagina=1");

    }
}

function cuentasList() {
    var pagina = Integer.valueOf(cocoon.request.pagina);
    var cuentaEmpresa = cocoon.request.getParameter("cuentaEmpresa");
    
    // Esto es necesario porque el servlet esta regresando una cadena
    // con el contenido null no un valor.
    if (cuentaEmpresa == "null") {
    	cuentaEmpresa = null;
    }
    var handler = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
    var bean = handler.getResultadosXPagina(cuentaEmpresa, pagina, cocoon.session.registrosPorPagina);
    cocoon.sendPage("forms/search/results.jx", {"bean":bean, "cuentaEmpresa":cuentaEmpresa,
                    "pagina":pagina, "handler":handler});
}
