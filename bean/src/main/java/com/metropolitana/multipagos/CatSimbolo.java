//==============================================================================
//===   cat_simbolo.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatSimbolo implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cSimboloId;
   private Integer    nSimboloNumero;
   private String     dSimboloNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCSimboloId() { return cSimboloId; }

   public void setCSimboloId(Integer cSimboloId)
   {
      this.cSimboloId = cSimboloId;
   }

   //---------------------------------------------------------------------------

   public Integer getNSimboloNumero() { return nSimboloNumero; }

   public void setNSimboloNumero(Integer nSimboloNumero)
   {
      this.nSimboloNumero = nSimboloNumero;
   }

   //---------------------------------------------------------------------------

   public String getDSimboloNombre() { return dSimboloNombre; }

   public void setDSimboloNombre(String dSimboloNombre)
   {
      this.dSimboloNombre = dSimboloNombre;
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
      rptDetalleVisitas.setCSimboloIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }
}

//==============================================================================

