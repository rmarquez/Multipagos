package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.localidad.LocalidadHandler;



public class LocalidadTestHelper {
	
	public final static String NOMBRE_LOCALIDAD_1 = "NOMBRE_LOCALIDAD_1";
    public final static String NOMBRE_LOCALIDAD_2 = "NOMBRE_LOCALIDAD_2";
    public final static String NOMBRE_LOCALIDAD_3 = "NOMBRE_LOCALIDAD_3";
    public final static String NOMBRE_LOCALIDAD_4 = "NOMBRE_LOCALIDAD_4";
    public final static String STR_SEARCH = "NOMBRE_LOCALIDAD";
    
	public final Localidad insertLocalidad(final String localidadNombre,
			Departamento depLocalidad, Integer usrId) throws Exception {
		
		LocalidadHandler handler = new LocalidadHandler();
		Localidad localidad = new Localidad();
		localidad.setLocalidadNombre(localidadNombre);
		localidad.setDepartamentoId(depLocalidad.getDepartamentoId());
		localidad.setInactivo(Boolean.FALSE);
		handler.insert(localidad, usrId);
		return localidad;

	}
}
