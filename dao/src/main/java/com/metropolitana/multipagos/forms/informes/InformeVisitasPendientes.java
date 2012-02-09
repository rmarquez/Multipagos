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

public class InformeVisitasPendientes {

	public static List getVisitasPendientes(Date fechaIngreso, Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer servicioId)
			throws Exception {
		PersistenceBroker broker = null;
		try {
			int pend = 0;
			int visit = 0;
			String contrato = "";
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			
			for (Iterator iterPendiente = broker
					.getReportQueryIteratorByQuery(query(fechaIngreso,
							fechaIni, fechaFin, departamentoId, servicioId, broker)); iterPendiente.hasNext();) {
				Object[] pendiente = (Object[]) iterPendiente.next();
				//pend++;
				//pendiente[9] = pend;				

				lista.add(pendiente);

			}
			/**
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryVisitas(fechaIngreso,
							fechaIni, fechaFin, departamentoId, servicioId)); iter
					.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				contrato = (String) detalle[0];							
				visit++;				
			}
			
			System.out.println("cantidad de visitas = " + visit); 
			
			for (Iterator iterPendiente = broker
					.getReportQueryIteratorByQuery(queryVisitasPendientes(
							fechaIngreso, contrato, departamentoId, servicioId)); iterPendiente.hasNext();) {
				Object[] pendiente = (Object[]) iterPendiente.next();
				pend++;
				pendiente[9] = pend;				

				lista.add(pendiente);

			}
			System.out.println("cantidad de pendientes = " + pend); 
			**/
			return lista;
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	
	private static ReportQueryByCriteria query(Date fechaIngreso,
			Date fechaIni, Date fechaFin, Integer departamentoId,
			Integer servicioId, PersistenceBroker broker) throws Exception {

		Criteria subCriterio = new Criteria();
		if (fechaIngreso != null) {
			subCriterio.addEqualTo("carteraIdRef.fechaIngreso", fechaIngreso);
		}
		if (servicioId != null) {
			subCriterio.addEqualTo("servicioId", servicioId);
		}
		if (fechaIni != null) {
			subCriterio.addGreaterOrEqualThan("fechaVisita", fechaIni);
		}
		if (fechaFin != null) {
			subCriterio.addLessOrEqualThan("fechaVisita", fechaFin);
		}
		ReportQueryByCriteria subQuery = new ReportQueryByCriteria(
				DetalleVisitas.class, subCriterio);
		subQuery.setAttributes(new String[] { "numeroContrato" });
		subQuery.addGroupBy("numeroContrato");

		Criteria criterio = new Criteria();
		criterio.addNotIn("contrato", subQuery);

		if (fechaIngreso != null) {
			criterio.addEqualTo("fechaIngreso", fechaIngreso);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("departamentoId", departamentoId);
		}

		if (servicioId != null) {
			criterio.addEqualTo("servicioId", servicioId);
		}
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				CarteraXDepartamento.class, criterio);
		query.setAttributes(new String[] { "contrato", "facturaInterna",
				"suscriptor", "departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre",
				"servicioIdRef.servicioNombre", "anio", "mes", "fechaIngreso",
				"0.00" });
		query.setDistinct(true);
		query.addGroupBy(new String[] { "contrato", "facturaInterna",
				"suscriptor", "departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre",
				"servicioIdRef.servicioNombre", "anio", "mes", "fechaIngreso" });
		return query;
	}
	 
	private static ReportQueryByCriteria queryVisitas(Date fechaIngreso,
			Date fechaIni, Date fechaFin, Integer departamentoId,
			Integer servicioId) {
		
        Criteria criterio = new Criteria();
        
        if (fechaIngreso != null) {
        	criterio.addEqualTo("carteraIdRef.fechaIngreso", fechaIngreso);
		}
        if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("fechaVisita", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("fechaVisita", fechaFin);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		if (servicioId != null) {
			criterio.addEqualTo("servicioId", servicioId);
		}

		ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleVisitas.class, criterio);
		query.setAttributes(new String[] { "numeroContrato" });
		query.addGroupBy(new String[] { "numeroContrato" });

		query.addOrderBy("numeroContrato", true);
		return query;
	}

	private static ReportQueryByCriteria queryVisitasPendientes(
			Date fechaIngreso, String numeroContrato, Integer departamentoId,
			Integer servicioId) {

		Criteria criterio = new Criteria();

		if (fechaIngreso != null) {
			criterio.addEqualTo("fechaIngreso", fechaIngreso);
		}
		if (numeroContrato != null) {
			criterio.addNotEqualTo("contrato", numeroContrato);
             //criterio.addNotIn("contrato", numeroContrato);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("departamentoId", departamentoId);
		}
		
		if (servicioId != null) {
			criterio.addEqualTo("servicioId", servicioId);
		}
		criterio.addEqualTo("pagado", Boolean.FALSE);
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				CarteraXDepartamento.class, criterio);
		query.setDistinct(true);
		query.setAttributes(new String[] { "contrato", "facturaInterna",
				"suscriptor", "departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre",
				"servicioIdRef.servicioNombre", "anio", "mes",
				"fechaIngreso", "0.00" });

		query.addGroupBy(new String[] { "contrato", "facturaInterna",
				"suscriptor", "departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre",
				"servicioIdRef.servicioNombre", "anio", "mes", "fechaIngreso" });

		query.addOrderBy("contrato", true);
		query.addOrderBy("fechaIngreso", true);
		return query;
	}

}
