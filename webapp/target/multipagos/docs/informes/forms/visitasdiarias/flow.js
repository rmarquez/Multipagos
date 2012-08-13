function visitasdiariasform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitasdiarias-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasDiarias();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        
        var bean = handler.getVisitasDiarias(fechaIni, fechaFin, departamentoId, colectorId);
		var URL = "forms/visitasdiarias/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitasdiarias/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "departamentoId":departamentoId, "colectorId":colectorId});
    }
}