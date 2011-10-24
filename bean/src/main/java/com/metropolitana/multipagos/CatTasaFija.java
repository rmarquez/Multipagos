//==============================================================================
//===   cat_tasa_fija.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatTasaFija implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Date       fTasaFecha;
   private BigDecimal vTasaCambioMes;

   //---------------------------------------------------------------------------

   public Date getFTasaFecha() { return fTasaFecha; }

   public void setFTasaFecha(Date fTasaFecha)
   {
      this.fTasaFecha = fTasaFecha;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTasaCambioMes() { return vTasaCambioMes; }

   public void setVTasaCambioMes(BigDecimal vTasaCambioMes)
   {
      this.vTasaCambioMes = vTasaCambioMes;
   }
}

//==============================================================================

