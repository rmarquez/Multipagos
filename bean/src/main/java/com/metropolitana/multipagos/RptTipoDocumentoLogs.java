//==============================================================================
//===   rpt_tipo_documento_logs.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptTipoDocumentoLogs implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cTipodLogId;
   private String     dTipodLogNombre;

   //---------------------------------------------------------------------------

   public Integer getCTipodLogId() { return cTipodLogId; }

   public void setCTipodLogId(Integer cTipodLogId)
   {
      this.cTipodLogId = cTipodLogId;
   }

   //---------------------------------------------------------------------------

   public String getDTipodLogNombre() { return dTipodLogNombre; }

   public void setDTipodLogNombre(String dTipodLogNombre)
   {
      this.dTipodLogNombre = dTipodLogNombre;
   }

   //---------------------------------------------------------------------------

   private Collection rptLogsList = new ArrayList();

   public Collection getRptLogsList() { return rptLogsList;}

   public void setRptLogsList(Collection c) { this.rptLogsList = c;}

   public void addRptLogs( RptLogs rptLogs )
   {
      rptLogs.setNTipodLogIdRef(this);
      rptLogsList.add( rptLogs );
   }
}

//==============================================================================

