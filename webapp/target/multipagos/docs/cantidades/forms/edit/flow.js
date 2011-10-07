function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.cantidadId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        /*var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }*/
        
        handlerBean.update(bean, auth_getUserID(), false);
        dialogosino("Cantidades", "Actualización de cantidad",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otra cantidad?",
                        "search", "/bienvenidos");
    }
}
