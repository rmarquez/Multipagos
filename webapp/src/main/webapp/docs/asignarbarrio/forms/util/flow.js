importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);

function alSeleccionarDepartamento(event) {
    var departamentoId = event.source.value;
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    if (departamentoId != null) {
    	localidadId.setSelectionList("cocoon:/localidad.combo?departamentoId=" + departamentoId);
    } else {
    	localidadId.setSelectionList(new EmptySelectionList("-- Localidad --"));
    }
}

function alSeleccionarLocalidad(event) {
	var localidadId = event.source.value;
    var detalle = event.source.parent.getChild("detalle");    	
		
    for (var i = 0; i < detalle.size; i++) {
    	 var row = detalle.getRow(i);	    	 
    	 var barrioId = row.getChild("barrioId"); 
    	 if (localidadId != null) {
    		 barrioId.setSelectionList("cocoon:/barrio.combo?localidadId=" + localidadId);
    	 } else {
    		 barrioId.setSelectionList(new EmptySelectionList("-- Barrio --"));
	     }	
    }
}

function validarBarrioUnico(lista, widgetCc_id) {
    // Determinamos si la cuenta esta en la lista
    if (lista.contains(widgetCc_id.value)) {
        widgetCc_id.setValidationError(new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.barrioyaelejido", true));
        return false;
    } else {
        lista.add(widgetCc_id.value);
        return true;
    }
}

function validarForm(form) {
	var handlerAsignacion = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var colectorId = form.getChild("colectorId").getValue();
    var lista = new java.util.ArrayList();
    var returnval = true;
    
    if (colectorId != null) {    	
    	if(handlerAsignacion.asignacionColector(colectorId)==true) {
    		form.getChild("mensajes de error").addMessage("A este colector ya se le fue asignado sus barrios, favor verificar si se encuentra activo.");
	  		return false;
    	} 
    }

    // Recorremos la lista de elementos que hay en el repeater
    var widget = form.getChild("detalle");
    for (var i = 0; i < widget.size; i++) {
        // Elemento actual de la lista
        var row = widget.getRow(i);        
        var widgetCc_id = row.getChild("barrioId");

        // Determinamos si la cuenta no esta repetida
        returnval = validarBarrioUnico(lista, widgetCc_id);           
    }    
    return returnval;
}

