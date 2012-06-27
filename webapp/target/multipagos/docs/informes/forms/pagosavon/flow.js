function pagosavonform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("pagosavon-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePagosAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getPagosAvon(fechaIni, fechaFin);
		var URL = "forms/pagosavon/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/pagosavon/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
