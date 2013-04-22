//==============================================================================
//===   colector.java                        Build:2672
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Colector implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    colectorId;
   private Integer    colectorNumero;
   private String     primerNombre;
   private String     segundoNombre;
   private String     primerApellido;
   private String     segundoApellido;
   private String     cedula;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorNumero() { return colectorNumero; }

   public void setColectorNumero(Integer colectorNumero)
   {
      this.colectorNumero = colectorNumero;
   }

   //---------------------------------------------------------------------------

   public String getPrimerNombre() { return primerNombre; }

   public void setPrimerNombre(String primerNombre)
   {
      this.primerNombre = primerNombre;
   }

   //---------------------------------------------------------------------------

   public String getSegundoNombre() { return segundoNombre; }

   public void setSegundoNombre(String segundoNombre)
   {
      this.segundoNombre = segundoNombre;
   }

   //---------------------------------------------------------------------------

   public String getPrimerApellido() { return primerApellido; }

   public void setPrimerApellido(String primerApellido)
   {
      this.primerApellido = primerApellido;
   }

   //---------------------------------------------------------------------------

   public String getSegundoApellido() { return segundoApellido; }

   public void setSegundoApellido(String segundoApellido)
   {
      this.segundoApellido = segundoApellido;
   }

   //---------------------------------------------------------------------------

   public String getCedula() { return cedula; }

   public void setCedula(String cedula)
   {
      this.cedula = cedula;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection asignarBarrioList = new ArrayList();

   public Collection getAsignarBarrioList() { return asignarBarrioList;}

   public void setAsignarBarrioList(Collection c) { this.asignarBarrioList = c;}

   public void addAsignarBarrio( AsignarBarrio asignarBarrio )
   {
      asignarBarrio.setColectorIdRef(this);
      asignarBarrioList.add( asignarBarrio );
   }

   //---------------------------------------------------------------------------

   private Collection detalleColectoresList = new ArrayList();

   public Collection getDetalleColectoresList() { return detalleColectoresList;}

   public void setDetalleColectoresList(Collection c) { this.detalleColectoresList = c;}

   public void addDetalleColectores( DetalleColectores detalleColectores )
   {
      detalleColectores.setColectorIdRef(this);
      detalleColectoresList.add( detalleColectores );
   }

   //---------------------------------------------------------------------------

   private Collection asignarVisitasList = new ArrayList();

   public Collection getAsignarVisitasList() { return asignarVisitasList;}

   public void setAsignarVisitasList(Collection c) { this.asignarVisitasList = c;}

   public void addAsignarVisitas( AsignarVisitas asignarVisitas )
   {
      asignarVisitas.setColectorIdRef(this);
      asignarVisitasList.add( asignarVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection detalleVisitasList = new ArrayList();

   public Collection getDetalleVisitasList() { return detalleVisitasList;}

   public void setDetalleVisitasList(Collection c) { this.detalleVisitasList = c;}

   public void addDetalleVisitas( DetalleVisitas detalleVisitas )
   {
      detalleVisitas.setColectorIdRef(this);
      detalleVisitasList.add( detalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection detallePagosList = new ArrayList();

   public Collection getDetallePagosList() { return detallePagosList;}

   public void setDetallePagosList(Collection c) { this.detallePagosList = c;}

   public void addDetallePagos( DetallePagos detallePagos )
   {
      detallePagos.setColectorIdRef(this);
      detallePagosList.add( detallePagos );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoPagosList = new ArrayList();

   public Collection getArqueoPagosList() { return arqueoPagosList;}

   public void setArqueoPagosList(Collection c) { this.arqueoPagosList = c;}

   public void addArqueoPagos( ArqueoPagos arqueoPagos )
   {
      arqueoPagos.setColectorIdRef(this);
      arqueoPagosList.add( arqueoPagos );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setColectorIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGestionList = new ArrayList();

   public Collection getDetalleGestionList() { return detalleGestionList;}

   public void setDetalleGestionList(Collection c) { this.detalleGestionList = c;}

   public void addDetalleGestion( DetalleGestion detalleGestion )
   {
      detalleGestion.setColectorIdRef(this);
      detalleGestionList.add( detalleGestion );
   }

   //---------------------------------------------------------------------------

   private Collection detalleAvonPagosList = new ArrayList();

   public Collection getDetalleAvonPagosList() { return detalleAvonPagosList;}

   public void setDetalleAvonPagosList(Collection c) { this.detalleAvonPagosList = c;}

   public void addDetalleAvonPagos( DetalleAvonPagos detalleAvonPagos )
   {
      detalleAvonPagos.setColectorIdRef(this);
      detalleAvonPagosList.add( detalleAvonPagos );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGIbwList = new ArrayList();

   public Collection getDetalleGIbwList() { return detalleGIbwList;}

   public void setDetalleGIbwList(Collection c) { this.detalleGIbwList = c;}

   public void addDetalleGIbw( DetalleGIbw detalleGIbw )
   {
      detalleGIbw.setColectorIdRef(this);
      detalleGIbwList.add( detalleGIbw );
   }
}

//==============================================================================

