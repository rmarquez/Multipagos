function migrarform(form) {
   // if (autorizar("informes_fact")) {
    	//form.showForm("cambiarestadofact-form-display");
        
    	var handler = new Packages.com.metropolitana.multipagos.forms.migrarcartera.MigrarHandler();
//		var FacEstadoHandler = new Packages.net.agssa.sga.forms.facturacion.FacEstadoHandler();
//		var TipoFacturaHandler = new Packages.net.agssa.sga.forms.tipofactura.TipoFacturaHandler();		
        handler.migrarDatosTablasCartera();
	    cocoon.redirectTo("/multipagos/bienvenidos", false);
 //   }
    
}
