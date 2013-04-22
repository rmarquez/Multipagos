package com.metropolitana.multipagos.forms;

import com.metropolitana.multipagos.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.PersistenceBroker;

import com.metropolitana.multipagos.forms.empresa.EmpresaHandler;
import com.metropolitana.multipagos.forms.informes.InformesUtil;

// RSJ 20120424 
import java.sql.*;
import java.text.*;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;


public class Util {
	
	
    /**
     * Calcula la fecha que contiene el primer día del mes actual.
     *
     * @return Date Fecha con el primer día del mes actual.
     */
    public static Date primeroDelMes() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * Calcula la fecha que contiene el primer día de un mes.
     *
     * @param fecha
     *            fecha de referencia a partir de la cual se calcula la fecha
     *            del primer día de mes
     * @return Date Fecha con el primer día del mes
     */
    public static Date primeroDelMes(final Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * Calcula la fecha que contiene el primer día del siguiente mes.
     *
     * @param fecha
     *            fecha de referencia a partir de la cual se calcula la fecha
     *            del siguiente mes
     * @return Date Fecha que contiene el primer día del siguiente mes a partir
     *         de la fecha actual
     */
    public static Date siguienteMes(final Date fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
    
    public static Date otroMes(final Date fecha, final int incremento) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, incremento);
        return calendar.getTime();
    }
    /**
     * Inicio del Periodo fiscal
     * @param fecha
     * @return Fecha contiene el primero de julio del año seleccionado
     */

