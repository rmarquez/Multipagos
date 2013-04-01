//==============================================================================
//===   detalle_gestion.java                        Build:2647
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleGestion implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    gestionId;
   private Integer    cavonId;
   private Integer    simboloId;
   private Integer    colectorId;
   private Integer    localidadId;
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

   public Integer getCavonId() { return cavonId; }

   public void setCavonId(Integer cavonId)
   {
      this.cavonId = cavonId;
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

   private GestionAvon gestionIdRef;

   public GestionAvon getGestionIdRef() { return gestionIdRef; }

   public void setGestionIdRef(GestionAvon gestionAvon)
   {
      this.gestionIdRef = gestionAvon;
   }


   //---------------------------------------------------------------------------

   private CarteraAvon cavonIdRef;

   public CarteraAvon getCavonIdRef() { return cavonIdRef; }

   public void setCavonIdRef(CarteraAvon carteraAvon)
   {
      this.cavonIdRef = carteraAvon;
   }


   //---------------------------------------------------------------------------

   private SimboloAvon simboloIdRef;

   public SimboloAvon getSimboloIdRef() { return simboloIdRef; }

   public void setSimboloIdRef(SimboloAvon simboloAvon)
   {
      this.simboloIdRef = simboloAvon;
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

   private Barrio barrioIdRef;

   public Barrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(Barrio barrio)
   {
      this.barrioIdRef = barrio;
   }

}

//==============================================================================

