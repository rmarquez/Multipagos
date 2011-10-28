function localidadCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
        var bean = handler.getLocalidadList();
        cocoon.sendPage("localidadCombo-data", {"bean": bean});
    }
}
