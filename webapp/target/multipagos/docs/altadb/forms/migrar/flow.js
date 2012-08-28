importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		handler.leerExcel();
		var asignacion = handler.procesoSql();
		
		var cantidad = handler.cantidadInsertados();
		var excluidos = handler.contarExcluidos();
		
		cocoon.sendPage("forms/migrar/results.jx", {"asignacion":asignacion , "cantidad":cantidad, "excluidos":excluidos});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

