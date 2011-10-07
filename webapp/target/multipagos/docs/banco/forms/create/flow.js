function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Banco();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Bancos", "Banco procesado con éxito",
                        "¿Desea procesar un nuevo banco?","create", "/bienvenidos");
    }
}
