function supervisorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

function cantidadcsCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var bean = handler.getList(false);
        cocoon.sendPage("cantidadcsCombo-data", {"bean": bean});
    }
}

function cantidadusCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var bean = handler.getList(true);
        cocoon.sendPage("cantidadusCombo-data", {"bean": bean});
    }
}

function bancoCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var bean = handler.getList();
        cocoon.sendPage("bancoCombo-data", {"bean": bean});
    }
}