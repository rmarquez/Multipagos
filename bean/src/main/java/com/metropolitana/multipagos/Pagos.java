//==============================================================================
//===   pagos.java                        Build:2533
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Pagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pagoId;
   private Integer    usrId;
   private Date       fecha;
   private BigDecimal montoTotal;
   private BigDecimal montoTotalUs;
   private Integer    cantidadPagos;
   private Integer    cantidadPagosUs;

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

   public BigDecimal getMontoTotalUs() { return montoTotalUs; }

   public void setMontoTotalUs(BigDecimal montoTotalUs)
   {
      this.montoTotalUs = montoTotalUs;
   }

   //---------------------------------------------------------------------------

   public Integer getCantidadPagos() { return cantidadPagos; }

   public void setCantidadPagos(Integer cantidadPagos)
   {
      this.cantidadPagos = cantidadPagos;
   }

   //---------------------------------------------------------------------------

   public Integer getCantidadPagosUs() { return cantidadPagosUs; }

   public void setCantidadPagosUs(Integer cantidadPagosUs)
   {
      this.cantidadPagosUs = cantidadPagosUs;
   }

   //---------------------------------------------------------------------------

   private Collection detallePagosList = new ArrayList();

   public Collection getDetallePagosList() { return detallePagosList;}

   public void setDetallePagosList(Collection c) { this.detallePagosList = c;}

   public void addDetallePagos( DetallePagos detallePagos )
   {
      detallePagos.setPagoIdRef(this);
      detallePagosList.add( detallePagos );
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

