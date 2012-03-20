importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);


function validarForm(form) {
	var handlerAsignacion = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var numeroCuenta = form.getChild("numeroCuenta").getValue();
    var lista = new java.util.ArrayList();
    var returnval = true;
    
    if (numeroCuenta != null) {    	
    	if(handlerAsignacion.validadNumCuenta(numeroCuenta)==true) {
    		form.getChild("mensajes de error").addMessage("EL numero de cuenta ya existe, favor verificar los datos");
    		returnval = false;
    	} 
    }
    
    return returnval;
}

