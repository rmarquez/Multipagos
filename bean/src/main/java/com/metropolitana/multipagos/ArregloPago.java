//==============================================================================
//===   arreglo_pago.java                        Build:2645
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class ArregloPago implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    arregloId;
   private Date       fecha;
   private String     codCliente;
   private String     nombre;
   private BigDecimal descuento;
   private String     plazo;
   private BigDecimal cuota;

   //---------------------------------------------------------------------------

   public Integer getArregloId() { return arregloId; }

   public void setArregloId(Integer arregloId)
   {
      this.arregloId = arregloId;
   }

   //---------------------------------------------------------------------------

   public Date getFecha() { return fecha; }

   public void setFecha(Date fecha)
   {
      this.fecha = fecha;
   }

   //---------------------------------------------------------------------------

   public String getCodCliente() { return codCliente; }

   public void setCodCliente(String codCliente)
   {
      this.codCliente = codCliente;
   }

   //---------------------------------------------------------------------------

   public String getNombre() { return nombre; }

   public void setNombre(String nombre)
   {
      this.nombre = nombre;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getDescuento() { return descuento; }

   public void setDescuento(BigDecimal descuento)
   {
      this.descuento = descuento;
   }

   //---------------------------------------------------------------------------

   public String getPlazo() { return plazo; }

   public void setPlazo(String plazo)
   {
      this.plazo = plazo;
   }

   //---------------------------------------------------------------------------

   public BigDecimal getCuota() { return cuota; }

   public void setCuota(BigDecimal cuota)
   {
      this.cuota = cuota;
   }

   //---------------------------------------------------------------------------

   private Collection arregloDeudaList = new ArrayList();

   public Collection getArregloDeudaList() { return arregloDeudaList;}

   public void setArregloDeudaList(Collection c) { this.arregloDeudaList = c;}

   public void addArregloDeuda( ArregloDeuda arregloDeuda )
   {
      arregloDeuda.setArregloIdRef(this);
      arregloDeudaList.add( arregloDeuda );
   }

   //---------------------------------------------------------------------------

   private Collection arregloCalendarioList = new ArrayList();

   public Collection getArregloCalendarioList() { return arregloCalendarioList;}

   public void setArregloCalendarioList(Collection c) { this.arregloCalendarioList = c;}

   public void addArregloCalendario( ArregloCalendario arregloCalendario )
   {
      arregloCalendario.setArregloIdRef(this);
      arregloCalendarioList.add( arregloCalendario );
   }
}

//==============================================================================

