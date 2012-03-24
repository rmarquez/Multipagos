//==============================================================================
//===   asignar_visitas.java                        Build:2568
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AsignarVisitas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    asignarvId;
   private Integer    usrId;
   private Date       fecha;
   private Integer    colectorId;
   private Long[]     barrioId;
   private Integer    servicioId;
   private Date       fechaIni;
   private Date       fechaFin;
   private String     mes;

   //---------------------------------------------------------------------------

   public Integer getAsignarvId() { return asignarvId; }

   public void setAsignarvId(Integer asignarvId)
   {
      this.asignarvId = asignarvId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public Date getFecha() { return fecha; }

   public void setFecha(Date fecha)
   {
      this.fecha = fecha;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

  // public Integer getBarrioId() { return barrioId; }

  // public void setBarrioId(Integer barrioId)
   //{
    //  this.barrioId = barrioId;
   //}

   public Long[] getBarrioId() { return barrioId; }

   public void setBarrioId(Long[] barrioId) 
   {
       this.barrioId = barrioId;
   }   
  
   //---------------------------------------------------------------------------

   public Integer getServicioId() { return servicioId; }

   public void setServicioId(Integer servicioId)
   {
      this.servicioId = servicioId;
   }

   //---------------------------------------------------------------------------

   public Date getFechaIni() { return fechaIni; }

   public void setFechaIni(Date fechaIni)
   {
      this.fechaIni = fechaIni;
   }

   //---------------------------------------------------------------------------

   public Date getFechaFin() { return fechaFin; }

   public void setFechaFin(Date fechaFin)
   {
      this.fechaFin = fechaFin;
   }

   //---------------------------------------------------------------------------

   public String getMes() { return mes; }

   public void setMes(String mes)
   {
      this.mes = mes;
   }

   //---------------------------------------------------------------------------

   private AuthUser usrIdRef;

   public AuthUser getUsrIdRef() { return usrIdRef; }

   public void setUsrIdRef(AuthUser authUser)
   {
      this.usrIdRef = authUser;
   }


   //---------------------------------------------------------------------------

   private Colector colectorIdRef;

   public Colector getColectorIdRef() { return colectorIdRef; }

   public void setColectorIdRef(Colector colector)
   {
      this.colectorIdRef = colector;
   }


   //---------------------------------------------------------------------------

   private Barrio barrioIdRef;

   public Barrio getBarrioIdRef() { return barrioIdRef; }

   public void setBarrioIdRef(Barrio barrio)
   {
      this.barrioIdRef = barrio;
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

