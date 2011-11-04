function createform(form) {
    if (autorizar("cata")) {
    	form.getChild("fechaIngreso").setValue(new Packages.java.util.Date());
    	form.getChild("pagado").setValue(false);
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.CarteraXDepartamento();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
        form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

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
