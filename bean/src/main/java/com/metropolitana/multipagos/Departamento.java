//==============================================================================
//===   departamento.java                        Build:2525
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Departamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    departamentoId;
   private String     departamentoNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
   }

   //---------------------------------------------------------------------------

   public String getDepartamentoNombre() { return departamentoNombre; }

   public void setDepartamentoNombre(String departamentoNombre)
   {
      this.departamentoNombre = departamentoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection localidadList = new ArrayList();

   public Collection getLocalidadList() { return localidadList;}

   public void setLocalidadList(Collection c) { this.localidadList = c;}

   public void addLocalidad( Localidad localidad )
   {
      localidad.setDepartamentoIdRef(this);
      localidadList.add( localidad );
   }

   //---------------------------------------------------------------------------

   private Collection carteraXDepartamentoList = new ArrayList();

   public Collection getCarteraXDepartamentoList() { return carteraXDepartamentoList;}

   public void setCarteraXDepartamentoList(Collection c) { this.carteraXDepartamentoList = c;}

   public void addCarteraXDepartamento( CarteraXDepartamento carteraXDepartamento )
   {
      carteraXDepartamento.setDepartamentoIdRef(this);
      carteraXDepartamentoList.add( carteraXDepartamento );
   }
}

//==============================================================================

