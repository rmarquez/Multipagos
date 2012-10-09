package com.metropolitana.multipagos.forms.empresa;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.metropolitana.multipagos.Empresa;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByIdentity;

/**
 * @author rmarquez
 *
 */
public class EmpresaHandler {

    /**
     * Valor predefinido para el identificador de la empresa.
     */
    private static final Integer EMPRESA_ID = Integer.valueOf(1);
    

    /**
     * Retorna un bean de una empresa existente
     *
     * @param broker
     *            PersistenceBroker
     * @return Empresa
     */
    public static Empresa retrieve(PersistenceBroker broker) {
        Empresa criterio = new Empresa();
        criterio.setEmpId(EMPRESA_ID);
        Query query = new QueryByIdentity(criterio);
        return (Empresa) broker.getObjectByQuery(query);
    }

    /**
     * Retorna un bean de una empresa existente o un bean nuevo
     *
     * @return Empresa
     * @throws Exception
     */
    public static Empresa getBeanEmpresa() throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            Empresa empresa = retrieve(broker);
            if (empresa == null) {
                empresa = new Empresa();
                empresa.setEmpId(EMPRESA_ID);
            }
            return empresa;
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Retorna un bean de una empresa existente o un bean nuevo
     *
     * @param llenar
     *            Si es true ciertos campos nulos se llenan con valores
     *            predefinidos
     * @return Empresa
     * @throws Exception
     */
    public static Empresa getBeanEmpresa(boolean llenar) throws Exception {
        try {
            Empresa item = getBeanEmpresa();
            return item;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Guarda los cambios en una empresa existente o agrega una nueva empresa
     *
     * @param beanEmpresa
     *            Empresa existente o nueva.
     * @throws Exception
     */
    public static void setBeanEmpresa(Empresa beanEmpresa, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            Date fecha = Calendar.getInstance().getTime();
            Empresa empresa = retrieve(broker);
            if (empresa == null) {
                empresa = new Empresa();
                PropertyUtils.copyProperties(empresa, beanEmpresa);
                empresa.setEmpId(EMPRESA_ID);
            } else {
                PropertyUtils.copyProperties(empresa, beanEmpresa);
            }
            broker.store(empresa);
            //broker.store(LogsHandler.setLogsDelSistema(empresa, fecha, usrId, broker));
            broker.commitTransaction();
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                if (broker.isInTransaction())
                    broker.abortTransaction();
                broker.close();
            }
        }
    }
}
