importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		handler.leerExcel();
		var cantidad = handler.sqlProcess();
java.lang.System.out.println("Cantidad de registros = " + cantidad);
		cocoon.sendPage("forms/migrar/results.jx", {"cantidad":cantidad});
        
}

