function searchform(form) {		
	var handler = new Packages.com.metropolitana.multipagos.forms.ContadorVisitasPagos();	
	var bean = handler.getControlDiario();
	cocoon.sendPage("forms/search/results.jx", {"bean":bean, "handler":handler});
}