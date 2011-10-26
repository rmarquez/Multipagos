//==============================================================================
//===   arqueo_deposito.java                        Build:2516
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArqueoDeposito implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arqueoId;
   private Integer    cuentaCs;
   private Integer    cuentaUs;
   private String     referencia;
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

   public Integer getCuentaCs() { return cuentaCs; }

   public void setCuentaCs(Integer cuentaCs)
   {
      this.cuentaCs = cuentaCs;
   }

   //---------------------------------------------------------------------------

   public Integer getCuentaUs() { return cuentaUs; }

   public void setCuentaUs(Integer cuentaUs)
   {
      this.cuentaUs = cuentaUs;
   }

   //---------------------------------------------------------------------------

   public String getReferencia() { return referencia; }

   public void setReferencia(String referencia)
   {
      this.referencia = referencia;
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

}

//==============================================================================

