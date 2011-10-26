//==============================================================================
//===   auth_resource.java                        Build:2516
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AuthResource implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    resId;
   private String     resKey;
   private String     resName;
   private Boolean    resEnable;

   //---------------------------------------------------------------------------

   public Integer getResId() { return resId; }

   public void setResId(Integer resId)
   {
      this.resId = resId;
   }

   //---------------------------------------------------------------------------

   public String getResKey() { return resKey; }

   public void setResKey(String resKey)
   {
      this.resKey = resKey;
   }

   //---------------------------------------------------------------------------

   public String getResName() { return resName; }

   public void setResName(String resName)
   {
      this.resName = resName;
   }

   //---------------------------------------------------------------------------

   public Boolean getResEnable() { return resEnable; }

   public void setResEnable(Boolean resEnable)
   {
      this.resEnable = resEnable;
   }

   //---------------------------------------------------------------------------

   private Collection authPermissionList = new ArrayList();

   public Collection getAuthPermissionList() { return authPermissionList;}

   public void setAuthPermissionList(Collection c) { this.authPermissionList = c;}

   public void addAuthPermission( AuthPermission authPermission )
   {
      authPermission.setResIdRef(this);
      authPermissionList.add( authPermission );
   }
}

//==============================================================================

