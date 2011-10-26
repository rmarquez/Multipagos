//==============================================================================
//===   banco.java                        Build:2516
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Banco implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    bancoId;
   private String     bancoNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getBancoId() { return bancoId; }

   public void setBancoId(Integer bancoId)
   {
      this.bancoId = bancoId;
   }

   //---------------------------------------------------------------------------

   public String getBancoNombre() { return bancoNombre; }

   public void setBancoNombre(String bancoNombre)
   {
      this.bancoNombre = bancoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection arqueoChequeList = new ArrayList();

   public Collection getArqueoChequeList() { return arqueoChequeList;}

   public void setArqueoChequeList(Collection c) { this.arqueoChequeList = c;}

   public void addArqueoCheque( ArqueoCheque arqueoCheque )
   {
      arqueoCheque.setBancoIdRef(this);
      arqueoChequeList.add( arqueoCheque );
   }
}

//==============================================================================

