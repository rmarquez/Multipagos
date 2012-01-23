importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function visitaspendientesform(form) {
    if (autorizar("cata")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitaspendientes-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasPendientes();
        var fecha = form.getChild("asignacionAnio").getValue();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        var servicioId = form.getChild("servicioId").getValue();
        
        var bean = handler.getVisitasPendientes(fecha, fechaIni, fechaFin, departamentoId, servicioId);
		var URL = "forms/visitaspendientes/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitaspendientes/results.xls";
		}
        cocoon.sendPageAndWait(URL,
            { "bean":bean, "fecha":fecha, "fechaIni":fechaIni, "fechaFin":fechaFin, "departamentoId":departamentoId, "servicioId":servicioId});
    }
}

function alSeleccionarServicio(event) {
    var servicioId = event.source.value;
    var asignacionAnio = event.source.parent.getChild("asignacionAnio");
    asignacionAnio.setValue(null);
    if (servicioId != null) {
    	asignacionAnio.setSelectionList("cocoon:/asignacionAnio.combo?servicioId=" + servicioId);
    } else {
    	asignacionAnio.setSelectionList(new EmptySelectionList("-- Año Asignacion --"));
    }
}
