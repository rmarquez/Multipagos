importClass(Packages.com.metropolitana.multipagos.forms.Util);
function createform(form) {
    if (autorizar("cata")) {
    	form.getChild("fechaIngreso").setValue(new Packages.java.util.Date());
    	form.getChild("pagado").setValue(false);
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.CarteraXDepartamento();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        
        var colectorId = form.getChild("colectorId").getValue();
        var recibo = form.getChild("recibo").getValue();
        var montoPago = form.getChild("montoPago").getValue();
        var horaRegistro = form.getChild("horaRegistro").getValue();
        
        
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID(), colectorId, recibo, montoPago, horaRegistro);

        dialogosino("Cartera", "Cliente procesado procesado con éxito",
                        "¿Desea procesar un nuevo colector?","create", "/bienvenidos");
    }
}

function alElejirDepartamento(event) {
    var departamentoId = event.source.value;
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    if (departamentoId != null) {
    	localidadId.setSelectionList("cocoon:/localidad.combo?departamentoId=" + departamentoId);
    } else {
    	localidadId.setSelectionList(new EmptySelectionList("-- Localidad --"));
    }
}

function alElejirLocalidad(event) {
    var localidadId = event.source.value;
    var barrioId = event.source.parent.getChild("barrioId");
    barrioId.setValue(null);
    if (localidadId != null) {
    	barrioId.setSelectionList("cocoon:/barrio.combo?localidadId=" + localidadId);
    } else {
    	barrioId.setSelectionList(new EmptySelectionList("-- Barrio --"));
    }
}

function alSeleccionarColector(event) {
	var colector = event.source.value;
	var util = new Util();
	var hora = util.getFechaString(new Packages.java.util.Date());
    var horaRegistro = event.source.parent.getChild("horaRegistro");
    horaRegistro.setValue(null);
    if (colector != null) {
    		horaRegistro.setValue(hora);
    }
}
