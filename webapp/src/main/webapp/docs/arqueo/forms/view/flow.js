importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.MenuUser);

function viewform(form) {
    if (autorizar("cata")) {
    	var handlerBean = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
    	var bean = handlerBean.retrieve(parseInt(cocoon.request.arqueoId));
    	form.getChild("agregar-p").performAction();
    	form.getChild("agregar").performAction();
    	form.getChild("agregar-us").performAction();
    	form.getChild("agregar-cheque").performAction();
    	form.getChild("agregar-deposito").performAction();
    	form.load(bean);
    	llenarCampos(form);
    	form.showForm("view-form-display");        
        form.save(bean);        	
        handlerBean.update(bean, auth_getUserID());
        	
        dialogosino("Arqueo", "Consulta", "¿Desea procesar otro arqueo?", "search", "/bienvenidos");
    }
}


function llenarCampos(form) {
	var colectorId = form.getChild("colectorId").getValue();
    var pagoFecha = form.getChild("pagoFecha").getValue();
    var detalle = form.getChild("detalle")
    var handlerArqueo = new Packages.com.metropolitana.multipagos.forms.arqueo.ArqueoHandler();
    if (colectorId != null) {    	
	    	var handler = new Packages.com.metropolitana.multipagos.forms.pagos.PagosXColector();
	    	var bean = handler.getListPagosXColector(pagoFecha, colectorId);
	    	for (var i = 0; i < bean.size(); i++) {
	        	var rowGuardar = bean.get(i);
	        	 detalle.addRow();	
	        	 var row = detalle.getRow(i);
	        	 row.getChild("recibo").setValue(rowGuardar[0]);   
	    	     row.getChild("facturaInterna").setValue(rowGuardar[1]);
	    	     row.getChild("numContrato").setValue(rowGuardar[2]);
	    	     row.getChild("fecha").setValue(rowGuardar[3]);
	    	     row.getChild("monto").setValue(rowGuardar[4]);
	       
	        }    	
    }
}