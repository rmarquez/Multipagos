function visitasxcolectorform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitasxcolector-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasXColector();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        
        var bean = handler.getVisitasXColector(fechaIni, fechaFin, departamentoId, colectorId);
		var URL = "forms/visitasxcolector/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitasxcolector/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "departamentoId":departamentoId, "colectorId":colectorId});
    }
}
