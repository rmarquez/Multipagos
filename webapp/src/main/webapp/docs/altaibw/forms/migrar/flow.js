importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraIbw();
		handler.leerExcel();
		handler.procesoSql();
		var cantidad = handler.cantidadInsertados();
		
		var excluidos = handler.contarExcluidosIbw();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad, "excluidos":excluidos});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

