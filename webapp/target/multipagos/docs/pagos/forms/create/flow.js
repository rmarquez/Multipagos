importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
function createform(form) {
    if (autorizar("cata")) {
    	var usrId = auth_getUserID();
    	var handlerUser = new Auth_userHandler();
    	var usuario = handlerUser.retrieve(usrId);
    	var bean = new Packages.com.metropolitana.multipagos.Pagos();
	    var handlerBean = new Packages.com.metropolitana.multipagos.forms.pagos.PagosHandler();
		form.getChild("usrId").setValue(usuario.getUsrId());
		form.getChild("fecha").setValue(new Packages.java.util.Date());		
		form.showForm("create-form-display");
        form.save(bean);        	
        handlerBean.insert(bean, auth_getUserID());      
        	
        dialogosino("Pagos", "Registro de pagos procesado con éxito",
                        "¿Desea procesar un nuevo registro de pagos?","create", "/bienvenidos");
    }
}