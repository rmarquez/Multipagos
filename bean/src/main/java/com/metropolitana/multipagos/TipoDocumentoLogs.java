//==============================================================================
//===   tipo_documento_logs.java                        Build:2525
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class TipoDocumentoLogs implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tipodLogId;
   private String     tipodLogNombre;

   //---------------------------------------------------------------------------

   public Integer getTipodLogId() { return tipodLogId; }

   public void setTipodLogId(Integer tipodLogId)
   {
      this.tipodLogId = tipodLogId;
   }

   //---------------------------------------------------------------------------

   public String getTipodLogNombre() { return tipodLogNombre; }

   public void setTipodLogNombre(String tipodLogNombre)
   {
      this.tipodLogNombre = tipodLogNombre;
   }

   //---------------------------------------------------------------------------

   private Collection logsList = new ArrayList();

   public Collection getLogsList() { return logsList;}

   public void setLogsList(Collection c) { this.logsList = c;}

   public void addLogs( Logs logs )
   {
      logs.setTipodLogIdRef(this);
      logsList.add( logs );
   }
}

//==============================================================================

