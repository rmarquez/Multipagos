importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	var bean = new Packages.com.metropolitana.multipagos.Pagos();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.pagos.PagosHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());	
		form.getChild("editar").setValue(Boolean.FALSE);
		form.showForm("create-form-display");
        form.save(bean);
        if (form.submitId == "aceptar") {
        	var detalle = form.getChild("detalle");
        	
        	if(detalle.size > 1){
        		handlerBean.insert(bean, auth_getUserID());
        		cocoon.sendPage("cliente");
        	}else if (detalle.size <= 1) {
        		cocoon.sendPage("cliente");
        	}        	
        } else if (form.submitId == "cancelar"){
        	form.getChild("aceptar").setState(WidgetState.OUTPUT);
        	form.getChild("cancelar").setState(WidgetState.OUTPUT);
        }
        
        if (form.submitId == "crear") {
        handlerBean.insert(bean, auth_getUserID());      
        	
        dialogosino("Pagos", "Registro de pagos procesado con éxito",
                        "¿Desea procesar un nuevo registro de pagos?","create", "/bienvenidos");
        }
    }
}