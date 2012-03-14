function searchform(form) {
    if (autorizar("buscar")) {
        form.showForm("search-form-display");
        
         var handler = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
         
        var colectorId = form.getChild("colectorId").getValue();
        var departamentoId =form.getChild("departamentoId").getValue();
        var localidadId = form.getChild("localidadId").getValue();
        
        var fecha = new Packages.java.util.Date();
        
        var salir = false;

        while (!salir) {
            var bean = handler.getListAsignaciones(colectorId, departamentoId, localidadId);
            
            var URL = "forms/search/results.jx";
    		if (form.submitId == "excel") {
    			URL = "forms/search/results.xls";
    		}
            
            cocoon.sendPageAndWait(URL, {"bean": bean, "fecha":fecha});
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

