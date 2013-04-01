//==============================================================================
//===   pagos_banpro.java                        Build:2628
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class PagosBanpro implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private String     cuenta;
   private String     nombre;
   private Date       fecPagoCord;
   private Date       fecPagoDol;
   private BigDecimal pagoCor;
   private BigDecimal pagoDol;
   private String     observaciones;
   private Date       fechaAlta;

   //---------------------------------------------------------------------------

   public String getCuenta() { return cuenta; }

   public void setCuenta(String cuenta)
   {
      this.cuenta = cuenta;
   }

   //---------------------------------------------------------------------------

   public String getNombre() { return nombre; }

   public void setNombre(String nombre)
   {
      this.nombre = nombre;
   }

   //---------------------------------------------------------------------------

   public Date getFecPagoCord() { return fecPagoCord; }

   public void setFecPagoCord(Date fecPagoCord)
   {
      this.fecPagoCord = fecPagoCord;
   }

   //---------------------------------------------------------------------------

   public Date getFecPagoDol() { return fecPagoDol; }

   public void setFecPagoDol(Date fecPagoDol)
   {
      this.fecPagoDol = fecPagoDol;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getPagoCor() { return pagoCor; }

   public void setPagoCor(BigDecimal pagoCor)
   {
      this.pagoCor = pagoCor;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getPagoDol() { return pagoDol; }

   public void setPagoDol(BigDecimal pagoDol)
   {
      this.pagoDol = pagoDol;
   }

   //---------------------------------------------------------------------------

   public String getObservaciones() { return observaciones; }

   public void setObservaciones(String observaciones)
   {
      this.observaciones = observaciones;
   }

   //---------------------------------------------------------------------------

   public Date getFechaAlta() { return fechaAlta; }

   public void setFechaAlta(Date fechaAlta)
   {
      this.fechaAlta = fechaAlta;
   }
}

//==============================================================================

