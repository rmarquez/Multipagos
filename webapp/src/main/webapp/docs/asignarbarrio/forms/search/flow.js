function searchform(form) {
    if (autorizar("buscar")) {
        form.showForm("search-form-display");
        
         var handler = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
         
        var colectorId = form.getChild("colectorId").getValue();
        var departamentoId =form.getChild("departamentoId").getValue();
        var localidadId = form.getChild("localidadId").getValue();
        var salir = false;

        while (!salir) {
            var bean = handler.getListAsignaciones(colectorId, departamentoId, localidadId);
            cocoon.sendPageAndWait("forms/search/results.jx", {"bean": bean});
            var asignarbId = parseInt(cocoon.request.asignarbId);

            if (!isNaN(asignarbId)) {
                var respuesta = cocoon.request.accion;
                if ("Editar".equals(respuesta)) {
                    salir = true;
                    cocoon.sendPage("edit");
                } 
            } else {
                dialogoaceptar("Asignacion", "Error", "La asignacion de Barrio no fue encontrada");
            }
        }
    }
}

