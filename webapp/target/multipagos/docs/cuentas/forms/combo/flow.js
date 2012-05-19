function bancoCombo() {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var bean = handler.getList();
        cocoon.sendPage("bancoCombo-data", {"bean": bean});
    }
}