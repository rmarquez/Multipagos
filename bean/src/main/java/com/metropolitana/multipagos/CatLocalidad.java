//==============================================================================
//===   cat_localidad.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatLocalidad implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cLocalidadId;
   private Integer    nDepartamentoId;
   private String     dLocalidadNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCLocalidadId() { return cLocalidadId; }

   public void setCLocalidadId(Integer cLocalidadId)
   {
      this.cLocalidadId = cLocalidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getNDepartamentoId() { return nDepartamentoId; }

   public void setNDepartamentoId(Integer nDepartamentoId)
   {
      this.nDepartamentoId = nDepartamentoId;
   }

   //---------------------------------------------------------------------------

   public String getDLocalidadNombre() { return dLocalidadNombre; }

   public void setDLocalidadNombre(String dLocalidadNombre)
   {
      this.dLocalidadNombre = dLocalidadNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getBInactivo() { return bInactivo; }

   public void setBInactivo(Boolean bInactivo)
   {
      this.bInactivo = bInactivo;
   }

   //---------------------------------------------------------------------------

   private Collection catBarrioList = new ArrayList();

   public Collection getCatBarrioList() { return catBarrioList;}

   public void setCatBarrioList(Collection c) { this.catBarrioList = c;}

   public void addCatBarrio( CatBarrio catBarrio )
   {
      catBarrio.setNLocalidadIdRef(this);
      catBarrioList.add( catBarrio );
   }

   //---------------------------------------------------------------------------

   private Collection rptCarteraXDepartamentoList = new ArrayList();

   public Collection getRptCarteraXDepartamentoList() { return rptCarteraXDepartamentoList;}

   public void setRptCarteraXDepartamentoList(Collection c) { this.rptCarteraXDepartamentoList = c;}

   public void addRptCarteraXDepartamento( RptCarteraXDepartamento rptCarteraXDepartamento )
   {
      rptCarteraXDepartamento.setNLocalidadIdRef(this);
      rptCarteraXDepartamentoList.add( rptCarteraXDepartamento );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetalleVisitasList = new ArrayList();

   public Collection getRptDetalleVisitasList() { return rptDetalleVisitasList;}

   public void setRptDetalleVisitasList(Collection c) { this.rptDetalleVisitasList = c;}

   public void addRptDetalleVisitas( RptDetalleVisitas rptDetalleVisitas )
   {
      rptDetalleVisitas.setCLocalidadIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetallePagosList = new ArrayList();

   public Collection getRptDetallePagosList() { return rptDetallePagosList;}

   public void setRptDetallePagosList(Collection c) { this.rptDetallePagosList = c;}

   public void addRptDetallePagos( RptDetallePagos rptDetallePagos )
   {
      rptDetallePagos.setCLocalidadIdRef(this);
      rptDetallePagosList.add( rptDetallePagos );
   }

   //---------------------------------------------------------------------------

   private CatDepartamento nDepartamentoIdRef;

   public CatDepartamento getNDepartamentoIdRef() { return nDepartamentoIdRef; }

   public void setNDepartamentoIdRef(CatDepartamento catDepartamento)
   {
      this.nDepartamentoIdRef = catDepartamento;
   }

}

//==============================================================================

