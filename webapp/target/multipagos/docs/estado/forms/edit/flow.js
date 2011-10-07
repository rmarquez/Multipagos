function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.estadoId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }
        
        handlerBean.update(bean, auth_getUserID(), false);
        dialogosino("Estado Corte", "Actualización de estado",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro estado?",
                        "search", "/bienvenidos");
    }
}
