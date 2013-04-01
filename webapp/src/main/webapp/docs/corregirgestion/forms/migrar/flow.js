importClass(Packages.java.lang.Integer);
function migrarform(form, usrId) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		var corregirHandler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.CorregirGestionClaro();
		var usrId = Integer.valueOf(cocoon.request.usrId);
		handler.leerExcel();
		var cantidad = corregirHandler.corregirGestio(usrId);
		
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad});
		//cocoon.sendPage("forms/migrar/results.jx");
        
}

