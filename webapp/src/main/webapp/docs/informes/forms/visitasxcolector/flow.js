function visitasxcolectorform(form) {
    if (autorizar("cata")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitasxcolector-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasXColector();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        var bean = handler.getVisitasXColector(fechaIni, fechaFin, colectorId);
		var URL = "forms/visitasxcolector/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitasxcolector/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "colectorId":colectorId});
    }
}