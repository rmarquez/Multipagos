function createform(form) {
    if (autorizar("cata")) {
    	form.getChild("fecha").setValue(new Packages.java.util.Date());
    	
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.ArregloPago();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.arreglo_pago.ArregloPagoHandler();
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Arreglo", "Arreglo de Pago procesado con éxito",
                        "¿Desea procesar un nuevo arreglo?","create", "/bienvenidos");
    }
}

