//==============================================================================
//===   cantidad_monedas.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CantidadMonedas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cantidadId;
   private String     cantidadNombre;
   private BigDecimal cantidadValor;
   private Boolean    dolares;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getCantidadId() { return cantidadId; }

   public void setCantidadId(Integer cantidadId)
   {
      this.cantidadId = cantidadId;
   }

   //---------------------------------------------------------------------------

   public String getCantidadNombre() { return cantidadNombre; }

   public void setCantidadNombre(String cantidadNombre)
   {
      this.cantidadNombre = cantidadNombre;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getCantidadValor() { return cantidadValor; }

   public void setCantidadValor(BigDecimal cantidadValor)
   {
      this.cantidadValor = cantidadValor;
   }

   //---------------------------------------------------------------------------

   public Boolean getDolares() { return dolares; }

   public void setDolares(Boolean dolares)
   {
      this.dolares = dolares;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection arqueoCantidadList = new ArrayList();

   public Collection getArqueoCantidadList() { return arqueoCantidadList;}

   public void setArqueoCantidadList(Collection c) { this.arqueoCantidadList = c;}

   public void addArqueoCantidad( ArqueoCantidad arqueoCantidad )
   {
      arqueoCantidad.setCantidadIdRef(this);
      arqueoCantidadList.add( arqueoCantidad );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoCantidadUsList = new ArrayList();

   public Collection getArqueoCantidadUsList() { return arqueoCantidadUsList;}

   public void setArqueoCantidadUsList(Collection c) { this.arqueoCantidadUsList = c;}

   public void addArqueoCantidadUs( ArqueoCantidadUs arqueoCantidadUs )
   {
      arqueoCantidadUs.setCantidadIdRef(this);
      arqueoCantidadUsList.add( arqueoCantidadUs );
   }
}

//==============================================================================

