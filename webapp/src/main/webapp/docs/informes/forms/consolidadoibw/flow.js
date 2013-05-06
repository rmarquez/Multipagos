function consolidadoibwform(form) {
    if (autorizar('ibwinformes')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("consolidadoibw-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePagosIbw();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getConsolidadosPagos(fechaIni, fechaFin);
		var URL = "forms/consolidadoibw/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/consolidadoibw/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
