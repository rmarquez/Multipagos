importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsCarteraBanpro();
		handler.leerPagos();
		var cantidad = handler.contarPagosBanpro();
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad});
		
}

