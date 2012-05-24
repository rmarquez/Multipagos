function visitasdiariasform(form) {
    if (autorizar("informes")) {
        form.getChild("fecha").setValue(new Packages.java.util.Date());
        form.showForm("visitasdiarias-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasDiarias();
        var fecha = form.getChild("fecha").getValue();
        
        var bean = handler.getVisitasDiarias(fecha);
		var URL = "forms/visitasdiarias/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitasdiarias/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fecha":fecha});
    }
}
