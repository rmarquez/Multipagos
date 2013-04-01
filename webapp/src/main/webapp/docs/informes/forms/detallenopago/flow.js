function detallenopagoform(form) {
	if (autorizar('iavon')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
    	form.getChild("fechaFin").setValue(new Packages.java.util.Date());
    	form.showForm("detallenopago-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeNoPagoAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getDetallesNoPago(fechaIni, fechaFin);
		var URL = "forms/detallenopago/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/detallenopago/results.xls";
		}
        cocoon.sendPageAndWait(URL, { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
