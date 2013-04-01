importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);


function validarForm(form) {
	var handler = new Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var simboloNumero = form.getChild("simboloNumero").getValue();
    var simboloNombre = form.getChild("simboloNombre").getValue();
    var lista = new java.util.ArrayList();
    var returnval = true;
    
    if (simboloNumero != null) {    	
    	if(handler.validarNumero(simboloNumero)==true) {
    		form.getChild("mensajes de error").addMessage("El identificador ya se encuentra asignado a un simbolo, favor revisar.");
    		returnval = false;
    	} 
    }
    
    if (simboloNombre != null) {    	
    	if(handler.validarNombre(simboloNombre)==true) {
    		form.getChild("mensajes de error").addMessage("La descripcion ya se encuentra registrada, favor revisar.");
    		returnval = false;
    	} 
    }
    
    return returnval;
}

