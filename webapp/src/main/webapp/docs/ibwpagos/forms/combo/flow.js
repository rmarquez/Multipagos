function supervisorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function municipioCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.municipio.MunicipioHandler();
       	var bean = handler.getMunicipioList();
        cocoon.sendPage("municipioCombo-data", {"bean": bean});
    }
}



function carteradCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraIbwHandler();
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
    	var municipioId = !isNaN(parseInt(cocoon.request.municipioId)) ? parseInt(cocoon.request.municipioId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.IBarrioHandler();
        bean = handler.getBarrioXMunicipio(municipioId);        
    }
    cocoon.sendPage("municipioCombo-data", {"bean": bean});
}
