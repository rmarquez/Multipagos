function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.localidadId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }        
        handlerBean.update(bean, auth_getUserID(), false);
        dialogosino("Departamento", "Actualización de departamento",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro departamento?",
                        "search", "/bienvenidos");
    }
}
