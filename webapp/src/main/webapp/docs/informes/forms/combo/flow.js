function colectorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

function departamentosCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentosCombo-data", {"bean": bean});
    }
}

function servicioCombo() {
    if (autorizar("cata")) {
    	var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var bean = handler.getServicioList();
        cocoon.sendPage("servicioCombo-data", {"bean": bean});
    }
}

function asignacionAnioCombo() {
    if (autorizar("cata")) {
		var servicioId = !isNaN(parseInt(cocoon.request.servicioId)) ? parseInt(cocoon.request.servicioId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
       	var bean = handler.getAnioAsignacion(servicioId);
        cocoon.sendPage("asignacionAnioCombo-data", {"bean": bean});
    }
}

