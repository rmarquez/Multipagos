//==============================================================================
//===   b_barrio.java                        Build:2625
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class BBarrio implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    barrioId;
   private Integer    ciudadId;
   private String     barrioNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
   }

   //---------------------------------------------------------------------------

   public Integer getCiudadId() { return ciudadId; }

   public void setCiudadId(Integer ciudadId)
   {
      this.ciudadId = ciudadId;
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

   private Collection carteraBanproList = new ArrayList();

   public Collection getCarteraBanproList() { return carteraBanproList;}

   public void setCarteraBanproList(Collection c) { this.carteraBanproList = c;}

   public void addCarteraBanpro( CarteraBanpro carteraBanpro )
   {
      carteraBanpro.setBarrioCRef(this);
      carteraBanproList.add( carteraBanpro );
   }

   //---------------------------------------------------------------------------

   private Ciudad ciudadIdRef;

   public Ciudad getCiudadIdRef() { return ciudadIdRef; }

   public void setCiudadIdRef(Ciudad ciudad)
   {
      this.ciudadIdRef = ciudad;
   }

}

//==============================================================================

