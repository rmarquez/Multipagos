//==============================================================================
//===   rpt_detalle_pagos.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptDetallePagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cPagoId;
   private Integer    cLocalidadId;
   private Integer    cCarteraId;
   private Integer    cServicioId;
   private Integer    cColectorId;
   private String     dNumeroContrato;
   private String     dFacturaInterna;
   private String     dNumeroFiscal;
   private String     dCupon;
   private BigDecimal vMontoPago;
   private Date       fFechaPago;
   private Integer    nRecibo;
   private String     dHoraRegistro;

   //---------------------------------------------------------------------------

   public Integer getCPagoId() { return cPagoId; }

   public void setCPagoId(Integer cPagoId)
   {
      this.cPagoId = cPagoId;
   }

   //---------------------------------------------------------------------------

   public Integer getCLocalidadId() { return cLocalidadId; }

   public void setCLocalidadId(Integer cLocalidadId)
   {
      this.cLocalidadId = cLocalidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getCCarteraId() { return cCarteraId; }

   public void setCCarteraId(Integer cCarteraId)
   {
      this.cCarteraId = cCarteraId;
   }

   //---------------------------------------------------------------------------

   public Integer getCServicioId() { return cServicioId; }

   public void setCServicioId(Integer cServicioId)
   {
      this.cServicioId = cServicioId;
   }

   //---------------------------------------------------------------------------

   public Integer getCColectorId() { return cColectorId; }

   public void setCColectorId(Integer cColectorId)
   {
      this.cColectorId = cColectorId;
   }

   //---------------------------------------------------------------------------

   public String getDNumeroContrato() { return dNumeroContrato; }

   public void setDNumeroContrato(String dNumeroContrato)
   {
      this.dNumeroContrato = dNumeroContrato;
   }

   //---------------------------------------------------------------------------

   public String getDFacturaInterna() { return dFacturaInterna; }

   public void setDFacturaInterna(String dFacturaInterna)
   {
      this.dFacturaInterna = dFacturaInterna;
   }

   //---------------------------------------------------------------------------

   public String getDNumeroFiscal() { return dNumeroFiscal; }

   public void setDNumeroFiscal(String dNumeroFiscal)
   {
      this.dNumeroFiscal = dNumeroFiscal;
   }

   //---------------------------------------------------------------------------

   public String getDCupon() { return dCupon; }

   public void setDCupon(String dCupon)
   {
      this.dCupon = dCupon;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVMontoPago() { return vMontoPago; }

   public void setVMontoPago(BigDecimal vMontoPago)
   {
      this.vMontoPago = vMontoPago;
   }

   //---------------------------------------------------------------------------

   public Date getFFechaPago() { return fFechaPago; }

   public void setFFechaPago(Date fFechaPago)
   {
      this.fFechaPago = fFechaPago;
   }

   //---------------------------------------------------------------------------

   public Integer getNRecibo() { return nRecibo; }

   public void setNRecibo(Integer nRecibo)
   {
      this.nRecibo = nRecibo;
   }

   //---------------------------------------------------------------------------

   public String getDHoraRegistro() { return dHoraRegistro; }

   public void setDHoraRegistro(String dHoraRegistro)
   {
      this.dHoraRegistro = dHoraRegistro;
   }

   //---------------------------------------------------------------------------

   private RptPagos cPagoIdRef;

   public RptPagos getCPagoIdRef() { return cPagoIdRef; }

   public void setCPagoIdRef(RptPagos rptPagos)
   {
      this.cPagoIdRef = rptPagos;
   }


   //---------------------------------------------------------------------------

   private CatLocalidad cLocalidadIdRef;

   public CatLocalidad getCLocalidadIdRef() { return cLocalidadIdRef; }

   public void setCLocalidadIdRef(CatLocalidad catLocalidad)
   {
      this.cLocalidadIdRef = catLocalidad;
   }


   //---------------------------------------------------------------------------

   private RptCarteraXDepartamento cCarteraIdRef;

   public RptCarteraXDepartamento getCCarteraIdRef() { return cCarteraIdRef; }

   public void setCCarteraIdRef(RptCarteraXDepartamento rptCarteraXDepartamento)
   {
      this.cCarteraIdRef = rptCarteraXDepartamento;
   }


   //---------------------------------------------------------------------------

   private CatServicio cServicioIdRef;

   public CatServicio getCServicioIdRef() { return cServicioIdRef; }

   public void setCServicioIdRef(CatServicio catServicio)
   {
      this.cServicioIdRef = catServicio;
   }


   //---------------------------------------------------------------------------

   private CatColector cColectorIdRef;

   public CatColector getCColectorIdRef() { return cColectorIdRef; }

   public void setCColectorIdRef(CatColector catColector)
   {
      this.cColectorIdRef = catColector;
   }

}

//==============================================================================

