//==============================================================================
//===   detalle_ibw_pagos.java                        Build:2683
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleIbwPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pagoId;
   private Integer    carteraId;
   private Integer    colectorId;
   private Integer    municipioId;
   private Integer    barrioId;
   private String     codigo;
   private String     factura;
   private BigDecimal montoPago;
   private Date       fechaPago;
   private Integer    recibo;
   private String     horaRegistro;
   private String     observaciones;
   private BigDecimal salgoPagar;
   private Date       fechaFactura;
   private String     mes;
   private Boolean    pagoCk;
   private String     numeroCk;

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

   public Integer getMunicipioId() { return municipioId; }

   public void setMunicipioId(Integer municipioId)
   {
      this.municipioId = municipioId;
   }

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
   }

   //---------------------------------------------------------------------------

   public String getCodigo() { return codigo; }

   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   //---------------------------------------------------------------------------

   public String getFactura() { return factura; }

   public void setFactura(String factura)
   {
      this.factura = factura;
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

   public Date getFechaFactura() { return fechaFactura; }

   public void setFechaFactura(Date fechaFactura)
   {
      this.fechaFactura = fechaFactura;
   }

   //---------------------------------------------------------------------------

   public String getMes() { return mes; }

   public void setMes(String mes)
   {
      this.mes = mes;
   }

   //---------------------------------------------------------------------------

   public Boolean getPagoCk() { return pagoCk; }

   public void setPagoCk(Boolean pagoCk)
   {
      this.pagoCk = pagoCk;
   }

   //---------------------------------------------------------------------------

   public String getNumeroCk() { return numeroCk; }

   public void setNumeroCk(String numeroCk)
   {
      this.numeroCk = numeroCk;
   }

   //---------------------------------------------------------------------------

   private IbwPagos pagoIdRef;

   public IbwPagos getPagoIdRef() { return pagoIdRef; }

   public void setPagoIdRef(IbwPagos ibwPagos)
   {
      this.pagoIdRef = ibwPagos;
   }


   //---------------------------------------------------------------------------

   private CarteraIbw carteraIdRef;

   public CarteraIbw getCarteraIdRef() { return carteraIdRef; }

   public void setCarteraIdRef(CarteraIbw carteraIbw)
   {
      this.carteraIdRef = carteraIbw;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }


   //---------------------------------------------------------------------------

   private Municipio municipioIdRef;

   public Municipio getMunicipioIdRef() { return municipioIdRef; }

   public void setMunicipioIdRef(Municipio municipio)
   {
      this.municipioIdRef = municipio;
   }


   //---------------------------------------------------------------------------

   private IBarrio barrioIdRef;

   public IBarrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(IBarrio iBarrio)
   {
      this.barrioIdRef = iBarrio;
   }

}

//==============================================================================

