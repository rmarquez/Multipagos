//==============================================================================
//===   rpt_arqueo_cantidad_us.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptArqueoCantidadUs implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cArqueoId;
   private Integer    cCantidadId;
   private Integer    nCantidad;
   private BigDecimal vTotal;

   //---------------------------------------------------------------------------

   public Integer getCArqueoId() { return cArqueoId; }

   public void setCArqueoId(Integer cArqueoId)
   {
      this.cArqueoId = cArqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCCantidadId() { return cCantidadId; }

   public void setCCantidadId(Integer cCantidadId)
   {
      this.cCantidadId = cCantidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getNCantidad() { return nCantidad; }

   public void setNCantidad(Integer nCantidad)
   {
      this.nCantidad = nCantidad;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotal() { return vTotal; }

   public void setVTotal(BigDecimal vTotal)
   {
      this.vTotal = vTotal;
   }

   //---------------------------------------------------------------------------

   private RptArqueoPagos cArqueoIdRef;

   public RptArqueoPagos getCArqueoIdRef() { return cArqueoIdRef; }

   public void setCArqueoIdRef(RptArqueoPagos rptArqueoPagos)
   {
      this.cArqueoIdRef = rptArqueoPagos;
   }


   //---------------------------------------------------------------------------

   private CatCantidadMonedas cCantidadIdRef;

   public CatCantidadMonedas getCCantidadIdRef() { return cCantidadIdRef; }

   public void setCCantidadIdRef(CatCantidadMonedas catCantidadMonedas)
   {
      this.cCantidadIdRef = catCantidadMonedas;
   }

}

//==============================================================================

