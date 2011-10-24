//==============================================================================
//===   rpt_logs.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptLogs implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cLogsId;
   private String     dLogsReferencia;
   private Date       fLogsFecha;
   private Integer    nTipodLogId;
   private String     dLogsDescripcion;

   //---------------------------------------------------------------------------

   public Integer getCLogsId() { return cLogsId; }

   public void setCLogsId(Integer cLogsId)
   {
      this.cLogsId = cLogsId;
   }

   //---------------------------------------------------------------------------

   public String getDLogsReferencia() { return dLogsReferencia; }

   public void setDLogsReferencia(String dLogsReferencia)
   {
      this.dLogsReferencia = dLogsReferencia;
   }

   //---------------------------------------------------------------------------

   public Date getFLogsFecha() { return fLogsFecha; }

   public void setFLogsFecha(Date fLogsFecha)
   {
      this.fLogsFecha = fLogsFecha;
   }

   //---------------------------------------------------------------------------

   public Integer getNTipodLogId() { return nTipodLogId; }

   public void setNTipodLogId(Integer nTipodLogId)
   {
      this.nTipodLogId = nTipodLogId;
   }

   //---------------------------------------------------------------------------

   public String getDLogsDescripcion() { return dLogsDescripcion; }

   public void setDLogsDescripcion(String dLogsDescripcion)
   {
      this.dLogsDescripcion = dLogsDescripcion;
   }

   //---------------------------------------------------------------------------

   private RptTipoDocumentoLogs nTipodLogIdRef;

   public RptTipoDocumentoLogs getNTipodLogIdRef() { return nTipodLogIdRef; }

   public void setNTipodLogIdRef(RptTipoDocumentoLogs rptTipoDocumentoLogs)
   {
      this.nTipodLogIdRef = rptTipoDocumentoLogs;
   }

}

//==============================================================================

