//==============================================================================
//===   i_barrio.java                        Build:2672
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class IBarrio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    barrioId;
   private Integer    municipioId;
   private String     barrioNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
   }

   //---------------------------------------------------------------------------

   public Integer getMunicipioId() { return municipioId; }

   public void setMunicipioId(Integer municipioId)
   {
      this.municipioId = municipioId;
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

   private Collection carteraIbwList = new ArrayList();

   public Collection getCarteraIbwList() { return carteraIbwList;}

   public void setCarteraIbwList(Collection c) { this.carteraIbwList = c;}

   public void addCarteraIbw( CarteraIbw carteraIbw )
   {
      carteraIbw.setBarrioIdRef(this);
      carteraIbwList.add( carteraIbw );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGIbwList = new ArrayList();

   public Collection getDetalleGIbwList() { return detalleGIbwList;}

   public void setDetalleGIbwList(Collection c) { this.detalleGIbwList = c;}

   public void addDetalleGIbw( DetalleGIbw detalleGIbw )
   {
      detalleGIbw.setBarrioIdRef(this);
      detalleGIbwList.add( detalleGIbw );
   }

   //---------------------------------------------------------------------------

   private Municipio municipioIdRef;

   public Municipio getMunicipioIdRef() { return municipioIdRef; }

   public void setMunicipioIdRef(Municipio municipio)
   {
      this.municipioIdRef = municipio;
   }

}

//==============================================================================

