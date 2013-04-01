//==============================================================================
//===   detalle_g_banpro.java                        Build:2647
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class DetalleGBanpro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    gestionId;
   private Integer    tmpId;
   private Integer    simboloId;
   private Integer    colectorId;
   private Integer    ciudadCId;
   private Integer    departamentoId;
   private String     observaciones;
   private String     codigo;
   private Date       fechaGestion;
   private Integer    avisoCobro;
   private String     horaRegistro;
   private String     cuenta;
   private String     tarjeta;
   private Boolean    gestionLlamada;

   //---------------------------------------------------------------------------

   public Integer getGestionId() { return gestionId; }

   public void setGestionId(Integer gestionId)
   {
      this.gestionId = gestionId;
   }

   //---------------------------------------------------------------------------

   public Integer getTmpId() { return tmpId; }

   public void setTmpId(Integer tmpId)
   {
      this.tmpId = tmpId;
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

   public Integer getCiudadCId() { return ciudadCId; }

   public void setCiudadCId(Integer ciudadCId)
   {
      this.ciudadCId = ciudadCId;
   }

   //---------------------------------------------------------------------------

   public Integer getDepartamentoId() { return departamentoId; }

   public void setDepartamentoId(Integer departamentoId)
   {
      this.departamentoId = departamentoId;
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

   public String getCuenta() { return cuenta; }

   public void setCuenta(String cuenta)
   {
      this.cuenta = cuenta;
   }

   //---------------------------------------------------------------------------

   public String getTarjeta() { return tarjeta; }

   public void setTarjeta(String tarjeta)
   {
      this.tarjeta = tarjeta;
   }

   //---------------------------------------------------------------------------

   public Boolean getGestionLlamada() { return gestionLlamada; }

   public void setGestionLlamada(Boolean gestionLlamada)
   {
      this.gestionLlamada = gestionLlamada;
   }

   //---------------------------------------------------------------------------

   private GestionBanpro gestionIdRef;

   public GestionBanpro getGestionIdRef() { return gestionIdRef; }

   public void setGestionIdRef(GestionBanpro gestionBanpro)
   {
      this.gestionIdRef = gestionBanpro;
   }


   //---------------------------------------------------------------------------

   private CarteraBanpro tmpIdRef;

   public CarteraBanpro getTmpIdRef() { return tmpIdRef; }

   public void setTmpIdRef(CarteraBanpro carteraBanpro)
   {
      this.tmpIdRef = carteraBanpro;
   }


   //---------------------------------------------------------------------------

   private SimboloBanpro simboloIdRef;

   public SimboloBanpro getSimboloIdRef() { return simboloIdRef; }

   public void setSimboloIdRef(SimboloBanpro simboloBanpro)
   {
      this.simboloIdRef = simboloBanpro;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }


   //---------------------------------------------------------------------------

   private Ciudad ciudadCIdRef;

   public Ciudad getCiudadCIdRef() { return ciudadCIdRef; }

   public void setCiudadCIdRef(Ciudad ciudad)
   {
      this.ciudadCIdRef = ciudad;
   }


   //---------------------------------------------------------------------------

   private BDepartamento departamentoIdRef;

   public BDepartamento getDepartamentoIdRef() { return departamentoIdRef; }

   public void setDepartamentoIdRef(BDepartamento bDepartamento)
   {
      this.departamentoIdRef = bDepartamento;
   }

}

//==============================================================================

