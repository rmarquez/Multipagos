importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		handler.leerExcel();
		handler.procesoSql();
		
		var cantidad = handler.cantidadInsertados();
		var excluidos = handler.contarExcluidos();
		
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad, "excluidos":excluidos});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

