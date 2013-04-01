importClass(Packages.java.lang.Integer);
function searchform(form) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("search-form-display");

        var handler = new Packages.com.metropolitana.multipagos.forms.logs.LogsHandler();
        var fechaini = form.getChild("fechaIni").getValue();
        var fechafin = form.getChild("fechaFin").getValue();

        var tipodLogId = form.getChild("tipodLogId").getValue();

        cocoon.sendPage("logs.list?fechaini="+fechaini+"&fechafin="+fechafin+"&tipodLogId="+tipodLogId+"&pagina=1");
}

function logsList() {
	var pagina = Integer.valueOf(cocoon.request.pagina);

	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.ScriptUtil();
	var fechaIni = null;
	if (cocoon.request.getParameter("fechaini") != null){
		fechaIni = handlerUtil.strToDate(cocoon.request.getParameter("fechaini"));
	}
	var fechaFin = null;
	if (cocoon.request.getParameter("fechafin") != "null" && cocoon.request.getParameter("fechafin") != '') {
		fechaFin = handlerUtil.strToDate(cocoon.request.getParameter("fechafin"));
	}
    var tipodLogId = null;
    if (cocoon.request.getParameter("tipodLogId") != "null" && cocoon.request.getParameter("tipodLogId") != '') {
    	tipodLogId = Integer.valueOf(cocoon.request.getParameter("tipodLogId"));
	}
	// TODO Esto es necesario porque el servlet esta regresando una cadena
	// con el contenido null no un valor.
	if (tipodLogId == "null") {
		tipodLogId = null;
	}
	var handler = new Packages.com.metropolitana.multipagos.forms.logs.LogsHandler();
	var bean = handler.getListPaginado(fechaIni, fechaFin, tipodLogId, pagina, cocoon.session.registrosPorPagina);


	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "fechaFin":fechaFin, "fechaFin":fechaFin, "tipodLogId":tipodLogId,
	 				"pagina":pagina, "handler":handler});
}

