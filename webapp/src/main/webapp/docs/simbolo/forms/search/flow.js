importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
        var simboloNombre = form.getChild("simboloNombre").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("simbolo.list?simboloNombre="+simboloNombre+"&filtrar="+filtrar+"&pagina=1");
    }
}

function simboloList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var simboloNombre = cocoon.request.getParameter("simboloNombre");
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (simboloNombre == "null") {
		simboloNombre = null;
	}
	if (filtrar == "null") {
        filtrar = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
	var bean = handler.getResultadosXPagina(simboloNombre, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "simboloNombre":simboloNombre, "pagina":pagina, "handler":handler, "filtrar":filtrar});
}