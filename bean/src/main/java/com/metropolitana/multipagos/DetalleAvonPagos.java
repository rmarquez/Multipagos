//==============================================================================
//===   detalle_avon_pagos.java                        Build:2583
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleAvonPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pagoId;
   private Integer    cavonId;
   private Integer    colectorId;
   private Integer    localidadId;
   private Integer    barrioId;
   private String     codigo;
   private String     factura;
   private BigDecimal montoPago;
   private Date       fechaPago;
   private Integer    recibo;
   private String     horaRegistro;
   private String     observaciones;
   private BigDecimal salgoPagar;
   private String     anio;
   private String     mes;

   //---------------------------------------------------------------------------

   public Integer getPagoId() { return pagoId; }

   public void setPagoId(Integer pagoId)
   {
      this.pagoId = pagoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCavonId() { return cavonId; }

   public void setCavonId(Integer cavonId)
   {
      this.cavonId = cavonId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public Integer getLocalidadId() { return localidadId; }

   public void setLocalidadId(Integer localidadId)
   {
      this.localidadId = localidadId;
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

   private AvonPagos pagoIdRef;

   public AvonPagos getPagoIdRef() { return pagoIdRef; }

   public void setPagoIdRef(AvonPagos avonPagos)
   {
      this.pagoIdRef = avonPagos;
   }


   //---------------------------------------------------------------------------

   private CarteraAvon cavonIdRef;

   public CarteraAvon getCavonIdRef() { return cavonIdRef; }

   public void setCavonIdRef(CarteraAvon carteraAvon)
   {
      this.cavonIdRef = carteraAvon;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }


   //---------------------------------------------------------------------------

   private Localidad localidadIdRef;

   public Localidad getLocalidadIdRef() { return localidadIdRef; }

   public void setLocalidadIdRef(Localidad localidad)
   {
      this.localidadIdRef = localidad;
   }


   //---------------------------------------------------------------------------

   private Barrio barrioIdRef;

   public Barrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(Barrio barrio)
   {
      this.barrioIdRef = barrio;
   }

}

//==============================================================================

