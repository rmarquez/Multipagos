//==============================================================================
//===   tmp_ibw.java                        Build:2671
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class TmpIbw implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tmpId;
   private String     nombre;
   private String     apellido;
   private String     departamento;
   private String     municipio;
   private String     barrio;
   private String     direccion;
   private String     telefonoC;
   private String     celular;
   private String     telefonoT;
   private String     codCliente;
   private String     facturaIbw;
   private String     serie;
   private Date       fechaFactura;
   private Date       fechaVence;
   private BigDecimal saldoDol;
   private BigDecimal totalSaldoDol;
   private String     tecnologia;
   private Date       fAsignado;

   //---------------------------------------------------------------------------

   public Integer getTmpId() { return tmpId; }

   public void setTmpId(Integer tmpId)
   {
      this.tmpId = tmpId;
   }

   //---------------------------------------------------------------------------

   public String getNombre() { return nombre; }

   public void setNombre(String nombre)
   {
      this.nombre = nombre;
   }

   //---------------------------------------------------------------------------

   public String getApellido() { return apellido; }

   public void setApellido(String apellido)
   {
      this.apellido = apellido;
   }

   //---------------------------------------------------------------------------

   public String getDepartamento() { return departamento; }

   public void setDepartamento(String departamento)
   {
      this.departamento = departamento;
   }

   //---------------------------------------------------------------------------

   public String getMunicipio() { return municipio; }

   public void setMunicipio(String municipio)
   {
      this.municipio = municipio;
   }

   //---------------------------------------------------------------------------

   public String getBarrio() { return barrio; }

   public void setBarrio(String barrio)
   {
      this.barrio = barrio;
   }

   //---------------------------------------------------------------------------

   public String getDireccion() { return direccion; }

   public void setDireccion(String direccion)
   {
      this.direccion = direccion;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoC() { return telefonoC; }

   public void setTelefonoC(String telefonoC)
   {
      this.telefonoC = telefonoC;
   }

   //---------------------------------------------------------------------------

   public String getCelular() { return celular; }

   public void setCelular(String celular)
   {
      this.celular = celular;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoT() { return telefonoT; }

   public void setTelefonoT(String telefonoT)
   {
      this.telefonoT = telefonoT;
   }

   //---------------------------------------------------------------------------

   public String getCodCliente() { return codCliente; }

   public void setCodCliente(String codCliente)
   {
      this.codCliente = codCliente;
   }

   //---------------------------------------------------------------------------

   public String getFacturaIbw() { return facturaIbw; }

   public void setFacturaIbw(String facturaIbw)
   {
      this.facturaIbw = facturaIbw;
   }

   //---------------------------------------------------------------------------

   public String getSerie() { return serie; }

   public void setSerie(String serie)
   {
      this.serie = serie;
   }

   //---------------------------------------------------------------------------

   public Date getFechaFactura() { return fechaFactura; }

   public void setFechaFactura(Date fechaFactura)
   {
      this.fechaFactura = fechaFactura;
   }

   //---------------------------------------------------------------------------

   public Date getFechaVence() { return fechaVence; }

   public void setFechaVence(Date fechaVence)
   {
      this.fechaVence = fechaVence;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getSaldoDol() { return saldoDol; }

   public void setSaldoDol(BigDecimal saldoDol)
   {
      this.saldoDol = saldoDol;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalSaldoDol() { return totalSaldoDol; }

   public void setTotalSaldoDol(BigDecimal totalSaldoDol)
   {
      this.totalSaldoDol = totalSaldoDol;
   }

   //---------------------------------------------------------------------------

   public String getTecnologia() { return tecnologia; }

   public void setTecnologia(String tecnologia)
   {
      this.tecnologia = tecnologia;
   }

   //---------------------------------------------------------------------------

   public Date getFAsignado() { return fAsignado; }

   public void setFAsignado(Date fAsignado)
   {
      this.fAsignado = fAsignado;
   }
}

//==============================================================================

