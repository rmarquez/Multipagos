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
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler();
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

function barrioCombo() {
	var bean = null;
    if (autorizar("agregar") || autorizar("editar")) {
    	var localidadId = !isNaN(parseInt(cocoon.request.localidadId)) ? parseInt(cocoon.request.localidadId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        bean = handler.getBarrioXLocalidad(localidadId);        
    }
    cocoon.sendPage("barrioCombo-data", {"bean": bean});
}
