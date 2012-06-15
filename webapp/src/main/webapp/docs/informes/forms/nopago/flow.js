function nopagoform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        //form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("nopago-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeNoPagoAvon();
        var fechaIni = form.getChild("fechaIni").getValue();
        
        var bean = handler.getConsolidadosNoPago(fechaIni);
		var URL = "forms/nopago/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/nopago/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni});
    }
}
