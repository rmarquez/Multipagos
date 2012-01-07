importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
function createform(form) {
    //if (autorizar("registro_visitas")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
		var bean = new Packages.com.metropolitana.multipagos.Visitas();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());
		
		form.showForm("create-form-display");
        
        form.save(bean);
        	
        handlerBean.insert(bean, auth_getUserID());
        	
        dialogosino("Visitas", "Registro de visitas procesado con éxito",
                        "¿Desea procesar un nuevo registro de visitas?","create", "/bienvenidos");
    //}
}

function validarForm(form) {
	var cantidadVisitas = form.getChild("cantidadVisitas").getValue();
	var widgetMensaje = form.getChild("mensajes de error");
	var detalle = form.getChild("detalle");
	
	if(cantidadVisitas==null) {
		form.getChild("mensajes de error").addMessage("¡ No se han registrado visitas !, verifique sus datos, puede contener algun error. ");
		return false;
	}	  
	return true;
}