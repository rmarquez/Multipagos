function promesasavonform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("promesasavon-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePromesasAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        var bean = handler.getPromesasAvon(fechaIni, fechaFin, colectorId);
		var URL = "forms/promesasavon/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/promesasavon/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "colectorId":colectorId});
    }
}
