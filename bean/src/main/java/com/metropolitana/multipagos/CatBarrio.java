//==============================================================================
//===   cat_barrio.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatBarrio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cBarrioId;
   private Integer    nLocalidadId;
   private String     dBarrioNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCBarrioId() { return cBarrioId; }

   public void setCBarrioId(Integer cBarrioId)
   {
      this.cBarrioId = cBarrioId;
   }

   //---------------------------------------------------------------------------

   public Integer getNLocalidadId() { return nLocalidadId; }

   public void setNLocalidadId(Integer nLocalidadId)
   {
      this.nLocalidadId = nLocalidadId;
   }

   //---------------------------------------------------------------------------

   public String getDBarrioNombre() { return dBarrioNombre; }

   public void setDBarrioNombre(String dBarrioNombre)
   {
      this.dBarrioNombre = dBarrioNombre;
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
      rptCarteraXDepartamento.setNBarrioIdRef(this);
      rptCarteraXDepartamentoList.add( rptCarteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private CatLocalidad nLocalidadIdRef;

   public CatLocalidad getNLocalidadIdRef() { return nLocalidadIdRef; }

   public void setNLocalidadIdRef(CatLocalidad catLocalidad)
   {
      this.nLocalidadIdRef = catLocalidad;
   }

}

//==============================================================================

