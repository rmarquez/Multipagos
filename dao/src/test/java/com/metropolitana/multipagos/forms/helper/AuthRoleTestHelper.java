package com.metropolitana.multipagos.forms.helper;

import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.AuthPermission;
import com.metropolitana.multipagos.AuthResource;

import org.apache.ojb.broker.query.Criteria;

import com.metropolitana.multipagos.AuthRole;
import com.metropolitana.multipagos.forms.auth_role.Auth_roleHandler;

public class AuthRoleTestHelper {

    public final static Criteria REMOVE_CRITERIO;
    static {
        REMOVE_CRITERIO = new Criteria();
        REMOVE_CRITERIO.addGreaterThan("rol_id", Long.valueOf(2L));
    }

    public final static String ROLE_NAME1 = "test_rolName1";
    public final static String ROLE_NAME2 = "test_rolName2";
    public final static String ROLE_NAME3 = "test_rolName3";
    public final static String STR_SEARCH = "test_rolName";

    public final AuthRole insertAuthRole(final String rolName, final Boolean rolEnable, List permissionList) throws Exception {
        Auth_roleHandler rolHandler = new Auth_roleHandler();
        AuthRole authRole = new AuthRole();
        authRole.setRolName(rolName);
        authRole.setRolEnable(rolEnable);

        for (Iterator iter = permissionList.iterator(); iter.hasNext();) {
            AuthResource element = (AuthResource) iter.next();
            AuthPermission permission = new AuthPermission();
            permission.setResId(element.getResId());
            permission.setResIdRef(element);
            authRole.addAuthPermission(permission);
        }

        rolHandler.insert(authRole);
        return authRole;
    }
}
