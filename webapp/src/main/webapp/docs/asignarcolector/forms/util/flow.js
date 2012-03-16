importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);

function validarColectorUnico(lista, widgetCc_id) {
    // Determinamos si la cuenta esta en la lista
    if (lista.contains(widgetCc_id.value)) {
        widgetCc_id.setValidationError(new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.colectoryaelejido", true));
        return false;
    } else {
        lista.add(widgetCc_id.value);
        return true;
    }
}

function validarForm(form) {
	var handlerAsigBarrio = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
	var handlerAsignacion = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarColectorHandler();
	var usrId = form.getChild("usrId").getValue();
	var widgetErrorMessage = form.getChild("mensajes de error");    
    var lista = new java.util.ArrayList();
    var returnval = true; 
    
    if (usrId != null) {    	
    	if(handlerAsignacion.asignacionColectorUsr(usrId)==true) {
    		form.getChild("mensajes de error").addMessage("A este Supervisor ya se le fue asignado sus colectores, favor verificar si se encuentra activo.");
	  		return false;
    	} 
    }    

    // Recorremos la lista de elementos que hay en el repeater
    var widget = form.getChild("detalle");
    for (var i = 0; i < widget.size; i++) {
        // Elemento actual de la lista
        var row = widget.getRow(i);        
        var widgetCc_id = row.getChild("colectorId");
        // Determinamos si la cuenta no esta repetida
        returnval = validarColectorUnico(lista, widgetCc_id); 
        // verificamos que el colector tenga barrios asignados
        if (widgetCc_id.value != null) {
            if(handlerAsigBarrio.asignacionColector(widgetCc_id.value)==false) {
	        	widgetCc_id.setValidationError(new Packages.org.apache.cocoon.forms.validation.ValidationError("com.metropolitana.key.colectorsinbarrios", true));
		  		return false;
	    	}
        }
    }    
    return returnval;
}

