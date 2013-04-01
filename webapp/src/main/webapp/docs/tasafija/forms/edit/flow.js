function editform(form) {
    if (autorizar("cata")) {
        var handler = new Packages.com.metropolitana.multipagos.forms.tasafija.TasafijaHandler();
        var dfecha = parametroFecha(cocoon.request.tasaFecha);
        var bean = handler.retrieve(dfecha);

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        handler.update(bean, auth_getUserID());

        dialogosino("Tasa de cambio C$ a U$", "Actualización de tasas de cambios",
                        "Todos los cambios se procesaron exitosamente. ¿Desea volver a procesar otra tasa de cambio?",
                        "search","/bienvenidos");
    }
}
