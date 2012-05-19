function promesasdepagoform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("promesasdepago-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePromesasPago();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        var bean = handler.getPromesasPago(fechaIni, fechaFin, colectorId);
		var URL = "forms/promesasdepago/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/promesasdepago/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "colectorId":colectorId});
    }
}
