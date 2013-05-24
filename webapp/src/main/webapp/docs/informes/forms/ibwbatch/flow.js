function ibwbatchform(form) {
	if (autorizar('ibwinformes')) {
    	form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
    	form.getChild("fechaFin").setValue(new Packages.java.util.Date());
    	form.showForm("ibwbatch-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeBatchIbw();
        var tasah = new Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();
        var util = Packages.com.metropolitana.multipagos.forms.Util();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        
        var bean = handler.getPagosBatch(fechaIni, fechaFin);
		var URL = "forms/ibwbatch/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/ibwbatch/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "tasah":tasah, "util":util});
    }
}