    public static Date inicioPeriodo(final int fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.YEAR, fecha);
        return calendar.getTime();
    }

    /**
     * Fin del Periodo fiscal
     * @param fecha
     * @return Fecha contiene el 30 de Junio.
     */

    public static Date finPeriodo(final int fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.YEAR, fecha+1);
        return calendar.getTime();
    }
    
    
    public static String hora(final Date fecha) {
        Calendar calendar = Calendar.getInstance();
        int hora, minutos, segundos;
        calendar.setTime(fecha);
        
        hora = calendar.get(Calendar.HOUR_OF_DAY);
        minutos= calendar.get(Calendar.MINUTE);
        segundos = calendar.get(Calendar.SECOND);
        String horaActual = hora + ":" + minutos + ":" + segundos;
        return horaActual;
    }

    /**
     * Regresa la fecha final del período a partir de una fecha dada.
     *
     * @param fecha Fecha con la que se calcula el final del período
     *
     * @return Fecha de fin de Período del período fiscal.
     */
    public static Date finalDelPeriodo(final Date fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        int mesActual = calendar.get(Calendar.MONTH);
        //Comprobamos si estamos al incio del período para aumentar el año
        if(mesActual >= Calendar.JULY) {
            calendar.add(Calendar.YEAR, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        return calendar.getTime();
    }

    /**
     * Calcula la fecha que contiene el último día de un mes.
     *
     * @param fecha
     *            fecha de referencia a partir de la cual se calcula el último
     *            día del mes
     * @return Date Fecha que contiene el último día del mes
     */
    public static Date ultimoDelMes(final Date fecha) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }    

    /**
     * Convierte una fecha en un cadena que contiene la fecha en formato
     * completa.
     *
     * @param fecha
     *            Fecha a formatear
     * @return String Cadena que contiene una fecha
     */
    public static String fechaToString(final Date fecha) {
    	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");    	
        return sdf.format(fecha);
    }
    
    public static String formatoFechaCompleta(final Date fecha) {
        return DateFormat.getDateInstance(DateFormat.FULL).format(fecha);
    }

    public static String getFechaString(final Date fecha) {
		String strDescripcion = "";
		return strDescripcion = fecha.getHours() + ":" + fecha.getMinutes();
	}
    
    public static String formatoNumero(final double numero, final String strPatron) {
        NumberFormat formatter = new DecimalFormat(strPatron);
        return formatter.format(numero);
    }

	/*public static Date stringToDate(final String fecha) throws ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
		Date date = (Date) formatter.parse(fecha);
		return date;
	} */
	
	public static Date stringToDate(String date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String s1 = date;
		Date d;
		String s2 = null;
		try {
			d = df.parse(s1);
			s2 = (new SimpleDateFormat("dd/MM/yyyy")).format(d);

		} catch (ParseException ex) {
		
		}
		return null;
	}
        
    public static String getMes(String contrato, Integer numAsignacion) throws Exception {
    	PersistenceBroker broker = null;
    	//System.out.println("++++++ Num Asignacion = " + numAsignacion);
    	String cadena ="";
        try {
	        String mes1 ="";
	        String mes2 ="";
	        String mes3 ="";
	        String mes4 ="";
	        String mes5 ="";
	        String mes6 ="";
	        String mes7 ="";
	        String mes8 ="";
	        String mes9 ="";
	        String mes10 ="";
	        String mes11 ="";
	        String mes12 ="";  	
            for ( Iterator iter = InformesUtil.getMesesXContrato(contrato, numAsignacion).listIterator(); iter.hasNext(); ) {
                Object[] meses = (Object[]) iter.next();
                 int numero = Integer.parseInt((String)meses[0]);
                    if(numero==1){
                           mes1 ="Ene"+(String)meses[1]+",";
                    } else if (numero==2){
                           mes2 ="Feb"+(String)meses[1]+",";
                    } else if (numero==3){
                           mes3 ="Marz"+(String)meses[1]+",";
                    } else if (numero==4){
                           mes4 ="Abr"+(String)meses[1]+",";
                    } else if (numero==5){
                           mes5 ="May"+(String)meses[1]+",";
                    } else if (numero==6){
                           mes6 ="Jun"+(String)meses[1]+",";
                    } else if (numero==7){
                           mes7 ="Jul"+(String)meses[1]+",";
                    } else if (numero==8){
                           mes8 ="Agos"+(String)meses[1]+",";
                    } else if (numero==9){
                           mes9 ="Sept"+(String)meses[1]+",";
                    } else if (numero==10){
                           mes10 ="Oct"+(String)meses[1]+",";
                    } else if (numero==11){
                           mes11 ="Nov"+(String)meses[1]+",";
                    }else if (numero==12){
                           mes12 ="Dic"+(String)meses[1];
                    }
                 cadena=mes1+mes2+mes3+mes4+mes5+mes6+mes7+mes8+mes9+mes10+mes11+mes12+"...";
                }
	        return cadena;
    	} catch (Exception e) {
                throw e;
        } finally {
                if (broker != null && !broker.isClosed()) {
                        broker.close();
                }
        }
    }
    
    public static String getMesfecha(final Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        String mes ="";
        int numero = Integer.parseInt(dateFormat.format(fecha));
                    if(numero==1){
                           mes ="Ene";
                    } else if (numero==2){
                           mes ="Feb";
                    } else if (numero==3){
                           mes ="Marz";
                    } else if (numero==4){
                           mes ="Abr";
                    } else if (numero==5){
                           mes ="May";
                    } else if (numero==6){
                           mes ="Jun";
                    } else if (numero==7){
                           mes ="Jul";
                    } else if (numero==8){
                           mes ="Agos";
                    } else if (numero==9){
                           mes ="Sept";
                    } else if (numero==10){
                           mes ="Oct";
                    } else if (numero==11){
                           mes ="Nov";
                    }else if (numero==12){
                           mes ="Dic";
                    }
        return mes;
    }
    
     public static BigDecimal getMontoPendiente(String contrato, Integer numLote) throws Exception {
            PersistenceBroker broker = null;
            try {
               broker = PersistenceBrokerFactory.defaultPersistenceBroker();
               Criteria criterio = new Criteria();
               if (contrato !=null) {
                   criterio.addEqualTo("contrato", contrato);
               }
               criterio.addEqualTo("numLote", numLote);
               ReportQueryByCriteria query = new ReportQueryByCriteria(PendientesClaro.class, criterio);
               query.setAttributes(new String[]{"sum(saldo)"});
               Iterator iter = broker.getReportQueryIteratorByQuery(query);
               BigDecimal saldo = BigDecimal.ZERO;
               if (iter.hasNext()) {
                   Object[] item = (Object[])iter.next();
                   if (item[0] != null) {
                       saldo = saldo.add((BigDecimal)item[0]);
                   }
               }
               return saldo;

            } catch (Exception e) {
                throw e;
            } finally {
                if (broker != null && !broker.isClosed()) {
                    broker.close();
                }
            }
       }
    
    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    private static final DecimalFormat df = new DecimalFormat();

    static {
        dfs.setGroupingSeparator('-');
        df.setGroupingSize(4);
        df.setMinimumIntegerDigits(8);
        df.setParseIntegerOnly(true);
        df.setDecimalFormatSymbols(dfs);
    }
    /**
     * Aplicar el formato "####-###0" a los números de factura, al momento
     * de imprimir los tickets de las facturas.
     * @param numero
     * 			Número de factura.
     * @return
     */
    public static String formatoNumeroFacturaTicket(final Integer numero) {
        return df.format(numero.longValue());
    }

    /**
     * Suma o resta una cantidad de días a una fecha.
     * @param fecha
     *          Fecha a la que se le sumará o restará dias.
     * @param dias
     *          Cantidad de días. Para sumar dias pase una cantidad
     *          mayor que cero, en caso contrario pase una cantidad
     *          negativa.
     * @return
     * @throws Exception
     */
    public static Date fechaDias(final Date fecha, final int dias) {
        if (fecha != null) {
           Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            cal.add(Calendar.DATE, dias);
            return cal.getTime();
        }
        return null;
    }

    /**
     * Compara 2 fechas dadas y devuelve true en el caso de ser iguales
     * @param fecha1
     *          primer parámetor de fecha
     * @param fecha2
     *          Segundo parámetor de fecha
     * @return
     */
    public static boolean compareFechas(final Date fecha1, final Date fecha2) {
        boolean igual = true;
        if (fecha1 != null && fecha2 != null) {
            Calendar yearInicio = Calendar.getInstance();
            yearInicio.setTime(fecha1);
            int yearIni = yearInicio.get(Calendar.YEAR);
            Calendar yearFinal = Calendar.getInstance();
            yearFinal.setTime(fecha2);
            int yearFin = yearFinal.get(Calendar.YEAR);
            if (yearIni > yearFin) {
                igual = false;
            } else if (yearIni == yearFin) {
                // Comprobar el mes.
                int mesIni = yearInicio.get(Calendar.MONTH);
                int mesFin = yearFinal.get(Calendar.MONTH);
                if (mesIni > mesFin) {
                    igual = false;
                } else if (mesIni == mesFin) {
                    // Comprobar los días.
                    int diaIni = yearInicio.get(Calendar.DAY_OF_MONTH);
                    int diaFin = yearFinal.get(Calendar.DAY_OF_MONTH);
                    if (diaIni > diaFin || diaIni < diaFin) {
                        igual = false;
                    }
                }
            }
        }
        return igual;
    }
    
    public static boolean validarAnioFecha(final Date fecha) {
        boolean mayor = false;
        if (fecha != null) {
	        Calendar yearActual = Calendar.getInstance();
	        int yearIni = yearActual.get(Calendar.YEAR);
	        
	        Calendar fSistema = Calendar.getInstance();
	        fSistema.setTime(fecha);
	        int fechaSistema = fSistema.get(Calendar.YEAR);
	        
	        if (fechaSistema > yearIni) {
	            mayor = true;
	        }
	        if (yearIni > fechaSistema) {
	            mayor = true;
	        }
        }
        return mayor;
    }

    /**
     * Método que recupera la cantidad de días entre dos fechas dadas.
	 *
     * @param fechaInicial
     * 			fecha inicial del periodo.
     * @param fechaFinal
     * 			fecha final del periodo.
     * @return
     */
    private static int cantidadDeDias(Date fechaInicial, Date fechaFinal) {
    	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
    	String fechaInicioString = df.format(fechaInicial);
    	String fechaFinalString = df.format(fechaFinal);
    	try {
    		fechaInicial = df.parse(fechaInicioString);
    		fechaFinal = df.parse(fechaFinalString);
    	} catch (ParseException ex) {}

    	long fechaInicialMs = fechaInicial.getTime();
    	long fechaFinalMs = fechaFinal.getTime();
    	long diferencia = fechaFinalMs - fechaInicialMs;
    	// dividimos entre los milesegundos de un día
    	double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
    	return ((int)dias);
	}

    /**
     * Método que recupera los sabados y domingos que se encuentren dentro de un
     * rango establecido.
     *
     * @param fechaIni
     * 			Fecha Inicial
     * @param fechaFin
     * 			Fecha Final
     * @return Cantidad de sábados y domingos.
     */
    private static int getSabDomDias(Date fechaIni, Date fechaFin) {
        int cantidad = 0;
        Calendar calIni = Calendar.getInstance();
		// Inicializando el calendario a la fecha de inicio.
		calIni.setTime(fechaIni);
		// Incrementamos día por día hasta la fecha final.
		// Cuando encontramos que es día sábado o domingo incrementamos la
		// variable "cantidad".
		while (fechaIni.compareTo(fechaFin) <= 0) {
		    // Validación de sábado o domingo.
		    if (calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
		            || calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
		        cantidad++;
		    }
		    // Esta instrucción incrementa la fecha por un día y no importa
		    // si está al fin de mes o al fin de año, siempre lo hace bien.
		    calIni.add(Calendar.DATE, 1);
		    fechaIni = calIni.getTime();
		}
        return cantidad;
    }

	/**
	 * Método que recupera los días entre dos fechas dadas. Si el parámetro
	 * excluir es true, se debe restar los días sabado y domingo que contenga el
	 * periodo.
	 *
	 * @param fechaIni
	 * 			fecha inicial del periodo
	 * @param fechaFin
	 * 			fecha final del periodo
	 * @param excluir
	 * 			boolean true/false
	 * @return Cantidad de días en un periodo.
	 */
    public static int diasCurso(Date fechaIni, Date fechaFin, Boolean excluir) {
		int dias = 0;
		if  (fechaIni != null && fechaFin != null) {
			if (excluir.booleanValue() == true) {
				dias = cantidadDeDias(fechaIni, fechaFin)
						- getSabDomDias(fechaIni, fechaFin);
			} else {
				dias = cantidadDeDias(fechaIni, fechaFin);
			}
		}
		return dias;
	}

    /**
     * Consulta para obtener los registros resultados de la búsqueda por
     * página.
     *
     * @param clase
     *          Clase.
     * @param criterio
     *          Criterio de búsqueda.
     * @param criterioOrden
     *          Criterio de orden para el resultado de la búsqueda.
     * @param pagina
     *          Página.
     * @param registrosPorPagina
     *          Cantidad de registros a mostrar por página.
     * @param broker
     *          PersistenceBroker.
     * @return Lista de resultados por página.
     * @throws Exception
     *          Indica que ha ocurrido un error al consultar la lista de
     *          resultados por página.
     */
    @SuppressWarnings("unchecked")
    public static final Collection getResultadosXPagina(final Class clase,
            final Criteria criterio, final String criterioOrden,
            final int pagina, final int registrosPorPagina,
            final PersistenceBroker broker) throws Exception {
        if (pagina >= 1  && registrosPorPagina >= 1) {
            int inicio = 1;
            if (pagina != 1) {
                inicio = (registrosPorPagina * (pagina - 1)) + 1;
            }
            QueryByCriteria query = new QueryByCriteria(clase, criterio);
            if (criterioOrden != null) {
            	query.addOrderBy(criterioOrden, true);
            }            
            query.setStartAtIndex(inicio);
            query.setEndAtIndex(inicio + registrosPorPagina - 1);
            return broker.getCollectionByQuery(query);
        }
        return null;
    }    

    /**
     * Contruir el criterio de búsqueda para la consulta en los formularios
     * del sistema.
     *
     * @param criterioBusqueda
     *          Criterio.
     * @param campoBusqueda
     *          Nombre del campo en el cual se debe buscar.
     * @return Criteria
     *          Criterio para la búsqueda.
     */
    public static final Criteria getCriterio(final String criterioBusqueda,
            final Integer filtrar, final String campoBusqueda) {
        Criteria criterio = new Criteria();
        if (criterioBusqueda != null && criterioBusqueda.length() > 0) {
            criterio.addLike("upper(" + campoBusqueda + ")",
                    criterioBusqueda.toUpperCase(Locale.getDefault()) + "*");
        }
        if(filtrar == Integer.valueOf(1)){
        	criterio.addEqualTo("inactivo", Boolean.FALSE);
        }
        if(filtrar == Integer.valueOf(2)){
        	criterio.addEqualTo("inactivo", Boolean.TRUE);
        }
        
        return criterio;
    }

    /**
     * Obtiene una cadena sin espacios al inicio y al final.
     * @param cadena
     *      Cadena con espacios.
     * @return
     *      String
     */
    public static String quitarEspacios(String cadena) {
        if (cadena != null) {
            cadena = cadena.trim();
            return cadena;
        }
        return null;
    }

    /**
     * Eliminar de un número la coma para el formato de miles.
     * Esto es necesario para el suggestion list, ya que si en
     * el formulario capturamos el valor del filtro con parseInt
     * lo que esta función hace es truncar el número hasta la coma.
     * @param numero
     *          Número del cual se necesita eliminar la coma.
     * @return Integer número que representa el identificador
     *          de un item.
     */
    public static Integer stringToInt(String numero) {
        int comma = numero.indexOf(",");
        // Si existe una coma en el número, se debe remover
        if (comma > -1) {
            StringBuffer stringbufferTo = new StringBuffer(numero);
            stringbufferTo.replace(comma, comma+1, "");
            numero = stringbufferTo.toString();
        }
        return Integer.valueOf(numero);
    }
    
    public static String integerToString(int i) {
        return Integer.toString(i);
    }
    
    

    /**
     * Escapar las comillas dobles contenidas en el parámetro
     * cadenaValor, este método es utilizado al momento de
     * crear listas tipo JSON para cargar las listas sugeridas.
     * @param cadenaValor
     *          Cadena a evaluar.
     * @return
     */
    public static String escaparComillas (final String cadenaValor) {
        StringBuffer stringbuffer = new StringBuffer(cadenaValor);
        int comillas = stringbuffer.toString().indexOf("\"");
        // Si existen comillas dobles en el parámetro cadenaValor,
        // escaparlas insertando una pleca invertida.
        if (comillas > -1) {
            stringbuffer.replace(comillas, comillas, "\\");
        }
        // Buscar las comillas de cierre
        comillas = stringbuffer.toString().indexOf("\"", comillas+2);
        if (comillas > -1) {
            stringbuffer.replace(comillas, comillas, "\\");
        }
        return stringbuffer.toString();
    }
    /**
     * Calcular el monto por denominacion para el arqueo diario.
     *  
     * @param valor
     * 		Valor de cada denominacion.
     * @param cantidad
     * 		Cantidad de billetes.
     * @return
     */
	public static BigDecimal calcularTotalCantidad(BigDecimal valor,
			Integer cantidad) {
		BigDecimal monto = BigDecimal.ZERO;		
		if (cantidad != null && cantidad.intValue() > 0) {

			monto = valor.multiply(new BigDecimal(cantidad.intValue()));//.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return monto;
	}
	
	/**
	* Metodo que convertir un String en BigDecimal
	* @param valor, el String
	* 
	* @return BigDecimal, el String convertido en BigDecimal
	*/
	public static BigDecimal stringToBigDecimal(String valor) {
		if (valor != null) {
			valor = valor.replace(",", "");
		}		
		return new BigDecimal(new Double(valor));
	}
	
	public static String sinAcentos(String valor) {
		if (valor != null) {
			valor=valor.replace('Á','A');
			valor=valor.replace('É','E');
			valor=valor.replace('Í','I');
			valor=valor.replace('Ó','O');
			valor=valor.replace('Ú','U');
			valor=valor.replace('Ñ','N');
			valor=valor.replace('Ä','A');
			valor=valor.replace('Ë','E');
			valor=valor.replace('Ï','I');
			valor=valor.replace('Ö','O');
			valor=valor.replace('Ü','U');
			valor=valor.replace('á','a');
			valor=valor.replace('é','e');
			valor=valor.replace('í','i');
			valor=valor.replace('ó','o');
			valor=valor.replace('ú','u');
			valor=valor.replace('ñ','n');
			valor=valor.replace('ä','a');
			valor=valor.replace('ë','e');
			valor=valor.replace('ï','i');
			valor=valor.replace('ö','o');
			valor=valor.replace('ü','u');
		}		
		return valor;
	}
	
	public static Long intToLong(Integer valor){
		long nLong = (long)valor;
		return nLong;
		
	}
	
	public static Boolean compararCadenas (String cadena1, String cadena2) {
		if(cadena1.equals(cadena2)) {
			return true;
		} else {
			return false;
		}		
	}
	
	public int getNumeroSerie() throws Exception {
        Integer numero = EmpresaHandler.getSerial();
        //System.out.println("NUMERO SERIE = " +numero);
        if(numero != null){
        	numero = numero+1;
        } else {
        	numero = 1;
        }
        setSerial(numero);
        
        return numero;
        
	}
	
   private void setSerial(Integer numero) throws Exception  {
        Empresa e = EmpresaHandler.getBeanEmpresa(true);
        e.setEmpSerial(numero);
        EmpresaHandler.setBeanEmpresa(e);
    }
   
   public static int getEmpMcodigo() throws Exception {
       Integer numero = EmpresaHandler.getMcodigo();
       if(numero != null){
       	numero = numero+1;
       } else {
       	numero = 1;
       }
       setMcodigo(numero);
       
       return numero;
       
	}
	
  private static void setMcodigo(Integer numero) throws Exception  {
       Empresa e = EmpresaHandler.getBeanEmpresa(true);
       e.setEmpMcodigo(numero);
       EmpresaHandler.setBeanEmpresa(e);
   }
	
	public static BigDecimal calcularCuota(BigDecimal saldo,
			BigDecimal descuento, BigDecimal plazo) {
		return (saldo.subtract(descuento)).divide(plazo);
	}
  
}
