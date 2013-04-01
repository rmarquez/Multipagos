function searchform(form) {
    if (autorizar("auth")) {
        form.showForm("search-form-display");
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var criteria = form.getChild("criteria").getValue();
        var repeat = true;
        var bean;

        while (repeat == true) {
            repeat = false;
            bean = handler.getList(criteria);

            cocoon.sendPageAndWait("forms/search/results.jx", {"bean": bean});
            var usrId = parseInt(cocoon.request.usrId);
            if (!isNaN(usrId)) {
                var respuesta = cocoon.request.accion;
                if ("Editar".equals(respuesta)) {
                    cocoon.sendPage("edit");
                }
                else  if ("Borrar".equals(respuesta)) {
                    handler.remove(usrId);
                    repeat = true;
                }
            } else {
                dialogoaceptar("Usuarios", "Error", "Usuario no encontrado");
                repeat = true;
            }
        }
    }
}
