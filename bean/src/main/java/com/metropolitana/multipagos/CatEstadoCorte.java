//==============================================================================
//===   cat_estado_corte.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatEstadoCorte implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cEstadoId;
   private String     dEstadoNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCEstadoId() { return cEstadoId; }

   public void setCEstadoId(Integer cEstadoId)
   {
      this.cEstadoId = cEstadoId;
   }

   //---------------------------------------------------------------------------

   public String getDEstadoNombre() { return dEstadoNombre; }

   public void setDEstadoNombre(String dEstadoNombre)
   {
      this.dEstadoNombre = dEstadoNombre;
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
      rptCarteraXDepartamento.setNEstadoIdRef(this);
      rptCarteraXDepartamentoList.add( rptCarteraXDepartamento );
   }
}

//==============================================================================

