//==============================================================================
//===   tecnologia.java                        Build:2671
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Tecnologia implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tecnologiaId;
   private String     tecnologiaNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getTecnologiaId() { return tecnologiaId; }

   public void setTecnologiaId(Integer tecnologiaId)
   {
      this.tecnologiaId = tecnologiaId;
   }

   //---------------------------------------------------------------------------

   public String getTecnologiaNombre() { return tecnologiaNombre; }

   public void setTecnologiaNombre(String tecnologiaNombre)
   {
      this.tecnologiaNombre = tecnologiaNombre;
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
      carteraIbw.setTecnologiaIdRef(this);
      carteraIbwList.add( carteraIbw );
   }
}

//==============================================================================

