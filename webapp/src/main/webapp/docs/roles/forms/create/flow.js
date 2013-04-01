function createform(form) {
    if (autorizar("auth")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.AuthRole();
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler();

        form.save(bean);
        handler.insert(bean);

        dialogosino("Rol de usuario", "Rol procesado con éxito",
                        "¿Desea procesar un nuevo Rol de usuario?","create", "/bienvenidos");
    }
}

