importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var servicioNombre = form.getChild("servicioNombre").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("servicio.list?servicioNombre="+servicioNombre+"&filtrar="+filtrar+"&pagina=1");
    }
}

function servicioList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var servicioNombre = cocoon.request.getParameter("servicioNombre");
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (servicioNombre == "null") {
		servicioNombre = null;
	}
	if (filtrar == "null") {
        filtrar = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
	var bean = handler.getResultadosXPagina(servicioNombre, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "servicioNombre":servicioNombre, "pagina":pagina, "handler":handler, "filtrar":filtrar});
}