importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.barrio.BarrioHandler();
	var util = new Util();
    var barrioNombre = form.getChild("barrioNombre").getValue();
    var barrioId = form.getChild("barrioId").getValue();
    var bN = form.getChild("bN").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    if(barrioId == null){
    	if (handlerBean.existeBarrio(barrioNombre)==true) {
		    form.getChild("mensajes de error").addMessage("El barrio ya existe, por favor ingrese un nuevo barrio.");
		    return false;
		}
    } else {
    	var iguales = util.compararCadenas(barrioNombre, bN);
    	if(iguales.equals(Boolean.TRUE)){
    		return true;
    	} else {
    		if (handlerBean.existeBarrio(barrioNombre)==true) {
			    form.getChild("mensajes de error").addMessage("El barrio ya existe, por favor ingrese un nuevo barrio.");
			    return false;
			}
    	}
    } 		

return true;
}