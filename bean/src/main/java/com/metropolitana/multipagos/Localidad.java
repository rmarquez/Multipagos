//==============================================================================
//===   localidad.java                        Build:2558
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Localidad implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    localidadId;
   private Integer    departamentoId;
   private String     localidadNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getLocalidadId() { return localidadId; }

   public void setLocalidadId(Integer localidadId)
   {
      this.localidadId = localidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
   }

   //---------------------------------------------------------------------------

   public String getLocalidadNombre() { return localidadNombre; }

   public void setLocalidadNombre(String localidadNombre)
   {
      this.localidadNombre = localidadNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection barrioList = new ArrayList();

   public Collection getBarrioList() { return barrioList;}

   public void setBarrioList(Collection c) { this.barrioList = c;}

   public void addBarrio( Barrio barrio )
   {
      barrio.setLocalidadIdRef(this);
      barrioList.add( barrio );
   }

   //---------------------------------------------------------------------------

   private Collection asignarBarrioList = new ArrayList();

   public Collection getAsignarBarrioList() { return asignarBarrioList;}

   public void setAsignarBarrioList(Collection c) { this.asignarBarrioList = c;}

   public void addAsignarBarrio( AsignarBarrio asignarBarrio )
   {
      asignarBarrio.setLocalidadIdRef(this);
      asignarBarrioList.add( asignarBarrio );
   }

   //---------------------------------------------------------------------------

   private Collection carteraXDepartamentoList = new ArrayList();

   public Collection getCarteraXDepartamentoList() { return carteraXDepartamentoList;}

   public void setCarteraXDepartamentoList(Collection c) { this.carteraXDepartamentoList = c;}

   public void addCarteraXDepartamento( CarteraXDepartamento carteraXDepartamento )
   {
      carteraXDepartamento.setLocalidadIdRef(this);
      carteraXDepartamentoList.add( carteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private Collection detalleVisitasList = new ArrayList();

   public Collection getDetalleVisitasList() { return detalleVisitasList;}

   public void setDetalleVisitasList(Collection c) { this.detalleVisitasList = c;}

   public void addDetalleVisitas( DetalleVisitas detalleVisitas )
   {
      detalleVisitas.setLocalidadIdRef(this);
      detalleVisitasList.add( detalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection detallePagosList = new ArrayList();

   public Collection getDetallePagosList() { return detallePagosList;}

   public void setDetallePagosList(Collection c) { this.detallePagosList = c;}

   public void addDetallePagos( DetallePagos detallePagos )
   {
      detallePagos.setLocalidadIdRef(this);
      detallePagosList.add( detallePagos );
   }

   //---------------------------------------------------------------------------

   private Departamento departamentoIdRef;

   public Departamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(Departamento departamento)
   {
      this.departamentoIdRef = departamento;
   }

}

//==============================================================================

