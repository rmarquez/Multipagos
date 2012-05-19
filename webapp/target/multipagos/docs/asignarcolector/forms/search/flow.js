function searchform(form) {
    if (autorizar("buscar")) {
        form.showForm("search-form-display");
        
         var handler = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarColectorHandler();
         
        var usrId = form.getChild("usrId").getValue();        
        var fecha = new Packages.java.util.Date();        
        var salir = false;

        while (!salir) {
            var bean = handler.getListAsignaciones(usrId);
            
            var URL = "forms/search/results.jx";
    		if (form.submitId == "excel") {
    			URL = "forms/search/results.xls";
    		}
            
            cocoon.sendPageAndWait(URL, {"bean": bean, "fecha":fecha});
            var asignarcId = parseInt(cocoon.request.asignarcId);

            if (!isNaN(asignarcId)) {
                var respuesta = cocoon.request.accion;
                if ("Editar".equals(respuesta)) {
                    salir = true;
                    cocoon.sendPage("edit");
                } 
            } else {
                dialogoaceptar("Asignacion", "Error", "La asignacion de Colector no fue encontrada");
            }
        }
    }
}

