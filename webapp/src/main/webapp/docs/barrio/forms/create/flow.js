function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Barrio();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Barrio", "Barrio procesado con éxito",
                        "¿Desea procesar un nuevo barrio?","create", "/bienvenidos");
    }
}

