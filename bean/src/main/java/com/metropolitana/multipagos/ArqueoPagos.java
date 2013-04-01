//==============================================================================
//===   arqueo_pagos.java                        Build:2643
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArqueoPagos implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arqueoId;
   private Integer    usrId;
   private Date       pagoFecha;
   private Integer    colectorId;
   private BigDecimal totalCs;
   private BigDecimal totalUs;
   private BigDecimal conversionUs;
   private BigDecimal totalCkCs;
   private BigDecimal totalCkUs;
   private BigDecimal conversionCkUs;
   private BigDecimal totalDpCs;
   private BigDecimal totalDpUs;
   private BigDecimal conversionDpUs;
   private BigDecimal totalRecibo;
   private BigDecimal totalGeneral;
   private BigDecimal diferencia;

   //---------------------------------------------------------------------------

   public Integer getArqueoId() { return arqueoId; }

   public void setArqueoId(Integer arqueoId)
   {
      this.arqueoId = arqueoId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public Date getPagoFecha() { return pagoFecha; }

   public void setPagoFecha(Date pagoFecha)
   {
      this.pagoFecha = pagoFecha;
   }

   //---------------------------------------------------------------------------

   public Integer getColectorId() { return colectorId; }

   public void setColectorId(Integer colectorId)
   {
      this.colectorId = colectorId;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalCs() { return totalCs; }

   public void setTotalCs(BigDecimal totalCs)
   {
      this.totalCs = totalCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalUs() { return totalUs; }

   public void setTotalUs(BigDecimal totalUs)
   {
      this.totalUs = totalUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getConversionUs() { return conversionUs; }

   public void setConversionUs(BigDecimal conversionUs)
   {
      this.conversionUs = conversionUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalCkCs() { return totalCkCs; }

   public void setTotalCkCs(BigDecimal totalCkCs)
   {
      this.totalCkCs = totalCkCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalCkUs() { return totalCkUs; }

   public void setTotalCkUs(BigDecimal totalCkUs)
   {
      this.totalCkUs = totalCkUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getConversionCkUs() { return conversionCkUs; }

   public void setConversionCkUs(BigDecimal conversionCkUs)
   {
      this.conversionCkUs = conversionCkUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalDpCs() { return totalDpCs; }

   public void setTotalDpCs(BigDecimal totalDpCs)
   {
      this.totalDpCs = totalDpCs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalDpUs() { return totalDpUs; }

   public void setTotalDpUs(BigDecimal totalDpUs)
   {
      this.totalDpUs = totalDpUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getConversionDpUs() { return conversionDpUs; }

   public void setConversionDpUs(BigDecimal conversionDpUs)
   {
      this.conversionDpUs = conversionDpUs;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalRecibo() { return totalRecibo; }

   public void setTotalRecibo(BigDecimal totalRecibo)
   {
      this.totalRecibo = totalRecibo;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalGeneral() { return totalGeneral; }

   public void setTotalGeneral(BigDecimal totalGeneral)
   {
      this.totalGeneral = totalGeneral;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDiferencia() { return diferencia; }

   public void setDiferencia(BigDecimal diferencia)
   {
      this.diferencia = diferencia;
   }

   //---------------------------------------------------------------------------

   private Collection arqueoCantidadList = new ArrayList();

   public Collection getArqueoCantidadList() { return arqueoCantidadList;}

   public void setArqueoCantidadList(Collection c) { this.arqueoCantidadList = c;}

   public void addArqueoCantidad( ArqueoCantidad arqueoCantidad )
   {
      arqueoCantidad.setArqueoIdRef(this);
      arqueoCantidadList.add( arqueoCantidad );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoCantidadUsList = new ArrayList();

   public Collection getArqueoCantidadUsList() { return arqueoCantidadUsList;}

   public void setArqueoCantidadUsList(Collection c) { this.arqueoCantidadUsList = c;}

   public void addArqueoCantidadUs( ArqueoCantidadUs arqueoCantidadUs )
   {
      arqueoCantidadUs.setArqueoIdRef(this);
      arqueoCantidadUsList.add( arqueoCantidadUs );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoChequeList = new ArrayList();

   public Collection getArqueoChequeList() { return arqueoChequeList;}

   public void setArqueoChequeList(Collection c) { this.arqueoChequeList = c;}

   public void addArqueoCheque( ArqueoCheque arqueoCheque )
   {
      arqueoCheque.setArqueoIdRef(this);
      arqueoChequeList.add( arqueoCheque );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoDepositoList = new ArrayList();

   public Collection getArqueoDepositoList() { return arqueoDepositoList;}

   public void setArqueoDepositoList(Collection c) { this.arqueoDepositoList = c;}

   public void addArqueoDeposito( ArqueoDeposito arqueoDeposito )
   {
      arqueoDeposito.setArqueoIdRef(this);
      arqueoDepositoList.add( arqueoDeposito );
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

}

//==============================================================================

