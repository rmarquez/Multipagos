//==============================================================================
//===   cartera_banpro.java                        Build:2643
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class CarteraBanpro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    tmpId;
   private String     codCliente;
   private String     cuenta;
   private String     nombre;
   private String     tarjeta;
   private String     producto;
   private BigDecimal antiguedadAnios;
   private BigDecimal deudaCor;
   private BigDecimal deudaDol;
   private BigDecimal totalDeudaDol;
   private String     empresaBa;
   private String     ubicacionBa;
   private String     cedula;
   private String     direccionC;
   private Integer    barrioC;
   private Integer    departamentoC;
   private Integer    ciudadC;
   private String     direccionT;
   private Integer    departamentoT;
   private Integer    ciudadT;
   private String     telefonoC;
   private String     telefonoT;
   private String     telefonoO;
   private String     codFiador;
   private String     nombreFiador;
   private String     telefonoFiador;
   private String     multCodigo;
   private Date       fechaAsignado;

   //---------------------------------------------------------------------------

   public Integer getTmpId() { return tmpId; }

   public void setTmpId(Integer tmpId)
   {
      this.tmpId = tmpId;
   }

   //---------------------------------------------------------------------------

   public String getCodCliente() { return codCliente; }

   public void setCodCliente(String codCliente)
   {
      this.codCliente = codCliente;
   }

   //---------------------------------------------------------------------------

   public String getCuenta() { return cuenta; }

   public void setCuenta(String cuenta)
   {
      this.cuenta = cuenta;
   }

   //---------------------------------------------------------------------------

   public String getNombre() { return nombre; }

   public void setNombre(String nombre)
   {
      this.nombre = nombre;
   }

   //---------------------------------------------------------------------------

   public String getTarjeta() { return tarjeta; }

   public void setTarjeta(String tarjeta)
   {
      this.tarjeta = tarjeta;
   }

   //---------------------------------------------------------------------------

   public String getProducto() { return producto; }

   public void setProducto(String producto)
   {
      this.producto = producto;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getAntiguedadAnios() { return antiguedadAnios; }

   public void setAntiguedadAnios(BigDecimal antiguedadAnios)
   {
      this.antiguedadAnios = antiguedadAnios;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDeudaCor() { return deudaCor; }

   public void setDeudaCor(BigDecimal deudaCor)
   {
      this.deudaCor = deudaCor;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDeudaDol() { return deudaDol; }

   public void setDeudaDol(BigDecimal deudaDol)
   {
      this.deudaDol = deudaDol;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalDeudaDol() { return totalDeudaDol; }

   public void setTotalDeudaDol(BigDecimal totalDeudaDol)
   {
      this.totalDeudaDol = totalDeudaDol;
   }

   //---------------------------------------------------------------------------

   public String getEmpresaBa() { return empresaBa; }

   public void setEmpresaBa(String empresaBa)
   {
      this.empresaBa = empresaBa;
   }

   //---------------------------------------------------------------------------

   public String getUbicacionBa() { return ubicacionBa; }

   public void setUbicacionBa(String ubicacionBa)
   {
      this.ubicacionBa = ubicacionBa;
   }

   //---------------------------------------------------------------------------

   public String getCedula() { return cedula; }

   public void setCedula(String cedula)
   {
      this.cedula = cedula;
   }

   //---------------------------------------------------------------------------

   public String getDireccionC() { return direccionC; }

   public void setDireccionC(String direccionC)
   {
      this.direccionC = direccionC;
   }

   //---------------------------------------------------------------------------

   public Integer getBarrioC() { return barrioC; }

   public void setBarrioC(Integer barrioC)
   {
      this.barrioC = barrioC;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoC() { return departamentoC; }

   public void setDepartamentoC(Integer departamentoC)
   {
      this.departamentoC = departamentoC;
   }

   //---------------------------------------------------------------------------

   public Integer getCiudadC() { return ciudadC; }

   public void setCiudadC(Integer ciudadC)
   {
      this.ciudadC = ciudadC;
   }

   //---------------------------------------------------------------------------

   public String getDireccionT() { return direccionT; }

   public void setDireccionT(String direccionT)
   {
      this.direccionT = direccionT;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoT() { return departamentoT; }

   public void setDepartamentoT(Integer departamentoT)
   {
      this.departamentoT = departamentoT;
   }

   //---------------------------------------------------------------------------

   public Integer getCiudadT() { return ciudadT; }

   public void setCiudadT(Integer ciudadT)
   {
      this.ciudadT = ciudadT;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoC() { return telefonoC; }

   public void setTelefonoC(String telefonoC)
   {
      this.telefonoC = telefonoC;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoT() { return telefonoT; }

   public void setTelefonoT(String telefonoT)
   {
      this.telefonoT = telefonoT;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoO() { return telefonoO; }

   public void setTelefonoO(String telefonoO)
   {
      this.telefonoO = telefonoO;
   }

   //---------------------------------------------------------------------------

   public String getCodFiador() { return codFiador; }

   public void setCodFiador(String codFiador)
   {
      this.codFiador = codFiador;
   }

   //---------------------------------------------------------------------------

   public String getNombreFiador() { return nombreFiador; }

   public void setNombreFiador(String nombreFiador)
   {
      this.nombreFiador = nombreFiador;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoFiador() { return telefonoFiador; }

   public void setTelefonoFiador(String telefonoFiador)
   {
      this.telefonoFiador = telefonoFiador;
   }

   //---------------------------------------------------------------------------

   public String getMultCodigo() { return multCodigo; }

   public void setMultCodigo(String multCodigo)
   {
      this.multCodigo = multCodigo;
   }

   //---------------------------------------------------------------------------

   public Date getFechaAsignado() { return fechaAsignado; }

   public void setFechaAsignado(Date fechaAsignado)
   {
      this.fechaAsignado = fechaAsignado;
   }

   //---------------------------------------------------------------------------

   private Collection detalleGBanproList = new ArrayList();

   public Collection getDetalleGBanproList() { return detalleGBanproList;}

   public void setDetalleGBanproList(Collection c) { this.detalleGBanproList = c;}

   public void addDetalleGBanpro( DetalleGBanpro detalleGBanpro )
   {
      detalleGBanpro.setTmpIdRef(this);
      detalleGBanproList.add( detalleGBanpro );
   }

   //---------------------------------------------------------------------------

   private Collection arregloDeudaList = new ArrayList();

   public Collection getArregloDeudaList() { return arregloDeudaList;}

   public void setArregloDeudaList(Collection c) { this.arregloDeudaList = c;}

   public void addArregloDeuda( ArregloDeuda arregloDeuda )
   {
      arregloDeuda.setTmpIdRef(this);
      arregloDeudaList.add( arregloDeuda );
   }

   //---------------------------------------------------------------------------

   private BBarrio barrioCRef;

   public BBarrio getBarrioCRef() { return barrioCRef; }

   public void setBarrioCRef(BBarrio bBarrio)
   {
      this.barrioCRef = bBarrio;
   }


   //---------------------------------------------------------------------------

   private BDepartamento departamentoCRef;

   public BDepartamento getDepartamentoCRef() { return departamentoCRef; }

   public void setDepartamentoCRef(BDepartamento bDepartamento)
   {
      this.departamentoCRef = bDepartamento;
   }


   //---------------------------------------------------------------------------

   private Ciudad ciudadCRef;

   public Ciudad getCiudadCRef() { return ciudadCRef; }

   public void setCiudadCRef(Ciudad ciudad)
   {
      this.ciudadCRef = ciudad;
   }


   //---------------------------------------------------------------------------

   private BDepartamento departamentoTRef;

   public BDepartamento getDepartamentoTRef() { return departamentoTRef; }

   public void setDepartamentoTRef(BDepartamento bDepartamento)
   {
      this.departamentoTRef = bDepartamento;
   }


   //---------------------------------------------------------------------------

   private Ciudad ciudadTRef;

   public Ciudad getCiudadTRef() { return ciudadTRef; }

   public void setCiudadTRef(Ciudad ciudad)
   {
      this.ciudadTRef = ciudad;
   }

}

//==============================================================================

