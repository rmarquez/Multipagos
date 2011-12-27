//==============================================================================
//===   cuentas.java                        Build:2545
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Cuentas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cuentaId;
   private Integer    bancoId;
   private String     cuentaEmpresa;
   private String     cuentaMoneda;
   private String     numeroCuenta;

   //---------------------------------------------------------------------------

   public Integer getCuentaId() { return cuentaId; }

   public void setCuentaId(Integer cuentaId)
   {
      this.cuentaId = cuentaId;
   }

   //---------------------------------------------------------------------------

   public Integer getBancoId() { return bancoId; }

   public void setBancoId(Integer bancoId)
   {
      this.bancoId = bancoId;
   }

   //---------------------------------------------------------------------------

   public String getCuentaEmpresa() { return cuentaEmpresa; }

   public void setCuentaEmpresa(String cuentaEmpresa)
   {
      this.cuentaEmpresa = cuentaEmpresa;
   }

   //---------------------------------------------------------------------------

   public String getCuentaMoneda() { return cuentaMoneda; }

   public void setCuentaMoneda(String cuentaMoneda)
   {
      this.cuentaMoneda = cuentaMoneda;
   }

   //---------------------------------------------------------------------------

   public String getNumeroCuenta() { return numeroCuenta; }

   public void setNumeroCuenta(String numeroCuenta)
   {
      this.numeroCuenta = numeroCuenta;
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

