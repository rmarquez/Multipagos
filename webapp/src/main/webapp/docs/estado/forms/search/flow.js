importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
        var estadoNombre = form.getChild("estadoNombre").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("estado.list?estadoNombre="+estadoNombre+"&filtrar="+filtrar+"&pagina=1");
    }
}

function estadoList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var estadoNombre = cocoon.request.getParameter("estadoNombre");
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (estadoNombre == "null") {
		estadoNombre = null;
	}
	if (filtrar == "null") {
        filtrar = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
	var bean = handler.getResultadosXPagina(estadoNombre, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "estadoNombre":estadoNombre, "pagina":pagina, "handler":handler, "filtrar":filtrar});
}