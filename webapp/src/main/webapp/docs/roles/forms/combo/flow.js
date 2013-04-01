function auth_resourceCombo() {
    if (autorizar("auth")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_resource.Auth_resourceHandler();
        var bean = handler.getList();
        cocoon.sendPage("auth_resourceCombo-data", {"bean": bean});
    }
}
