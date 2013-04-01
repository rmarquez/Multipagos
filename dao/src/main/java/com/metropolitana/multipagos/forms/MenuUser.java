package com.metropolitana.multipagos.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.AuthResource;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections.Transformer;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class MenuUser {

	private List<String> resources = new ArrayList<String>();

	/***
	 * Constructor del objeto MenuUser el cual carga la lista
	 * de recursos a los que tiene acceso el usuario.
	 * @param usrId Identificador del usuario
	 * @throws Exception
	 */
	public MenuUser(Integer usrId) throws Exception {
		PersistenceBroker broker = null;
        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            // 2. Define query
            Criteria crit = new Criteria();
            crit.addEqualTo("authPermissionList.rolIdRef.authUserRoleList.usrIdRef.usrId", usrId);
            ReportQueryByCriteria query = new ReportQueryByCriteria(AuthResource.class, crit);
            query.setAttributes(new String[]{"resKey"});
            // 3. Execute Query
            Iterator iter = broker.getReportQueryIteratorByQuery(query);
            iter = IteratorUtils.transformedIterator(iter, new Transformer() {
				public Object transform(Object arg0) {
					Object[] obj = (Object[])arg0;
					return (String)obj[0];
				}
            });
            resources = IteratorUtils.toList(iter);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
	}

	/**
	 * Regresa una lista que contiene los recursos del usuario
	 * @return Lista de recursos del usuario
	 */
	public List<String> getResources() {
		return resources;
	}
}
