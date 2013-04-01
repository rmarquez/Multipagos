//==============================================================================
//===   cartera_avon.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CarteraAvon implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cavonId;
   private String     codigo;
   private String     consejero;
   private String     cedula;
   private String     direccion;
   private Integer    departamentoId;
   private Integer    localidadId;
   private Integer    barrioId;
   private String     telefono;
   private String     zona;
   private String     factura;
   private BigDecimal saldo;
   private String     mes;
   private String     anio;
   private String     agencia;
   private String     campania;
   private Date       fechaPago;
   private Date       fechaAsignado;
   private Date       fechaBaja;
   private Date       fechaFactura;
   private Boolean    pagado;

   //---------------------------------------------------------------------------

   public Integer getCavonId() { return cavonId; }

   public void setCavonId(Integer cavonId)
   {
      this.cavonId = cavonId;
   }

   //---------------------------------------------------------------------------

   public String getCodigo() { return codigo; }

   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   //---------------------------------------------------------------------------

   public String getConsejero() { return consejero; }

   public void setConsejero(String consejero)
   {
      this.consejero = consejero;
   }

   //---------------------------------------------------------------------------

   public String getCedula() { return cedula; }

   public void setCedula(String cedula)
   {
      this.cedula = cedula;
   }

   //---------------------------------------------------------------------------

   public String getDireccion() { return direccion; }

   public void setDireccion(String direccion)
   {
      this.direccion = direccion;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
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

   public String getTelefono() { return telefono; }

   public void setTelefono(String telefono)
   {
      this.telefono = telefono;
   }

   //---------------------------------------------------------------------------

   public String getZona() { return zona; }

   public void setZona(String zona)
   {
      this.zona = zona;
   }

   //---------------------------------------------------------------------------

   public String getFactura() { return factura; }

   public void setFactura(String factura)
   {
      this.factura = factura;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getSaldo() { return saldo; }

   public void setSaldo(BigDecimal saldo)
   {
      this.saldo = saldo;
   }

   //---------------------------------------------------------------------------

   public String getMes() { return mes; }

   public void setMes(String mes)
   {
      this.mes = mes;
   }

   //---------------------------------------------------------------------------

   public String getAnio() { return anio; }

   public void setAnio(String anio)
   {
      this.anio = anio;
   }

   //---------------------------------------------------------------------------

   public String getAgencia() { return agencia; }

   public void setAgencia(String agencia)
   {
      this.agencia = agencia;
   }

   //---------------------------------------------------------------------------

   public String getCampania() { return campania; }

   public void setCampania(String campania)
   {
      this.campania = campania;
   }

   //---------------------------------------------------------------------------

   public Date getFechaPago() { return fechaPago; }

   public void setFechaPago(Date fechaPago)
   {
      this.fechaPago = fechaPago;
   }

   //---------------------------------------------------------------------------

   public Date getFechaAsignado() { return fechaAsignado; }

   public void setFechaAsignado(Date fechaAsignado)
   {
      this.fechaAsignado = fechaAsignado;
   }

   //---------------------------------------------------------------------------

   public Date getFechaBaja() { return fechaBaja; }

   public void setFechaBaja(Date fechaBaja)
   {
      this.fechaBaja = fechaBaja;
   }

   //---------------------------------------------------------------------------

   public Date getFechaFactura() { return fechaFactura; }

   public void setFechaFactura(Date fechaFactura)
   {
      this.fechaFactura = fechaFactura;
   }

   //---------------------------------------------------------------------------

   public Boolean getPagado() { return pagado; }

   public void setPagado(Boolean pagado)
   {
      this.pagado = pagado;
   }

   //---------------------------------------------------------------------------

   private Collection detalleGestionList = new ArrayList();

   public Collection getDetalleGestionList() { return detalleGestionList;}

   public void setDetalleGestionList(Collection c) { this.detalleGestionList = c;}

   public void addDetalleGestion( DetalleGestion detalleGestion )
   {
      detalleGestion.setCavonIdRef(this);
      detalleGestionList.add( detalleGestion );
   }

   //---------------------------------------------------------------------------

   private Collection detalleAvonPagosList = new ArrayList();

   public Collection getDetalleAvonPagosList() { return detalleAvonPagosList;}

   public void setDetalleAvonPagosList(Collection c) { this.detalleAvonPagosList = c;}

   public void addDetalleAvonPagos( DetalleAvonPagos detalleAvonPagos )
   {
      detalleAvonPagos.setCavonIdRef(this);
      detalleAvonPagosList.add( detalleAvonPagos );
   }

   //---------------------------------------------------------------------------

   private Departamento departamentoIdRef;

   public Departamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(Departamento departamento)
   {
      this.departamentoIdRef = departamento;
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

