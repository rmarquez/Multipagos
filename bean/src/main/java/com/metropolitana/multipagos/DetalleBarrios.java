//==============================================================================
//===   detalle_barrios.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleBarrios implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    asignarbId;
   private Integer    barrioId;

   //---------------------------------------------------------------------------

   public Integer getAsignarbId() { return asignarbId; }

   public void setAsignarbId(Integer asignarbId)
   {
      this.asignarbId = asignarbId;
   }

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
   }

   //---------------------------------------------------------------------------

   private AsignarBarrio asignarbIdRef;

   public AsignarBarrio getAsignarbIdRef() { return asignarbIdRef; }

   public void setAsignarbIdRef(AsignarBarrio asignarBarrio)
   {
      this.asignarbIdRef = asignarBarrio;
   }


   //---------------------------------------------------------------------------

   private Barrio barrioIdRef;

   public Barrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(Barrio barrio)
   {
      this.barrioIdRef = barrio;
   }

}

//==============================================================================

