//==============================================================================
//===   detalle_visitas.java                        Build:2548
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
   private Integer    localidadId;
   private Integer    servicioId;
   private String     observaciones;
   private String     numeroContrato;
   private Date       fechaVisita;
   private Integer    avisoCobro;
   private String     horaRegistro;
   private Date       fprogCobro;

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

   public Integer getLocalidadId() { return localidadId; }

   public void setLocalidadId(Integer localidadId)
   {
      this.localidadId = localidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getServicioId() { return servicioId; }

   public void setServicioId(Integer servicioId)
   {
      this.servicioId = servicioId;
   }

   //---------------------------------------------------------------------------

   public String getObservaciones() { return observaciones; }

   public void setObservaciones(String observaciones)
   {
      this.observaciones = observaciones;
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

