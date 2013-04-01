//==============================================================================
//===   tmp_datos.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class TmpDatos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tmpDatos;
   private String     contrato;
   private String     facturaInterna;

   //---------------------------------------------------------------------------

   public Integer getTmpDatos() { return tmpDatos; }

   public void setTmpDatos(Integer tmpDatos)
   {
      this.tmpDatos = tmpDatos;
   }

   //---------------------------------------------------------------------------

   public String getContrato() { return contrato; }

   public void setContrato(String contrato)
   {
      this.contrato = contrato;
   }

   //---------------------------------------------------------------------------

   public String getFacturaInterna() { return facturaInterna; }

   public void setFacturaInterna(String facturaInterna)
   {
      this.facturaInterna = facturaInterna;
   }
}

//==============================================================================

