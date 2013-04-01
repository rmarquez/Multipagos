importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
function editform(form) {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.asignarbId));
    	form.load(bean);
        form.showForm("edit-form-display");
		form.save(bean);
        handlerBean.update(bean, auth_getUserID());

        dialogosino("Asigancion", "Asignacion de barrios actualizada con éxito",
                        "¿Desea procesar una nueva asignacion?","search", "/bienvenidos");
    }
}
