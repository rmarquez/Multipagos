importClass(Packages.java.lang.Integer);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.simbolo.SimboloHandler);
importClass(Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler);

function editform(form) {
    if (autorizar("informes")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.visitas.VisitasHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.visitaId));
    	form.load(bean);
    	llenarCamposlimpios(form);    	
    	form.showForm("edit-form-display");        
        form.save(bean);        	
        handlerBean.update(bean, auth_getUserID());
        	
        dialogosino("Visitas", "Registro de visitas actualizada con éxito",
                        "¿Desea procesar un nuevo registro de visitas?","search", "/bienvenidos");
    }
}

function llenarCamposlimpios(form) {
	var handlerSimbolo = new SimboloHandler();
	var handlerColector = new ColectorHandler();
    var detalle = form.getChild("detalle");
    for (var i=0; i < detalle.size ; i++){
        var row = detalle.getRow(i);
        var simboloId = row.getChild("simboloId").getValue();
        var colectorId = row.getChild("colectorId").getValue();
        if(simboloId != null){
        	var simb = handlerSimbolo.retrieve(simboloId);
        	row.getChild("simbolo").setValue(simb.getSimboloNumero());
        }
        if(colectorId != null){
        	var colect = handlerColector.retrieve(colectorId);
        	row.getChild("colectorNumero").setValue(colect.getColectorNumero());
        }        
    }    
}