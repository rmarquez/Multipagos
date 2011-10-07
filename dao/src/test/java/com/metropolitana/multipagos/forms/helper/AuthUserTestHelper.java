package com.metropolitana.multipagos.forms.helper;

import org.apache.ojb.broker.query.Criteria;

import com.metropolitana.multipagos.AuthRole;
import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.AuthUserRole;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;

public class AuthUserTestHelper {

    public final static Criteria REMOVE_CRITERIO;
    static {
        REMOVE_CRITERIO = new Criteria();
        REMOVE_CRITERIO.addNotEqualTo("usrLogin", "admin");
    }

    public final static String USER_LOGIN_1 = "USER_LOGIN_1";
    public final static String USER_CARGO_1 = "USER_CARGO_1";
    public final static String USER_PASS_1 = "password_1";

    public final static String USER_LOGIN_2 = "USER_LOGIN_2";
    public final static String USER_CARGO_2 = "USER_CARGO_2";
    public final static String USER_PASS_2 = "password_2";

    public final static String USER_RESKEY_1 = "auth";
    public final static String USER_RESKEY_2 = "cata";


	public final AuthUser insertUsuario(final String usrLogin,
			final String usrCargo, final String usrPass) throws Exception {

		Auth_userHandler userHandler = new Auth_userHandler();

		AuthUser usuario = new AuthUser();
		usuario.setUsrCargo(usrCargo);
		usuario.setUsrLogin(usrLogin);
		usuario.setUsrPassword(usrPass);
		usuario.setUsrEnable(Boolean.TRUE);
		//usuario.setUsrNotificarOfertas(Boolean.FALSE);
		userHandler.insert(usuario);
		return usuario;
	}

	public final AuthUserRole insertRoles(AuthUser usuario, AuthRole rol)
			throws Exception {
		Auth_userHandler userHandler = new Auth_userHandler();

		AuthUserRole roles = new AuthUserRole();
		roles.setRolId(rol.getRolId());
		roles.setRolIdRef(rol);
		roles.setUsrId(usuario.getUsrId());
		roles.setUsrIdRef(usuario);

		usuario.addAuthUserRole(roles);
		userHandler.insert(usuario);
		return roles;
	}
}
