function detallevisitasform(form) {
	if (autorizar('iavon')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
    	form.getChild("fechaFin").setValue(new Packages.java.util.Date());
    	form.showForm("detallevisitas-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeAvonConsolidadoVisitas();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getDetallesVisitas(fechaIni, fechaFin);
		var URL = "forms/detallevisitas/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/detallevisitas/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
