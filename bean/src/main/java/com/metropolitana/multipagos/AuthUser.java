//==============================================================================
//===   auth_user.java                        Build:2525
//==============================================================================

package com.metropolitana.multipagos;

import java.util.*;
import java.math.*;
import com.metropolitana.multipagos.*;

//==============================================================================

public class AuthUser implements java.io.Serializable
{

   //---------------------------------------------------------------------------


   //---------------------------------------------------------------------------

   private Integer    usrId;
   private String     usrLogin;
   private String     usrFullName;
   private String     usrPassword;
   private Boolean    usrEnable;
   private String     usrEmail;
   private String     usrCargo;

   //---------------------------------------------------------------------------

   public Integer getUsrId() { return usrId; }

   public void setUsrId(Integer usrId)
   {
      this.usrId = usrId;
   }

   //---------------------------------------------------------------------------

   public String getUsrLogin() { return usrLogin; }

   public void setUsrLogin(String usrLogin)
   {
      this.usrLogin = usrLogin;
   }

   //---------------------------------------------------------------------------

   public String getUsrFullName() { return usrFullName; }

   public void setUsrFullName(String usrFullName)
   {
      this.usrFullName = usrFullName;
   }

   //---------------------------------------------------------------------------

   public String getUsrPassword() { return usrPassword; }

   public void setUsrPassword(String usrPassword)
   {
      this.usrPassword = usrPassword;
   }

   //---------------------------------------------------------------------------

   public Boolean getUsrEnable() { return usrEnable; }

   public void setUsrEnable(Boolean usrEnable)
   {
      this.usrEnable = usrEnable;
   }

   //---------------------------------------------------------------------------

   public String getUsrEmail() { return usrEmail; }

   public void setUsrEmail(String usrEmail)
   {
      this.usrEmail = usrEmail;
   }

   //---------------------------------------------------------------------------

   public String getUsrCargo() { return usrCargo; }

   public void setUsrCargo(String usrCargo)
   {
      this.usrCargo = usrCargo;
   }

   //---------------------------------------------------------------------------

   private Collection authUserRoleList = new ArrayList();

   public Collection getAuthUserRoleList() { return authUserRoleList;}

   public void setAuthUserRoleList(Collection c) { this.authUserRoleList = c;}

   public void addAuthUserRole( AuthUserRole authUserRole )
   {
      authUserRole.setUsrIdRef(this);
      authUserRoleList.add( authUserRole );
   }

   //---------------------------------------------------------------------------

   private Collection visitasList = new ArrayList();

   public Collection getVisitasList() { return visitasList;}

   public void setVisitasList(Collection c) { this.visitasList = c;}

   public void addVisitas( Visitas visitas )
   {
      visitas.setUsrIdRef(this);
      visitasList.add( visitas );
   }

   //---------------------------------------------------------------------------

   private Collection pagosList = new ArrayList();

   public Collection getPagosList() { return pagosList;}

   public void setPagosList(Collection c) { this.pagosList = c;}

   public void addPagos( Pagos pagos )
   {
      pagos.setUsrIdRef(this);
      pagosList.add( pagos );
   }

   //---------------------------------------------------------------------------

   private Collection arqueoPagosList = new ArrayList();

   public Collection getArqueoPagosList() { return arqueoPagosList;}

   public void setArqueoPagosList(Collection c) { this.arqueoPagosList = c;}

   public void addArqueoPagos( ArqueoPagos arqueoPagos )
   {
      arqueoPagos.setUsrIdRef(this);
      arqueoPagosList.add( arqueoPagos );
   }
}

//==============================================================================

