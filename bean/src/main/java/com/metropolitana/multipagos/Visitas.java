//==============================================================================
//===   visitas.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Visitas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    visitaId;
   private Integer    usrId;
   private Date       fecha;
   private Integer    cantidadVisitas;

   //---------------------------------------------------------------------------

   public Integer getVisitaId() { return visitaId; }

   public void setVisitaId(Integer visitaId)
   {
      this.visitaId = visitaId;
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

   public Integer getCantidadVisitas() { return cantidadVisitas; }

   public void setCantidadVisitas(Integer cantidadVisitas)
   {
      this.cantidadVisitas = cantidadVisitas;
   }

   //---------------------------------------------------------------------------

   private Collection detalleVisitasList = new ArrayList();

   public Collection getDetalleVisitasList() { return detalleVisitasList;}

   public void setDetalleVisitasList(Collection c) { this.detalleVisitasList = c;}

   public void addDetalleVisitas( DetalleVisitas detalleVisitas )
   {
      detalleVisitas.setVisitaIdRef(this);
      detalleVisitasList.add( detalleVisitas );
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

