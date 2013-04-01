function searchform(form) {		
	var handler = new Packages.com.metropolitana.multipagos.forms.ContadorVisitasPagos();
	var util = new Packages.com.metropolitana.multipagos.forms.Util();
	var bean = handler.getControlDiario();
	var usr = handler.getControlSupervisor();
	var fecha = new Packages.java.util.Date();
	var hora = util.hora(fecha);
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "handler":handler, "usr":usr, "fecha": fecha, "hora":hora});
}