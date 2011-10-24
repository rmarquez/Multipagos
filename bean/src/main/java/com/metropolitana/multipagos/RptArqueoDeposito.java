//==============================================================================
//===   rpt_arqueo_deposito.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptArqueoDeposito implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cArqueoId;
   private Integer    cCuentaCs;
   private Integer    cCuentaUs;
   private String     dReferencia;
   private BigDecimal vMontoCs;
   private BigDecimal vMontoUs;
   private BigDecimal vConversion;
   private Boolean    bDolares;

   //---------------------------------------------------------------------------

   public Integer getCArqueoId() { return cArqueoId; }

   public void setCArqueoId(Integer cArqueoId)
   {
      this.cArqueoId = cArqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCCuentaCs() { return cCuentaCs; }

   public void setCCuentaCs(Integer cCuentaCs)
   {
      this.cCuentaCs = cCuentaCs;
   }

   //---------------------------------------------------------------------------

   public Integer getCCuentaUs() { return cCuentaUs; }

   public void setCCuentaUs(Integer cCuentaUs)
   {
      this.cCuentaUs = cCuentaUs;
   }

   //---------------------------------------------------------------------------

   public String getDReferencia() { return dReferencia; }

   public void setDReferencia(String dReferencia)
   {
      this.dReferencia = dReferencia;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVMontoCs() { return vMontoCs; }

   public void setVMontoCs(BigDecimal vMontoCs)
   {
      this.vMontoCs = vMontoCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVMontoUs() { return vMontoUs; }

   public void setVMontoUs(BigDecimal vMontoUs)
   {
      this.vMontoUs = vMontoUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVConversion() { return vConversion; }

   public void setVConversion(BigDecimal vConversion)
   {
      this.vConversion = vConversion;
   }

   //---------------------------------------------------------------------------

   public Boolean getBDolares() { return bDolares; }

   public void setBDolares(Boolean bDolares)
   {
      this.bDolares = bDolares;
   }

   //---------------------------------------------------------------------------

   private RptArqueoPagos cArqueoIdRef;

   public RptArqueoPagos getCArqueoIdRef() { return cArqueoIdRef; }

   public void setCArqueoIdRef(RptArqueoPagos rptArqueoPagos)
   {
      this.cArqueoIdRef = rptArqueoPagos;
   }

}

//==============================================================================

