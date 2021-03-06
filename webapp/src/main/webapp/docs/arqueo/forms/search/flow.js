function searchform(form) {
    if (autorizar("buscar")) {
        form.getChild("fecha").setValue(new Packages.java.util.Date());
        form.showForm("search-form-display");
        
        var fecha = form.getChild("fecha").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        cocoon.sendPage("arqueo.list?fecha="+fecha+"&colectorId="+colectorId+"&pagina=1");       
        
    }
}


function arqueoList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);
	var fecha = null;
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.ScriptUtil();
	if (cocoon.request.getParameter("fecha") != null){
		fecha = handlerUtil.strToDate(cocoon.request.getParameter("fecha"));
	}
	
	var colectorId = null;
	if (cocoon.request.getParameter("colectorId") != "null" && cocoon.request.getParameter("colectorId") != '') {
		colectorId = Integer.valueOf(cocoon.request.getParameter("colectorId"));
	}	
	//var filtrar = cocoon.request.getParameter("filtrar");
	// Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (colectorId == "null") {
		colectorId = null;
	}
	
	var handler = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
	var bean = handler.getResultadosXPagina(fecha, colectorId, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "fecha":fecha, "pagina":pagina, "handler":handler, "colectorId":colectorId});
}
