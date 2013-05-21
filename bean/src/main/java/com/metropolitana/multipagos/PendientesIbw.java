//==============================================================================
//===   pendientes_ibw.java                        Build:2681
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class PendientesIbw implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pendienteId;
   private String     nombre;
   private String     apellido;
   private String     departamento;
   private String     municipio;
   private String     barrio;
   private String     direccion;
   private String     codCliente;
   private String     facturaIbw;
   private Date       fechaFactura;
   private Date       fechaVence;
   private String     tecnologia;
   private BigDecimal saldoDol;
   private String     telefonoC;
   private Integer    numLote;
   private Integer    usrId;

   //---------------------------------------------------------------------------

   public Integer getPendienteId() { return pendienteId; }

   public void setPendienteId(Integer pendienteId)
   {
      this.pendienteId = pendienteId;
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

   public String getTecnologia() { return tecnologia; }

   public void setTecnologia(String tecnologia)
   {
      this.tecnologia = tecnologia;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getSaldoDol() { return saldoDol; }

   public void setSaldoDol(BigDecimal saldoDol)
   {
      this.saldoDol = saldoDol;
   }

   //---------------------------------------------------------------------------

   public String getTelefonoC() { return telefonoC; }

   public void setTelefonoC(String telefonoC)
   {
      this.telefonoC = telefonoC;
   }

   //---------------------------------------------------------------------------

   public Integer getNumLote() { return numLote; }

   public void setNumLote(Integer numLote)
   {
      this.numLote = numLote;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   private AuthUser usrIdRef;

   public AuthUser getUsrIdRef() { return usrIdRef; }

   public void setUsrIdRef(AuthUser authUser)
   {
      this.usrIdRef = authUser;
   }

}

//==============================================================================

