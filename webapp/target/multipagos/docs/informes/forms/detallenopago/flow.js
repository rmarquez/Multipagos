function detallenopagoform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.showForm("detallenopago-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeNoPagoAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        
        var bean = handler.getDetallesNoPago(fechaIni);
		var URL = "forms/detallenopago/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/detallenopago/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni});
    }
}
