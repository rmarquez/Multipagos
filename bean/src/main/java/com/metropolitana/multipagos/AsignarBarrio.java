//==============================================================================
//===   asignar_barrio.java                        Build:2559
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AsignarBarrio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    asignarbId;
   private Integer    colectorId;
   private Integer    departamentoId;
   private Integer    localidadId;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getAsignarbId() { return asignarbId; }

   public void setAsignarbId(Integer asignarbId)
   {
      this.asignarbId = asignarbId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
   }

   //---------------------------------------------------------------------------

   public Integer getLocalidadId() { return localidadId; }

   public void setLocalidadId(Integer localidadId)
   {
      this.localidadId = localidadId;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection detalleBarriosList = new ArrayList();

   public Collection getDetalleBarriosList() { return detalleBarriosList;}

   public void setDetalleBarriosList(Collection c) { this.detalleBarriosList = c;}

   public void addDetalleBarrios( DetalleBarrios detalleBarrios )
   {
      detalleBarrios.setAsignarbIdRef(this);
      detalleBarriosList.add( detalleBarrios );
   }

   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }


   //---------------------------------------------------------------------------

   private Departamento departamentoIdRef;

   public Departamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(Departamento departamento)
   {
      this.departamentoIdRef = departamento;
   }


   //---------------------------------------------------------------------------

   private Localidad localidadIdRef;

   public Localidad getLocalidadIdRef() { return localidadIdRef; }

   public void setLocalidadIdRef(Localidad localidad)
   {
      this.localidadIdRef = localidad;
   }

}

//==============================================================================

