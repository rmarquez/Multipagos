/**
 *
 */
package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.metropolitana.multipagos.forms.ScriptUtil;
import com.metropolitana.multipagos.junit.PBTestBase;


/**
 *
 * @author rmarquez
 *
 */
public class TestUtils extends PBTestBase {

	public void setUp() throws Exception {
        super.setUp();
    }

	public void testScriptUtil() throws Exception {

		// Probar los métodos de la clase  ScriptUtil

		//Date fechaConv = ScriptUtil.strToDate("10/01/2010");
		Date fechaConv = ScriptUtil.strToDate("Tue Nov 09 14:35:52 CST 2010");

		Calendar fechaTmp = Calendar.getInstance();
		fechaTmp.setTime(fechaConv);
		assertEquals("El dato no es una fecha.", fechaTmp.getTime().toString(), fechaConv.toString());

		Date fechaConvNull = ScriptUtil.strToDate(null);
		assertNull("El objeto existe.", fechaConvNull);

		fechaConvNull = ScriptUtil.strToDate("");
        assertNull("El objeto existe.", fechaConvNull);

        fechaConvNull = ScriptUtil.strToDate("undefined");
        assertNull("El objeto existe.", fechaConvNull);

		Integer IntConv = ScriptUtil.strToInteger("09112010");
		assertEquals("El valor no es entero.", Integer.valueOf(9112010), IntConv);

		Integer IntConvNull = ScriptUtil.strToInteger(null);
		assertNull("El objeto existe.", IntConvNull);

		IntConvNull = ScriptUtil.strToInteger("");
        assertNull("El objeto existe.", IntConvNull);

        IntConvNull = ScriptUtil.strToInteger("undefined");
        assertNull("El objeto existe.", IntConvNull);

		Boolean BoolConv = ScriptUtil.strToBoolean("true");
		assertTrue("El valor no es booleano.", BoolConv);

		Boolean BoolConvF = ScriptUtil.strToBoolean("false");
        assertTrue("El valor no es booleano.", BoolConv);

		Boolean BoolConvNull = ScriptUtil.strToBoolean(null);
		assertNull("El objeto existe.", BoolConvNull);

		BoolConvNull = ScriptUtil.strToBoolean("");
        assertNull("El objeto existe.", BoolConvNull);

        BoolConvNull = ScriptUtil.strToBoolean("undefined");
        assertNull("El objeto existe.", BoolConvNull);

		BigDecimal BigDConv = ScriptUtil.strToBigDecimal("42.96");
		assertEquals("El valor no es decimal.", BigDecimal.valueOf(42.96), BigDConv);

		BigDecimal BigDConvNull = ScriptUtil.strToBigDecimal(null);
		assertNull("El objeto existe.", BigDConvNull);

		BigDConvNull = ScriptUtil.strToBigDecimal("");
        assertNull("El objeto existe.", BigDConvNull);

        BigDConvNull = ScriptUtil.strToBigDecimal("undefined");
        assertNull("El objeto existe.", BigDConvNull);


        // TODO: La siguiente condicional evita que los tests se caigan en la generación del sitio maven.
        // Se debe eliminar esta condición una vez que maven soporte es como idioma
        if (!Locale.getDefault().getLanguage().equals("en")) {
          Date fechaActual = ScriptUtil.getTime();
		  assertEquals("No es la fecha actual del sistema.", new Date().toString(), fechaActual.toString());
          String fechaCompleta = ScriptUtil.formatoFechaCompleta(fechaConv);
          assertEquals("El valor no es el formato de fecha completa.", "martes 09 de noviembre de 2010", fechaCompleta);
        }

        String fecha = Util.formatoFechaCompleta(fechaConv);
        assertEquals("El valor no es el formato de fecha completa.", "martes 09 de noviembre de 2010", fecha);
	}

