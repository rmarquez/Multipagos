importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        //form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        
        var cantidadNombre = form.getChild("criteria").getValue();
        //var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("cantidades.list?cantidadNombre="+cantidadNombre+"&pagina=1");

    }
}

function cantidadesList() {
    var pagina = Integer.valueOf(cocoon.request.pagina);
    var cantidadNombre = cocoon.request.getParameter("cantidadNombre");
    /*if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}*/
    // Esto es necesario porque el servlet esta regresando una cadena
    // con el contenido null no un valor.
    if (cantidadNombre == "null") {
        cantidadNombre = null;
    }
    var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
    var bean = handler.getResultadosXPagina(cantidadNombre, pagina, cocoon.session.registrosPorPagina);
    cocoon.sendPage("forms/search/results.jx", {"bean":bean, "cantidadNombre":cantidadNombre,
                    "pagina":pagina, "handler":handler});
}
