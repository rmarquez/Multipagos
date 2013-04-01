function supervisorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("cata")) {
    	var usrId = !isNaN(parseInt(cocoon.request.usrId)) ? parseInt(cocoon.request.usrId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarColectorHandler();
        var bean = handler.getColectoresAsignados(usrId);
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

function barrioCombo() {
    if (autorizar("cata")) {
		var colectorId = !isNaN(parseInt(cocoon.request.colectorId)) ? parseInt(cocoon.request.colectorId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
        var util = Packages.com.metropolitana.multipagos.forms.Util();
        var bean = handler.getBarriosAsignados(colectorId);
        cocoon.sendPage("barrioCombo-data", {"bean": bean, "util": util});
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
        var util = Packages.com.metropolitana.multipagos.forms.Util();
       	var bean = handler.getAnioAsignacion(servicioId);
        cocoon.sendPage("asignacionAnioCombo-data", {"bean": bean, "util": util});
    }
}
