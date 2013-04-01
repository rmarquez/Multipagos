function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.barrioId));
		form.getChild("bN").setValue(bean.getBarrioNombre());
        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }        
        handlerBean.update(bean, auth_getUserID(), false);
        dialogosino("Barrio", "Actualización de barrio",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro barrio?",
                        "search", "/bienvenidos");
    }
}
