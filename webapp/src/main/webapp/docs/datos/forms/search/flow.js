importClass(Packages.java.lang.Integer);
importClass(Packages.com.metropolitana.multipagos.forms.MenuUser);
importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);

function searchform(form) {		
	var usrId = auth_getUserID();
	var handlerUser = new Auth_userHandler();
	var usuario = handlerUser.retrieve(usrId);
	var permisos = new MenuUser(parseInt(usuario.getUsrId()));
	
	var handler = new Packages.com.metropolitana.multipagos.forms.ContadorVisitasPagos();
	var handlerIbw = new Packages.com.metropolitana.multipagos.forms.ContVisitasIbw();
	
	var util = new Packages.com.metropolitana.multipagos.forms.Util();
	
	var bean = handler.getControlDiario();
	var beanIbw = handler.getControlDiario();
	
	var usr = handler.getControlSupervisor();
	var usrIbw = handlerIbw.getControlSupervisor();

	var fecha = new Packages.java.util.Date();
	var hora = util.hora(fecha);
	cocoon.sendPage("forms/search/results.jx", {
		"bean" : bean,
		"handler" : handler,
		"usr" : usr,
		"fecha" : fecha,
		"hora" : hora,
		"beanIbw" : beanIbw,
		"handlerIbw" : handlerIbw,
		"usrIbw" : usrIbw,
		"permisos" : permisos
	});
}