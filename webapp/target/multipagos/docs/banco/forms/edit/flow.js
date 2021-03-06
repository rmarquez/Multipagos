function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.bancoId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        var inactivo = form.getChild("inactivo").getValue();
        if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }
        
        handlerBean.update(bean, auth_getUserID(), false);
        dialogosino("Bancos", "Actualización de banco",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otro banco?",
                        "search", "/bienvenidos");
    }
}
