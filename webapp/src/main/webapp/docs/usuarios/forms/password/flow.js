function passwordform(form) {
    var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
    var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
    // Usuario logeado
    var userID = auth_getUserID();
    var bean = handler.retrieve(userID);

    if (userID == 1) {
	    // El usuario admin puede ver la lista de todos los usuarios.
    	form.getChild("usrId").setSelectionList("cocoon:/auth_user.combo?usrLogin=");
	} else {
		// El usuario normal solo puede ver su propio usuario
		form.getChild("usrId").setSelectionList("cocoon:/solo_user.combo?" +
		     "usrId=" + bean.getUsrId() + "&usrLogin=" + bean.getUsrLogin());
	}
    if (form.submitId=="guardar") {
    	var usrPassword = form.getChild("usrPasswordNew").getValue();
    	var md5 = encriptar.encriptarMD5(usrPassword);
    	form.getChild("usrPasswordNew").setValue(md5);
    }
    form.load(bean);
    var kont = form.showForm("password-form-display");

    handler.updatePassword(form.getChild("usrId").getValue(), encriptar.encriptarMD5(form.getChild("usrPasswordNew").getValue()));

    kont.invalidate();

    dialogoaceptar("Usuarios", "Actualización de contraseña de usuario",
                        "Todos los cambios se procesaron exitosamente",
                        "/bienvenidos");
}

//
// Verifica si el cambio de la contraseña se puede hacer
//
// @param Integer userID            - Id del usuario que inicio la sesion
// @param Widget  usr_id            - Ide del usuario al cual le queremos cambiar la contraseña
// @param String  usr_password_db   - Contraseña almacena actualmente en la base de datos
// @param Widget  password_actual   - Contraseña actual ingresada en el formulario, esta debe ser igual a "usr_password_db"
// @param Widget  password_nuevo    - Nueva contraseña
// @param Widget  password_repetir  - Nueva contraseña, esta debe ser igual a "password_nuevo"
// @return true si el cambio de contraseña puede ser llevado a cabo
//
function passwordValidar(userID, usrId, usrPasswordDb, passwordActual, passwordNuevo, passwordRepetir) {
	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
	//var md5 = encriptar.encriptarMD5(passwordActual);
    // Determinamos si el usuario que inicio sesion es el administrador
    if (userID == 1) {
        // Si el usuario administrador se esta cambiando su contaseña, siempre
        // se debe verificar la contraseña actual
        if (userID == usrId.getValue()) {
            if (!usrPasswordDb.equals(encriptar.encriptarMD5(passwordActual.getValue()))) {
                var validationError = new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.passwderror", true);
                passwordActual.setValidationError(validationError);
                return false;
            }
        // El administador esta cambiando las contraseñas de otros usuarios
        // verificar el password unicamente si este es proporcionado
        } else if (passwordActual.getValue() != null && !usrPasswordDb.equals(encriptar.encriptarMD5(passwordActual.getValue()))) {
            var validationError = new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.passwderror", true);
            passwordActual.setValidationError(validationError);
            return false;
        }
    } else {
        // En el caso de cualquier otro usuario el password siempre se válida
        if (!usrPasswordDb.equals(encriptar.encriptarMD5(passwordActual.getValue()))) {
            var validationError = new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.passwderror", true);
            passwordActual.setValidationError(validationError);
            return false;
        }
    }
    // Ya verificamos que el password actual sea válido ahora hay que
    // Determinar si el password nuevo se ingreso correctamente
    var result = passwordRepetir.getValue() != null && passwordNuevo.getValue() != null &&
                 passwordNuevo.getValue().equals(passwordRepetir.getValue());
    if (!result) {
        var validationError = new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.dospasswordnoigual", true);
        passwordRepetir.setValidationError(validationError);
    }
    return result;
}

function passwordValidarFormulario(form) {
	var encriptar = new Packages.com.metropolitana.multipagos.forms.EncriptarMd5();
    var result = false;
    // Usuario que inicio sesión
    var userID = auth_getUserID();
    // Usuario al que hay que cambiarle la contraseña
    var usrId = form.getChild("usrId");
    var passwordActual = form.getChild("usrPasswordActual");
    //var md5 = encriptar.encriptarMD5(passwordActual);
    
    var passwordNuevo = form.getChild("usrPasswordNew");
    
    var passwordRepetir = form.getChild("usrPasswordRepetir");
    
    var handler = new Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler();
    // Usuario al que hay que cambiar la contaseña
    var bean = handler.retrieve(usrId.getValue());

    if (userID == 1) {
        // El usuario actual es el administrador y puede cambiar
        // la contraseña de cualquier usuario
        result = passwordValidar(userID, usrId, bean.getUsrPassword(), passwordActual, passwordNuevo, passwordRepetir);
    } else {
        // Hay que verificar que un usuario normal no intente
        // cambiar la contraseña de otro usuario
        if (userID != usrId.getValue()) {
            var validationError = new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.operacionnopermitida", true);
            usrId.setValidationError(validationError);
        } else {
            result = passwordValidar(userID, usrId, bean.getUsrPassword(), passwordActual, passwordNuevo, passwordRepetir);
        }
    }
    return result;
}

