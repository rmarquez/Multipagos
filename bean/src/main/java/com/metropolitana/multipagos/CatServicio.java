//==============================================================================
//===   cat_servicio.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatServicio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cServicioId;
   private String     dServicioNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCServicioId() { return cServicioId; }

   public void setCServicioId(Integer cServicioId)
   {
      this.cServicioId = cServicioId;
   }

   //---------------------------------------------------------------------------

   public String getDServicioNombre() { return dServicioNombre; }

   public void setDServicioNombre(String dServicioNombre)
   {
      this.dServicioNombre = dServicioNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getBInactivo() { return bInactivo; }

   public void setBInactivo(Boolean bInactivo)
   {
      this.bInactivo = bInactivo;
   }

   //---------------------------------------------------------------------------

   private Collection rptCarteraXDepartamentoList = new ArrayList();

   public Collection getRptCarteraXDepartamentoList() { return rptCarteraXDepartamentoList;}

   public void setRptCarteraXDepartamentoList(Collection c) { this.rptCarteraXDepartamentoList = c;}

   public void addRptCarteraXDepartamento( RptCarteraXDepartamento rptCarteraXDepartamento )
   {
      rptCarteraXDepartamento.setNServicioIdRef(this);
      rptCarteraXDepartamentoList.add( rptCarteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetalleVisitasList = new ArrayList();

   public Collection getRptDetalleVisitasList() { return rptDetalleVisitasList;}

   public void setRptDetalleVisitasList(Collection c) { this.rptDetalleVisitasList = c;}

   public void addRptDetalleVisitas( RptDetalleVisitas rptDetalleVisitas )
   {
      rptDetalleVisitas.setCServicioIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetallePagosList = new ArrayList();

   public Collection getRptDetallePagosList() { return rptDetallePagosList;}

   public void setRptDetallePagosList(Collection c) { this.rptDetallePagosList = c;}

   public void addRptDetallePagos( RptDetallePagos rptDetallePagos )
   {
      rptDetallePagos.setCServicioIdRef(this);
      rptDetallePagosList.add( rptDetallePagos );
   }
}

//==============================================================================

