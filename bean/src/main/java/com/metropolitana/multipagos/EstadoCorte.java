//==============================================================================
//===   estado_corte.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class EstadoCorte implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    estadoId;
   private String     estadoNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getEstadoId() { return estadoId; }

   public void setEstadoId(Integer estadoId)
   {
      this.estadoId = estadoId;
   }

   //---------------------------------------------------------------------------

   public String getEstadoNombre() { return estadoNombre; }

   public void setEstadoNombre(String estadoNombre)
   {
      this.estadoNombre = estadoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection carteraXDepartamentoList = new ArrayList();

   public Collection getCarteraXDepartamentoList() { return carteraXDepartamentoList;}

   public void setCarteraXDepartamentoList(Collection c) { this.carteraXDepartamentoList = c;}

   public void addCarteraXDepartamento( CarteraXDepartamento carteraXDepartamento )
   {
      carteraXDepartamento.setEstadoIdRef(this);
      carteraXDepartamentoList.add( carteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private Collection asignacionClaroList = new ArrayList();

   public Collection getAsignacionClaroList() { return asignacionClaroList;}

   public void setAsignacionClaroList(Collection c) { this.asignacionClaroList = c;}

   public void addAsignacionClaro( AsignacionClaro asignacionClaro )
   {
      asignacionClaro.setEstadoIdRef(this);
      asignacionClaroList.add( asignacionClaro );
   }
}

//==============================================================================

