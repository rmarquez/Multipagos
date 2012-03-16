//==============================================================================
//===   detalle_colectores.java                        Build:2561
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleColectores implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    asignarcId;
   private Integer    colectorId;

   //---------------------------------------------------------------------------

   public Integer getAsignarcId() { return asignarcId; }

   public void setAsignarcId(Integer asignarcId)
   {
      this.asignarcId = asignarcId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   private AsignarColector asignarcIdRef;

   public AsignarColector getAsignarcIdRef() { return asignarcIdRef; }

   public void setAsignarcIdRef(AsignarColector asignarColector)
   {
      this.asignarcIdRef = asignarColector;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }

}

//==============================================================================

