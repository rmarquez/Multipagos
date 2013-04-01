package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ScriptUtil contiene una serie de funciones para ser utilizadas en el flow
 *
 * @author rmarquez
 *
 */
public class ScriptUtil {

    /**
     * Convertir una cadena(String) a fecha(Date), la fecha de salidad tiene el
     * formato dd/MM/yyyy En javascript el tipo Date no es el mismo que el tipo
     * Date en java, por tal motivo se necesita esta función para realizar dicha
     * conversiones.
     *
     * @param strDate
     *            Cadena con la fecha de entrada
     * @return Retorna una fecha (Date)
     * @throws ParseException
     */
    public static Date strToDate(String strDate) throws ParseException {
        if (strDate != null && strDate.length() > 0 && !strDate.equals("undefined")) {
        	//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        	try {
                return (Date)sdf.parse(strDate);
            } catch (ParseException e) {
            	e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Convertir una cadena(String) a un Entero(Integer). Esta función esta
     * diseñada para ser utilizada en el flow, si el flow captura un valor desde
     * los parámetros de la solicitud y este no existe, entonces pone el valor
     * de "undefined", el cual generaría una excepción, por ejemplo cuando
     * tomamos valores de un radio button, el cual si esta vacío el parámetro no
     * es enviado.
     *
     * @param strInt
     *            Cadena que contiene en número
     * @return Retorna un Integer
     * @throws NumberFormatException
     */
    public static Integer strToInteger(String strInt) throws NumberFormatException {
        if (strInt != null && strInt.length() > 0 && !strInt.equals("undefined")) {
            try {
                return Integer.valueOf(strInt);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    /**
     * Convertir una cadena(String) a un Boleano(Boolean). Esta función esta
     * diseñada para se utilizada en el flow.
     *
     * @param strBool
     * @return Boolean
     */
    public static Boolean strToBoolean(String strBool) {
        if (strBool != null && strBool.length() > 0
                && (strBool.equals("true") || strBool.equals("false"))) {
            return Boolean.valueOf(strBool);
        }
        return null;
    }

    /**
     * Convertir una cadena(String) a un BigDecimal. Esta función esta diseñada
     * para ser utilizada en el flow, si el flow captura un valor desde los
     * parámetros de la solicitud y este no existe, entonces pone el valor de
     * "undefined", el cual generaría una excepción, por ejemplo cuando tomamos
     * valores de un radio button, el cual si esta vacío el parámetro no es
     * enviado.
     *
     * @param strBig
     *            Cadena que contiene el número
     * @return Retorna un BigDecimal
     * @throws NumberFormatException
     */
    public static BigDecimal strToBigDecimal(String strBig) throws NumberFormatException {
        if (strBig != null && strBig.length() > 0 && !strBig.equals("undefined")) {
            try {
                return new BigDecimal(strBig);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    /**
     * Retorna la fecha actual del sistema
     *
     * @return Date Fecha actual
     */
    public static Date getTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Retorna una cadena que contiene la fecha en formato completo.
     *
     * @param fecha
     *            Fecha a formatear
     * @return String fecha formateada
     */
    public static String formatoFechaCompleta(Date fecha) {
        return DateFormat.getDateInstance(DateFormat.FULL).format(fecha);
    }
}