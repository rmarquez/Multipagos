//==============================================================================
//===   pendientes_claro.java                        Build:2656
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class PendientesClaro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    pendienteId;
   private String     contrato;
   private String     suscriptor;
   private String     nit;
   private String     direccion;
   private String     barrio;
   private String     facturaInterna;
   private String     anio;
   private String     mes;
   private BigDecimal saldo;
   private String     departamento;
   private String     localidad;
   private String     telefono;
   private String     servicio;
   private String     movil1;
   private String     movil2;
   private Date       fechaInstalacion;
   private String     canal;
   private Integer    numLote;
   private Integer    usrId;

   //---------------------------------------------------------------------------

   public Integer getPendienteId() { return pendienteId; }

   public void setPendienteId(Integer pendienteId)
   {
      this.pendienteId = pendienteId;
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

   public String getBarrio() { return barrio; }

   public void setBarrio(String barrio)
   {
      this.barrio = barrio;
   }

   //---------------------------------------------------------------------------

   public String getFacturaInterna() { return facturaInterna; }

   public void setFacturaInterna(String facturaInterna)
   {
      this.facturaInterna = facturaInterna;
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

   public String getDepartamento() { return departamento; }

   public void setDepartamento(String departamento)
   {
      this.departamento = departamento;
   }

   //---------------------------------------------------------------------------

   public String getLocalidad() { return localidad; }

   public void setLocalidad(String localidad)
   {
      this.localidad = localidad;
   }

   //---------------------------------------------------------------------------

   public String getTelefono() { return telefono; }

   public void setTelefono(String telefono)
   {
      this.telefono = telefono;
   }

   //---------------------------------------------------------------------------

   public String getServicio() { return servicio; }

   public void setServicio(String servicio)
   {
      this.servicio = servicio;
   }

   //---------------------------------------------------------------------------

   public String getMovil1() { return movil1; }

   public void setMovil1(String movil1)
   {
      this.movil1 = movil1;
   }

   //---------------------------------------------------------------------------

   public String getMovil2() { return movil2; }

   public void setMovil2(String movil2)
   {
      this.movil2 = movil2;
   }

   //---------------------------------------------------------------------------

   public Date getFechaInstalacion() { return fechaInstalacion; }

   public void setFechaInstalacion(Date fechaInstalacion)
   {
      this.fechaInstalacion = fechaInstalacion;
   }

   //---------------------------------------------------------------------------

   public String getCanal() { return canal; }

   public void setCanal(String canal)
   {
      this.canal = canal;
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

