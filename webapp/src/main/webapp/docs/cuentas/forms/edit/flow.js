function editform(form) {
    if (autorizar("cata")) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
        var bean = handlerBean.retrieve(parseInt(cocoon.request.cuentaId));

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        /**if (inactivo == "true") {
        	handlerBean.update(bean, auth_getUserID(), true);
        }**/
        
        handlerBean.update(bean, auth_getUserID());
        dialogosino("Cuentas", "Actualización de cuenta",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otra cuenta?",
                        "search", "/bienvenidos");
    }
}
