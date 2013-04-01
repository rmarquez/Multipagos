function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Colector();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Colector", "Colector procesado con éxito",
                        "¿Desea procesar un nuevo colector?","create", "/bienvenidos");
    }
}
