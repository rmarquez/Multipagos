function supervisorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.getList();
        cocoon.sendPage("supervisorCombo-data", {"bean": bean});
    }
}

function colectorCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
        var bean = handler.getList();
        cocoon.sendPage("colectorCombo-data", {"bean": bean});
    }
}

