importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.java.lang.Integer);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);


function searchform(form) {
	var salir = false;
    while (!salir) {
        form.showForm("search-form-display");
        
        if (form.submitId == "buscar") {
        	var suscriptor = form.getChild("suscriptor").getValue();
        	var contrato = form.getChild("contrato").getValue();
        	var factura = form.getChild("factura").getValue();
        	var dCliente = form.getChild("dCliente");
        	
        	
        	if(suscriptor==null && contrato == null && factura == null){
        		form.getChild("mensajes de error").addMessage("La busqueda debe ser por almenos uno de los datos de  contrato, factura o nombre del cliente.");
        	} else {	
        		var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler();
            	var bean = handler.getDatosClienteList(suscriptor, contrato, factura);
            	
        		for (var i = 0; i < bean.size(); i++) {
                	var rowGuardar = bean.get(i);
                	 dCliente.addRow();	
                	 var row = dCliente.getRow(i);
                	 row.getChild("numContrato").setValue(rowGuardar[1]);
            	     row.getChild("nombre").setValue(rowGuardar[2]);
            	     row.getChild("facInterna").setValue(rowGuardar[3]);
            	     row.getChild("mes").setValue(rowGuardar[4]);
            	     row.getChild("anio").setValue(rowGuardar[5]);
            	     row.getChild("saldo").setValue(rowGuardar[6]);
            	     row.getChild("fechaIngreso").setValue(rowGuardar[9]);
            	     row.getChild("pagado").setValue(rowGuardar[7]);
            	     row.getChild("pagadoClaro").setValue(rowGuardar[8]);
                }
        		
        		form.getChild("suscriptor").setState(WidgetState.OUTPUT);
        		form.getChild("contrato").setState(WidgetState.OUTPUT);
        		form.getChild("factura").setState(WidgetState.OUTPUT);
        	}	
        	salir = false;	
		} 
        if (form.submitId == "salir") {
        	salir = true;	
		}
        
    }
    dialogosino("clientesclaro", "Clientes Claro", "¿Desea realizar una nueva busqueda ?","search", "/bienvenidos");
}


