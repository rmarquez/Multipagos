//==============================================================================
//===   detalle_g_ibw.java                        Build:2675
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleGIbw implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    gestionId;
   private Integer    carteraId;
   private Integer    simboloId;
   private Integer    colectorId;
   private Integer    municipioId;
   private Integer    barrioId;
   private String     observaciones;
   private String     codigo;
   private Date       fechaGestion;
   private Integer    avisoCobro;
   private String     horaRegistro;
   private Date       fprogCobro;
   private Boolean    gestionLlamada;

   //---------------------------------------------------------------------------

   public Integer getGestionId() { return gestionId; }

   public void setGestionId(Integer gestionId)
   {
      this.gestionId = gestionId;
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

   public String getObservaciones() { return observaciones; }

   public void setObservaciones(String observaciones)
   {
      this.observaciones = observaciones;
   }

   //---------------------------------------------------------------------------

   public String getCodigo() { return codigo; }

   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   //---------------------------------------------------------------------------

   public Date getFechaGestion() { return fechaGestion; }

   public void setFechaGestion(Date fechaGestion)
   {
      this.fechaGestion = fechaGestion;
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

   public Boolean getGestionLlamada() { return gestionLlamada; }

   public void setGestionLlamada(Boolean gestionLlamada)
   {
      this.gestionLlamada = gestionLlamada;
   }

   //---------------------------------------------------------------------------

   private GestionIbw gestionIdRef;

   public GestionIbw getGestionIdRef() { return gestionIdRef; }

   public void setGestionIdRef(GestionIbw gestionIbw)
   {
      this.gestionIdRef = gestionIbw;
   }


   //---------------------------------------------------------------------------

   private CarteraIbw carteraIdRef;

   public CarteraIbw getCarteraIdRef() { return carteraIdRef; }

   public void setCarteraIdRef(CarteraIbw carteraIbw)
   {
      this.carteraIdRef = carteraIbw;
   }


   //---------------------------------------------------------------------------

   private SimboloIbw simboloIdRef;

   public SimboloIbw getSimboloIdRef() { return simboloIdRef; }

   public void setSimboloIdRef(SimboloIbw simboloIbw)
   {
      this.simboloIdRef = simboloIbw;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
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

}

//==============================================================================

