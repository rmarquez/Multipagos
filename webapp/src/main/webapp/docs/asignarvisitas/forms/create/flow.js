importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	form.getChild("usrId").setValue(usuario.getUsrId());
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.AsignarBarrio();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
		form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Asigancion", "Asignacion de barrios procesada con éxito",
                        "¿Desea procesar una nueva asignacion?","create", "/bienvenidos");
    }
}


function alSeleccionarSupervisor(event) {
    var usrId = event.source.value;
    var colectorId = event.source.parent.getChild("colectorId");
    colectorId.setValue(null);
    if (usrId != null) {
    	colectorId.setSelectionList("cocoon:/colector.combo?usrId=" + usrId);
    } else {
    	colectorId.setSelectionList(new EmptySelectionList("-- Colector --"));
    }
}

function alSeleccionarColector(event) {
    var colectorId = event.source.value;
    var barrioId = event.source.parent.getChild("barrioId");
    barrioId.setValue(null);
    if (colectorId != null) {
    	barrioId.setSelectionList("cocoon:/barrio.combo?colectorId=" + colectorId);
    } else {
    	barrioId.setSelectionList(new EmptySelectionList("-- Colector --"));
    }
}

