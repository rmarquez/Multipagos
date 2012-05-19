importClass(Packages.java.lang.Boolean);
function validarForm(form) {
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.banco.BancoHandler();
        var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
        var bancoNombre = form.getChild("bancoNombre").getValue();
        var bancoId = form.getChild("bancoId").getValue();
        var widgetMensaje = form.getChild("mensajes de error");
    
	    if(bancoId == null){
	    	if (handlerBean.existeBanco(bancoNombre)==true) {
	            form.getChild("mensajes de error").addMessage("El banco ya existe, por favor ingrese un nuevo banco.");
	            return false;
	        }
	    } else {
	     	var bean = handlerBean.retrieve(bancoId);
	    	var iguales = handlerUtil.compararCadenas(bancoNombre,bean.getBancoNombre());
	    	if(iguales.equals(Boolean.TRUE)){
	    		return true;
	    	} else {
	    	 	if (handlerBean.existeBanco(bancoNombre)==true) {
		            form.getChild("mensajes de error").addMessage("El banco ya existe, por favor ingrese un nuevo banco.");
		            return false;
		        }
	    	}
	    }

return true;
}