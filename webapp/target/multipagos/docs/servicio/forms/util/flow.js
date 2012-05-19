importClass(Packages.java.lang.Boolean);
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    var servicioNombre = form.getChild("servicioNombre").getValue();
    var servicioId = form.getChild("servicioId").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    //boolean existe = handlerBean.existeNombre(bancoNombre);
    if(servicioId == null){
    	if (handlerBean.existeServicio(servicioNombre)==true) {
    	    form.getChild("mensajes de error").addMessage("El servicio ya existe, por favor ingrese un nuevo servicio.");
    	    return false;
    	}
    } else {
    	var bean = handlerBean.retrieve(servicioId);
    	var iguales = handlerUtil.compararCadenas(servicioNombre,bean.getServicioNombre());
    	if(iguales.equals(Boolean.TRUE)){
    		return true;
    	} else {
    		if (handlerBean.existeServicio(servicioNombre)==true) {
    		    form.getChild("mensajes de error").addMessage("El servicio ya existe, por favor ingrese un nuevo servicio.");
    		    return false;
    		}
    	}
    } 		

return true;
}