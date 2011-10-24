//==============================================================================
//===   rpt_cartera_x_departamento.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptCarteraXDepartamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cCarteraId;
   private String     dContrato;
   private String     dSuscriptor;
   private String     dNit;
   private String     dDireccion;
   private Integer    nBarrioId;
   private String     dFacturaInterna;
   private String     dNumeroFiscal;
   private String     dAnio;
   private String     dMes;
   private BigDecimal vSaldo;
   private Integer    nEstadoId;
   private Integer    nDepartamentoId;
   private Integer    nLocalidadId;
   private String     dCupon;
   private String     dTelefono;
   private BigDecimal vDescuento;
   private Integer    nServicioId;
   private String     dEmpleador;
   private String     dDireccionEmpleador;
   private Boolean    bPagado;
   private Date       fFechaPago;
   private Date       fFechaIngreso;

   //---------------------------------------------------------------------------

   public Integer getCCarteraId() { return cCarteraId; }

   public void setCCarteraId(Integer cCarteraId)
   {
      this.cCarteraId = cCarteraId;
   }

   //---------------------------------------------------------------------------

   public String getDContrato() { return dContrato; }

   public void setDContrato(String dContrato)
   {
      this.dContrato = dContrato;
   }

   //---------------------------------------------------------------------------

   public String getDSuscriptor() { return dSuscriptor; }

   public void setDSuscriptor(String dSuscriptor)
   {
      this.dSuscriptor = dSuscriptor;
   }

   //---------------------------------------------------------------------------

   public String getDNit() { return dNit; }

   public void setDNit(String dNit)
   {
      this.dNit = dNit;
   }

   //---------------------------------------------------------------------------

   public String getDDireccion() { return dDireccion; }

   public void setDDireccion(String dDireccion)
   {
      this.dDireccion = dDireccion;
   }

   //---------------------------------------------------------------------------

   public Integer getNBarrioId() { return nBarrioId; }

   public void setNBarrioId(Integer nBarrioId)
   {
      this.nBarrioId = nBarrioId;
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

   public String getDAnio() { return dAnio; }

   public void setDAnio(String dAnio)
   {
      this.dAnio = dAnio;
   }

   //---------------------------------------------------------------------------

   public String getDMes() { return dMes; }

   public void setDMes(String dMes)
   {
      this.dMes = dMes;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVSaldo() { return vSaldo; }

   public void setVSaldo(BigDecimal vSaldo)
   {
      this.vSaldo = vSaldo;
   }

   //---------------------------------------------------------------------------

   public Integer getNEstadoId() { return nEstadoId; }

   public void setNEstadoId(Integer nEstadoId)
   {
      this.nEstadoId = nEstadoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNDepartamentoId() { return nDepartamentoId; }

   public void setNDepartamentoId(Integer nDepartamentoId)
   {
      this.nDepartamentoId = nDepartamentoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNLocalidadId() { return nLocalidadId; }

   public void setNLocalidadId(Integer nLocalidadId)
   {
      this.nLocalidadId = nLocalidadId;
   }

   //---------------------------------------------------------------------------

   public String getDCupon() { return dCupon; }

   public void setDCupon(String dCupon)
   {
      this.dCupon = dCupon;
   }

   //---------------------------------------------------------------------------

   public String getDTelefono() { return dTelefono; }

   public void setDTelefono(String dTelefono)
   {
      this.dTelefono = dTelefono;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVDescuento() { return vDescuento; }

   public void setVDescuento(BigDecimal vDescuento)
   {
      this.vDescuento = vDescuento;
   }

   //---------------------------------------------------------------------------

   public Integer getNServicioId() { return nServicioId; }

   public void setNServicioId(Integer nServicioId)
   {
      this.nServicioId = nServicioId;
   }

   //---------------------------------------------------------------------------

   public String getDEmpleador() { return dEmpleador; }

   public void setDEmpleador(String dEmpleador)
   {
      this.dEmpleador = dEmpleador;
   }

   //---------------------------------------------------------------------------

   public String getDDireccionEmpleador() { return dDireccionEmpleador; }

   public void setDDireccionEmpleador(String dDireccionEmpleador)
   {
      this.dDireccionEmpleador = dDireccionEmpleador;
   }

   //---------------------------------------------------------------------------

   public Boolean getBPagado() { return bPagado; }

   public void setBPagado(Boolean bPagado)
   {
      this.bPagado = bPagado;
   }

   //---------------------------------------------------------------------------

   public Date getFFechaPago() { return fFechaPago; }

   public void setFFechaPago(Date fFechaPago)
   {
      this.fFechaPago = fFechaPago;
   }

   //---------------------------------------------------------------------------

   public Date getFFechaIngreso() { return fFechaIngreso; }

   public void setFFechaIngreso(Date fFechaIngreso)
   {
      this.fFechaIngreso = fFechaIngreso;
   }

   //---------------------------------------------------------------------------

   private Collection rptDetalleVisitasList = new ArrayList();

   public Collection getRptDetalleVisitasList() { return rptDetalleVisitasList;}

   public void setRptDetalleVisitasList(Collection c) { this.rptDetalleVisitasList = c;}

   public void addRptDetalleVisitas( RptDetalleVisitas rptDetalleVisitas )
   {
      rptDetalleVisitas.setCCarteraIdRef(this);
      rptDetalleVisitasList.add( rptDetalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection rptDetallePagosList = new ArrayList();

   public Collection getRptDetallePagosList() { return rptDetallePagosList;}

   public void setRptDetallePagosList(Collection c) { this.rptDetallePagosList = c;}

   public void addRptDetallePagos( RptDetallePagos rptDetallePagos )
   {
      rptDetallePagos.setCCarteraIdRef(this);
      rptDetallePagosList.add( rptDetallePagos );
   }

   //---------------------------------------------------------------------------

   private CatBarrio nBarrioIdRef;

   public CatBarrio getNBarrioIdRef() { return nBarrioIdRef; }

   public void setNBarrioIdRef(CatBarrio catBarrio)
   {
      this.nBarrioIdRef = catBarrio;
   }


   //---------------------------------------------------------------------------

   private CatEstadoCorte nEstadoIdRef;

   public CatEstadoCorte getNEstadoIdRef() { return nEstadoIdRef; }

   public void setNEstadoIdRef(CatEstadoCorte catEstadoCorte)
   {
      this.nEstadoIdRef = catEstadoCorte;
   }


   //---------------------------------------------------------------------------

   private CatDepartamento nDepartamentoIdRef;

   public CatDepartamento getNDepartamentoIdRef() { return nDepartamentoIdRef; }

   public void setNDepartamentoIdRef(CatDepartamento catDepartamento)
   {
      this.nDepartamentoIdRef = catDepartamento;
   }


   //---------------------------------------------------------------------------

   private CatLocalidad nLocalidadIdRef;

   public CatLocalidad getNLocalidadIdRef() { return nLocalidadIdRef; }

   public void setNLocalidadIdRef(CatLocalidad catLocalidad)
   {
      this.nLocalidadIdRef = catLocalidad;
   }


   //---------------------------------------------------------------------------

   private CatServicio nServicioIdRef;

   public CatServicio getNServicioIdRef() { return nServicioIdRef; }

   public void setNServicioIdRef(CatServicio catServicio)
   {
      this.nServicioIdRef = catServicio;
   }

}

//==============================================================================

