//==============================================================================
//===   arreglo_deuda.java                        Build:2643
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArregloDeuda implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arregloId;
   private Integer    tmpId;
   private String     producto;
   private String     tarjeta;
   private String     cuenta;
   private BigDecimal deudaCor;
   private BigDecimal deudaDol;
   private BigDecimal totalDeudaDol;

   //---------------------------------------------------------------------------

   public Integer getArregloId() { return arregloId; }

   public void setArregloId(Integer arregloId)
   {
      this.arregloId = arregloId;
   }

   //---------------------------------------------------------------------------

   public Integer getTmpId() { return tmpId; }

   public void setTmpId(Integer tmpId)
   {
      this.tmpId = tmpId;
   }

   //---------------------------------------------------------------------------

   public String getProducto() { return producto; }

   public void setProducto(String producto)
   {
      this.producto = producto;
   }

   //---------------------------------------------------------------------------

   public String getTarjeta() { return tarjeta; }

   public void setTarjeta(String tarjeta)
   {
      this.tarjeta = tarjeta;
   }

   //---------------------------------------------------------------------------

   public String getCuenta() { return cuenta; }

   public void setCuenta(String cuenta)
   {
      this.cuenta = cuenta;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDeudaCor() { return deudaCor; }

   public void setDeudaCor(BigDecimal deudaCor)
   {
      this.deudaCor = deudaCor;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDeudaDol() { return deudaDol; }

   public void setDeudaDol(BigDecimal deudaDol)
   {
      this.deudaDol = deudaDol;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getTotalDeudaDol() { return totalDeudaDol; }

   public void setTotalDeudaDol(BigDecimal totalDeudaDol)
   {
      this.totalDeudaDol = totalDeudaDol;
   }

   //---------------------------------------------------------------------------

   private ArregloPago arregloIdRef;

   public ArregloPago getArregloIdRef() { return arregloIdRef; }

   public void setArregloIdRef(ArregloPago arregloPago)
   {
      this.arregloIdRef = arregloPago;
   }


   //---------------------------------------------------------------------------

   private CarteraBanpro tmpIdRef;

   public CarteraBanpro getTmpIdRef() { return tmpIdRef; }

   public void setTmpIdRef(CarteraBanpro carteraBanpro)
   {
      this.tmpIdRef = carteraBanpro;
   }

}

//==============================================================================

