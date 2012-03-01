importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraClaro();
		handler.leerExcel();
		handler.pendientesClaro();
		cocoon.sendPage("/bienvenidos");
		/**var cantidad = handler.sqlProcess();
		var excluidos = handler.contarExcluidos();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad, "excluidos":excluidos});**/
        
}

