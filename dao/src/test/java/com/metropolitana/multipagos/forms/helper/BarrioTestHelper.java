package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.forms.barrio.BarrioHandler;



public class BarrioTestHelper {
	
	public final static String NOMBRE_BARRIO_1 = "NOMBRE_BARRIO_1";
    public final static String NOMBRE_BARRIO_2 = "NOMBRE_BARRIO_2";
    public final static String NOMBRE_BARRIO_3 = "NOMBRE_BARRIO_3";
    public final static String NOMBRE_BARRIO_4 = "NOMBRE_BARRIO_4";
    public final static String STR_SEARCH = "NOMBRE_BARRIO";
    
	public final Barrio insertBarrio(final String barrioNombre,
			Localidad localidad, Integer usrId) throws Exception {
		
		BarrioHandler handler = new BarrioHandler();
		Barrio barrio = new Barrio();
		barrio.setBarrioNombre(barrioNombre);
		barrio.setLocalidadId(localidad.getLocalidadId());
		barrio.setInactivo(Boolean.FALSE);
		handler.insert(barrio, usrId);
		return barrio;

	}
}
