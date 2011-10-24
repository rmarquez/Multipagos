//==============================================================================
//===   cat_banco.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CatBanco implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cBancoId;
   private String     dBancoNombre;
   private Boolean    bInactivo;

   //---------------------------------------------------------------------------

   public Integer getCBancoId() { return cBancoId; }

   public void setCBancoId(Integer cBancoId)
   {
      this.cBancoId = cBancoId;
   }

   //---------------------------------------------------------------------------

   public String getDBancoNombre() { return dBancoNombre; }

   public void setDBancoNombre(String dBancoNombre)
   {
      this.dBancoNombre = dBancoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getBInactivo() { return bInactivo; }

   public void setBInactivo(Boolean bInactivo)
   {
      this.bInactivo = bInactivo;
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoChequeList = new ArrayList();

   public Collection getRptArqueoChequeList() { return rptArqueoChequeList;}

   public void setRptArqueoChequeList(Collection c) { this.rptArqueoChequeList = c;}

   public void addRptArqueoCheque( RptArqueoCheque rptArqueoCheque )
   {
      rptArqueoCheque.setCBancoIdRef(this);
      rptArqueoChequeList.add( rptArqueoCheque );
   }
}

//==============================================================================

