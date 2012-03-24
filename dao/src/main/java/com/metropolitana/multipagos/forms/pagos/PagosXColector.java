package com.metropolitana.multipagos.forms.pagos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.DetallePagos;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;



public class PagosXColector {
	
	
	public static List getListPagosXColector(Date fecha, Integer colectorId)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			List<Object[]> pagos = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker.getReportQueryIteratorByQuery(queryPagosXColector(fecha, colectorId)); iter.hasNext();) {

				Object[] detalle = (Object[]) iter.next();

				pagos.add(detalle);
			}
			return pagos;
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	private static ReportQueryByCriteria queryPagosXColector(Date fecha, Integer colectorId) {

		Criteria criterio = new Criteria();
		if (fecha != null) {
			criterio.addEqualTo("fechaPago", fecha);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
		}
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetallePagos.class, criterio);
		query.setAttributes(new String[] { "recibo", "facturaInterna",
				"numeroContrato", "fechaPago", "montoPago",
				"localidadIdRef.localidadNombre", "carteraIdRef.suscriptor",
				"carteraIdRef.mes", "carteraIdRef.anio", "recibo" });
		query.addGroupBy(new String[] { "recibo", "facturaInterna",
				"numeroContrato", "fechaPago", "montoPago",
				"localidadIdRef.localidadNombre", "carteraIdRef.suscriptor",
				"carteraIdRef.mes", "carteraIdRef.anio", "recibo" });

		query.addOrderBy("recibo", true);
		query.addOrderBy("fechaPago", true);
		return query;
	}

}
