importClass(Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler);
importClass(Packages.org.apache.cocoon.forms.util.I18nMessage);
importClass(Packages.org.apache.cocoon.forms.validation.ValidationError);
importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
importClass(Packages.org.apache.cocoon.forms.formmodel.WidgetState);

function searchform(form) {
        form.showForm("search-form-display");
        cocoon.sendPage("/bienvenidos");
        //dialogosino("Arreglo", "Arreglo de Pago procesado con éxito",
        //                "¿Desea procesar un nuevo arreglo?","create", "/bienvenidos");
}

function clienteDeuda(event) {
	//var handlerTasa = new TasafijaHandler();
	var codCliente = event.source.value;
	var detalle = event.source.parent.getChild("deuda");
	var nombre = event.source.parent.getChild("nombre");
	nombre.setValue(null);
	var handler = new Packages.com.metropolitana.multipagos.forms.cartera.CarteraBanproHandler();
	if (codCliente != null) {
		var bean = handler.getListDeudaXCliente(codCliente);
		
		for (var i = 0; i < bean.size(); i++) {
        	var rowGuardar = bean.get(i);
        	 detalle.addRow();	
        	 var row = detalle.getRow(i);
        	 nombre.setValue(rowGuardar[1]);
        	 row.getChild("producto").setValue(rowGuardar[2]);
    	     row.getChild("tarjeta").setValue(rowGuardar[3]);
    	     row.getChild("cuenta").setValue(rowGuardar[4]);
    	     row.getChild("deudaCor").setValue(rowGuardar[5]);
    	     row.getChild("deudaDol").setValue(rowGuardar[6]);
    	     row.getChild("totalDeudaDol").setValue(rowGuardar[7]);
        }
		
	}  else { 
		 event.source.parent.getChild("codCliente").setValidationError(new ValidationError("Codigo NO encontrado, verifique el codigo."));				
	}
}

function clientePagos(event) {
	var codCliente = event.source.value;
	var detalle = event.source.parent.getChild("pagos");
	var handler = new Packages.com.metropolitana.multipagos.forms.clientes_banpro.UtilBanpro();
	if (codCliente != null) {
		var bean = handler.getPagosClientes(codCliente);
		
		for (var j = 0; j < bean.size(); j++) {
        	var rowGuardar = bean.get(j);
        	 detalle.addRow();	
        	 var row = detalle.getRow(j);
        	 row.getChild("cuenta").setValue(rowGuardar[0]);
    	     row.getChild("fechaCor").setValue(rowGuardar[1]);
    	     row.getChild("pagoCor").setValue(rowGuardar[2]);
    	     row.getChild("fechaDol").setValue(rowGuardar[3]);
    	     row.getChild("pagoDol").setValue(rowGuardar[4]);
    	     row.getChild("observaciones").setValue(rowGuardar[5]);
        }
	}  
}

function clienteArreglos(event) {
	var codCliente = event.source.value;
	var detalle = event.source.parent.getChild("arreglo");
	var handler = new Packages.com.metropolitana.multipagos.forms.clientes_banpro.UtilBanpro();
	var subTotal = java.math.BigDecimal.ZERO;
	var mDeuda = java.math.BigDecimal.ZERO;
	if (codCliente != null) {
		var bean = handler.getArreglosClientes(codCliente);
		
		
        var deuda = event.source.parent.getChild("deuda");
             for (var k = 0; k < deuda.size; k++) {
                var row = deuda.getRow(k);
                  var totalDeudaDol = row.getChild("totalDeudaDol").getValue();
                  if (totalDeudaDol != null) {
                	  mDeuda = mDeuda.add(totalDeudaDol).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
                }
            }
		
		 var pagos = event.source.parent.getChild("pagos");
         for (var i = 0; i < pagos.size; i++) {
            var row = pagos.getRow(i);
              var pago = row.getChild("pagoDol").getValue();
              if (pago != null) {
                    subTotal = subTotal.add(pago).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
            }
        }
		
		for (var j = 0; j < bean.size(); j++) {
        	var rowGuardar = bean.get(j);
        	 detalle.addRow();	
        	 var row = detalle.getRow(j);
        	 row.getChild("fecha").setValue(rowGuardar[0]);
    	     row.getChild("descuento").setValue(rowGuardar[1]);
    	     row.getChild("plazo").setValue(rowGuardar[2]);
    	     row.getChild("cuota").setValue(rowGuardar[3]);
    	     row.getChild("pagoTotalUS").setValue(subTotal);
    	     row.getChild("deudaTotalUS").setValue(mDeuda);
        }  
	} 
}


function clienteCuotas(event) {
	var codCliente = event.source.value;
	var detalle = event.source.parent.getChild("cuotas");
	var handler = new Packages.com.metropolitana.multipagos.forms.clientes_banpro.UtilBanpro();
	if (codCliente != null) {
		var bean = handler.getCuotasClientes(codCliente);
		
		for (var j = 0; j < bean.size(); j++) {
        	var rowGuardar = bean.get(j);
        	 detalle.addRow();	
        	 var row = detalle.getRow(j);
        	 row.getChild("fechaCuota").setValue(rowGuardar[0]);
    	     row.getChild("montoCuota").setValue(rowGuardar[1]);
    	     row.getChild("montoPago").setValue(rowGuardar[2]);
    	     row.getChild("fechaPago").setValue(rowGuardar[3]);
        }
	}  
}
