//==============================================================================
//===   auth_user_role.java                        Build:2489
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AuthUserRole implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    rolId;
   private Integer    usrId;

   //---------------------------------------------------------------------------

   public Integer getRolId() { return rolId; }

   public void setRolId(Integer rolId)
   {
      this.rolId = rolId;
   }

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   private AuthRole rolIdRef;

   public AuthRole getRolIdRef() { return rolIdRef; }

   public void setRolIdRef(AuthRole authRole)
   {
      this.rolIdRef = authRole;
   }


   //---------------------------------------------------------------------------

   private AuthUser usrIdRef;

   public AuthUser getUsrIdRef() { return usrIdRef; }

   public void setUsrIdRef(AuthUser authUser)
   {
      this.usrIdRef = authUser;
   }

}

//==============================================================================

