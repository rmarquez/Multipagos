function pagosxdepartamentoform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("pagosxdepartamento-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformePagosXDepartamento();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        var colectorId = form.getChild("colectorId").getValue();
        
        var bean = handler.getPagosXDepartamentos(fechaIni, fechaFin, departamentoId, colectorId);
		var URL = "forms/pagosxdepartamento/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/pagosxdepartamento/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fechaIni":fechaIni, "fechaFin":fechaFin, "departamentoId":departamentoId, "colectorId":colectorId});
    }
}
