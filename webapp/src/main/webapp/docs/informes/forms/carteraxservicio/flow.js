importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function carteraxservicioform(form) {
    if (autorizar("informes")) {
        form.showForm("carteraxservicio-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeCarteraXServicio();
        var departamentoId = form.getChild("departamentoId").getValue();
        var servicioId = form.getChild("servicioId").getValue();
        var pagado = form.getChild("pagado").getValue();
        var pagadoClaro = form.getChild("pagadoClaro").getValue();
        
        var bean = handler.getCarteraXServicio(departamentoId, servicioId, pagado, pagadoClaro);
		var URL = "forms/carteraxservicio/results.xls";
		
        cocoon.sendPageAndWait(URL, { "bean":bean, "departamentoId":departamentoId, "servicioId":servicioId, "pagado":pagado, "pagadoClaro":pagadoClaro});
    }
}
