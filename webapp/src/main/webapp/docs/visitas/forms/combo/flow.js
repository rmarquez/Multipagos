function supervisorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
    //if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
       	var bean = handler.getList();
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    //}
}

function carteradCombo() {
    //if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        var bean = handlerBean.getCartera();
        cocoon.sendPage("carteraCombo-data", {"bean": bean});
    //}
}

function simboloCombo() {
    //if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
        var bean = handlerBean.simboloXNumeroList();
        cocoon.sendPage("simboloCombo-data", {"bean": bean});
    //}
}

function colectorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}
function servicioCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var bean = handler.getServicioList();
        cocoon.sendPage("servicioCombo-data", {"bean": bean});
    }
}