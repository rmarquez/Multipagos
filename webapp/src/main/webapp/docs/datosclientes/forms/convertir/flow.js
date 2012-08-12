importClass(Packages.java.lang.Integer);
importClass(Packages.java.lang.Boolean);

function convertirform(form) {   
       
		var esFactura = false;
		if (cocoon.request.getParameter("esFactura") != "null" && cocoon.request.getParameter("esFactura") != '') {
			esFactura = Boolean.valueOf(cocoon.request.getParameter("esFactura"));
		}
		
		if (esFactura == "null") {
			esFactura = false;
	    }
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.Xls2Postgres();
		handler.leerDatos(esFactura);
		
		var bean = handler.getDatosDPendientes(esFactura);
		var URL = "forms/convertir/results.xls";
		cocoon.sendPage(URL, { "bean":bean });
		
		
        
}

