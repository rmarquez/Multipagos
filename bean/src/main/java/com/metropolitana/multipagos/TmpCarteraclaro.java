//==============================================================================
//===   tmp_carteraclaro.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class TmpCarteraclaro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tmpId;
   private String     contrato;
   private String     suscriptor;
   private String     direccion;
   private String     barrio;
   private String     facturaInterna;
   private String     numeroFiscal;
   private String     anio;
   private String     mes;
   private BigDecimal saldo;
   private String     estado;
   private String     departamento;
   private String     servicio;
   private Date       fAsignado;

   //---------------------------------------------------------------------------

   public Integer getTmpId() { return tmpId; }

   public void setTmpId(Integer tmpId)
   {
      this.tmpId = tmpId;
   }

   //---------------------------------------------------------------------------

   public String getContrato() { return contrato; }

   public void setContrato(String contrato)
   {
      this.contrato = contrato;
   }

   //---------------------------------------------------------------------------

   public String getSuscriptor() { return suscriptor; }

   public void setSuscriptor(String suscriptor)
   {
      this.suscriptor = suscriptor;
   }

   //---------------------------------------------------------------------------

   public String getDireccion() { return direccion; }

   public void setDireccion(String direccion)
   {
      this.direccion = direccion;
   }

   //---------------------------------------------------------------------------

   public String getBarrio() { return barrio; }

   public void setBarrio(String barrio)
   {
      this.barrio = barrio;
   }

   //---------------------------------------------------------------------------

   public String getFacturaInterna() { return facturaInterna; }

   public void setFacturaInterna(String facturaInterna)
   {
      this.facturaInterna = facturaInterna;
   }

   //---------------------------------------------------------------------------

   public String getNumeroFiscal() { return numeroFiscal; }

   public void setNumeroFiscal(String numeroFiscal)
   {
      this.numeroFiscal = numeroFiscal;
   }

   //---------------------------------------------------------------------------

   public String getAnio() { return anio; }

   public void setAnio(String anio)
   {
      this.anio = anio;
   }

   //---------------------------------------------------------------------------

   public String getMes() { return mes; }

   public void setMes(String mes)
   {
      this.mes = mes;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getSaldo() { return saldo; }

   public void setSaldo(BigDecimal saldo)
   {
      this.saldo = saldo;
   }

   //---------------------------------------------------------------------------

   public String getEstado() { return estado; }

   public void setEstado(String estado)
   {
      this.estado = estado;
   }

   //---------------------------------------------------------------------------

   public String getDepartamento() { return departamento; }

   public void setDepartamento(String departamento)
   {
      this.departamento = departamento;
   }

   //---------------------------------------------------------------------------

   public String getServicio() { return servicio; }

   public void setServicio(String servicio)
   {
      this.servicio = servicio;
   }

   //---------------------------------------------------------------------------

   public Date getFAsignado() { return fAsignado; }

   public void setFAsignado(Date fAsignado)
   {
      this.fAsignado = fAsignado;
   }
}

//==============================================================================

