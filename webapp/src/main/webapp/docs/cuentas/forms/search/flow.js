importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
    	form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
        var cuentaEmpresa = form.getChild("criteria").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("cuentas.list?cuentaEmpresa="+cuentaEmpresa+"&filtrar="+filtrar+"&pagina=1");

    }
}

function cuentasList() {
    var pagina = Integer.valueOf(cocoon.request.pagina);
    var cuentaEmpresa = cocoon.request.getParameter("cuentaEmpresa");
    if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
    
    // Esto es necesario porque el servlet esta regresando una cadena
    // con el contenido null no un valor.
    if (cuentaEmpresa == "null") {
    	cuentaEmpresa = null;
    }
    var handler = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
    var bean = handler.getResultadosXPagina(cuentaEmpresa, filtrar, pagina, cocoon.session.registrosPorPagina);
    cocoon.sendPage("forms/search/results.jx", {"bean":bean, "cuentaEmpresa":cuentaEmpresa,
                    "pagina":pagina, "handler":handler, "filtrar":filtrar});
}
