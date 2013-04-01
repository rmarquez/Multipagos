function departamentosCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
        var bean = handler.getList();
        cocoon.sendPage("departamentosCombo-data", {"bean": bean});
    }
}

function localidadCombo() {
    if (autorizar("cata")) {
		var departamentoId = !isNaN(parseInt(cocoon.request.departamentoId)) ? parseInt(cocoon.request.departamentoId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
        var bean = handler.getLocalidadXDepartamento(departamentoId);
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    }
}

function barrioCombo() {
    if (autorizar("cata")) {
		var localidadId = !isNaN(parseInt(cocoon.request.localidadId)) ? parseInt(cocoon.request.localidadId): null;
        var handler = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        var bean = handler.getBarrioXLocalidad(localidadId);
        cocoon.sendPage("barrioCombo-data", {"bean": bean});
    }
}
