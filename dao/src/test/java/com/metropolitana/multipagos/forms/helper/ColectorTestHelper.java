package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.forms.colector.ColectorHandler;

public class ColectorTestHelper {
	public final static String NOMBRE_COLECTOR_1 = "NOMBRE_COLECTOR_1";
    public final static String NOMBRE_COLECTOR_2 = "NOMBRE_COLECTOR_2";
    public final static String NOMBRE_COLECTOR_3 = "NOMBRE_COLECTOR_3";
    
    public final static String APELLIDO_COLECTOR_1 = "APELLIDO_COLECTOR_1";
    public final static String APELLIDO_COLECTOR_2 = "APELLIDO_COLECTOR_2";
    public final static String APELLIDO_COLECTOR_3 = "APELLIDO_COLECTOR_3";
    public final static String STR_SEARCH = "NOMBRE_COLECTOR";
    
	public final Colector insertColector(final Integer num,
			final String primerNombre, final String primerApellido,
			Integer usrId) throws Exception {
		ColectorHandler colectorHandler = new ColectorHandler();
		Colector colector = new Colector();
		colector.setColectorNumero(num);
		colector.setPrimerNombre(primerNombre);
		colector.setPrimerApellido(primerApellido);
		colector.setInactivo(Boolean.FALSE);
		colectorHandler.insert(colector, usrId);
		return colector;
	}

}
