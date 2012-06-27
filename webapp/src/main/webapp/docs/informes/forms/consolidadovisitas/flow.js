function consolidadovisitasform(form) {
    if (autorizar('iavon')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("consolidadovisitas-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeAvonConsolidadoVisitas();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        handler.crearTabla();
        var bean = handler.getConsolidadosVisitas(fechaIni, fechaFin);
		var URL = "forms/consolidadovisitas/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/consolidadovisitas/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
