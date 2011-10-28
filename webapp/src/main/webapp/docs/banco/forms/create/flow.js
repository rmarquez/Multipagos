function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Banco();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Bancos", "Banco procesado con éxito",
                        "¿Desea procesar un nuevo banco?","create", "/bienvenidos");
    }
}

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
}
