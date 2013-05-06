function detallepagosibwform(form) {
	if (autorizar('ibwinformes')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
    	form.getChild("fechaFin").setValue(new Packages.java.util.Date());
    	form.showForm("detallepagosibw-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePagosIbw();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getDetallesPagos(fechaIni, fechaFin);
		var URL = "forms/detallepagosibw/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/detallepagosibw/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin});
    }
}
