importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var primerNombre = form.getChild("primerNombre").getValue();
        var primerApellido = form.getChild("primerApellido").getValue();
        var cedula = form.getChild("cedula").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("colector.list?primerNombre="+primerNombre+"&primerApellido="+primerApellido+"&cedula="+cedula+"&filtrar="+filtrar+"&pagina=1");
    }
}

function colectorList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var primerNombre = cocoon.request.getParameter("primerNombre");
	var primerApellido = cocoon.request.getParameter("primerApellido");
	var cedula = cocoon.request.getParameter("cedula");
	
	if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
	//var filtrar = cocoon.request.getParameter("filtrar");
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (primerNombre == "null") {
		primerNombre = null;
	}
	if (primerApellido == "null") {
        primerApellido = null;
    }
    if (cedula == "null") {
        cedula = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
	var bean = handler.getResultadosXPagina(primerNombre, primerApellido, cedula, filtrar, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "primerNombre":primerNombre, "pagina":pagina, "handler":handler, "primerApellido":primerApellido, "cedula":cedula, "filtrar":filtrar});
}