//==============================================================================
//===   municipio.java                        Build:2672
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Municipio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    municipioId;
   private Integer    departamentoId;
   private String     municipioNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getMunicipioId() { return municipioId; }

   public void setMunicipioId(Integer municipioId)
   {
      this.municipioId = municipioId;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
   }

   //---------------------------------------------------------------------------

   public String getMunicipioNombre() { return municipioNombre; }

   public void setMunicipioNombre(String municipioNombre)
   {
      this.municipioNombre = municipioNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection carteraIbwList = new ArrayList();

   public Collection getCarteraIbwList() { return carteraIbwList;}

   public void setCarteraIbwList(Collection c) { this.carteraIbwList = c;}

   public void addCarteraIbw( CarteraIbw carteraIbw )
   {
      carteraIbw.setMunicipioIdRef(this);
      carteraIbwList.add( carteraIbw );
   }

   //---------------------------------------------------------------------------

   private Collection iBarrioList = new ArrayList();

   public Collection getIBarrioList() { return iBarrioList;}

   public void setIBarrioList(Collection c) { this.iBarrioList = c;}

   public void addIBarrio( IBarrio iBarrio )
   {
      iBarrio.setMunicipioIdRef(this);
      iBarrioList.add( iBarrio );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGIbwList = new ArrayList();

   public Collection getDetalleGIbwList() { return detalleGIbwList;}

   public void setDetalleGIbwList(Collection c) { this.detalleGIbwList = c;}

   public void addDetalleGIbw( DetalleGIbw detalleGIbw )
   {
      detalleGIbw.setMunicipioIdRef(this);
      detalleGIbwList.add( detalleGIbw );
   }

   //---------------------------------------------------------------------------

   private IDepartamento departamentoIdRef;

   public IDepartamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(IDepartamento iDepartamento)
   {
      this.departamentoIdRef = iDepartamento;
   }

}

//==============================================================================

