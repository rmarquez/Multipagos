importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);

function editform(form) {
    if (autorizar("informes")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.pagos.PagosHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.pagoId));
    	java.lang.System.out.println("*** pagoId " + parseInt(cocoon.request.pagoId));
    	   java.lang.System.out.println("*** bean " + bean);
    	   llenarCamposlimpios(form);  
    	form.load(bean);
    	llenarCamposlimpios(form);    	
    	form.showForm("edit-form-display");        
        form.save(bean);        	
        handlerBean.update(bean, auth_getUserID());
        	
        dialogosino("Pagos", "Registro de pagos actualizada con éxito",
                        "¿Desea procesar un nuevo registro de pagos?","search", "/bienvenidos");
    }
}

function llenarCamposlimpios(form) {
	var handlerColector = new ColectorHandler();
    var detalle = form.getChild("detalle");
    for (var i=0; i < detalle.size ; i++){
        var row = detalle.getRow(i);
        var colectorId = row.getChild("colectorId").getValue();
        if(colectorId != null){
        	var colect = handlerColector.retrieve(colectorId);
        	row.getChild("colectorNumero").setValue(colect.getColectorNumero());
        }        
    }    
}