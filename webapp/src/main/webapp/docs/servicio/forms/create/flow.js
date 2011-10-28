function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.Servicio();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
		form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Simbolo", "Simbolo procesado con éxito",
                        "¿Desea procesar un nuevo simbolo?","create", "/bienvenidos");
    }
}
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
    var servicioNombre = form.getChild("servicioNombre").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    //boolean existe = handlerBean.existeNombre(bancoNombre);
	if (handlerBean.existeServicio(servicioNombre)==true) {
	    form.getChild("mensajes de error").addMessage("El servicio ya existe, por favor ingrese un nuevo servicio.");
	    return false;
	} 		

return true;
}
