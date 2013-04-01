function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Cuentas();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Cuentas", "Cuenta procesada con éxito",
                        "¿Desea procesar una nueva cuenta?","create", "/bienvenidos");
    }
}
/**
function validarForm(form) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var bancoNombre = form.getChild("bancoNombre").getValue();
        var widgetMensaje = form.getChild("mensajes de error");
        //boolean existe = handlerBean.existeNombre(bancoNombre);
    if (handlerBean.existeBanco(bancoNombre)==true) {
        form.getChild("mensajes de error").addMessage("El banco ya existe, por favor ingrese un nuevo banco.");
        return false;
    } 		

return true;
}**/
