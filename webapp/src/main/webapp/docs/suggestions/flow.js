importClass(Packages.com.metropolitana.multipagos.forms.Util);
importClass(Packages.java.lang.Integer);


function suggestionListDocumentLogs(filter) {
	var suggestions = [];
	var handler = new Packages.com.metropolitana.multipagos.forms.logs.TipoDocumentoLogHandler();
	var util = new Util();
	if (filter) {
	  var phase = cocoon.request.getParameter("phase");
	  if (phase && phase.equals("init")) {
	    if (!isNaN(parseInt(filter))) {
	      var bean = handler.retrieve(util.stringToInt(filter));
		  suggestions.push({
		       value: bean.getTipodLogId().intValue(),
		       // Corregir los nombre en caso que la cadena
     		   // contenga comillas dobles, para cargar
			   // el arreglo JSON sin problemas.
		       label: new String(util.escaparComillas(bean.getTipodLogNombre()))
	        });
	    } else {
	      cocoon.log.error("El filtro: '" + filter + "' debe ser un numero.");
	    }
	  } else {
	    var list = handler.getList(filter);
	    for (var i = 0; i < list.size(); i++) {
	      var item = list.get(i);
	      suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(item[1]))
	        });
	    }
	  }
	} else {
	  var list = handler.getList();
	  for (var i = 0; i < list.size(); i++) {
	    var item = list.get(i);
	    suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(item[1]))
	        });
	  }
	}
	return suggestions;
}

function suggestionListServicio(filter) {
	var suggestions = [];
	var handler = new Packages.com.metropolitana.multipagos.forms.servicio.ServicioHandler();
	var util = new Util();
	if (filter) {
	  var phase = cocoon.request.getParameter("phase");
	  if (phase && phase.equals("init")) {
	    if (!isNaN(parseInt(filter))) {
	      var bean = handler.retrieve(util.stringToInt(filter));
		  suggestions.push({
		       value: bean.getServicioId().intValue(),
		       // Corregir los nombre en caso que la cadena
     		   // contenga comillas dobles, para cargar
			   // el arreglo JSON sin problemas.
		       label: new String(util.escaparComillas(bean.getServicioNombre()))
	        });
	    } else {
	      cocoon.log.error("El filtro: '" + filter + "' debe ser un numero.");
	    }
	  } else {
	    var list = handler.getList(filter);
	    for (var i = 0; i < list.size(); i++) {
	      var item = list.get(i);
	      suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(item[1]))
	        });
	    }
	  }
	} else {
	  var list = handler.getList();
	  for (var i = 0; i < list.size(); i++) {
	    var item = list.get(i);
	    suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(item[1]))
	        });
	  }
	}
	return suggestions;
}

function suggestionListColector(filter) {
	var suggestions = [];
	var handler = new Packages.com.metropolitana.multipagos.forms.colector.ColectorHandler();
	var util = new Util();
	if (filter) {
	  var phase = cocoon.request.getParameter("phase");
	  if (phase && phase.equals("init")) {
	    if (!isNaN(parseInt(filter))) {
	      var bean = handler.retrieve(util.stringToInt(filter));
	      var nombre = bean.getPrimerNombre()+bean.getPrimerApellido();
		  suggestions.push({
		       value: bean.getColectorId().intValue(),
		       // Corregir los nombre en caso que la cadena
     		   // contenga comillas dobles, para cargar
			   // el arreglo JSON sin problemas.
		       label: new String(util.escaparComillas(nombre))  });
	    } else {
	      cocoon.log.error("El filtro: '" + filter + "' debe ser un numero.");
	    }
	  } else {
	    var list = handler.getListColector(filter);
	    for (var i = 0; i < list.size(); i++) {
	      var item = list.get(i);
	      var nombre = item[1]+" " +item[2];
	      suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(nombre))
	        });
	    }
	  }
	} else {
	  var list = handler.getListColector();
	  for (var i = 0; i < list.size(); i++) {
	    var item = list.get(i);
	    var nombre = item[1]+" " +item[2];
	    suggestions.push({
	        value: item[0].intValue(),
	        // Corregir los nombre en caso que la cadena
	 		// contenga comillas dobles, para cargar
			// el arreglo JSON sin problemas.
	        label: new String(util.escaparComillas(nombre))
	        });
	  }
	}
	return suggestions;
}