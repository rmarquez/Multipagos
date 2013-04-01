//==============================================================================
//===   b_departamento.java                        Build:2637
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class BDepartamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    bDepartamentoId;
   private String     bDepartamentoNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getBDepartamentoId() { return bDepartamentoId; }

   public void setBDepartamentoId(Integer bDepartamentoId)
   {
      this.bDepartamentoId = bDepartamentoId;
   }

   //---------------------------------------------------------------------------

   public String getBDepartamentoNombre() { return bDepartamentoNombre; }

   public void setBDepartamentoNombre(String bDepartamentoNombre)
   {
      this.bDepartamentoNombre = bDepartamentoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection departamentoCCarteraBanproList = new ArrayList();

   public Collection getDepartamentoCCarteraBanproList() { return departamentoCCarteraBanproList;}

   public void setDepartamentoCCarteraBanproList(Collection c) { this.departamentoCCarteraBanproList = c;}

   public void addDepartamentoCCarteraBanpro( CarteraBanpro carteraBanpro )
   {
      carteraBanpro.setDepartamentoCRef(this);
      departamentoCCarteraBanproList.add( carteraBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection departamentoTCarteraBanproList = new ArrayList();

   public Collection getDepartamentoTCarteraBanproList() { return departamentoTCarteraBanproList;}

   public void setDepartamentoTCarteraBanproList(Collection c) { this.departamentoTCarteraBanproList = c;}

   public void addDepartamentoTCarteraBanpro( CarteraBanpro carteraBanpro )
   {
      carteraBanpro.setDepartamentoTRef(this);
      departamentoTCarteraBanproList.add( carteraBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection ciudadList = new ArrayList();

   public Collection getCiudadList() { return ciudadList;}

   public void setCiudadList(Collection c) { this.ciudadList = c;}

   public void addCiudad( Ciudad ciudad )
   {
      ciudad.setDepartamentoIdRef(this);
      ciudadList.add( ciudad );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setDepartamentoIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
   }
}

//==============================================================================

