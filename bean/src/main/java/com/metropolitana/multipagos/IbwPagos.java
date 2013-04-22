//==============================================================================
//===   ibw_pagos.java                        Build:2679
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class IbwPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pagoId;
   private Integer    usrId;
   private Date       fecha;
   private BigDecimal montoTotal;
   private Integer    cantidadPagos;

   //---------------------------------------------------------------------------

   public Integer getPagoId() { return pagoId; }

   public void setPagoId(Integer pagoId)
   {
      this.pagoId = pagoId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public Date getFecha() { return fecha; }

   public void setFecha(Date fecha)
   {
      this.fecha = fecha;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getMontoTotal() { return montoTotal; }

   public void setMontoTotal(BigDecimal montoTotal)
   {
      this.montoTotal = montoTotal;
   }

   //---------------------------------------------------------------------------

   public Integer getCantidadPagos() { return cantidadPagos; }

   public void setCantidadPagos(Integer cantidadPagos)
   {
      this.cantidadPagos = cantidadPagos;
   }

   //---------------------------------------------------------------------------

   private Collection detalleIbwPagosList = new ArrayList();

   public Collection getDetalleIbwPagosList() { return detalleIbwPagosList;}

   public void setDetalleIbwPagosList(Collection c) { this.detalleIbwPagosList = c;}

   public void addDetalleIbwPagos( DetalleIbwPagos detalleIbwPagos )
   {
      detalleIbwPagos.setPagoIdRef(this);
      detalleIbwPagosList.add( detalleIbwPagos );
   }

   //---------------------------------------------------------------------------

   private AuthUser usrIdRef;

   public AuthUser getUsrIdRef() { return usrIdRef; }

   public void setUsrIdRef(AuthUser authUser)
   {
      this.usrIdRef = authUser;
   }

}

//==============================================================================

