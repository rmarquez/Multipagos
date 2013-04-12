importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);

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

function alSeleccionarInicio(event) {
    var usrPinicio = event.source.value;
    var usrOrden = event.source.parent.getChild("usrOrden");
    usrOrden.setValue(null);
    if (usrPinicio.booleanValue()== true) {
    	usrOrden.setState(WidgetState.ACTIVE);        
    } else {
    	usrOrden.setState(WidgetState.INVISIBLE);
    }
}

