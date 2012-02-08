importClass(Packages.java.lang.Integer);
function migrarform(form) {   
       
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		handler.leerExcel();
        cocoon.sendPage("/bienvenidos");
        
}

