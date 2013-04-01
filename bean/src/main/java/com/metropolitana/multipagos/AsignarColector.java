//==============================================================================
//===   asignar_colector.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AsignarColector implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    asignarcId;
   private Integer    usrId;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getAsignarcId() { return asignarcId; }

   public void setAsignarcId(Integer asignarcId)
   {
      this.asignarcId = asignarcId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection detalleColectoresList = new ArrayList();

   public Collection getDetalleColectoresList() { return detalleColectoresList;}

   public void setDetalleColectoresList(Collection c) { this.detalleColectoresList = c;}

   public void addDetalleColectores( DetalleColectores detalleColectores )
   {
      detalleColectores.setAsignarcIdRef(this);
      detalleColectoresList.add( detalleColectores );
   }

   //---------------------------------------------------------------------------

   private AuthUser usrIdRef;

   public AuthUser getUsrIdRef() { return usrIdRef; }

   public void setUsrIdRef(AuthUser authUser)
   {
      this.usrIdRef = authUser;
   }

}

//==============================================================================

