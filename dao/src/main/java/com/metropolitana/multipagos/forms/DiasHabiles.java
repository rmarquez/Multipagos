package com.metropolitana.multipagos.forms;
/**
** @author Tomado de Internet
*/
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiasHabiles {
	/* Formateadores de fechas */
    public static SimpleDateFormat formatFecha_4 = new SimpleDateFormat("dd/MM/yyyy", new java.util.Locale("es", "NI"));
    public static SimpleDateFormat formatoFecha_Predefinido=formatFecha_4;
    private Locale _currentLocale;
    private Calendar _calIni; // calendario global de la fecha inicial se recarga en el metodo fiGetDaysOn2
    private Calendar _calFin = null; // calendario global de la fecha final se  recarga en el metodo fiGetDaysOn2
    private boolean bDebug = false;

      /** Creates a new instance of DiasHabiles */
     public DiasHabiles() {
          _currentLocale = new Locale("es", "NI");
         SimpleDateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy",_currentLocale);
     }

     /**
      * Método que retorna el número de días para llegar al próximo lunes.
      *
      * @return Día de la semana.
      * @param iDia
      *            Día de la semana
      */
     private static int nextMonday(int iDia) {
         if (iDia == 1)
             return 1; //domingo
         else
             return 9 - iDia;
     }
     /**
      * Retorna días de la semana faltantes para el próximo día hábil
      *
      * @param iDias
      *            Días en la semana.
      * @param iDiaSemana
      *            Día de la semana.
      * @return Días para el siguiente día hábil.
      */
     private static int finalRange(int iDias, int iDiaSemana) {
         if (iDiaSemana == 1)
             return iDias + 1; //domingo
         else if (iDiaSemana == 7)
             return iDias + 2; //sabado
         else
             return iDias;
     }

     /**
      * Método que recuperaa los sabados y domingos que se encuentren dentro de
      * un rango establecido
      *
      * @param iDInicio
      *            Número de dia del año (inicial)
      * @param iDFin
      *            Número de dia del año (final)
      * @param iDiaSemana
      *            Día de la semana.
      * @param iDiaSemanaFin
      *            Día de la semana.
      * @return Cantidad de sábados y domingos.
      */
     private int getSatSunDays(int iDInicio, int iDFin, int iDiaSemana, int iDiaSemanaFin) {
         boolean bDiasAntes = false;

         int iNumeroDias = iDFin - iDInicio;

         //Obtiene el numero de dias para llegar al proximo lunes que es el dia
         // 1
         int iTmp = nextMonday(iDiaSemana);
         /*
          * if (iDInicio == 163 && iDFin == 168 && iDiaSemana == 5 &&
          * iDiaSemanaFin == 3) { }
          */

         int iCountSabDom = 0;

         iTmp = iTmp + iDInicio;
         while (iTmp + 7 <= finalRange(iDFin, iDiaSemanaFin)) {
             iTmp = iTmp + 7;
             iCountSabDom = iCountSabDom + 2;
             bDiasAntes = false;
         }

         if (iDiaSemana == 1) {
             iCountSabDom += 1;//domingo
             bDiasAntes = true;
         } else if (iDiaSemana == 7) {
             iCountSabDom += 2; //sabado
             bDiasAntes = true;
         }

         if (iNumeroDias > 5 && !bDiasAntes)
             iCountSabDom += 2; //cuando dia final lunes

         return iCountSabDom;
     }

      /**
      * Método que recupera los sabados y domingos que se encuentren dentro de un
      * rango establecido.
      *
      * @param sFechaIni
      *            Fecha inicial en formato "dd/mm/aaaa"
      * @param sFechaFin
      *            Fecha final en formato "dd/mm/aaaa".
      * @return Cantidad de sábados y domingos.
      */
     static int getSatSunDays2(String sFechaIni, String sFechaFin) {
         int iCountSatSun = 0;
         try {
             Calendar calIni = Calendar.getInstance();
             Date dateIni = DiasHabiles.formatoFecha_Predefinido.parse(sFechaIni);
             Date dateFin = DiasHabiles.formatoFecha_Predefinido.parse(sFechaFin);

             // Inicializando el calendario a la fecha de inicio.
             calIni.setTime(dateIni);
             // Incrementamos día por día hasta la fecha final.
             // Cuando encontramos que es día sábado o domingo incrementamos la
             // variable "iCountSatSun".
             while (dateIni.compareTo(dateFin) <= 0) {
                 // Validación de sábado o domingo.
                 if (calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                         || calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                     iCountSatSun++;
                 }
                 // Esta instrucción incrementa la fecha por un día y no importa
                 // si está al fin de mes o al fin de año, siempre lo hace bien.
                 calIni.add(Calendar.DATE, 1);
                 dateIni = calIni.getTime();
             }
         } catch (ParseException pe) {
             System.out.println(pe);
             iCountSatSun = 0;
         }
         return iCountSatSun;
     }




     /*Metodo que recuperara la fecha final despues de ver los dias nohabiles
     *
     *@ param FechaIni
     *         Cadena con la fecha a calcular los dias inhabiles
     *@ param Dias
     *      Numero de dias habiles a sumar
     *@ return una Fecha con formato dd-mm-yyyy
     */
     public int getDateHabiles(String FechaIni, int Dias){
        //Esta es la Fecha que se recibe
         int countd=0;
         int contapasadas=0;
         int iYear = Integer.parseInt(FechaIni.substring(6, 10));
         int iMonth = Integer.parseInt(FechaIni.substring(3, 5));
         int iDay = Integer.parseInt(FechaIni.substring(0, 2));
         Calendar cldInicio = Calendar.getInstance(_currentLocale);
         cldInicio.setFirstDayOfWeek(cldInicio.MONDAY);
         //declaro variables para la obtencion del dia de la semana
         int iDiaSemana =0;
         //hacemos el recorrido de todos los dias quitando los sabados y domingos
         while(Dias>=countd)
         {
                  contapasadas++;
                  //System.out.println("Dia"+Dias+"!=Countd"+countd);
                  cldInicio.set(iYear, iMonth - 1, iDay);
                  iDiaSemana=cldInicio.get(cldInicio.DAY_OF_WEEK);
                  //System.out.println("Dia de la semana impresa="+iDiaSemana);
                  if(iDiaSemana==1 || iDiaSemana== 7)
                  {
                      //System.out.println("Entro en el if");
                  }else{

                  countd++; // se le suma uno al contador del while
                  //System.out.println("Entro en el else");

                  }
                 iDay++; //se le suma uno al dia para recorrerlo no importa el numero quesea en la semana

               //System.out.println("Es numero de conteos del while="+countd);
               //System.out.println("Numero de Dias iDays="+iDay);
         }

         //System.out.println("Final Es numero de conteos del while="+countd);
         //System.out.println("Final Numero de Dias iDays="+iDay);
         //System.out.println("Conteo de pasadas="+contapasadas);

       return contapasadas-1;


     }


     /*Metodo que recupera la fecha habil
      *@ param FechaIn
      *            La fecha de entrada para calcular la fecha habil
      *@ param Dias
      *            Numero de dias habiles
      *
      *@ return fecha en formato dd/mm/yyyy
      */

     public String FechHabil(String FechaIn, int Dias)
     {
           int iNumeroDias = 0;

         int iDiasAsueto = 0;
         int iDiasHabiles = 0;
         int iCountSabDom = 0;
         int TotalDias=0;

         TotalDias=getDateHabiles(FechaIn, Dias);//numero de dias finales
         //Obtiene la fecha
         int iYear = Integer.parseInt(FechaIn.substring(6, 10));
         int iMonth = Integer.parseInt(FechaIn.substring(3, 5));
         int iDay = Integer.parseInt(FechaIn.substring(0, 2));
         Calendar cldInicio = Calendar.getInstance(_currentLocale);
         cldInicio.setFirstDayOfWeek(cldInicio.MONDAY);
         cldInicio.set(iYear, iMonth - 1, iDay);

         int iDiaSemana = cldInicio.get(cldInicio.DAY_OF_WEEK);
         int iDInicio = cldInicio.get(cldInicio.DAY_OF_YEAR);

         Calendar cldFin = Calendar.getInstance(_currentLocale);
         cldFin.setFirstDayOfWeek(cldFin.MONDAY);
         cldFin.set(iYear, iMonth - 1, iDay);

         cldFin.add(cldFin.DATE, TotalDias);

         int iDiaSemanaFin = cldFin.get(cldFin.DAY_OF_WEEK);
         int iDFin = cldFin.get(cldFin.DAY_OF_YEAR);

         //Recupera los dias festivos
        // iDiasAsueto = getHollyDays(iDInicio, iDFin);

         iCountSabDom = getSatSunDays(iDInicio, iDFin, iDiaSemana,iDiaSemanaFin);

        // cldFin.add(cldFin.DATE, iDiasAsueto + iCountSabDom);

         //cldFin.add(cldFin.DATE,iCountSabDom);
         DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,_currentLocale);
         Date d = cldFin.getTime();

         return df.format(d)+" (Sab y dom:"+iCountSabDom+" )";

     }

    /**
      * Método que recupera una fecha que sea el resultado de agregar un "n"
      * número de días a una fecha dada
      *
      * @param sFechaIni
      *            Cadena que contiene la fecha.
      * @param iDias
      *            Número de dias de a sumar.
      * @return Fecha en formato dd-mm-yyyy
      */
     public String getDatePlusDays(String sFechaIni, int iDias) {
         int iNumeroDias = 0;

         int iDiasAsueto = 0;
         int iDiasHabiles = 0;
         int iCountSabDom = 0;

         //Obtiene la fecha
         int iYear = Integer.parseInt(sFechaIni.substring(6, 10));
         int iMonth = Integer.parseInt(sFechaIni.substring(3, 5));
         int iDay = Integer.parseInt(sFechaIni.substring(0, 2));
         Calendar cldInicio = Calendar.getInstance(_currentLocale);
         cldInicio.setFirstDayOfWeek(cldInicio.MONDAY);
         cldInicio.set(iYear, iMonth - 1, iDay);

         int iDiaSemana = cldInicio.get(cldInicio.DAY_OF_WEEK);
         int iDInicio = cldInicio.get(cldInicio.DAY_OF_YEAR);

         Calendar cldFin = Calendar.getInstance(_currentLocale);
         cldFin.setFirstDayOfWeek(cldFin.MONDAY);
         cldFin.set(iYear, iMonth - 1, iDay);

         cldFin.add(cldFin.DATE, iDias);

         int iDiaSemanaFin = cldFin.get(cldFin.DAY_OF_WEEK);
         int iDFin = cldFin.get(cldFin.DAY_OF_YEAR);

         //Recupera los dias festivos
        // iDiasAsueto = getHollyDays(iDInicio, iDFin);

         iCountSabDom = getSatSunDays(iDInicio, iDFin, iDiaSemana,
                 iDiaSemanaFin);

        // cldFin.add(cldFin.DATE, iDiasAsueto + iCountSabDom);

         cldFin.add(cldFin.DATE,iCountSabDom);
         DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,_currentLocale);
         Date d = cldFin.getTime();

         return df.format(d);
     }





       /**
      * Recuperar el numero de dias habiles entre dos fechas dadas.
      *
      * @param sFechaIni
      *            String en formato "dd/mm/yyyy"
      * @param sFechaFin
      *            String en formato "dd/mm/yyyy"
      * @return cantidad de días hábiles.
      */
     public int getWorkDay2(String sFechaIni, String sFechaFin) {
         int iDiasAsueto = 0;
         int iDiasHabiles = 0;
         int iCountSabDom = 0;

         //Obtiene la fecha
         int iYear = Integer.parseInt(sFechaIni.substring(6, 10));
         int iMonth = Integer.parseInt(sFechaIni.substring(3, 5));
         int iDay = Integer.parseInt(sFechaIni.substring(0, 2));

         Calendar cldInicio = Calendar.getInstance(_currentLocale);
         _calIni = Calendar.getInstance(_currentLocale);
         _calIni.set(iYear, iMonth - 1, iDay);

         //JuCa: 12/05/2004 --> tomamos el mes
         int viMesInicio = iMonth;

         cldInicio.setFirstDayOfWeek(cldInicio.MONDAY);
         cldInicio.set(iYear, iMonth - 1, iDay);

         int iDiaSemana = cldInicio.get(cldInicio.DAY_OF_WEEK);
         int iDInicio = cldInicio.get(cldInicio.DAY_OF_YEAR);

         iYear = Integer.parseInt(sFechaFin.substring(6, 10));
         iMonth = Integer.parseInt(sFechaFin.substring(3, 5));
         iDay = Integer.parseInt(sFechaFin.substring(0, 2));
         Calendar cldFin = Calendar.getInstance(_currentLocale);

         _calFin = Calendar.getInstance(_currentLocale);
         _calFin.set(iYear, iMonth - 1, iDay);

         cldFin.setFirstDayOfWeek(cldFin.MONDAY);
         cldFin.set(iYear, iMonth - 1, iDay);

         //JuCa: 12/05/2004 --> tomamos el mes
         int viMesFin = iMonth;

         int iDiaSemanaFin = cldFin.get(cldFin.DAY_OF_WEEK);
         int iDFin = cldFin.get(cldFin.DAY_OF_YEAR);

         if ((cldInicio == null) || (cldFin == null))
             return 0;

         //Recupera los dias naturales
         int iNumeroDias = 0;
         if (viMesInicio == viMesFin) {
             iNumeroDias = iDFin - iDInicio; //JuCa: 11/05/2004 ---> Agregaba un
             // día extra y eso no es válido para
             // el mismo mes!!!
         } else {
             iNumeroDias = (iDFin - iDInicio);// + 1; //JuCa: 26/08/2004 --> Ese
             // 1 estaba de más :D
         }

         if (iDFin == iDInicio)
             iNumeroDias = 0;
         //Recupera los dias festivos
        // iDiasAsueto = getHollyDaysSinSatSun(iDInicio, iDFin);
         String sFechaIniAux = sFechaIni;
         if (sFechaIni.indexOf(" ") != -1)
             sFechaIniAux = sFechaIni.substring(0, sFechaIni.indexOf(" "));
         iCountSabDom = getSatSunDays2(sFechaIniAux, sFechaFin);
         iDiasHabiles = (iNumeroDias - iCountSabDom) - iDiasAsueto;
         if(bDebug)System.out.println("\nNumero de dias:"+iNumeroDias+";SabadosyDomingos:"+iCountSabDom+";DiasAsueto:"+iDiasAsueto);
         if (iDiasHabiles < 0)
             iDiasHabiles = 0;

         return iDiasHabiles;
     }




     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
         // TODO code application logic here
         String fechainicial="01/10/2008";
         String fechafinal="29/11/2008";
         DiasHabiles da=new DiasHabiles();
         //da.getConnec();
         String FechaHabil = da.FechHabil(fechainicial,10);
         System.out.println("Fecha Final Habil="+FechaHabil);

         int resultado=getSatSunDays2(fechainicial, fechafinal);
         System.out.println("numero de sabados y domingos"+resultado);
         String fecharesul=da.getDatePlusDays(fechainicial,15);
         System.out.println("Fecha resultado de sumar los dias"+fecharesul);


     }

}
