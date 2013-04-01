importClass(Packages.java.lang.Integer);
function searchform(form) {
    if (autorizar("cata")) {
        form.getChild("filtrar").setValue(Integer.valueOf(3));
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var bancoNombre = form.getChild("criteria").getValue();
        var filtrar = form.getChild("filtrar").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("bancos.list?bancoNombre="+bancoNombre+"&filtrar="+filtrar+"&pagina=1");

    }
}

function bancosList() {
    var pagina = Integer.valueOf(cocoon.request.pagina);
    var bancoNombre = cocoon.request.getParameter("bancoNombre");
    if (cocoon.request.getParameter("filtrar") != "null" && cocoon.request.getParameter("filtrar") != '') {
    	var filtrar = Integer.valueOf(cocoon.request.getParameter("filtrar"));
	}
    // Esto es necesario porque el servlet esta regresando una cadena
    // con el contenido null no un valor.
    if (bancoNombre == "null") {
        bancoNombre = null;
    }
    var handler = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
    var bean = handler.getResultadosXPagina(bancoNombre, filtrar, pagina, cocoon.session.registrosPorPagina);
    cocoon.sendPage("forms/search/results.jx", {"bean":bean, "bancoNombre":bancoNombre,
                    "pagina":pagina, "handler":handler, "filtrar":filtrar});
}
