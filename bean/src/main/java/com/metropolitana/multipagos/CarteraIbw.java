//==============================================================================
//===   cartera_ibw.java                        Build:2679
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CarteraIbw implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    carteraId;
   private String     nombre;
   private String     apellido;
   private Integer    departamentoId;
   private Integer    municipioId;
   private Integer    barrioId;
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
   private Integer    tecnologiaId;
   private Date       fAsignado;
   private Boolean    pagado;
   private Date       fechaPago;

   //---------------------------------------------------------------------------

   public Integer getCarteraId() { return carteraId; }

   public void setCarteraId(Integer carteraId)
   {
      this.carteraId = carteraId;
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

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
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

   public Integer getTecnologiaId() { return tecnologiaId; }

   public void setTecnologiaId(Integer tecnologiaId)
   {
      this.tecnologiaId = tecnologiaId;
   }

   //---------------------------------------------------------------------------

   public Date getFAsignado() { return fAsignado; }

   public void setFAsignado(Date fAsignado)
   {
      this.fAsignado = fAsignado;
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

   private Collection detalleGIbwList = new ArrayList();

   public Collection getDetalleGIbwList() { return detalleGIbwList;}

   public void setDetalleGIbwList(Collection c) { this.detalleGIbwList = c;}

   public void addDetalleGIbw( DetalleGIbw detalleGIbw )
   {
      detalleGIbw.setCarteraIdRef(this);
      detalleGIbwList.add( detalleGIbw );
   }

   //---------------------------------------------------------------------------

   private Collection detalleIbwPagosList = new ArrayList();

   public Collection getDetalleIbwPagosList() { return detalleIbwPagosList;}

   public void setDetalleIbwPagosList(Collection c) { this.detalleIbwPagosList = c;}

   public void addDetalleIbwPagos( DetalleIbwPagos detalleIbwPagos )
   {
      detalleIbwPagos.setCarteraIdRef(this);
      detalleIbwPagosList.add( detalleIbwPagos );
   }

   //---------------------------------------------------------------------------

   private IDepartamento departamentoIdRef;

   public IDepartamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(IDepartamento iDepartamento)
   {
      this.departamentoIdRef = iDepartamento;
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


   //---------------------------------------------------------------------------

   private Tecnologia tecnologiaIdRef;

   public Tecnologia getTecnologiaIdRef() { return tecnologiaIdRef; }

   public void setTecnologiaIdRef(Tecnologia tecnologia)
   {
      this.tecnologiaIdRef = tecnologia;
   }

}

//==============================================================================

