//==============================================================================
//===   gestion_ibw.java                        Build:2675
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class GestionIbw implements java.io.Serializable
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

   private Collection detalleGIbwList = new ArrayList();

   public Collection getDetalleGIbwList() { return detalleGIbwList;}

   public void setDetalleGIbwList(Collection c) { this.detalleGIbwList = c;}

   public void addDetalleGIbw( DetalleGIbw detalleGIbw )
   {
      detalleGIbw.setGestionIdRef(this);
      detalleGIbwList.add( detalleGIbw );
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

