function carteraCombo() {
    if (autorizar("agregar") || autorizar("editar")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler();
        var bean = handlerBean.getCartera();
        cocoon.sendPage("carteraCombo-data", {"bean": bean});
    }
}
