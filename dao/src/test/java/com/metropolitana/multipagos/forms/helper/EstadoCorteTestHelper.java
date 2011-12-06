package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.forms.estado_corte.EstadoCorteHandler;

public class EstadoCorteTestHelper {
	
	public final static String NOMBRE_ESTADO_1 = "NOMBRE_ESTADO_1";
    public final static String NOMBRE_ESTADO_2 = "NOMBRE_ESTADO_2";
    public final static String NOMBRE_ESTADO_3 = "NOMBRE_ESTADO_3";
    public final static String NOMBRE_ESTADO_4 = "NOMBRE_ESTADO_4";
    public final static String NOMBRE_ESTADO_5 = "NOMBRE_ESTADO_5";
    public final static String NOMBRE_ESTADO_6 = "NOMBRE_ESTADO_6";
    public final static String STR_SEARCH = "NOMBRE_ESTADO";
    
	public final EstadoCorte insertEstado(final String estadoNombre,
			Integer usrId) throws Exception {
		EstadoCorte estado = new EstadoCorte();
		EstadoCorteHandler handler = new EstadoCorteHandler();
		estado.setEstadoNombre(estadoNombre);
		estado.setInactivo(Boolean.FALSE);
		handler.insert(estado, usrId);
		return estado;
	}

}
