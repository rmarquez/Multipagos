package com.metropolitana.multipagos.forms.helper;

import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.forms.departamentos.DepartamentosHandler;



public class DepartamentoTestHelper {
    public final static String NOMBRE_DEP_1 = "NOMBRE_DEP_1";
    public final static String NOMBRE_DEP_2 = "NOMBRE_DEP_2";
    public final static String NOMBRE_DEP_3 = "NOMBRE_DEP_3";
    public final static String NOMBRE_DEP_4 = "NOMBRE_DEP_4";
    public final static String NOMBRE_DEP_5 = "NOMBRE_DEP_5";
    public final static String NOMBRE_DEP_6 = "NOMBRE_DEP_6";
    public final static String STR_SEARCH = "NOMBRE_DEP";

    public final Departamento insertDepartamento(final String depNombre, Integer usrId) throws Exception {
        DepartamentosHandler depHandler = new DepartamentosHandler();
        Departamento departamento = new Departamento();
        departamento.setDepartamentoNombre(depNombre);
        departamento.setInactivo(Boolean.FALSE);
        depHandler.insert(departamento, usrId);
        return departamento;
    }
}
