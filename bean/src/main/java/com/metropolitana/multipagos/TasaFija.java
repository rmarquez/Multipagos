//==============================================================================
//===   tasa_fija.java                        Build:2516
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class TasaFija implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Date       tasaFecha;
   private BigDecimal tasaCambioMes;

   //---------------------------------------------------------------------------

   public Date getTasaFecha() { return tasaFecha; }

   public void setTasaFecha(Date tasaFecha)
   {
      this.tasaFecha = tasaFecha;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTasaCambioMes() { return tasaCambioMes; }

   public void setTasaCambioMes(BigDecimal tasaCambioMes)
   {
      this.tasaCambioMes = tasaCambioMes;
   }
}

//==============================================================================

