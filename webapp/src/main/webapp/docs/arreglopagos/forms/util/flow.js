importClass(Packages.java.util.Date);
importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.java.util.Calendar);
importClass(Packages.java.math.BigDecimal);
importClass(Packages.java.text.DateFormat);
importClass(Packages.java.text.SimpleDateFormat);
importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler);
importClass(Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);

function alInsertarCodido(event) {
	//var handlerTasa = new TasafijaHandler();
	var codCliente = event.source.value;
	var detalle = event.source.parent.getChild("detalle");
	var nombre = event.source.parent.getChild("nombre");
	nombre.setValue(null);
	var handlerCartera = new CarteraBanproHandler(); 
	var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler();
	if (codCliente != null) {
		var bean = handler.getListDeudaXCliente(codCliente);
		
		for (var i = 0; i < bean.size(); i++) {
        	var rowGuardar = bean.get(i);
        	 detalle.addRow();	
        	 var row = detalle.getRow(i);
        	 nombre.setValue(rowGuardar[1]);
        	 row.getChild("tmpId").setValue(rowGuardar[0]);   
    	     row.getChild("producto").setValue(rowGuardar[2]);
    	     row.getChild("tarjeta").setValue(rowGuardar[3]);
    	     row.getChild("cuenta").setValue(rowGuardar[4]);
    	     row.getChild("deudaCor").setValue(rowGuardar[5]);
    	     row.getChild("deudaDol").setValue(rowGuardar[6]);
    	     row.getChild("totalDeudaDol").setValue(rowGuardar[7]);
    	     var pagoD = handlerCartera.getPagosDolares(rowGuardar[4]);
    	     row.getChild("pago").setValue(pagoD);
        }
	}  else { 
		 event.source.parent.getChild("codCliente").setValidationError(new ValidationError("Codigo NO encontrado, verifique el codigo."));				
	}
}

function alInsertarPlazo(event) {
	var plazo = event.source.value;
	var subTotal = java.math.BigDecimal.ZERO;
	var saldoUS = event.source.parent.getChild("saldoUS").getValue();
	var descuento = event.source.parent.getChild("descuento").getValue();
	var fecha = event.source.parent.getChild("fecha").getValue();
	var util = new Packages.com.metropolitana.multipagos.forms.Util();
	if(plazo != null){
		if(descuento != null){
			if (descuento.compareTo(saldoUS) == -1) {
				subTotal = util.calcularCuota(saldoUS, descuento.setScale(2, java.math.BigDecimal.ROUND_HALF_UP), util.stringToBigDecimal(plazo));
			} else {
				event.source.parent.getChild("descuento").setValidationError(new ValidationError("El descuento debe ser menor al saldo pendiente."));
			}
			
	    } else {
	    	subTotal = util.calcularCuota(saldoUS, java.math.BigDecimal.ZERO, util.stringToBigDecimal(plazo));
	    }
		var detalle = event.source.parent.getChild("detalleCalendario");
		var numero = util.stringToInt(plazo);
		for (var i = 0; i < numero; i++) {
	    	//var rowGuardar = bean.get(i);
	    	 detalle.addRow();	
	    	 var row = detalle.getRow(i);
	    	 var newFecha = util.otroMes(fecha, i);
	    	 
	    	 row.getChild("fechaCuota").setValue(newFecha);   
		     
	    }	
	}
	event.source.parent.getChild("cuota").setValue(subTotal);
	
	
}

