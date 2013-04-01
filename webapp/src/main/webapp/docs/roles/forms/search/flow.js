function searchform(form) {
    if (autorizar("auth")) {
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler();
        var criteria = form.getChild("criteria").getValue();
        var repeat = true;
        var bean;
        while (repeat == true) {
            repeat = false;
            bean = handler.getList(criteria);
            cocoon.sendPageAndWait("forms/search/results.jx", {"bean": bean});
            var rolId = parseInt(cocoon.request.rolId);
            if (!isNaN(rolId)) {
                var respuesta = cocoon.request.accion;
                if ("Editar".equals(respuesta))
                    cocoon.sendPage("edit");
                else if ("Borrar".equals(respuesta)) {
                    handler.remove(rolId);
                    repeat = true;
                }
            } else {
                dialogoaceptar("Rol de usuario", "Error", "Rol de usuario no encontrado");
                repeat = true;
            }
        }
    }
}

