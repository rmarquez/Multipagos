function promesasbanproform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("promesasbanpro-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePromesasBanpro();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getPromesasBanpro(fechaIni, fechaFin);
		var URL = "forms/promesasbanpro/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/promesasbanpro/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
