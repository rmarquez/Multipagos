//==============================================================================
//===   rpt_visitas.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptVisitas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cVisitaId;
   private Integer    nUsrId;
   private Date       fFecha;
   private Integer    nCantidadVisitas;

   //---------------------------------------------------------------------------

   public Integer getCVisitaId() { return cVisitaId; }

   public void setCVisitaId(Integer cVisitaId)
   {
      this.cVisitaId = cVisitaId;
   }

   //---------------------------------------------------------------------------

   public Integer getNUsrId() { return nUsrId; }

   public void setNUsrId(Integer nUsrId)
   {
      this.nUsrId = nUsrId;
   }

   //---------------------------------------------------------------------------

   public Date getFFecha() { return fFecha; }

   public void setFFecha(Date fFecha)
   {
      this.fFecha = fFecha;
   }

   //---------------------------------------------------------------------------

   public Integer getNCantidadVisitas() { return nCantidadVisitas; }

   public void setNCantidadVisitas(Integer nCantidadVisitas)
   {
      this.nCantidadVisitas = nCantidadVisitas;
   }

   //---------------------------------------------------------------------------

   private Collection rptDetalleVisitasList = new ArrayList();

   public Collection getRptDetalleVisitasList() { return rptDetalleVisitasList;}

   public void setRptDetalleVisitasList(Collection c) { this.rptDetalleVisitasList = c;}

   public void addRptDetalleVisitas( RptDetalleVisitas rptDetalleVisitas )
   {
      rptDetalleVisitas.setCVisitaIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }

   //---------------------------------------------------------------------------

   private AuthUser nUsrIdRef;

   public AuthUser getNUsrIdRef() { return nUsrIdRef; }

   public void setNUsrIdRef(AuthUser authUser)
   {
      this.nUsrIdRef = authUser;
   }

}

//==============================================================================

