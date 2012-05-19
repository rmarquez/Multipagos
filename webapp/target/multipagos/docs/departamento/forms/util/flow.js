importClass(Packages.java.lang.Boolean);
function validarForm(form) {
	var handlerBean = new Packages.com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler();
	var handlerUtil = new Packages.com.metropolitana.multipagos.forms.Util();
    var departamentoNombre = form.getChild("departamentoNombre").getValue();
    var departamentoId = form.getChild("departamentoId").getValue();
    var widgetMensaje = form.getChild("mensajes de error");
    //boolean existe = handlerBean.existeNombre(bancoNombre);
    if(departamentoId == null){
    	if (handlerBean.existeDepartamento(servicioNombre)==true) {
    	    form.getChild("mensajes de error").addMessage("El departamento ya existe, por favor ingrese un nuevo departamento.");
    	    return false;
    	}
    } else {
    	var bean = handlerBean.retrieve(departamentoId);
    	var iguales = handlerUtil.compararCadenas(departamentoNombre,bean.getDepartamentoNombre());
    	if(iguales.equals(Boolean.TRUE)){
    		return true;
    	} else {
    		if (handlerBean.existeDepartamento(departamentoNombre)==true) {
    		    form.getChild("mensajes de error").addMessage("El departamento ya existe, por favor ingrese un nuevo departamento.");
    		    return false;
    		}
    	}
    } 		

return true;
}