function searchform(form) {		
	var handler = new Packages.com.metropolitana.multipagos.forms.ContadorVisitasPagos();	
	cocoon.sendPage("forms/search/results.jx", {"handler":handler});
}