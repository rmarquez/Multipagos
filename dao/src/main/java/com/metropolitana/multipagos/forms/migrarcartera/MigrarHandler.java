package com.metropolitana.multipagos.forms.migrarcartera;

import com.metropolitana.multipagos.TmpCartera;

import com.metropolitana.multipagos.TmpVistaDuplicados;
import java.util.Collection;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryByIdentity;



public class MigrarHandler {
	
	
	
	
	
	public void removeDuplicados(Integer tmpId, String facturaInterna) throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
            broker.beginTransaction();
            TmpCartera beanC = retrieveCartera(tmpId, broker);
            TmpVistaDuplicados beanV = carteraXFactura(facturaInterna);
            broker.delete(beanC);
            broker.delete(beanV);
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
	
	public static TmpCartera retrieveCartera(final Integer tmpId,
                    final PersistenceBroker broker) {
            TmpCartera criterio = new TmpCartera();
            criterio.setTmpId(tmpId);
            Query query = new QueryByIdentity(criterio);
            return (TmpCartera) broker.getObjectByQuery(query);
	}
        public static TmpVistaDuplicados retrieve(final String facturaInterna,
                    final PersistenceBroker broker) {
            TmpVistaDuplicados criterio = new TmpVistaDuplicados();
            criterio.setFacturaInterna(facturaInterna);
            Query query = new QueryByIdentity(criterio);
            return (TmpVistaDuplicados) broker.getObjectByQuery(query);
	}
        
        
        public static TmpVistaDuplicados carteraXFactura(final String factura)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXFactura(factura, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public static TmpVistaDuplicados carteraXFactura(final String factura,
			final PersistenceBroker broker) {
		TmpVistaDuplicados criterio = new TmpVistaDuplicados();
		criterio.setFacturaInterna(factura);
		Query query = new QueryByCriteria(criterio);
		return (TmpVistaDuplicados) broker.getObjectByQuery(query);
	}
	
	
	/**
	public Collection getList() throws Exception {
		
		
		Connection connPostgres = null;
		String selectQuery;

		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
			try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra el Driver: " + e.getMessage());
            }
            String username = "postgres";
            String password = "";
            String url = "jdbc:postgresql://localhost:5432/multipagos";
            connPostgres = DriverManager.getConnection(url, username, password);
            selectQuery = "select * from tmp_cartera where factura_interna in (	select factura_interna from tmp_cartera	group by factura_interna having count(*) > order by factura_interna ) order by factura_interna, saldo";
            //ResultSet rs = stmt.executeQuery(selectQuery);  
			
			
            
            return simpleJdbcTemplate().query(selectQuery);
		} catch (Exception e) {
			throw e;
		} finally {
			if(connPostgres != null) connPostgres.close();
			
		}
	}**/
        
        private Collection getList(final Criteria criterio) throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(TmpVistaDuplicados.class,
					criterio);
			query.addOrderBy("facturaInterna", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	public Collection getList() throws Exception {
		try {
			return getList(new Criteria());
		} catch (Exception e) {
			throw e;
		}
	}
}
