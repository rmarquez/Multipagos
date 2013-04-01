package com.metropolitana.multipagos.forms.estado_pago;

import java.util.Date;
import java.util.Iterator;

import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class EstadoPagoHandler {
	
	
	
	public void cambiarEstadoCartera() throws Exception {
        PersistenceBroker broker = null;

        try {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker.getReportQueryIteratorByQuery(queryPagos()); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				if(detalle[1] != null) {
					CarteraXDepartamento cartera = CarteraXDepartamentoHandler.retrieve((Integer)detalle[0]);
					if(cartera.getPagado()==false){
						cartera.setPagado(Boolean.TRUE);
						cartera.setFechaPago((Date)detalle[2]);
						broker.store(cartera);
					}					
				}
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
	
	
	private static ReportQueryByCriteria queryPagos() {

		Criteria criterio = new Criteria();

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetallePagos.class, criterio);
		query.setAttributes(new String[] { "carteraId", "facturaInterna",
				"fechaPago" });
		query.addGroupBy(new String[] { "carteraId", "facturaInterna",
				"fechaPago" });
		query.addOrderBy("carteraId", true);
		return query;
	}

}
