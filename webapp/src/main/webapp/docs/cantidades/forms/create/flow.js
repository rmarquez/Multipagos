function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.CantidadMonedas();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
		//form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Cantidades", "Cantidad procesado con éxito",
                        "¿Desea procesar una nuevo cantidad?","create", "/bienvenidos");
    }
}
