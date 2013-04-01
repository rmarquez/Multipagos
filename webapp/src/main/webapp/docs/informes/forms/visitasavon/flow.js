function visitasavonform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitasavon-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeGestionAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getGestionAvon(fechaIni, fechaFin);
		var URL = "forms/visitasavon/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitasavon/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
