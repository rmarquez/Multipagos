function supervisorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
       	var bean = handler.getLocalidadList();
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    }
}



function carteradCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        var bean = handlerBean.getCartera();
        cocoon.sendPage("carteraCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}
function servicioCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var carteraId = !isNaN(parseInt(cocoon.request.carteraId)) ? parseInt(cocoon.request.carteraId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var bean = handler.getServicioXCartera(carteraId);
        cocoon.sendPage("servicioCombo-data", {"bean": bean});
    }
}

function departamentosCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentosCombo-data", {"bean": bean});
    }
}

function estadoCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
        var bean = handler.getList();
        cocoon.sendPage("estadoCombo-data", {"bean": bean});
    }
}

function barrioCombo() {
	var bean = null;
    if (autorizar("agregar") || autorizar("editar")) {
    	var localidadId = !isNaN(parseInt(cocoon.request.localidadId)) ? parseInt(cocoon.request.localidadId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        bean = handler.getBarrioXLocalidad(localidadId);        
    }
    cocoon.sendPage("barrioCombo-data", {"bean": bean});
}
