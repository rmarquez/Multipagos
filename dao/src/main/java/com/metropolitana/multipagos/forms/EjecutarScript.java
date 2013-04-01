package com.metropolitana.multipagos.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;

public class EjecutarScript {

    /**
     * Ejecuta código escrito en javascript
     *
     * @param strFuncion
     *            Código en javascript
     * @return Object resultado de la ejecución del código
     * @throws JavaScriptException
     */
    public static Object ejecutar(String strFuncion) throws JavaScriptException {
        Context cx = Context.enter();
        try {
            return cx.evaluateString(cx.initStandardObjects(null), strFuncion, "<cmd>", 1, null);
        } finally {
            Context.exit();
        }
    }

    /**
     * Crea una función en javascript
     *
     * @param valores
     *            Listado de los valores de los parámetros, puede estar vacío,
     *            los valores pueden ser también NaN
     * @param cuerpoFuncion
     *            Cuerpo de la función a crear
     * @return String cadena que contiene código en javascript
     * @throws Exception
     */
    public static String crearFuncion(HashMap valores, String cuerpoFuncion) {
        StringBuffer strCodigo = new StringBuffer(valores.size() * 40);
        StringBuffer strParam = new StringBuffer(valores.size() * 40);

        // Determinos si se pasaron los parametros y sus valores a la función
        if (!valores.isEmpty()) {
            // Variables para cada valor y le asignamos su valor si es necesario
            for (Iterator i = valores.entrySet().iterator(); i.hasNext();) {
                Map.Entry e = (Map.Entry) i.next();
                strCodigo.append("var ").append(e.getKey());
                if (e.getValue() != null) {
                    strCodigo.append("=").append(e.getValue().toString());
                }
                strCodigo.append(";");
                // Cadena de la lista de parametros en la función
                strParam.append(e.getKey());
                if (i.hasNext()) {
                    strParam.append(",");
                }
            }
        }
        // Creamos el cuerpo de la función y su llamado
        strCodigo.append(" function f(" + strParam + "){" + cuerpoFuncion + "}" + " f(" + strParam + ");");
        return strCodigo.toString();
    }

    /**
     * Crea una función en javascript y la ejecuta
     *
     * @param valores
     *            Listado de los valores de los parámetros, puede estar vacío,
     *            los valores pueden ser también NaN
     * @param cuerpoFuncion
     *            Cuerpo de la función a ejecutar
     * @return Object Resultado de la ejecución de la función, la conversión
     *         estara a cargo del llamador
     * @throws Exception
     */
    public static Object ejecutar(HashMap valores, String cuerpoFuncion) throws Exception {
        try {
            return ejecutar(crearFuncion(valores, cuerpoFuncion));
        } catch (Exception e) {
            throw e;
        }
    }

}