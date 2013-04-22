//==============================================================================
//===   i_departamento.java                        Build:2672
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class IDepartamento implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    iDepartamentoId;
   private String     iDepartamentoNombre;
   private Boolean    inactivo;

   //---------------------------------------------------------------------------

   public Integer getIDepartamentoId() { return iDepartamentoId; }

   public void setIDepartamentoId(Integer iDepartamentoId)
   {
      this.iDepartamentoId = iDepartamentoId;
   }

   //---------------------------------------------------------------------------

   public String getIDepartamentoNombre() { return iDepartamentoNombre; }

   public void setIDepartamentoNombre(String iDepartamentoNombre)
   {
      this.iDepartamentoNombre = iDepartamentoNombre;
   }

   //---------------------------------------------------------------------------

   public Boolean getInactivo() { return inactivo; }

   public void setInactivo(Boolean inactivo)
   {
      this.inactivo = inactivo;
   }

   //---------------------------------------------------------------------------

   private Collection carteraIbwList = new ArrayList();

   public Collection getCarteraIbwList() { return carteraIbwList;}

   public void setCarteraIbwList(Collection c) { this.carteraIbwList = c;}

   public void addCarteraIbw( CarteraIbw carteraIbw )
   {
      carteraIbw.setDepartamentoIdRef(this);
      carteraIbwList.add( carteraIbw );
   }

   //---------------------------------------------------------------------------

   private Collection municipioList = new ArrayList();

   public Collection getMunicipioList() { return municipioList;}

   public void setMunicipioList(Collection c) { this.municipioList = c;}

   public void addMunicipio( Municipio municipio )
   {
      municipio.setDepartamentoIdRef(this);
      municipioList.add( municipio );
   }
}

//==============================================================================

