//==============================================================================
//===   cat_colector.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatColector implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cColectorId;
   private Integer    nColectorNumero;
   private String     dPrimerNombre;
   private String     dSegundoNombre;
   private String     dPrimerApellido;
   private String     dSegundoApellido;
   private String     dCedula;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCColectorId() { return cColectorId; }

   public void setCColectorId(Integer cColectorId)
   {
      this.cColectorId = cColectorId;
   }

   //---------------------------------------------------------------------------

   public Integer getNColectorNumero() { return nColectorNumero; }

   public void setNColectorNumero(Integer nColectorNumero)
   {
      this.nColectorNumero = nColectorNumero;
   }

   //---------------------------------------------------------------------------

   public String getDPrimerNombre() { return dPrimerNombre; }

   public void setDPrimerNombre(String dPrimerNombre)
   {
      this.dPrimerNombre = dPrimerNombre;
   }

   //---------------------------------------------------------------------------

   public String getDSegundoNombre() { return dSegundoNombre; }

   public void setDSegundoNombre(String dSegundoNombre)
   {
      this.dSegundoNombre = dSegundoNombre;
   }

   //---------------------------------------------------------------------------

   public String getDPrimerApellido() { return dPrimerApellido; }

   public void setDPrimerApellido(String dPrimerApellido)
   {
      this.dPrimerApellido = dPrimerApellido;
   }

   //---------------------------------------------------------------------------

   public String getDSegundoApellido() { return dSegundoApellido; }

   public void setDSegundoApellido(String dSegundoApellido)
   {
      this.dSegundoApellido = dSegundoApellido;
   }

   //---------------------------------------------------------------------------

   public String getDCedula() { return dCedula; }

   public void setDCedula(String dCedula)
   {
      this.dCedula = dCedula;
   }

   //---------------------------------------------------------------------------

   public Boolean getBInactivo() { return bInactivo; }

   public void setBInactivo(Boolean bInactivo)
   {
      this.bInactivo = bInactivo;
   }

   //---------------------------------------------------------------------------

   private Collection rptDetalleVisitasList = new ArrayList();

   public Collection getRptDetalleVisitasList() { return rptDetalleVisitasList;}

   public void setRptDetalleVisitasList(Collection c) { this.rptDetalleVisitasList = c;}

   public void addRptDetalleVisitas( RptDetalleVisitas rptDetalleVisitas )
   {
      rptDetalleVisitas.setCColectorIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetallePagosList = new ArrayList();

   public Collection getRptDetallePagosList() { return rptDetallePagosList;}

   public void setRptDetallePagosList(Collection c) { this.rptDetallePagosList = c;}

   public void addRptDetallePagos( RptDetallePagos rptDetallePagos )
   {
      rptDetallePagos.setCColectorIdRef(this);
      rptDetallePagosList.add( rptDetallePagos );
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoPagosList = new ArrayList();

   public Collection getRptArqueoPagosList() { return rptArqueoPagosList;}

   public void setRptArqueoPagosList(Collection c) { this.rptArqueoPagosList = c;}

   public void addRptArqueoPagos( RptArqueoPagos rptArqueoPagos )
   {
      rptArqueoPagos.setNColectorIdRef(this);
      rptArqueoPagosList.add( rptArqueoPagos );
   }
}

//==============================================================================

