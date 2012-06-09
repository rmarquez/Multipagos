//==============================================================================
//===   simbolo_avon.java                        Build:2576
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class SimboloAvon implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    simboloId;
   private Integer    simboloNumero;
   private String     simboloNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getSimboloId() { return simboloId; }

   public void setSimboloId(Integer simboloId)
   {
      this.simboloId = simboloId;
   }

   //---------------------------------------------------------------------------

   public Integer getSimboloNumero() { return simboloNumero; }

   public void setSimboloNumero(Integer simboloNumero)
   {
      this.simboloNumero = simboloNumero;
   }

   //---------------------------------------------------------------------------

   public String getSimboloNombre() { return simboloNombre; }

   public void setSimboloNombre(String simboloNombre)
   {
      this.simboloNombre = simboloNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection detalleGestionList = new ArrayList();

   public Collection getDetalleGestionList() { return detalleGestionList;}

   public void setDetalleGestionList(Collection c) { this.detalleGestionList = c;}

   public void addDetalleGestion( DetalleGestion detalleGestion )
   {
      detalleGestion.setSimboloIdRef(this);
      detalleGestionList.add( detalleGestion );
   }
}

//==============================================================================

