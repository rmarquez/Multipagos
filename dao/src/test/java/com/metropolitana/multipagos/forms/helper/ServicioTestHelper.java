package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.forms.servicio.ServicioHandler;

public class ServicioTestHelper {
	
	public final static String NOMBRE_SERVICIO_1 = "NOMBRE_SERVICIO_1";
    public final static String NOMBRE_SERVICIO_2 = "NOMBRE_SERVICIO_2";
    public final static String NOMBRE_SERVICIO_3 = "NOMBRE_SERVICIO_3";
    public final static String STR_SEARCH = "NOMBRE_SERVICIO";
    
    public final Servicio insertServicio(final String servicioNombre, Integer usrId) throws Exception {
        ServicioHandler handler = new ServicioHandler();
        Servicio servicio = new Servicio();
        servicio.setServicioNombre(servicioNombre);
        servicio.setInactivo(Boolean.FALSE);
        handler.insert(servicio, usrId);
        return servicio;
    }

}
