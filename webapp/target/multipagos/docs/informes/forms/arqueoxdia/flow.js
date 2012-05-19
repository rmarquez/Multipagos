function arqueoxdiaform(form) {
    if (autorizar("cata")) {
        form.getChild("fecha").setValue(new Packages.java.util.Date());
        form.showForm("arqueoxdia-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePromesasPago();
        var fecha = form.getChild("fecha").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        var bean = handler.getPromesasPago(fechaIni, fechaFin, colectorId);
		var URL = "forms/arqueoxdia/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/arqueoxdia/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fecha":fecha, "colectorId":colectorId});
    }
}
