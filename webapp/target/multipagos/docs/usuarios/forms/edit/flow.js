function editform(form) {
    if (autorizar("auth")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var bean = handler.retrieve(parseInt(cocoon.request.usrId));
        var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
        if (bean != null) {
            if (!bean.getUsrLogin().equals("admin")) {
                form.load(bean);
                form.showForm("edit-form-display");
                if (form.submitId=="guardar") {
                	var usrPassword = form.getChild("usrPassword").getValue();
                	var md5 = encriptar.encriptarMD5(usrPassword);
                	form.getChild("usrPassword").setValue(md5);
                }
                form.save(bean);
                handler.update(bean);

                dialogosino("Usuarios", "Actualización de usuario",
                                "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro usuario?",
                                "search","/bienvenidos");
            } else {
                cocoon.sendPage("/noautorizado");
            }
        } else {
            cocoon.sendPage("/not-found");
        }

    }
}
