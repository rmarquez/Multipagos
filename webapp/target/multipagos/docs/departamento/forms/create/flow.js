function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Departamento();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Departamento", "Departamento procesado con éxito",
                        "¿Desea procesar un nuevo departamento?","create", "/bienvenidos");
    }
}
