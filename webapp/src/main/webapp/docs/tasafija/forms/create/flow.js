function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.TasaFija();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();

        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Tasa de cambio $ a C$", "Tasa de cambio procesada con éxito",
                        "¿Desea procesar una nueva tasa de cambio?","create","/bienvenidos");
    }
}
function validarForm(form) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();
        var tasaFecha = form.getChild("tasaFecha").getValue();
        var widgetMensaje = form.getChild("mensajes de error");    	 	    

    if (handlerBean.retrieve(tasaFecha) != null) {
        form.getChild("mensajes de error").addMessage("La tasa de cambio ya existe, por favor ingrese una nueva.");
        return false;
    } 		

return true;
}
