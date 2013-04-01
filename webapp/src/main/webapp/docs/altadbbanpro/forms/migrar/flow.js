importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraBanpro();
		handler.leerExcel();
		var cantidad = handler.sqlProcessBanpro();
		var excluidos = handler.contarExcluidosBanpro();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad, "excluidos":excluidos});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

