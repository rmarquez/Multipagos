//==============================================================================
//===   gestion_banpro.java                        Build:2636
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class GestionBanpro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    gestionId;
   private Integer    usrId;
   private Date       fecha;

   //---------------------------------------------------------------------------

   public Integer getGestionId() { return gestionId; }

   public void setGestionId(Integer gestionId)
   {
      this.gestionId = gestionId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public Date getFecha() { return fecha; }

   public void setFecha(Date fecha)
   {
      this.fecha = fecha;
   }

   //---------------------------------------------------------------------------

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setGestionIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
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

