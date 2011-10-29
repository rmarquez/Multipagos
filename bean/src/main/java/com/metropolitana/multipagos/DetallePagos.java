//==============================================================================
//===   detalle_pagos.java                        Build:2526
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetallePagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pagoId;
   private Integer    carteraId;
   private Integer    colectorId;
   private String     numeroContrato;
   private String     facturaInterna;
   private String     numeroFiscal;
   private String     cupon;
   private BigDecimal montoPago;
   private Date       fechaPago;
   private Integer    recibo;
   private String     horaRegistro;
   private String     localidadNombre;
   private String     servicioNombre;
   private String     observaciones;
   private BigDecimal salgoPagar;

   //---------------------------------------------------------------------------

   public Integer getPagoId() { return pagoId; }

   public void setPagoId(Integer pagoId)
   {
      this.pagoId = pagoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCarteraId() { return carteraId; }

   public void setCarteraId(Integer carteraId)
   {
      this.carteraId = carteraId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public String getNumeroContrato() { return numeroContrato; }

   public void setNumeroContrato(String numeroContrato)
   {
      this.numeroContrato = numeroContrato;
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

   public String getCupon() { return cupon; }

   public void setCupon(String cupon)
   {
      this.cupon = cupon;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getMontoPago() { return montoPago; }

   public void setMontoPago(BigDecimal montoPago)
   {
      this.montoPago = montoPago;
   }

   //---------------------------------------------------------------------------

   public Date getFechaPago() { return fechaPago; }

   public void setFechaPago(Date fechaPago)
   {
      this.fechaPago = fechaPago;
   }

   //---------------------------------------------------------------------------

   public Integer getRecibo() { return recibo; }

   public void setRecibo(Integer recibo)
   {
      this.recibo = recibo;
   }

   //---------------------------------------------------------------------------

   public String getHoraRegistro() { return horaRegistro; }

   public void setHoraRegistro(String horaRegistro)
   {
      this.horaRegistro = horaRegistro;
   }

   //---------------------------------------------------------------------------

   public String getLocalidadNombre() { return localidadNombre; }

   public void setLocalidadNombre(String localidadNombre)
   {
      this.localidadNombre = localidadNombre;
   }

   //---------------------------------------------------------------------------

   public String getServicioNombre() { return servicioNombre; }

   public void setServicioNombre(String servicioNombre)
   {
      this.servicioNombre = servicioNombre;
   }

   //---------------------------------------------------------------------------

   public String getObservaciones() { return observaciones; }

   public void setObservaciones(String observaciones)
   {
      this.observaciones = observaciones;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getSalgoPagar() { return salgoPagar; }

   public void setSalgoPagar(BigDecimal salgoPagar)
   {
      this.salgoPagar = salgoPagar;
   }

   //---------------------------------------------------------------------------

   private Pagos pagoIdRef;

   public Pagos getPagoIdRef() { return pagoIdRef; }

   public void setPagoIdRef(Pagos pagos)
   {
      this.pagoIdRef = pagos;
   }


   //---------------------------------------------------------------------------

   private CarteraXDepartamento carteraIdRef;

   public CarteraXDepartamento getCarteraIdRef() { return carteraIdRef; }

   public void setCarteraIdRef(CarteraXDepartamento carteraXDepartamento)
   {
      this.carteraIdRef = carteraXDepartamento;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }

}

//==============================================================================

