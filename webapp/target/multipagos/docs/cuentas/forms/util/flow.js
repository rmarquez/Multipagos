importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.java.lang.Boolean);

function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.cuentas.CuentasHandler();
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    var widgetErrorMessage = form.getChild("mensajes de error");
    var numeroCuenta = form.getChild("numeroCuenta").getValue();
    var cuentaId = form.getChild("cuentaId").getValue();
    var lista = new java.util.ArrayList();
    
    if(cuentaId == null){
	    	if(handlerBean.validadNumCuenta(numeroCuenta)==true) {
	    		form.getChild("mensajes de error").addMessage("EL numero de cuenta ya existe, favor verificar los datos");
	    		return false;
	    	} 
	    } else {
	     	var bean = handlerBean.retrieve(cuentaId);
	    	var iguales = handlerUtil.compararCadenas(numeroCuenta,bean.getNumeroCuenta());
	    	java.lang.System.out.println("Iguales = " + iguales)
	    	if(iguales.equals(Boolean.TRUE)){
	    		return true;
	    	} else {
	    	 	if(handlerBean.validadNumCuenta(numeroCuenta)==true) {
		    		form.getChild("mensajes de error").addMessage("EL numero de cuenta ya existe, favor verificar los datos");
		    		return false;
		    	} 
	    	}
	    }

return true;

}

