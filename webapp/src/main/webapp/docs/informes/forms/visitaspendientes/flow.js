importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);

function visitaspendientesform(form) {
    if (autorizar("informes")) {
        form.getChild("fechaIni").setValue(Packages.com.metropolitana.multipagos.forms.Util.primeroDelMes());
        form.getChild("fechaFin").setValue(new Packages.java.util.Date());
        form.showForm("visitaspendientes-form-display");
        
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasPendientes();
        var util = Packages.com.metropolitana.multipagos.forms.Util();
        var informeUtil = Packages.com.metropolitana.multipagos.forms.informes.InformesUtil();
        var fecha = form.getChild("asignacionAnio").getValue();
        var fechaIni = form.getChild("fechaIni").getValue();
        var fechaFin = form.getChild("fechaFin").getValue();
        var departamentoId = form.getChild("departamentoId").getValue();
        var localidadId = form.getChild("localidadId").getValue();
        //var barrioId = form.getChild("barrioId").getValue();
        var numAsignacion = form.getChild("numAsignacion").getValue();
        
        var bean = handler.getVisitasPendientes(fecha, fechaIni, fechaFin, departamentoId, localidadId, null, numAsignacion);
		var URL = "forms/visitaspendientes/results.jx";
		if (form.submitId == "excel") {
			URL = "forms/visitaspendientes/results.xls";
		}
		if (form.submitId == "aviso") {
			URL = "forms/visitaspendientes/imprimir.pdf";
		}
		if (form.submitId == "lista") {
			URL = "forms/visitaspendientes/lista.xls";
		}
        cocoon.sendPageAndWait(URL,
             {
			"bean" : bean,
			"fecha" : fecha,
			"fechaIni" : fechaIni,
			"fechaFin" : fechaFin,
			"departamentoId" : departamentoId,
			"localidadId" : localidadId,
			//"barrioId" : barrioId,
			"util" : util,
			"numAsignacion"	: numAsignacion
		});
	}
}

function alSeleccionarNum(event) {
    var numAsignacion = event.source.value;
    var asignacionAnio = event.source.parent.getChild("asignacionAnio");
    asignacionAnio.setValue(null);
    if (numAsignacion == null) {
    	asignacionAnio.setValue(null);
    	asignacionAnio.setSelectionList(new EmptySelectionList("-- Año Asignacion --"));
    } else {
    	asignacionAnio.setSelectionList("cocoon:/asignacionAnio.combo?numAsignacion=" + numAsignacion);
    }
}

function alSeleccionarDepartamento(event) {
    var departamentoId = event.source.value;
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    if (departamentoId == null) {
    	localidadId.setValue(null);
    	localidadId.setSelectionList(new EmptySelectionList("-- Localidad --"));
    } else {
    	localidadId.setSelectionList("cocoon:/localidad.combo?departamentoId=" + departamentoId);
    }
}

function alSeleccionarLocalidad(event) {
	var localidadId = event.source.value;
    var barrioId = event.source.parent.getChild("barrioId");  
    barrioId.setValue(null);
    if (localidadId == null) {
    	barrioId.setValue(null);
    	barrioId.setSelectionList(new EmptySelectionList("-- Barrio --"));
	 } else {
		 barrioId.setSelectionList("cocoon:/barrio.combo?localidadId=" + localidadId);
    }
}

function alSeleccionarBarrio(event) {
	var barrioId = event.source.value;
    var numeroPendientes = event.source.parent.getChild("numeroPendientes"); 
    var fecha = event.source.parent.getChild("asignacionAnio").getValue();
    var fechaIni = event.source.parent.getChild("fechaIni").getValue();
    var fechaFin = event.source.parent.getChild("fechaFin").getValue();
    var departamentoId = event.source.parent.getChild("departamentoId").getValue();
    var localidadId = event.source.parent.getChild("localidadId").getValue();
    var numAsignacion = event.source.parent.getChild("numAsignacion").getValue();
    var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformeVisitasPendientes();
    numeroPendientes.setValue(null);
    if (barrioId != null) {
    	var pedientes = handler.contarPendientes(fecha, fechaIni, fechaFin, departamentoId, localidadId, barrioId, numAsignacion);
    	numeroPendientes.setValue(pedientes);
	 } 
}

function alSeleccionarFecha(event) {
	var asignacionAnio = event.source.value;
    var departamentoId = event.source.parent.getChild("departamentoId");  
    departamentoId.setValue(null);
    if (asignacionAnio == null) {
    	departamentoId.setValue(null);
    	departamentoId.setSelectionList(new EmptySelectionList("-- Fecha Asignacion --"));
	 } else {
		 departamentoId.setSelectionList("cocoon:/departamentosAsignacion.combo?asignacionAnio=" + asignacionAnio);
    }
}
