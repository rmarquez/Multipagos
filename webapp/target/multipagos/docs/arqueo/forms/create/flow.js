importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	form.getChild("usrId").setValue(usuario.getUsrId());
        
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.ArqueoPagos();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
		//form.getChild("inactivo").setValue(false);
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Arqueo", "Arqueo procesado con éxito",
                        "¿Desea procesar una nuevo arqueo?","create", "/bienvenidos");
    }
}

function pagoEnDolares(event) {
    var dolares = event.source.value;
    var montoCs = event.source.parent.getChild("montoCs");
    var montoUs = event.source.parent.getChild("montoUs");
    montoCs.setValue(null);
    montoUs.setValue(null);
    if (dolares.booleanValue()== true) {
        montoCs.setValue(null);
        montoCs.setState(WidgetState.INVISIBLE);
        montoUs.setState(WidgetState.ACTIVE);        
    } else {
    	montoCs.setState(WidgetState.ACTIVE);
    	montoUs.setState(WidgetState.INVISIBLE);
    }
}

function alSeleccionarCantidad(event) {
    var cantidadId = event.source.value;
    var cantidadValor = event.source.parent.getChild("cantidadValor");
    cantidadValor.setValue(null);
    if (cantidadId != null) {
    	var handler = new Packages.com.metropolitana.multipagos.forms.cantidades.CantidadHandler();
        var cantd = handler.retrieve(cantidadId);
    	cantidadValor.setValue(cantd.getCantidadValor());
    }
}

function alSeleccionarValor(event) {
    var cantidad = event.source.value;
    var cantidadValor = event.source.parent.getChild("cantidadValor").getValue();
    var total = event.source.parent.getChild("total");
    total.setValue(null);
    if (cantidad != null) {
    	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    	total.setValue(handlerUtil.calcularTotalCantidad(cantidadValor, cantidad));
    }
}