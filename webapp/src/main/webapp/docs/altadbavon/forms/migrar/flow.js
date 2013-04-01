importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraAvon();
		handler.leerExcel();
		var cantidad = handler.sqlProcessAvon();
		var excluidos = handler.contarExcluidosAvon();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad, "excluidos":excluidos});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

