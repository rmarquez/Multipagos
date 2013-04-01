function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Localidad();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Localidad", "Localidad procesada con éxito",
                        "¿Desea procesar una nueva localidad?","create", "/bienvenidos");
    }
}
/**
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
    var localidadNombre = form.getChild("localidadNombre").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    //boolean existe = handlerBean.existeNombre(bancoNombre);
	if (handlerBean.existeLocalidad(localidadNombre)==true) {
	    form.getChild("mensajes de error").addMessage("La localidad ya existe, por favor ingrese una nueva localidad.");
	    return false;
	} 		

return true;
}
**/