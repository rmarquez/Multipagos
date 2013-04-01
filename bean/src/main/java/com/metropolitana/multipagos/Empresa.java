//==============================================================================
//===   empresa.java                        Build:2180
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class Empresa implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    empId;
   private Integer    empSerial;
   private Integer    empMcodigo;

   //---------------------------------------------------------------------------

   public Integer getEmpId() { return empId; }

   public void setEmpId(Integer empId)
   {
      this.empId = empId;
   }

   //---------------------------------------------------------------------------

   public Integer getEmpSerial() { return empSerial; }

   public void setEmpSerial(Integer empSerial)
   {
      this.empSerial = empSerial;
   }

   //---------------------------------------------------------------------------

   public Integer getEmpMcodigo() { return empMcodigo; }

   public void setEmpMcodigo(Integer empMcodigo)
   {
      this.empMcodigo = empMcodigo;
   }
}

//==============================================================================

