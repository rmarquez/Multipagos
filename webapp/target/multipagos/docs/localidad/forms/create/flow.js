function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Localidad();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Localidad", "Localidad procesada con éxito",
                        "¿Desea procesar una nueva localidad?","create", "/bienvenidos");
    }
}
