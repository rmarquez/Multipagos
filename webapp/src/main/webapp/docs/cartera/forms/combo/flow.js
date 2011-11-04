function departamentosCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentosCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
	var bean = null;
	if (autorizar("cata")) {
		var departamentoId = !isNaN(parseInt(cocoon.request.departamentoId)) ? parseInt(cocoon.request.departamentoId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
       	bean = handler.getLocalidadXDepartamento(departamentoId);        
    }
	cocoon.sendPage("localidadCombo-data", {"bean": bean});
}

function barrioCombo() {
	var bean = null;
    if (autorizar("cata")) {
    	var localidadId = !isNaN(parseInt(cocoon.request.localidadId)) ? parseInt(cocoon.request.localidadId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        bean = handler.getBarrioXLocalidad(localidadId);        
    }
    cocoon.sendPage("barrioCombo-data", {"bean": bean});
}

function servicioCombo() {
    if (autorizar("cata")) {
    	var carteraId = !isNaN(parseInt(cocoon.request.carteraId)) ? parseInt(cocoon.request.carteraId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var bean = handler.getServicioXCartera(carteraId);
        cocoon.sendPage("servicioCombo-data", {"bean": bean});
    }
}

function estadoCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
        var bean = handler.getList();
        cocoon.sendPage("estadoCombo-data", {"bean": bean});
    }
}

function simboloCombo() {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
        var bean = handlerBean.simboloXNumeroList();
        cocoon.sendPage("simboloCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

