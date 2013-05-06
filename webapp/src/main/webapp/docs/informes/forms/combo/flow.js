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

function departamentoIbwCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.IDepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentoIbwCombo-data", {"bean": bean});
    }
}

function simboloBanproCombo() {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloBanproHandler();
        var bean = handlerBean.simboloXNumeroList();
        cocoon.sendPage("simboloBanproCombo-data", {"bean": bean});
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
		var numAsignacion = !isNaN(parseInt(cocoon.request.numAsignacion)) ? parseInt(cocoon.request.numAsignacion): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        var util = Packages.com.metropolitana.multipagos.forms.Util();
       	var bean = handler.getAnioAsignacion(numAsignacion);
        cocoon.sendPage("asignacionAnioCombo-data", {"bean": bean, "util": util});
    }
}

/*function aAsignacionCombo() {
    if (autorizar("cata")) {
    	var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        var bean = handler.getAAsignacion();
        cocoon.sendPage("aAsignacionCombo-data", {"bean": bean});
    }
}*/

function numAsignacionCombo() {
    if (autorizar("cata")) {
		var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformesUtil();
        var bean = handler.getNumAsignacion();
        cocoon.sendPage("numAsignacionCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
    if (autorizar("cata")) {
		var departamentoId = !isNaN(parseInt(cocoon.request.departamentoId)) ? parseInt(cocoon.request.departamentoId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformesUtil();
        var bean = handler.getLocalidadXDepartamento(departamentoId);
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    }
}

function barrioCombo() {
    if (autorizar("cata")) {
		var localidadId = !isNaN(parseInt(cocoon.request.localidadId)) ? parseInt(cocoon.request.localidadId): null;
		var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformesUtil();
        var bean = handler.getBarrioXLocalidad(localidadId);
        cocoon.sendPage("barrioCombo-data", {"bean": bean});
    }
}

function departamentosAsignacionCombo() {
    if (autorizar("cata")) {
    	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.ScriptUtil();
    	var fecha = handlerUtil.strToDate(cocoon.request.getParameter("asignacionAnio"));
    	var handler = new Packages.com.metropolitana.multipagos.forms.informes.InformesUtil();
        var bean = handler.getDepartamentoXAsignacion(fecha);
        cocoon.sendPage("departamentosAsignacionCombo-data", {"bean": bean});
    }
}

