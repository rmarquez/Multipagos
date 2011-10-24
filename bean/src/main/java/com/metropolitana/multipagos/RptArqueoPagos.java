//==============================================================================
//===   rpt_arqueo_pagos.java                        Build:2546
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class RptArqueoPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    cArqueoId;
   private Integer    nUsrId;
   private Date       fPagoFecha;
   private Integer    nColectorId;
   private BigDecimal vTotalCs;
   private BigDecimal vTotalUs;
   private BigDecimal vConversionUs;
   private BigDecimal vTotalCkCs;
   private BigDecimal vTotalCkUs;
   private BigDecimal vConversionCkUs;
   private BigDecimal vTotalDpCs;
   private BigDecimal vTotalDpUs;
   private BigDecimal vConversionDpUs;
   private BigDecimal vTotalRecibo;
   private BigDecimal vTotalGeneral;
   private BigDecimal vDiferencia;

   //---------------------------------------------------------------------------

   public Integer getCArqueoId() { return cArqueoId; }

   public void setCArqueoId(Integer cArqueoId)
   {
      this.cArqueoId = cArqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getNUsrId() { return nUsrId; }

   public void setNUsrId(Integer nUsrId)
   {
      this.nUsrId = nUsrId;
   }

   //---------------------------------------------------------------------------

   public Date getFPagoFecha() { return fPagoFecha; }

   public void setFPagoFecha(Date fPagoFecha)
   {
      this.fPagoFecha = fPagoFecha;
   }

   //---------------------------------------------------------------------------

   public Integer getNColectorId() { return nColectorId; }

   public void setNColectorId(Integer nColectorId)
   {
      this.nColectorId = nColectorId;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalCs() { return vTotalCs; }

   public void setVTotalCs(BigDecimal vTotalCs)
   {
      this.vTotalCs = vTotalCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalUs() { return vTotalUs; }

   public void setVTotalUs(BigDecimal vTotalUs)
   {
      this.vTotalUs = vTotalUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVConversionUs() { return vConversionUs; }

   public void setVConversionUs(BigDecimal vConversionUs)
   {
      this.vConversionUs = vConversionUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalCkCs() { return vTotalCkCs; }

   public void setVTotalCkCs(BigDecimal vTotalCkCs)
   {
      this.vTotalCkCs = vTotalCkCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalCkUs() { return vTotalCkUs; }

   public void setVTotalCkUs(BigDecimal vTotalCkUs)
   {
      this.vTotalCkUs = vTotalCkUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVConversionCkUs() { return vConversionCkUs; }

   public void setVConversionCkUs(BigDecimal vConversionCkUs)
   {
      this.vConversionCkUs = vConversionCkUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalDpCs() { return vTotalDpCs; }

   public void setVTotalDpCs(BigDecimal vTotalDpCs)
   {
      this.vTotalDpCs = vTotalDpCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalDpUs() { return vTotalDpUs; }

   public void setVTotalDpUs(BigDecimal vTotalDpUs)
   {
      this.vTotalDpUs = vTotalDpUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVConversionDpUs() { return vConversionDpUs; }

   public void setVConversionDpUs(BigDecimal vConversionDpUs)
   {
      this.vConversionDpUs = vConversionDpUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalRecibo() { return vTotalRecibo; }

   public void setVTotalRecibo(BigDecimal vTotalRecibo)
   {
      this.vTotalRecibo = vTotalRecibo;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVTotalGeneral() { return vTotalGeneral; }

   public void setVTotalGeneral(BigDecimal vTotalGeneral)
   {
      this.vTotalGeneral = vTotalGeneral;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getVDiferencia() { return vDiferencia; }

   public void setVDiferencia(BigDecimal vDiferencia)
   {
      this.vDiferencia = vDiferencia;
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoCantidadList = new ArrayList();

   public Collection getRptArqueoCantidadList() { return rptArqueoCantidadList;}

   public void setRptArqueoCantidadList(Collection c) { this.rptArqueoCantidadList = c;}

   public void addRptArqueoCantidad( RptArqueoCantidad rptArqueoCantidad )
   {
      rptArqueoCantidad.setCArqueoIdRef(this);
      rptArqueoCantidadList.add( rptArqueoCantidad );
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoCantidadUsList = new ArrayList();

   public Collection getRptArqueoCantidadUsList() { return rptArqueoCantidadUsList;}

   public void setRptArqueoCantidadUsList(Collection c) { this.rptArqueoCantidadUsList = c;}

   public void addRptArqueoCantidadUs( RptArqueoCantidadUs rptArqueoCantidadUs )
   {
      rptArqueoCantidadUs.setCArqueoIdRef(this);
      rptArqueoCantidadUsList.add( rptArqueoCantidadUs );
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoChequeList = new ArrayList();

   public Collection getRptArqueoChequeList() { return rptArqueoChequeList;}

   public void setRptArqueoChequeList(Collection c) { this.rptArqueoChequeList = c;}

   public void addRptArqueoCheque( RptArqueoCheque rptArqueoCheque )
   {
      rptArqueoCheque.setCArqueoIdRef(this);
      rptArqueoChequeList.add( rptArqueoCheque );
   }

   //---------------------------------------------------------------------------

   private Collection rptArqueoDepositoList = new ArrayList();

   public Collection getRptArqueoDepositoList() { return rptArqueoDepositoList;}

   public void setRptArqueoDepositoList(Collection c) { this.rptArqueoDepositoList = c;}

   public void addRptArqueoDeposito( RptArqueoDeposito rptArqueoDeposito )
   {
      rptArqueoDeposito.setCArqueoIdRef(this);
      rptArqueoDepositoList.add( rptArqueoDeposito );
   }

   //---------------------------------------------------------------------------

   private AuthUser nUsrIdRef;

   public AuthUser getNUsrIdRef() { return nUsrIdRef; }

   public void setNUsrIdRef(AuthUser authUser)
   {
      this.nUsrIdRef = authUser;
   }


   //---------------------------------------------------------------------------

   private CatColector nColectorIdRef;

   public CatColector getNColectorIdRef() { return nColectorIdRef; }

   public void setNColectorIdRef(CatColector catColector)
   {
      this.nColectorIdRef = catColector;
   }

}

//==============================================================================

