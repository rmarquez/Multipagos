importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        var barrioNombre = form.getChild("barrioNombre").getValue();
        var localidadId = form.getChild("localidadId").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("barrio.list?barrioNombre="+barrioNombre+"&localidadId="+localidadId+"&filtrar="+filtrar+"&pagina=1");
    }
}

function barrioList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var barrioNombre = cocoon.request.getParameter("barrioNombre");
	var localidadId = null;
	if (cocoon.request.getParameter("localidadId") != "null" && cocoon.request.getParameter("localidadId") != '') {
    	localidadId = Integer.valueOf(cocoon.request.getParameter("localidadId"));
	}
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	//var filtrar = cocoon.request.getParameter("filtrar");
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (barrioNombre == "null") {
		barrioNombre = null;
	}
	if (localidadId == "null") {
        localidadId = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
	var bean = handler.getResultadosXPagina(barrioNombre, localidadId, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "barrioNombre":barrioNombre, "pagina":pagina, "handler":handler, "localidadId":localidadId, "filtrar":filtrar});
}