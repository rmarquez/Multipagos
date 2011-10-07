/*
    Este formulario es un cuadro de dialogo con el botón aceptar
    Parámetros:

        titulo      :
        titulos1    :
        descripcion :
        sendpageaceptar  : Indica la pagina a visitar cuando el usuario hace click
                      en el boton aceptar. Sin embargo esta función podría ser llamada
                      desde el flow, si desea que la función retorne al flow llamador
                      este parámetro no debe se incluído.

    Puede incluir esta función en el sitemap o en el flow de la siguiente manera:
    Flow:
            cocoon.load("context://docs/util/dialogos/aceptar/flow.js");

    Sitemap:
            <map:script src="context://docs/util/dialogos/aceptar/flow.js"/>
*/

function dialogoaceptar(titulo, titulos1, descripcion, sendpageaceptar) {
    var form = new Form("context://docs/util/dialogos/aceptar/definition.xml");

    var kont = form.showForm("/util/aceptar-dlg-display",
                  {"titulo":titulo, "titulos1":titulos1, "descripcion":descripcion});
    kont.invalidate();
    if (form.submitId == "aceptar") {
        if (sendpageaceptar==undefined) {
            return true;
        }
        cocoon.sendPage(sendpageaceptar);
    } else {
        cocoon.sendPage("/bienvenidos");
    }
}
// Fin del Funcion

