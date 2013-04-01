importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);


function validarForm(form) {
	var handlerAsignacion = new Packages.com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var estadoNombre = form.getChild("estadoNombre").getValue();
    var lista = new java.util.ArrayList();
    var returnval = true;
    
    if (estadoNombre != null) {    	
    	if(handlerAsignacion.validarEstado(estadoNombre)==true) {
    		form.getChild("mensajes de error").addMessage("Este estado ya se encuentra registrado, favor revisar sus datos.");
    		returnval = false;
    	} 
    }
    
    return returnval;
}

