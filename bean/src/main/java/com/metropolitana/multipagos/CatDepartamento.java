//==============================================================================
//===   cat_departamento.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatDepartamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cDepartamentoId;
   private String     dDepartamentoNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCDepartamentoId() { return cDepartamentoId; }

   public void setCDepartamentoId(Integer cDepartamentoId)
   {
      this.cDepartamentoId = cDepartamentoId;
   }

   //---------------------------------------------------------------------------

   public String getDDepartamentoNombre() { return dDepartamentoNombre; }

   public void setDDepartamentoNombre(String dDepartamentoNombre)
   {
      this.dDepartamentoNombre = dDepartamentoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getBInactivo() { return bInactivo; }

   public void setBInactivo(Boolean bInactivo)
   {
      this.bInactivo = bInactivo;
   }

   //---------------------------------------------------------------------------

   private Collection catLocalidadList = new ArrayList();

   public Collection getCatLocalidadList() { return catLocalidadList;}

   public void setCatLocalidadList(Collection c) { this.catLocalidadList = c;}

   public void addCatLocalidad( CatLocalidad catLocalidad )
   {
      catLocalidad.setNDepartamentoIdRef(this);
      catLocalidadList.add( catLocalidad );
   }

   //---------------------------------------------------------------------------

   private Collection rptCarteraXDepartamentoList = new ArrayList();

   public Collection getRptCarteraXDepartamentoList() { return rptCarteraXDepartamentoList;}

   public void setRptCarteraXDepartamentoList(Collection c) { this.rptCarteraXDepartamentoList = c;}

   public void addRptCarteraXDepartamento( RptCarteraXDepartamento rptCarteraXDepartamento )
   {
      rptCarteraXDepartamento.setNDepartamentoIdRef(this);
      rptCarteraXDepartamentoList.add( rptCarteraXDepartamento );
   }
}

//==============================================================================

