//==============================================================================
//===   cat_cantidad_monedas.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatCantidadMonedas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cCantidadId;
   private String     dCantidadNombre;
   private Integer    nCantidadValor;
   private Boolean    bDolares;

   //---------------------------------------------------------------------------

   public Integer getCCantidadId() { return cCantidadId; }

   public void setCCantidadId(Integer cCantidadId)
   {
      this.cCantidadId = cCantidadId;
   }

   //---------------------------------------------------------------------------

   public String getDCantidadNombre() { return dCantidadNombre; }

   public void setDCantidadNombre(String dCantidadNombre)
   {
      this.dCantidadNombre = dCantidadNombre;
   }

   //---------------------------------------------------------------------------

   public Integer getNCantidadValor() { return nCantidadValor; }

   public void setNCantidadValor(Integer nCantidadValor)
   {
      this.nCantidadValor = nCantidadValor;
   }

   //---------------------------------------------------------------------------

   public Boolean getBDolares() { return bDolares; }

   public void setBDolares(Boolean bDolares)
   {
      this.bDolares = bDolares;
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoCantidadList = new ArrayList();

   public Collection getRptArqueoCantidadList() { return rptArqueoCantidadList;}

   public void setRptArqueoCantidadList(Collection c) { this.rptArqueoCantidadList = c;}

   public void addRptArqueoCantidad( RptArqueoCantidad rptArqueoCantidad )
   {
      rptArqueoCantidad.setCCantidadIdRef(this);
      rptArqueoCantidadList.add( rptArqueoCantidad );
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoCantidadUsList = new ArrayList();

   public Collection getRptArqueoCantidadUsList() { return rptArqueoCantidadUsList;}

   public void setRptArqueoCantidadUsList(Collection c) { this.rptArqueoCantidadUsList = c;}

   public void addRptArqueoCantidadUs( RptArqueoCantidadUs rptArqueoCantidadUs )
   {
      rptArqueoCantidadUs.setCCantidadIdRef(this);
      rptArqueoCantidadUsList.add( rptArqueoCantidadUs );
   }
}

//==============================================================================

