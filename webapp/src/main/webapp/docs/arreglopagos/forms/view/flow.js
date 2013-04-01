function viewform(form) {
    if (autorizar("buscar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.arreglo_pago.ArregloPagoHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.arregloId));
    	form.load(bean);
        form.showForm("view-form-display");
        form.save(bean);
        handlerBean.update(bean, auth_getUserID());

        dialogosino("Arreglo", "Vista de Arreglos de pagos",
                        "¿Desea consultar otro arreglo?","search", "/bienvenidos");
    }
}

