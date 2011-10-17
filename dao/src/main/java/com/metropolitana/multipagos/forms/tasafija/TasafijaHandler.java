/*
 * Created on 15-09-2011
 */

package com.metropolitana.multipagos.forms.tasafija;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Collection;

import com.metropolitana.multipagos.TasaFija;
import com.metropolitana.multipagos.forms.Util;
//import com.metropolitana.multipagos.forms.logs.LogsHandler;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * @author Rafael Márquez
 */
public class TasafijaHandler {

    /**
     * Inserta una nueva tasa de cambio
     *
     * @param tasa
     *            bean que contiene el tipo de cambio a insertar
     * @throws Exception
     */
    public void insert(TasaFija tasa, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            // Resulta que la llave primaria ya viene con valores y es la fecha
            // por
            // tal motivo si mandamos a hacer un "store" este modifica un
            // registro
            // existente y no manda ninguna excepción
            if (retrieve(tasa.getTasaFecha(), broker) != null) {
                throw new Exception("La tasa de cambio ya existe, por favor ingrese una nueva.");
            } else {
                broker.beginTransaction();
                Date fecha = Calendar.getInstance().getTime();
                broker.store(tasa);
                //broker.store(LogsHandler.setLogsDelSistema(tasa, fecha, usrId, Integer.valueOf(1), broker));
                broker.commitTransaction();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                if (broker.isInTransaction()) {
                    broker.abortTransaction();
                }
                broker.close();
            }
        }
    }

    /**
     * Actualiza un tipo de cambio
     *
     * @param bean
     *            bean que contienen el tipo cambio a actualizar
     * @throws Exception
     */
    public void update(TasaFija bean, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            Date fecha = Calendar.getInstance().getTime();
            broker.store(bean);
            //broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId, Integer.valueOf(3), broker));
            broker.commitTransaction();
            broker.clearCache();
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                if (broker.isInTransaction()) {
                    broker.abortTransaction();
                }
                broker.close();
            }
        }
    }

    /**
     * Obtienen un tipo de cambio
     *
     * @param tasaFecha
     *            fecha de la tasa de cambio a recuperar
     * @param broker
     * @return tasa de cambio
     */
    public static TasaFija retrieve(Date fecha, PersistenceBroker broker) {
        Criteria criterio = new Criteria();
        //Buscar el mes correspondiente al parametro fecha
        criterio.addEqualTo("tasaFecha", Util.primeroDelMes(fecha));
        ReportQueryByCriteria query = new ReportQueryByCriteria(TasaFija.class, criterio);
        return (TasaFija) broker.getObjectByQuery(query);
    }

    /**
     * Obtiene una tasa de cambio
     *
     * @param tasa_fechacambio
     *            fecha de la tasa de cambio
     * @return tasa de cambio
     * @throws Exception
     */
    public static TasaFija retrieve(Date tasaFecha) throws Exception {
        PersistenceBroker broker = null;
       try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            return retrieve(tasaFecha, broker);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Elimina una tasa de cambio
     *
     * @param tasaFecha
     *            fecha de la tasa de cambio a eliminar
     * @throws Exception
     */
    public void remove(Date tasaFecha, Integer usrId) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            Date fecha = Calendar.getInstance().getTime();
            TasaFija bean = retrieve(tasaFecha, broker);
            broker.delete(bean);
            //broker.store(LogsHandler.setLogsDelSistema(bean, fecha, usrId, Integer.valueOf(3), broker));
            broker.commitTransaction();
            broker.clearCache();
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                if (broker.isInTransaction()) {
                    broker.abortTransaction();
                }
                broker.close();
            }
        }
    }

    /**
     * Genera el listado de tasas de cambios
     *
     * @param criterio
     *            Criterio de filtrado de la lista
     * @return Collection o null Listado de tasas de cambios
     * @throws Exception
     */
    private Collection getList(Criteria criterio) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(TasaFija.class, criterio);
            query.addOrderBy("tasaFecha", true);
            return broker.getCollectionByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }

    /**
     * Genera el listado de tasas de cambios
     *
     * @param tasaFecha
     *            Fecha de inicio del criterio de búsqueda
     * @param strfechafin
     *            Fecha final del criterio de búsqueda
     * @return Collection o null listado de tasas de cambios
     * @throws Exception
     */
    public Collection getList(Date tasaFecha) throws Exception {
        try {
            Criteria criterio = new Criteria();
            if (tasaFecha != null) {
                criterio.addEqualTo("tasaFecha", tasaFecha);
                }
            return getList(criterio);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Listado de todas las tasas de cambios
     *
     * @return Collection o null listado de las tasas de cambios
     * @throws Exception
     */
    public Collection getList() throws Exception {
        try {
            return getList(new Criteria());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retorna el valor número de la tasa de cambio
     *
     * @param fecha
     *            Fecha de la tasa de cambio
     * @return BigDecimal Tasa de cambio o null
     * @throws Exception
     */
    public static BigDecimal getTasaFija(Date fecha) throws Exception {
        TasaFija tasaFija = retrieve(fecha);
        if (tasaFija != null) {
            return tasaFija.getTasaCambioMes();
        }
        return null;
    }

}