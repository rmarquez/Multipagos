//==============================================================================
//===   arqueo_cantidad.java                        Build:2508
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArqueoCantidad implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arqueoId;
   private Integer    cantidadId;
   private Integer    cantidad;
   private BigDecimal total;

   //---------------------------------------------------------------------------

   public Integer getArqueoId() { return arqueoId; }

   public void setArqueoId(Integer arqueoId)
   {
      this.arqueoId = arqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCantidadId() { return cantidadId; }

   public void setCantidadId(Integer cantidadId)
   {
      this.cantidadId = cantidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getCantidad() { return cantidad; }

   public void setCantidad(Integer cantidad)
   {
      this.cantidad = cantidad;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotal() { return total; }

   public void setTotal(BigDecimal total)
   {
      this.total = total;
   }

   //---------------------------------------------------------------------------

   private ArqueoPagos arqueoIdRef;

   public ArqueoPagos getArqueoIdRef() { return arqueoIdRef; }

   public void setArqueoIdRef(ArqueoPagos arqueoPagos)
   {
      this.arqueoIdRef = arqueoPagos;
   }


   //---------------------------------------------------------------------------

   private CantidadMonedas cantidadIdRef;

   public CantidadMonedas getCantidadIdRef() { return cantidadIdRef; }

   public void setCantidadIdRef(CantidadMonedas cantidadMonedas)
   {
      this.cantidadIdRef = cantidadMonedas;
   }

}

//==============================================================================

