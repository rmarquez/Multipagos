function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.simboloId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }
        dialogosino("Simbolo", "Actualización de simbolo",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro simbolo?",
                        "search", "/bienvenidos");
    }
}
