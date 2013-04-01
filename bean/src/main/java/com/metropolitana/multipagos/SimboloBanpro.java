//==============================================================================
//===   simbolo_banpro.java                        Build:2636
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class SimboloBanpro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    simboloId;
   private Integer    simboloNumero;
   private String     simboloCorto;
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

   public String getSimboloCorto() { return simboloCorto; }

   public void setSimboloCorto(String simboloCorto)
   {
      this.simboloCorto = simboloCorto;
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

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setSimboloIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
   }
}

//==============================================================================

