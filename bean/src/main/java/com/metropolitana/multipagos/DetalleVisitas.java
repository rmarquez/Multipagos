//==============================================================================
//===   detalle_visitas.java                        Build:2525
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleVisitas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    visitaId;
   private Integer    carteraId;
   private Integer    simboloId;
   private Integer    colectorId;
   private String     localidadNombre;
   private String     servicioNombre;
   private String     numeroContrato;
   private Date       fechaVisita;
   private Integer    avisoCobro;
   private String     horaRegistro;
   private Date       fprogCobro;
   private Boolean    encontroCliente;

   //---------------------------------------------------------------------------

   public Integer getVisitaId() { return visitaId; }

   public void setVisitaId(Integer visitaId)
   {
      this.visitaId = visitaId;
   }

   //---------------------------------------------------------------------------

   public Integer getCarteraId() { return carteraId; }

   public void setCarteraId(Integer carteraId)
   {
      this.carteraId = carteraId;
   }

   //---------------------------------------------------------------------------

   public Integer getSimboloId() { return simboloId; }

   public void setSimboloId(Integer simboloId)
   {
      this.simboloId = simboloId;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public String getLocalidadNombre() { return localidadNombre; }

   public void setLocalidadNombre(String localidadNombre)
   {
      this.localidadNombre = localidadNombre;
   }

   //---------------------------------------------------------------------------

   public String getServicioNombre() { return servicioNombre; }

   public void setServicioNombre(String servicioNombre)
   {
      this.servicioNombre = servicioNombre;
   }

   //---------------------------------------------------------------------------

   public String getNumeroContrato() { return numeroContrato; }

   public void setNumeroContrato(String numeroContrato)
   {
      this.numeroContrato = numeroContrato;
   }

   //---------------------------------------------------------------------------

   public Date getFechaVisita() { return fechaVisita; }

   public void setFechaVisita(Date fechaVisita)
   {
      this.fechaVisita = fechaVisita;
   }

   //---------------------------------------------------------------------------

   public Integer getAvisoCobro() { return avisoCobro; }

   public void setAvisoCobro(Integer avisoCobro)
   {
      this.avisoCobro = avisoCobro;
   }

   //---------------------------------------------------------------------------

   public String getHoraRegistro() { return horaRegistro; }

   public void setHoraRegistro(String horaRegistro)
   {
      this.horaRegistro = horaRegistro;
   }

   //---------------------------------------------------------------------------

   public Date getFprogCobro() { return fprogCobro; }

   public void setFprogCobro(Date fprogCobro)
   {
      this.fprogCobro = fprogCobro;
   }

   //---------------------------------------------------------------------------

   public Boolean getEncontroCliente() { return encontroCliente; }

   public void setEncontroCliente(Boolean encontroCliente)
   {
      this.encontroCliente = encontroCliente;
   }

   //---------------------------------------------------------------------------

   private Visitas visitaIdRef;

   public Visitas getVisitaIdRef() { return visitaIdRef; }

   public void setVisitaIdRef(Visitas visitas)
   {
      this.visitaIdRef = visitas;
   }


   //---------------------------------------------------------------------------

   private CarteraXDepartamento carteraIdRef;

   public CarteraXDepartamento getCarteraIdRef() { return carteraIdRef; }

   public void setCarteraIdRef(CarteraXDepartamento carteraXDepartamento)
   {
      this.carteraIdRef = carteraXDepartamento;
   }


   //---------------------------------------------------------------------------

   private Simbolo simboloIdRef;

   public Simbolo getSimboloIdRef() { return simboloIdRef; }

   public void setSimboloIdRef(Simbolo simbolo)
   {
      this.simboloIdRef = simbolo;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }

}

//==============================================================================

