function detallevisitasform(form) {
    if (autorizar("informes")) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.showForm("detallevisitas-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeAvonConsolidadoVisitas();
        var fechaIni = form.getChild("fechaIni").getValue();
        
        var bean = handler.getDetallesVisitas(fechaIni);
		var URL = "forms/detallevisitas/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/detallevisitas/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni});
    }
}
