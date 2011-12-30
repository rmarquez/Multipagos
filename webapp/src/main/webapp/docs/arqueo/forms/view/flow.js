importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.MenuUser);

function viewform(form) {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.arqueoId));
    	java.lang.System.out.println("*** Arqueo == " + bean);
    	//form.getChild("agregar-p").performAction();
    	form.getChild("agregar").performAction();
    	form.getChild("agregar-us").performAction();
    	form.getChild("agregar-cheque").performAction();
    	form.getChild("agregar-deposito").performAction();
    	form.load(bean);   	
    	form.showForm("view-form-display");        
        form.save(bean);        	
        handlerBean.update(bean, auth_getUserID());
        	
        dialogosino("Arqueo", "Consulta", "¿Desea procesar otro arqueo?", "search", "/bienvenidos");
    }
}