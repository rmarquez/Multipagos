function supervisorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
		var carteraId = !isNaN(parseInt(cocoon.request.carteraId)) ? parseInt(cocoon.request.carteraId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
       	var bean = handler.getLocalidadXCarteraAvon(carteraId);
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    }
}

function barrioCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
		var carteraId = !isNaN(parseInt(cocoon.request.carteraId)) ? parseInt(cocoon.request.carteraId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
       	var bean = handler.getBarrioXCarteraAvon(carteraId);
        cocoon.sendPage("barrioCombo-data", {"bean": bean});
    }
}



function carteradCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler();
        var bean = handlerBean.getCartera();
        cocoon.sendPage("carteraCombo-data", {"bean": bean});
    }
}

function simboloCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloAvonHandler();
        var bean = handlerBean.simboloXNumeroList();
        cocoon.sendPage("simboloCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

