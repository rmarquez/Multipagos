function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Barrio();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Barrio", "Barrio procesado con éxito",
                        "¿Desea procesar un nuevo barrio?","create", "/bienvenidos");
    }
}

function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
    var barrioNombre = form.getChild("barrioNombre").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    //boolean existe = handlerBean.existeNombre(bancoNombre);
	if (handlerBean.existeBarrio(barrioNombre)==true) {
	    form.getChild("mensajes de error").addMessage("El barrio ya existe, por favor ingrese un nuevo barrio.");
	    return false;
	} 		

return true;
}
