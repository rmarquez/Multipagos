//==============================================================================
//===   cartera_x_departamento.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CarteraXDepartamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    carteraId;
   private String     contrato;
   private String     suscriptor;
   private String     nit;
   private String     direccion;
   private Integer    barrioId;
   private String     facturaInterna;
   private String     numeroFiscal;
   private String     anio;
   private String     mes;
   private BigDecimal saldo;
   private Integer    estadoId;
   private Integer    departamentoId;
   private Integer    localidadId;
   private String     cupon;
   private String     telefono;
   private BigDecimal descuento;
   private Integer    servicioId;
   private String     empleador;
   private String     direccionEmpleador;
   private Boolean    pagado;
   private Date       fechaPago;
   private Date       fechaIngreso;
   private String     cuenta;
   private String     conceptoDiferido;
   private Boolean    esDiferido;

   //---------------------------------------------------------------------------

   public Integer getCarteraId() { return carteraId; }

   public void setCarteraId(Integer carteraId)
   {
      this.carteraId = carteraId;
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

   public String getNit() { return nit; }

   public void setNit(String nit)
   {
      this.nit = nit;
   }

   //---------------------------------------------------------------------------

   public String getDireccion() { return direccion; }

   public void setDireccion(String direccion)
   {
      this.direccion = direccion;
   }

   //---------------------------------------------------------------------------

   public Integer getBarrioId() { return barrioId; }

   public void setBarrioId(Integer barrioId)
   {
      this.barrioId = barrioId;
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

   public Integer getEstadoId() { return estadoId; }

   public void setEstadoId(Integer estadoId)
   {
      this.estadoId = estadoId;
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

   public String getCupon() { return cupon; }

   public void setCupon(String cupon)
   {
      this.cupon = cupon;
   }

   //---------------------------------------------------------------------------

   public String getTelefono() { return telefono; }

   public void setTelefono(String telefono)
   {
      this.telefono = telefono;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDescuento() { return descuento; }

   public void setDescuento(BigDecimal descuento)
   {
      this.descuento = descuento;
   }

   //---------------------------------------------------------------------------

   public Integer getServicioId() { return servicioId; }

   public void setServicioId(Integer servicioId)
   {
      this.servicioId = servicioId;
   }

   //---------------------------------------------------------------------------

   public String getEmpleador() { return empleador; }

   public void setEmpleador(String empleador)
   {
      this.empleador = empleador;
   }

   //---------------------------------------------------------------------------

   public String getDireccionEmpleador() { return direccionEmpleador; }

   public void setDireccionEmpleador(String direccionEmpleador)
   {
      this.direccionEmpleador = direccionEmpleador;
   }

   //---------------------------------------------------------------------------

   public Boolean getPagado() { return pagado; }

   public void setPagado(Boolean pagado)
   {
      this.pagado = pagado;
   }

   //---------------------------------------------------------------------------

   public Date getFechaPago() { return fechaPago; }

   public void setFechaPago(Date fechaPago)
   {
      this.fechaPago = fechaPago;
   }

   //---------------------------------------------------------------------------

   public Date getFechaIngreso() { return fechaIngreso; }

   public void setFechaIngreso(Date fechaIngreso)
   {
      this.fechaIngreso = fechaIngreso;
   }

   //---------------------------------------------------------------------------

   public String getCuenta() { return cuenta; }

   public void setCuenta(String cuenta)
   {
      this.cuenta = cuenta;
   }

   //---------------------------------------------------------------------------

   public String getConceptoDiferido() { return conceptoDiferido; }

   public void setConceptoDiferido(String conceptoDiferido)
   {
      this.conceptoDiferido = conceptoDiferido;
   }

   //---------------------------------------------------------------------------

   public Boolean getEsDiferido() { return esDiferido; }

   public void setEsDiferido(Boolean esDiferido)
   {
      this.esDiferido = esDiferido;
   }

   //---------------------------------------------------------------------------

   private Collection detalleVisitasList = new ArrayList();

   public Collection getDetalleVisitasList() { return detalleVisitasList;}

   public void setDetalleVisitasList(Collection c) { this.detalleVisitasList = c;}

   public void addDetalleVisitas( DetalleVisitas detalleVisitas )
   {
      detalleVisitas.setCarteraIdRef(this);
      detalleVisitasList.add( detalleVisitas );
   }

   //---------------------------------------------------------------------------

   private Collection detallePagosList = new ArrayList();

   public Collection getDetallePagosList() { return detallePagosList;}

   public void setDetallePagosList(Collection c) { this.detallePagosList = c;}

   public void addDetallePagos( DetallePagos detallePagos )
   {
      detallePagos.setCarteraIdRef(this);
      detallePagosList.add( detallePagos );
   }

   //---------------------------------------------------------------------------

   private Barrio barrioIdRef;

   public Barrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(Barrio barrio)
   {
      this.barrioIdRef = barrio;
   }


   //---------------------------------------------------------------------------

   private EstadoCorte estadoIdRef;

   public EstadoCorte getEstadoIdRef() { return estadoIdRef; }

   public void setEstadoIdRef(EstadoCorte estadoCorte)
   {
      this.estadoIdRef = estadoCorte;
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

   private Servicio servicioIdRef;

   public Servicio getServicioIdRef() { return servicioIdRef; }

   public void setServicioIdRef(Servicio servicio)
   {
      this.servicioIdRef = servicio;
   }

}

//==============================================================================

