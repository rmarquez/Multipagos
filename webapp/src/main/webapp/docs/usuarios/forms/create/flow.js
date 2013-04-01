function createform(form) {
    if (autorizar("auth")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.AuthUser();
        var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
        var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
        if (form.submitId=="crear") {
        	var usrPassword = form.getChild("usrPassword").getValue();
        	var md5 = encriptar.encriptarMD5(usrPassword);
        	form.getChild("usrPassword").setValue(md5);
        }

        form.save(bean);
        handler.insert(bean);

        dialogosino("Usuarios", "Usuario procesado con éxito",
                        "¿Desea procesar un nuevo Usuario?","create", "/bienvenidos");
    }
}

