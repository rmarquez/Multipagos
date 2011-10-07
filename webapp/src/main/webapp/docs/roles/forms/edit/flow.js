function editform(form) {
    if (autorizar("auth")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler();
        var bean = handler.retrieve(parseInt(cocoon.request.rolId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        handler.update(bean);

        dialogosino("Rol de usuario", "Actualización de rol de usuario",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro rol de usuario?",
                        "search","/bienvenidos");
    }
}
