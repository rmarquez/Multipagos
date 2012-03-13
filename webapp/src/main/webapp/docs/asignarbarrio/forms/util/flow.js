function alSeleccionarDepartamento(event) {
    var departamentoId = event.source.value;
    var localidadId = event.source.parent.getChild("localidadId");
    localidadId.setValue(null);
    if (departamentoId != null) {
    	localidadId.setSelectionList("cocoon:/localidad.combo?departamentoId=" + departamentoId);
    } else {
    	localidadId.setSelectionList(new EmptySelectionList("-- Localidad --"));
    }
}

function alSeleccionarLocalidad(event) {
	var localidadId = event.source.value;
    var detalle = event.source.parent.getChild("detalle");    	
		
    for (var i = 0; i < detalle.size; i++) {
    	 var row = detalle.getRow(i);	    	 
    	 var barrioId = row.getChild("barrioId"); 
    	 if (localidadId != null) {
    		 barrioId.setSelectionList("cocoon:/barrio.combo?localidadId=" + localidadId);
    	 } else {
    		 barrioId.setSelectionList(new EmptySelectionList("-- Barrio --"));
	     }	
    }
}

