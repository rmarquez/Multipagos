//==============================================================================
//===   arqueo_cheque.java                        Build:2540
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArqueoCheque implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arqueoId;
   private Integer    bancoId;
   private Integer    nCheque;
   private String     beneficiario;
   private BigDecimal montoCs;
   private BigDecimal montoUs;
   private BigDecimal conversion;
   private Boolean    dolares;

   //---------------------------------------------------------------------------

   public Integer getArqueoId() { return arqueoId; }

   public void setArqueoId(Integer arqueoId)
   {
      this.arqueoId = arqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getBancoId() { return bancoId; }

   public void setBancoId(Integer bancoId)
   {
      this.bancoId = bancoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNCheque() { return nCheque; }

   public void setNCheque(Integer nCheque)
   {
      this.nCheque = nCheque;
   }

   //---------------------------------------------------------------------------

   public String getBeneficiario() { return beneficiario; }

   public void setBeneficiario(String beneficiario)
   {
      this.beneficiario = beneficiario;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getMontoCs() { return montoCs; }

   public void setMontoCs(BigDecimal montoCs)
   {
      this.montoCs = montoCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getMontoUs() { return montoUs; }

   public void setMontoUs(BigDecimal montoUs)
   {
      this.montoUs = montoUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getConversion() { return conversion; }

   public void setConversion(BigDecimal conversion)
   {
      this.conversion = conversion;
   }

   //---------------------------------------------------------------------------

   public Boolean getDolares() { return dolares; }

   public void setDolares(Boolean dolares)
   {
      this.dolares = dolares;
   }

   //---------------------------------------------------------------------------

   private ArqueoPagos arqueoIdRef;

   public ArqueoPagos getArqueoIdRef() { return arqueoIdRef; }

   public void setArqueoIdRef(ArqueoPagos arqueoPagos)
   {
      this.arqueoIdRef = arqueoPagos;
   }


   //---------------------------------------------------------------------------

   private Banco bancoIdRef;

   public Banco getBancoIdRef() { return bancoIdRef; }

   public void setBancoIdRef(Banco banco)
   {
      this.bancoIdRef = banco;
   }

}

//==============================================================================

