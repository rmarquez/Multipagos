importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);


function validarForm(form) {
	var handlerAsignacion = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var colectorNumero = form.getChild("colectorNumero").getValue();
    var lista = new java.util.ArrayList();
    var returnval = true;
    
    if (colectorNumero != null) {    	
    	if(handlerAsignacion.validadNumColector(colectorNumero)==true) {
    		form.getChild("mensajes de error").addMessage("EL numero ya se encuentra asignado a otro colector");
    		returnval = false;
    	} 
    }
    
    return returnval;
}

