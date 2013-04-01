//==============================================================================
//===   auth_role.java                        Build:2616
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AuthRole implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    rolId;
   private String     rolName;
   private Boolean    rolEnable;

   //---------------------------------------------------------------------------

   public Integer getRolId() { return rolId; }

   public void setRolId(Integer rolId)
   {
      this.rolId = rolId;
   }

   //---------------------------------------------------------------------------

   public String getRolName() { return rolName; }

   public void setRolName(String rolName)
   {
      this.rolName = rolName;
   }

   //---------------------------------------------------------------------------

   public Boolean getRolEnable() { return rolEnable; }

   public void setRolEnable(Boolean rolEnable)
   {
      this.rolEnable = rolEnable;
   }

   //---------------------------------------------------------------------------

   private Collection authUserRoleList = new ArrayList();

   public Collection getAuthUserRoleList() { return authUserRoleList;}

   public void setAuthUserRoleList(Collection c) { this.authUserRoleList = c;}

   public void addAuthUserRole( AuthUserRole authUserRole )
   {
      authUserRole.setRolIdRef(this);
      authUserRoleList.add( authUserRole );
   }

   //---------------------------------------------------------------------------

   private Collection authPermissionList = new ArrayList();

   public Collection getAuthPermissionList() { return authPermissionList;}

   public void setAuthPermissionList(Collection c) { this.authPermissionList = c;}

   public void addAuthPermission( AuthPermission authPermission )
   {
      authPermission.setRolIdRef(this);
      authPermissionList.add( authPermission );
   }
}

//==============================================================================

