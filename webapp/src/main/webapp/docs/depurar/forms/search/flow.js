importClass(Packages.java.lang.Integer);
function searchform(form) {
      
       
        var handler = new Packages.com.metropolitana.multipagos.forms.migrarcartera.MigrarHandler();
        var salir = false;
        
        while (!salir) {
	        var bean = handler.getList();
	        
	        cocoon.sendPageAndWait("forms/search/results.jx", {"bean": bean});	        
	        var tmpId =  parseInt(cocoon.request.cTmpId);
                var facturaInterna =  cocoon.request.dFacturaInterna;
	        if (!isNaN(tmpId)) {
	        	var respuesta = cocoon.request.accion;
	            if ("Borrar".equals(respuesta)) {
	                handler.removeDuplicados(tmpId, facturaInterna);
		         } else {
		             dialogoaceptar("Error", "Registro no encontrado");
		         }
	        }
        }
}