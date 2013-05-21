importClass(Packages.java.lang.Integer);
function migrarform(form) {   
		var usrId = auth_getUserID();
		var handler = new Packages.com.metropolitana.multipagos.forms.xlstopostgresql.XlsVisitasPendientes();
		var util = Packages.com.metropolitana.multipagos.forms.Util();
		var numLote = handler.numeroLote(usrId);
		var bean = handler.getPendientesClaro(numLote, usrId);
		
		cocoon.sendPageAndWait("forms/migrar/results.pdf", {"bean" : bean, "util" : util});
}

