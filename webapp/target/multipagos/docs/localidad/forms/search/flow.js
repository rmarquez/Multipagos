importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Long);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
        var localidadNombre = form.getChild("localidadNombre").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("localidad.list?localidadNombre="+localidadNombre+"&departamentoId="+departamentoId+"&filtrar="+filtrar+"&pagina=1");
    }
}

function localidadList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var localidadNombre = cocoon.request.getParameter("localidadNombre");
	var departamentoId = null;
	if (cocoon.request.getParameter("departamentoId") != "null" && cocoon.request.getParameter("departamentoId") != '') {
    	departamentoId = Integer.valueOf(cocoon.request.getParameter("departamentoId"));
	}
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	//var filtrar = cocoon.request.getParameter("filtrar");
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (localidadNombre == "null") {
		localidadNombre = null;
	}
	if (departamentoId == "null") {
        departamentoId = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
	var bean = handler.getResultadosXPagina(localidadNombre, departamentoId, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "localidadNombre":localidadNombre, "pagina":pagina, "handler":handler, "departamentoId":departamentoId, "filtrar":filtrar});
}