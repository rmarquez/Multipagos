//==============================================================================
//===   auth_permission.java                        Build:2489
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AuthPermission implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    rolId;
   private Integer    resId;

   //---------------------------------------------------------------------------

   public Integer getRolId() { return rolId; }

   public void setRolId(Integer rolId)
   {
      this.rolId = rolId;
   }

   //---------------------------------------------------------------------------

   public Integer getResId() { return resId; }

   public void setResId(Integer resId)
   {
      this.resId = resId;
   }

   //---------------------------------------------------------------------------

   private AuthRole rolIdRef;

   public AuthRole getRolIdRef() { return rolIdRef; }

   public void setRolIdRef(AuthRole authRole)
   {
      this.rolIdRef = authRole;
   }


   //---------------------------------------------------------------------------

   private AuthResource resIdRef;

   public AuthResource getResIdRef() { return resIdRef; }

   public void setResIdRef(AuthResource authResource)
   {
      this.resIdRef = authResource;
   }

}

//==============================================================================

