//==============================================================================
//===   ciudad.java                        Build:2636
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Ciudad implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    ciudadId;
   private Integer    departamentoId;
   private String     ciudadNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getCiudadId() { return ciudadId; }

   public void setCiudadId(Integer ciudadId)
   {
      this.ciudadId = ciudadId;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
   }

   //---------------------------------------------------------------------------

   public String getCiudadNombre() { return ciudadNombre; }

   public void setCiudadNombre(String ciudadNombre)
   {
      this.ciudadNombre = ciudadNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection ciudadCCarteraBanproList = new ArrayList();

   public Collection getCiudadCCarteraBanproList() { return ciudadCCarteraBanproList;}

   public void setCiudadCCarteraBanproList(Collection c) { this.ciudadCCarteraBanproList = c;}

   public void addCiudadCCarteraBanpro( CarteraBanpro carteraBanpro )
   {
      carteraBanpro.setCiudadCRef(this);
      ciudadCCarteraBanproList.add( carteraBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection ciudadTCarteraBanproList = new ArrayList();

   public Collection getCiudadTCarteraBanproList() { return ciudadTCarteraBanproList;}

   public void setCiudadTCarteraBanproList(Collection c) { this.ciudadTCarteraBanproList = c;}

   public void addCiudadTCarteraBanpro( CarteraBanpro carteraBanpro )
   {
      carteraBanpro.setCiudadTRef(this);
      ciudadTCarteraBanproList.add( carteraBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection bBarrioList = new ArrayList();

   public Collection getBBarrioList() { return bBarrioList;}

   public void setBBarrioList(Collection c) { this.bBarrioList = c;}

   public void addBBarrio( BBarrio bBarrio )
   {
      bBarrio.setCiudadIdRef(this);
      bBarrioList.add( bBarrio );
   }

   //---------------------------------------------------------------------------

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setCiudadCIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
   }

   //---------------------------------------------------------------------------

   private BDepartamento departamentoIdRef;

   public BDepartamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(BDepartamento bDepartamento)
   {
      this.departamentoIdRef = bDepartamento;
   }

}

//==============================================================================

