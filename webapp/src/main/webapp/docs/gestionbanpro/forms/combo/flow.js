function supervisorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function ciudadcasaCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
		var tmpId = !isNaN(parseInt(cocoon.request.tmpId)) ? parseInt(cocoon.request.tmpId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.ciudad.CiudadHandler();
       	var bean = handler.getCiudadCXCartera(tmpId);
        cocoon.sendPage("ciudadcasaCombo-data", {"bean": bean});
    }
}

function departamentoCombo() {
	//if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.BDepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentoCombo-data", {"bean": bean});
    //}
}

function carteradCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler();
        var bean = handlerBean.getCartera();
        cocoon.sendPage("carteraCombo-data", {"bean": bean});
    }
}

function simboloCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloBanproHandler();
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

