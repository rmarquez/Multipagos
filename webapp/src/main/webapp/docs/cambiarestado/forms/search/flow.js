importClass(Packages.java.lang.Integer);
function searchform(form) {   
       
        var handler = new Packages.com.metropolitana.multipagos.forms.estado_pago.EstadoPagoHandler();
        handler.cambiarEstadoCartera();
        cocoon.sendPage("/bienvenidos");
        
}