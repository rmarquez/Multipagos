//==============================================================================
//===   barrio.java                        Build:2577
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Barrio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    barrioId;
   private Integer    localidadId;
   private String     barrioNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
   }

   //---------------------------------------------------------------------------

   public Integer getLocalidadId() { return localidadId; }

   public void setLocalidadId(Integer localidadId)
   {
      this.localidadId = localidadId;
   }

   //---------------------------------------------------------------------------

   public String getBarrioNombre() { return barrioNombre; }

   public void setBarrioNombre(String barrioNombre)
   {
      this.barrioNombre = barrioNombre;
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
      detalleBarrios.setBarrioIdRef(this);
      detalleBarriosList.add( detalleBarrios );
   }

   //---------------------------------------------------------------------------

   private Collection asignarVisitasList = new ArrayList();

   public Collection getAsignarVisitasList() { return asignarVisitasList;}

   public void setAsignarVisitasList(Collection c) { this.asignarVisitasList = c;}

   public void addAsignarVisitas( AsignarVisitas asignarVisitas )
   {
      asignarVisitas.setBarrioIdRef(this);
      asignarVisitasList.add( asignarVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection carteraXDepartamentoList = new ArrayList();

   public Collection getCarteraXDepartamentoList() { return carteraXDepartamentoList;}

   public void setCarteraXDepartamentoList(Collection c) { this.carteraXDepartamentoList = c;}

   public void addCarteraXDepartamento( CarteraXDepartamento carteraXDepartamento )
   {
      carteraXDepartamento.setBarrioIdRef(this);
      carteraXDepartamentoList.add( carteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private Collection detalleVisitasList = new ArrayList();

   public Collection getDetalleVisitasList() { return detalleVisitasList;}

   public void setDetalleVisitasList(Collection c) { this.detalleVisitasList = c;}

   public void addDetalleVisitas( DetalleVisitas detalleVisitas )
   {
      detalleVisitas.setBarrioIdRef(this);
      detalleVisitasList.add( detalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection detallePagosList = new ArrayList();

   public Collection getDetallePagosList() { return detallePagosList;}

   public void setDetallePagosList(Collection c) { this.detallePagosList = c;}

   public void addDetallePagos( DetallePagos detallePagos )
   {
      detallePagos.setBarrioIdRef(this);
      detallePagosList.add( detallePagos );
   }

   //---------------------------------------------------------------------------

   private Collection carteraAvonList = new ArrayList();

   public Collection getCarteraAvonList() { return carteraAvonList;}

   public void setCarteraAvonList(Collection c) { this.carteraAvonList = c;}

   public void addCarteraAvon( CarteraAvon carteraAvon )
   {
      carteraAvon.setBarrioIdRef(this);
      carteraAvonList.add( carteraAvon );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGestionList = new ArrayList();

   public Collection getDetalleGestionList() { return detalleGestionList;}

   public void setDetalleGestionList(Collection c) { this.detalleGestionList = c;}

   public void addDetalleGestion( DetalleGestion detalleGestion )
   {
      detalleGestion.setBarrioIdRef(this);
      detalleGestionList.add( detalleGestion );
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

