importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
        var departamentoNombre = form.getChild("departamentoNombre").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("departamentos.list?departamentoNombre="+departamentoNombre+"&filtrar="+filtrar+"&pagina=1");
    }
}

function departamentosList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var departamentoNombre = cocoon.request.getParameter("departamentoNombre");
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (departamentoNombre == "null") {
		departamentoNombre = null;
	}
	if (filtrar == "null") {
        filtrar = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
	var bean = handler.getResultadosXPagina(departamentoNombre, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "departamentoNombre":departamentoNombre, "pagina":pagina, "handler":handler, "filtrar":filtrar});
}