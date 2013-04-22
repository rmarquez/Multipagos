importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraAvonHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);
function createform(form) {
    if (autorizar("agregar")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	var bean = new Packages.com.metropolitana.multipagos.IbwPagos();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.ibw_pagos.IbwPagosHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());	
		form.getChild("editar").setValue(Boolean.FALSE);
		form.showForm("create-form-display");
        form.save(bean);
        
        //if (form.submitId == "crear") {
        handlerBean.insert(bean, auth_getUserID());      
        	
        dialogosino("Pagos", "Registro de pagos procesado con éxito",
                        "¿Desea procesar un nuevo registro de pagos?","create", "/bienvenidos");
        //}
    }
}