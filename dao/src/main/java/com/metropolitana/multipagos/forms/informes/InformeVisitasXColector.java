package com.metropolitana.multipagos.forms.informes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



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
public class InformeVisitasXColector {
	
	
	public static List getVisitasXColector(Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer colectorId) throws Exception {
		PersistenceBroker broker = null;
		try {
			int visitas = 0;
			int numRepetidos = 0;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryVisitasXColector(fechaIni, fechaFin, departamentoId, colectorId)); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				visitas++;
				
				detalle[15]= visitas;
				
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
	
	private static ReportQueryByCriteria queryVisitasXColector(Date fechaIni, Date fechaFin, Integer departamentoId, Integer colectorId) {

		Criteria criterio = new Criteria();

		if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("fechaVisita", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("fechaVisita", fechaFin);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
		}
        
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetalleVisitas.class, criterio);
		query.setAttributes(new String[] { "visitaId", "numeroContrato",
				"carteraIdRef.suscriptor",
				"carteraIdRef.departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre", "fechaVisita",
				"simboloIdRef.simboloNumero", "simboloIdRef.simboloNombre",
				"servicioIdRef.servicioNombre", "colectorId",
				"colectorIdRef.colectorNumero", "colectorIdRef.primerNombre",
				"colectorIdRef.primerApellido", "avisoCobro", "fprogCobro",
				"0.00", "0.00" });

		query.addGroupBy(new String[] { "visitaId", "numeroContrato",
				"carteraIdRef.suscriptor",
				"carteraIdRef.departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre", "fechaVisita",
				"simboloIdRef.simboloNumero", "simboloIdRef.simboloNombre",
				"servicioIdRef.servicioNombre", "colectorId",
				"colectorIdRef.colectorNumero", "colectorIdRef.primerNombre",
				"colectorIdRef.primerApellido", "avisoCobro", "fprogCobro" });
		

		query.addOrderBy("avisoCobro", true);
		query.addOrderBy("fechaVisita", true);
		query.addOrderBy("colectorId", true);
		return query;
	}

}
