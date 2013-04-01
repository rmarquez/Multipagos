function editform(form) {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarColectorHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.asignarcId));
    	form.load(bean);
        form.showForm("edit-form-display");
		form.save(bean);
        handlerBean.update(bean, auth_getUserID());

        dialogosino("Asigancion", "Asignacion de colectores actualizada con éxito",
                        "¿Desea procesar una nueva asignacion?","search", "/bienvenidos");
    }
}