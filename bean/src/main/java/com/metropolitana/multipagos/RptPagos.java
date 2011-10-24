//==============================================================================
//===   rpt_pagos.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cPagoId;
   private Integer    nUsrId;
   private Date       fFecha;
   private BigDecimal vMontoTotal;
   private BigDecimal vMontoTotalUs;
   private Integer    nCantidadPagos;
   private Integer    nCantidadPagosUs;

   //---------------------------------------------------------------------------

   public Integer getCPagoId() { return cPagoId; }

   public void setCPagoId(Integer cPagoId)
   {
      this.cPagoId = cPagoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNUsrId() { return nUsrId; }

   public void setNUsrId(Integer nUsrId)
   {
      this.nUsrId = nUsrId;
   }

   //---------------------------------------------------------------------------

   public Date getFFecha() { return fFecha; }

   public void setFFecha(Date fFecha)
   {
      this.fFecha = fFecha;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVMontoTotal() { return vMontoTotal; }

   public void setVMontoTotal(BigDecimal vMontoTotal)
   {
      this.vMontoTotal = vMontoTotal;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVMontoTotalUs() { return vMontoTotalUs; }

   public void setVMontoTotalUs(BigDecimal vMontoTotalUs)
   {
      this.vMontoTotalUs = vMontoTotalUs;
   }

   //---------------------------------------------------------------------------

   public Integer getNCantidadPagos() { return nCantidadPagos; }

   public void setNCantidadPagos(Integer nCantidadPagos)
   {
      this.nCantidadPagos = nCantidadPagos;
   }

   //---------------------------------------------------------------------------

   public Integer getNCantidadPagosUs() { return nCantidadPagosUs; }

   public void setNCantidadPagosUs(Integer nCantidadPagosUs)
   {
      this.nCantidadPagosUs = nCantidadPagosUs;
   }

   //---------------------------------------------------------------------------

   private Collection rptDetallePagosList = new ArrayList();

   public Collection getRptDetallePagosList() { return rptDetallePagosList;}

   public void setRptDetallePagosList(Collection c) { this.rptDetallePagosList = c;}

   public void addRptDetallePagos( RptDetallePagos rptDetallePagos )
   {
      rptDetallePagos.setCPagoIdRef(this);
      rptDetallePagosList.add( rptDetallePagos );
   }

   //---------------------------------------------------------------------------

   private AuthUser nUsrIdRef;

   public AuthUser getNUsrIdRef() { return nUsrIdRef; }

   public void setNUsrIdRef(AuthUser authUser)
   {
      this.nUsrIdRef = authUser;
   }

}

//==============================================================================

