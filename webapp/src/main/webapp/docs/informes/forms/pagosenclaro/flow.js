function pagosenclaroform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("pagosenclaro-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePagosClaro();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        var servicioId = form.getChild("servicioId").getValue();
        
        var bean = handler.getPagosClaro(fechaIni, fechaFin, departamentoId, servicioId);
		var URL = "forms/pagosenclaro/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/pagosenclaro/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "departamentoId":departamentoId, "servicioId":servicioId});
    }
}
