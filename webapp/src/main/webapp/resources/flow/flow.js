function success(titulo, subtitulo, descripcion, enlace) {
  cocoon.sendPage("/success",
    {
			"titulo" : titulo,
			"subtitulo" : subtitulo,
			"descripcion" : descripcion,
			"enlace" : enlace
    }
  );
}

function display_menu() {
    var usrId = auth_getUserID();
    var bean = new Packages.com.metropolitana.multipagos.forms.MenuUser(parseInt(usrId));
	cocoon.sendPage("menu-vista", {"bean": bean, "usrId":usrId})
}
