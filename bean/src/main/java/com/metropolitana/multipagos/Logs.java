//==============================================================================
//===   logs.java                        Build:2489
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Logs implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    logsId;
   private String     logsReferencia;
   private Date       logsFecha;
   private Integer    tipodLogId;
   private String     logsDescripcion;

   //---------------------------------------------------------------------------

   public Integer getLogsId() { return logsId; }

   public void setLogsId(Integer logsId)
   {
      this.logsId = logsId;
   }

   //---------------------------------------------------------------------------

   public String getLogsReferencia() { return logsReferencia; }

   public void setLogsReferencia(String logsReferencia)
   {
      this.logsReferencia = logsReferencia;
   }

   //---------------------------------------------------------------------------

   public Date getLogsFecha() { return logsFecha; }

   public void setLogsFecha(Date logsFecha)
   {
      this.logsFecha = logsFecha;
   }

   //---------------------------------------------------------------------------

   public Integer getTipodLogId() { return tipodLogId; }

   public void setTipodLogId(Integer tipodLogId)
   {
      this.tipodLogId = tipodLogId;
   }

   //---------------------------------------------------------------------------

   public String getLogsDescripcion() { return logsDescripcion; }

   public void setLogsDescripcion(String logsDescripcion)
   {
      this.logsDescripcion = logsDescripcion;
   }

   //---------------------------------------------------------------------------

   private TipoDocumentoLogs tipodLogIdRef;

   public TipoDocumentoLogs getTipodLogIdRef() { return tipodLogIdRef; }

   public void setTipodLogIdRef(TipoDocumentoLogs tipoDocumentoLogs)
   {
      this.tipodLogIdRef = tipoDocumentoLogs;
   }

}

//==============================================================================

