importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Long);
importClass(Packages.java.lang.Boolean);
function searchform(form) {
    if (autorizar("buscar")) {
    	var editar = false;
    	
        form.showForm("search-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
        var fecha = form.getChild("fecha").getValue();
        var usrId = form.getChild("usrId").getValue();
        // Parámetros para mostrar la primer página.
        cocoon.sendPage("visitas.list?fecha="+fecha+"&usrId="+usrId+"&pagina=1");
    }
}

function visitasList() {
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.ScriptUtil();
	var pagina = Integer.valueOf(cocoon.request.pagina);
	
	var fecha = null;
	if (cocoon.request.getParameter("fecha") != null){
		fecha = handlerUtil.strToDate(cocoon.request.getParameter("fecha"));
	}
	var usrId = null;
	if (cocoon.request.getParameter("usrId") != "null" && cocoon.request.getParameter("usrId") != '') {
    	usrId = Integer.valueOf(cocoon.request.getParameter("usrId"));
	} 
	
	if (usrId == "null") {
		usrId = null;
    }
	var handler = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
	var bean = handler.getResultadosXPagina(fecha, usrId, pagina, cocoon.session.registrosPorPagina);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "fecha":fecha, "pagina":pagina, "handler":handler, "usrId":usrId});
}

function borrarList() {
	var visitaId = Integer.valueOf(cocoon.request.getParameter("visitaId"));
	var handler = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.ScriptUtil();
	var fecha = handlerUtil.strToDate(cocoon.request.getParameter("fecha"));
	
	if (cocoon.request.getParameter("usrId") == "null" && cocoon.request.getParameter("usrId") == '') {
    	var usrId = Integer.valueOf(cocoon.request.getParameter("usrId"));
	} else {
		var usrId = null
	}
	handler.remove(visitaId, auth_getUserID());	
	cocoon.sendPage("visitas.list?fecha="+fecha+"&usrId="+usrId+"&pagina="+Integer.valueOf(cocoon.request.pagina));
}