	public void testUtil() throws Exception {

			Date primerodelMes = Util.primeroDelMes();

			Calendar fechaTmp = Calendar.getInstance();
			fechaTmp.set(Calendar.DAY_OF_MONTH, 1);
			assertNotNull("No existe la fecha.", primerodelMes);
			assertNotSame("El tipo de datos no es el correcto.", "Sun Oct 01 13:46:17 CST 2010", primerodelMes);
			assertEquals("El tipo de datos no es el correcto.", fechaTmp.getTime().toString(), primerodelMes.toString());

			Calendar fechaTmp2 = Calendar.getInstance();
			fechaTmp2.set(Calendar.MONTH, 3);
			fechaTmp2.set(Calendar.DAY_OF_MONTH, 10);

			Calendar fechaTmp1 = Calendar.getInstance();
			fechaTmp1.set(Calendar.MONTH, 3);
			fechaTmp1.set(Calendar.DAY_OF_MONTH, 1);
			Date primerodelMesFecha = Util.primeroDelMes(fechaTmp2.getTime());
			assertEquals("No es el primer dia del mes.", fechaTmp1.getTime().toString(), primerodelMesFecha.toString());

			Calendar fechaTmpSig = Calendar.getInstance();
			fechaTmpSig.set(Calendar.MONTH, 4);
			fechaTmpSig.set(Calendar.DAY_OF_MONTH, 1);
			Date siguienteMesFecha = Util.siguienteMes(fechaTmp2.getTime());
			assertEquals("No es el siguiente mes.", fechaTmpSig.getTime().toString(), siguienteMesFecha.toString());

			//Contiene el primero de julio -- inicio periodo fiscal
			Calendar fechaInicioPeriodo = Calendar.getInstance();
			fechaInicioPeriodo.set(Calendar.MONTH, 6);
			fechaInicioPeriodo.set(Calendar.DAY_OF_MONTH, 1);
			Date inicioperiodo = Util.inicioPeriodo(fechaTmp.get(Calendar.YEAR));
			assertEquals("No contiene el primero de julio", fechaInicioPeriodo.getTime().toString(), inicioperiodo.toString());

			//Contiene el 30 de junio --- fin periodo fiscal
			Calendar fechaFinPeriodo = Calendar.getInstance();
			fechaFinPeriodo.set(Calendar.MONTH, 5);
			fechaFinPeriodo.set(Calendar.DAY_OF_MONTH, 30);
			fechaFinPeriodo.set(Calendar.YEAR, fechaFinPeriodo.get(Calendar.YEAR) + 1);
			Date finperiodo = Util.finPeriodo(fechaTmp.get(Calendar.YEAR));
			assertEquals("No contiene el 30 de junio", fechaFinPeriodo.getTime().toString(), finperiodo.toString());

			Calendar fechaTmpUlt = Calendar.getInstance();
			fechaTmpUlt.setTime(fechaTmp.getTime());
			fechaTmpUlt.set(Calendar.DAY_OF_MONTH, fechaTmpUlt.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date ultimoDelMesFecha = Util.ultimoDelMes(fechaTmp.getTime());
			assertEquals("No es el ultimo del mes.", fechaTmpUlt.getTime().toString(), ultimoDelMesFecha.toString());

			Date fechadias = Util.fechaDias(null, 0);
			assertNull("No introdujo una fecha", fechadias);

			fechadias = Util.fechaDias(inicioperiodo, 0);
			assertEquals("las fechas no son equivalentes", inicioperiodo.toString(), fechadias.toString());

			String formatnum = Util.formatoNumero(16053, "#,###.00");
            assertEquals("No se aplicó el formato", "16,053.00", formatnum);

            Calendar fecha1 = Calendar.getInstance();
            fecha1.set(Calendar.MONTH, 5);
            fecha1.set(Calendar.DAY_OF_MONTH, 30);
            fecha1.set(Calendar.YEAR, fecha1.get(Calendar.YEAR) + 1);

            Calendar fecha2 = Calendar.getInstance();
            fecha2.set(Calendar.MONTH, 6);
            fecha2.set(Calendar.DAY_OF_MONTH, 16);

            Calendar fecha3 = Calendar.getInstance();
            fecha3.set(Calendar.MONTH, 4);
            fecha3.set(Calendar.DAY_OF_MONTH, 30);

            Calendar fecha4 = Calendar.getInstance();
            fecha4.set(Calendar.MONTH, 6);
            fecha4.set(Calendar.DAY_OF_MONTH, 30);

           //año inicial mayor que el final
            Boolean compfechas = Util.compareFechas(fecha1.getTime(), fecha2.getTime());
            assertFalse("Las fechas no son iguales", compfechas);

            //año inicial menor que el final
            compfechas = Util.compareFechas(fecha2.getTime(), fecha1.getTime());
            assertTrue("Las fechas no son iguales", compfechas);

            //dos años iguales
            compfechas = Util.compareFechas(fecha2.getTime(), fecha3.getTime());
            assertFalse("Las fechas no son iguales", compfechas);

            //mes inicial mayor que el mes final
            compfechas = Util.compareFechas(fecha2.getTime(), fecha3.getTime());
            assertFalse("Las fechas no son iguales", compfechas);

            //mes inicial menor que el mes final
            compfechas = Util.compareFechas(fecha3.getTime(), fecha2.getTime());
            assertTrue("Las fechas no son iguales", compfechas);

            //mes inicial igual al mes final
            compfechas = Util.compareFechas(fecha2.getTime(), fecha4.getTime());
            assertFalse("Las fechas no son iguales", compfechas);

            //dia inicial mayor que el dia final
            compfechas = Util.compareFechas(fecha3.getTime(), fecha2.getTime());
            assertTrue("Las fechas no son iguales", compfechas);

            //dia final mayor que el dia inicial
            compfechas = Util.compareFechas(fecha4.getTime(), fecha2.getTime());
            assertFalse("Las fechas no son iguales", compfechas);

            //dia inicial y final iguales
            compfechas = Util.compareFechas(fecha4.getTime(), fecha4.getTime());
            assertTrue("Las fechas no son iguales", compfechas);

            //ambas fechas null
            compfechas = Util.compareFechas(null, null);
            assertTrue("Las fechas no son iguales", compfechas);

            compfechas = Util.compareFechas(null, fecha4.getTime());
            assertTrue("Las fechas no son iguales", compfechas);

            compfechas = Util.compareFechas(fecha4.getTime(), null);
            assertTrue("Las fechas no son iguales", compfechas);

            String cadena = Util.quitarEspacios(inicioperiodo.toString());
            assertNotNull("No se obtuvo la cadena", cadena);

            cadena = Util.quitarEspacios(null);
            assertNull("No se obtuvo la cadena", cadena);

            Integer numero = Util.stringToInt("1,236");
            assertNotNull("No se obtuvo el numero");

            numero = Util.stringToInt("1236");
            assertNotNull("No se obtuvo el numero");

            String cadenacomillas = Util.escaparComillas("\"concomillas\"");
            assertNotNull("La cadena no fue obtenida", cadenacomillas);

            cadenacomillas = Util.escaparComillas("sincomillas");
            assertNotNull("La cadena no fue obtenida", cadenacomillas);

			// Formato de número de ticket.
			String facNumero = Util.formatoNumeroFacturaTicket(1);
			assertEquals("El formato para el número de factura no es correcto.", "0000-0001", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(12);
			assertEquals("El formato para el número de factura no es correcto.", "0000-0012", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(123);
			assertEquals("El formato para el número de factura no es correcto.", "0000-0123", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(1234);
			assertEquals("El formato para el número de factura no es correcto.", "0000-1234", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(12345);
			assertEquals("El formato para el número de factura no es correcto.", "0001-2345", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(123456);
			assertEquals("El formato para el número de factura no es correcto.", "0012-3456", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(1234567);
			assertEquals("El formato para el número de factura no es correcto.", "0123-4567", facNumero);
			facNumero = Util.formatoNumeroFacturaTicket(12345678);
			assertEquals("El formato para el número de factura no es correcto.", "1234-5678", facNumero);
			}

	protected void cleanUpDB() throws Exception {
		// No hay nada que eliminar.
    }

}
