importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	form.getChild("usrId").setValue(usuario.getUsrId());
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.AsignarColector();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarColectorHandler();
		form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Asigancion", "Asignacion de colectores procesada con éxito",
                        "¿Desea procesar una nueva asignacion?","create", "/bienvenidos");
    }
}