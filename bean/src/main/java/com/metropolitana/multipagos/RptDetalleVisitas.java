//==============================================================================
//===   rpt_detalle_visitas.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptDetalleVisitas implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cVisitaId;
   private Integer    cCarteraId;
   private Integer    cLocalidadId;
   private Integer    cSimboloId;
   private Integer    cServicioId;
   private Integer    cColectorId;
   private String     dNumeroContrato;
   private Date       fFechaVisita;
   private Integer    nAvisoCobro;
   private String     dHoraRegistro;
   private Date       fProgCobro;
   private Boolean    bEncontroCliente;

   //---------------------------------------------------------------------------

   public Integer getCVisitaId() { return cVisitaId; }

   public void setCVisitaId(Integer cVisitaId)
   {
      this.cVisitaId = cVisitaId;
   }

   //---------------------------------------------------------------------------

   public Integer getCCarteraId() { return cCarteraId; }

   public void setCCarteraId(Integer cCarteraId)
   {
      this.cCarteraId = cCarteraId;
   }

   //---------------------------------------------------------------------------

   public Integer getCLocalidadId() { return cLocalidadId; }

   public void setCLocalidadId(Integer cLocalidadId)
   {
      this.cLocalidadId = cLocalidadId;
   }

   //---------------------------------------------------------------------------

   public Integer getCSimboloId() { return cSimboloId; }

   public void setCSimboloId(Integer cSimboloId)
   {
      this.cSimboloId = cSimboloId;
   }

   //---------------------------------------------------------------------------

   public Integer getCServicioId() { return cServicioId; }

   public void setCServicioId(Integer cServicioId)
   {
      this.cServicioId = cServicioId;
   }

   //---------------------------------------------------------------------------

   public Integer getCColectorId() { return cColectorId; }

   public void setCColectorId(Integer cColectorId)
   {
      this.cColectorId = cColectorId;
   }

   //---------------------------------------------------------------------------

   public String getDNumeroContrato() { return dNumeroContrato; }

   public void setDNumeroContrato(String dNumeroContrato)
   {
      this.dNumeroContrato = dNumeroContrato;
   }

   //---------------------------------------------------------------------------

   public Date getFFechaVisita() { return fFechaVisita; }

   public void setFFechaVisita(Date fFechaVisita)
   {
      this.fFechaVisita = fFechaVisita;
   }

   //---------------------------------------------------------------------------

   public Integer getNAvisoCobro() { return nAvisoCobro; }

   public void setNAvisoCobro(Integer nAvisoCobro)
   {
      this.nAvisoCobro = nAvisoCobro;
   }

   //---------------------------------------------------------------------------

   public String getDHoraRegistro() { return dHoraRegistro; }

   public void setDHoraRegistro(String dHoraRegistro)
   {
      this.dHoraRegistro = dHoraRegistro;
   }

   //---------------------------------------------------------------------------

   public Date getFProgCobro() { return fProgCobro; }

   public void setFProgCobro(Date fProgCobro)
   {
      this.fProgCobro = fProgCobro;
   }

   //---------------------------------------------------------------------------

   public Boolean getBEncontroCliente() { return bEncontroCliente; }

   public void setBEncontroCliente(Boolean bEncontroCliente)
   {
      this.bEncontroCliente = bEncontroCliente;
   }

   //---------------------------------------------------------------------------

   private RptVisitas cVisitaIdRef;

   public RptVisitas getCVisitaIdRef() { return cVisitaIdRef; }

   public void setCVisitaIdRef(RptVisitas rptVisitas)
   {
      this.cVisitaIdRef = rptVisitas;
   }


   //---------------------------------------------------------------------------

   private RptCarteraXDepartamento cCarteraIdRef;

   public RptCarteraXDepartamento getCCarteraIdRef() { return cCarteraIdRef; }

   public void setCCarteraIdRef(RptCarteraXDepartamento rptCarteraXDepartamento)
   {
      this.cCarteraIdRef = rptCarteraXDepartamento;
   }


   //---------------------------------------------------------------------------

   private CatLocalidad cLocalidadIdRef;

   public CatLocalidad getCLocalidadIdRef() { return cLocalidadIdRef; }

   public void setCLocalidadIdRef(CatLocalidad catLocalidad)
   {
      this.cLocalidadIdRef = catLocalidad;
   }


   //---------------------------------------------------------------------------

   private CatSimbolo cSimboloIdRef;

   public CatSimbolo getCSimboloIdRef() { return cSimboloIdRef; }

   public void setCSimboloIdRef(CatSimbolo catSimbolo)
   {
      this.cSimboloIdRef = catSimbolo;
   }


   //---------------------------------------------------------------------------

   private CatServicio cServicioIdRef;

   public CatServicio getCServicioIdRef() { return cServicioIdRef; }

   public void setCServicioIdRef(CatServicio catServicio)
   {
      this.cServicioIdRef = catServicio;
   }


   //---------------------------------------------------------------------------

   private CatColector cColectorIdRef;

   public CatColector getCColectorIdRef() { return cColectorIdRef; }

   public void setCColectorIdRef(CatColector catColector)
   {
      this.cColectorIdRef = catColector;
   }

}

//==============================================================================

