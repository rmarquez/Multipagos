function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.servicioId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }
        dialogosino("Servicio", "Actualización de servicio",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro servicio?",
                        "search", "/bienvenidos");
    }
}
