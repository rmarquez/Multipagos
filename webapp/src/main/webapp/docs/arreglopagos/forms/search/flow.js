importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
    	form.showForm("search-form-display");
        //var handler = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
        var codCliente = form.getChild("criteria").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("arreglo.list?codCliente="+codCliente+"&pagina=1");

    }
}

function arregloList() {
    var pagina = Integer.valueOf(cocoon.request.pagina);
    var codCliente = cocoon.request.getParameter("codCliente");
    // Esto es necesario porque el servlet esta regresando una cadena
    // con el contenido null no un valor.
    if (codCliente == "null") {
    	codCliente = null;
    }
    var handler = new Packages.com.metropolitana.multipagos.forms.arreglo_pago.ArregloPagoHandler();
    var bean = handler.getResultadosXPagina(codCliente, pagina, cocoon.session.registrosPorPagina);
    cocoon.sendPage("forms/search/results.jx", {"bean":bean, "codCliente":codCliente,
                    "pagina":pagina, "handler":handler});
}
