//==============================================================================
//===   arreglo_calendario.java                        Build:2643
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArregloCalendario implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arregloId;
   private Date       fechaCuota;

   //---------------------------------------------------------------------------

   public Integer getArregloId() { return arregloId; }

   public void setArregloId(Integer arregloId)
   {
      this.arregloId = arregloId;
   }

   //---------------------------------------------------------------------------

   public Date getFechaCuota() { return fechaCuota; }

   public void setFechaCuota(Date fechaCuota)
   {
      this.fechaCuota = fechaCuota;
   }

   //---------------------------------------------------------------------------

   private ArregloPago arregloIdRef;

   public ArregloPago getArregloIdRef() { return arregloIdRef; }

   public void setArregloIdRef(ArregloPago arregloPago)
   {
      this.arregloIdRef = arregloPago;
   }

}

//==============================================================================

