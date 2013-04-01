/*
    Este formulario es un cuadro de dialogo con botones de Sí y No
    Parámetros:

        titulo      : Este campo puede ser el area que esta tratando el usuario,
                      por ejemplo "Catálogo Contable", "Usuarios", etc.
        titulos1    : Este campo puede ser utilizado para describir de forma general
                      la acción a realizar por el usuario. por ejemplo:
                      "Borrar cuenta", "Eliminar usuario", etc.
        descripcion : Este campo se puede utilizar para presentar la consulta
                      de forma concreta al usuario, podría incluir una explicación
                      rápida, por ejemplo "¿Esta seguro que desea eliminar la cuenta ?".
                      Este campo se escribe como un texto preformateado <pre></pre>
                      en HTML, por lo tanto es posible que envía saltos de línea
                      como parte de la cadena, en caso de que tenga que presentar
                      un texto extenso. Tambien es posible que este campo sea
                      un mensaje en i18n.
        sendpagesi  : Indica la pagina a visitar cuando el usuario hace click
                      en el boton sí. Sin embargo esta función podría ser llamada
                      desde el flow, para lo cual podría retornar un valor de
                      true o false, si desea que la función retorne true o false
                      este parámetro no debe se incluído.
        sendpageno  : Indica la pagina a visitar cuando el usuario hace click
                      en el boton no. Sin embargo esta función podría ser llamada
                      desde el flow, para lo cual podría retornar un valor de
                      true o false, si desea que la función retorne true o false
                      este parámetro no debe se incluído.

    Puede incluir esta función en el sitemap o en el flow de la siguiente manera:
    Flow:
            cocoon.load("context://docs/util/dialogos/sino/flow.js");

    Sitemap:
            <map:script src="context://docs/util/dialogos/sino/flow.js"/>
*/

function dialogosino(titulo, titulos1, descripcion, sendpagesi, sendpageno) {
    var form = new Form("context://docs/util/dialogos/sino/definition.xml");

    var kont = form.showForm("/util/sino-dlg-display",
                  {"titulo":titulo, "titulos1":titulos1, "descripcion":descripcion});
    kont.invalidate();
    if (form.submitId == "true") {
        if (sendpagesi==undefined) {
            return true;
        }
        cocoon.sendPage(sendpagesi);
    } else if (form.submitId == "false") {
        if (sendpageno==undefined) {
            return false;
        }
        cocoon.sendPage(sendpageno);
    } else {
        cocoon.sendPage("/bienvenidos");
    }
}
