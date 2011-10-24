//==============================================================================
//===   rpt_arqueo_cheque.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptArqueoCheque implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cArqueoId;
   private Integer    cBancoId;
   private Integer    nCheque;
   private String     dBeneficiario;
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

   public Integer getCBancoId() { return cBancoId; }

   public void setCBancoId(Integer cBancoId)
   {
      this.cBancoId = cBancoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNCheque() { return nCheque; }

   public void setNCheque(Integer nCheque)
   {
      this.nCheque = nCheque;
   }

   //---------------------------------------------------------------------------

   public String getDBeneficiario() { return dBeneficiario; }

   public void setDBeneficiario(String dBeneficiario)
   {
      this.dBeneficiario = dBeneficiario;
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


   //---------------------------------------------------------------------------

   private CatBanco cBancoIdRef;

   public CatBanco getCBancoIdRef() { return cBancoIdRef; }

   public void setCBancoIdRef(CatBanco catBanco)
   {
      this.cBancoIdRef = catBanco;
   }

}

//==============================================================================

