function searchform(form) {
    if (autorizar("cata")) {
        form.showForm("search-form-display");

        var handler = new Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();
        var dfechaini = form.getChild("fechaini").getValue();
        var salir = false;

        while (!salir) {
            var bean = handler.getList(dfechaini);
            cocoon.sendPageAndWait("forms/search/results.jx", {"bean": bean});
            var dtasaFecha = parametroFecha(cocoon.request.tasaFecha);

            if (dtasaFecha != null) {
                var respuesta = cocoon.request.accion;
                if ("Editar".equals(respuesta)) {
                    salir = true;
                    cocoon.sendPage("edit");
                } else if ("Borrar".equals(respuesta)) {
                    handler.remove(dtasaFecha, auth_getUserID());
                }
            } else {
                dialogoaceptar("Tasa de cambio", "Error", "Tasa de cambio no encontrada");
            }
        }
    }
}

function parametroFecha(pFecha) {
    var strFechaPatron = new String("dd/MM/yyyy");
    var fecha = null;

    try {
        fecha = new java.text.SimpleDateFormat(strFechaPatron).parse(pFecha);
    } catch (e) { }
    return fecha
}
