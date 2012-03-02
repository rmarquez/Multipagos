importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);
importClass(Packages.com.metropolitana.multipagos.forms.ScriptUtil);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraClaro();
		
		
		/**
		if (cocoon.request.getParameter("pagado") != null){
			var pagado = ScriptUtil.strToBoolean(cocoon.request.getParameter("pagado"));
		}
		if (cocoon.request.getParameter("pendiente") != null){
			var pendiente = ScriptUtil.strToBoolean(cocoon.request.getParameter("pendiente"));
		}				
		handler.leerExcel();
		if(pagado.equals(Boolean.TRUE)){
			handler.pagosClaro();
		}
		if(pendiente.equals(Boolean.TRUE)) {
			//handler.pendientesClaro();
		}**/
		
		
		handler.leerExcel();
		
		var cantidad = handler.pagosClaro();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad});	
        
}

