importClass(Packages.java.lang.Boolean);
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.localidad.LocalidadHandler();
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    var localidadNombre = form.getChild("localidadNombre").getValue();
    var localidadId = form.getChild("localidadId").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    java.lang.System.out.println("localidadId = " + localidadId);
    if(localidadId == null){
    	if (handlerBean.existeLocalidad(localidadNombre)==true) {
		    form.getChild("mensajes de error").addMessage("La localidad ya existe, por favor ingrese una nueva localidad.");
		    return false;
		}
    } else {
    	var bean = handlerBean.retrieve(localidadId);
    	var iguales = handlerUtil.compararCadenas(localidadNombre,bean.getLocalidadNombre());
    	if(iguales.equals(Boolean.TRUE)){
    		return true;
    	} else {
    		if (handlerBean.existeLocalidad(localidadNombre)==true) {
			    form.getChild("mensajes de error").addMessage("La localidad ya existe, por favor ingrese una nueva localidad.");
			    return false;
			}
    	}
    } 		

return true;
}