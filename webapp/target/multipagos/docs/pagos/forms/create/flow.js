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
java.lang.System.out.println("***** antes *****");
        if (form.submitId == "aceptar") {
        	var detalle = form.getChild("detalle");
java.lang.System.out.println("detalle = " + detalle);
java.lang.System.out.println("Lineas repeater = " + detalle.size);
        	
        	if(detalle.size > 1){
java.lang.System.out.println("Lineas repeater = " + detalle.size());
        		detalle.removeRow(detalle.size)
        		//handlerBean.insert(bean, auth_getUserID());
        		cocoon.sendPage("/cartera/create");
        	} else if (detalle.size <= 1) {
        		cocoon.sendPage("/cartera/create");
        	}
        	
        }
        handlerBean.insert(bean, auth_getUserID());      
        	
        dialogosino("Pagos", "Registro de pagos procesado con éxito",
                        "¿Desea procesar un nuevo registro de pagos?","create", "/bienvenidos");
    }
}