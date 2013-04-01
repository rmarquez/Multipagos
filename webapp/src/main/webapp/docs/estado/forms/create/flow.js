function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.EstadoCorte();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Estado Corte", "Estado procesado con éxito",
                        "¿Desea procesar un nuevo estado?","create", "/bienvenidos");
    }
}
