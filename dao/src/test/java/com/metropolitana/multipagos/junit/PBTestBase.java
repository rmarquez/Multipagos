package com.metropolitana.multipagos.junit;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;

import junit.framework.TestCase;

public abstract class PBTestBase extends TestCase {

    public PBTestBase() {
        super();
    }

    public PBTestBase(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        cleanUpDB();
    }

    protected void tearDown() throws Exception {
        cleanUpDB();
        super.tearDown();
    }

    protected abstract void cleanUpDB() throws Exception;

    protected void removeAll(Class clazz) throws Exception {
        removeAll(clazz, new Criteria());
    }

    protected void removeAll(Class clazz, Criteria criteria) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            QueryByCriteria query = new QueryByCriteria(clazz, criteria);
            broker.deleteByQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
            if (broker != null && !broker.isClosed()) {
                broker.close();
            }
        }
    }
}
