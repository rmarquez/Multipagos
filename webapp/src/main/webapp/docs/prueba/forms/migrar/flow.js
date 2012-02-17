importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		//var utilExcel = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres();
		handler.leerExcel();
		handler.sqlProcess();
        cocoon.sendPage("/bienvenidos");
        
}

