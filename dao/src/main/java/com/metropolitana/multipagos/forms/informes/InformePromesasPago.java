package com.metropolitana.multipagos.forms.informes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.DetalleVisitas;



import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;



/**
 * 
 * @author rmarquez
 *
 */
public class InformePromesasPago {
	
	
	public static List getPromesasPago(Date fechaIni,
			Date fechaFin, Integer colectorId) throws Exception {
		PersistenceBroker broker = null;
		try {
			int visitas = 0;
			int numRepetidos = 0;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryPromesas(fechaIni, fechaFin, colectorId)); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				visitas++;
				
                lista.add(detalle);
			}
			return lista;
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	private static ReportQueryByCriteria queryPromesas(Date fechaIni,
			Date fechaFin, Integer colectorId) {
		Criteria criterio = new Criteria();

		if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("fprogCobro", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("fprogCobro", fechaFin);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
		}

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetalleVisitas.class, criterio);
		query.setAttributes(new String[] { "visitaId", "colectorId", "numeroContrato",
				"carteraIdRef.suscriptor", "carteraIdRef.direccion", "localidadIdRef.departamentoIdRef.departamentoNombre",
				"carteraIdRef.facturaInterna", "carteraIdRef.mes", "carteraIdRef.saldo",
				"fprogCobro", "colectorIdRef.primerNombre", "colectorIdRef.primerApellido" });

		query.addGroupBy(new String[] { "visitaId", "colectorId", "numeroContrato",
				"carteraIdRef.suscriptor", "carteraIdRef.direccion", "localidadIdRef.departamentoIdRef.departamentoNombre",
				"carteraIdRef.facturaInterna", "carteraIdRef.mes", "carteraIdRef.saldo",
				"fprogCobro", "colectorIdRef.primerNombre", "colectorIdRef.primerApellido" });

		query.addOrderBy("colectorId", true);
		query.addOrderBy("fprogCobro", true);
		return query;
	}
	
	

}
