function estadogestionform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("estadogestion-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeEstadoGestion();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var simboloId = form.getChild("simboloId").getValue();
        
        var bean = handler.getEstadoGestion(fechaIni, fechaFin, simboloId);
		var URL = "forms/estadogestion/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/estadogestion/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "simboloId":simboloId});
    }
}
