package com.metropolitana.multipagos.forms.informes;

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

/**
 * 
 * @author rmarquez
 *
 */
public class InformePagosXDepartamento {
	
	public static List getPagosXDepartamentos(Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer colectorId) throws Exception {
		PersistenceBroker broker = null;
		try {
			BigDecimal montoTotal = BigDecimal.ZERO;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryPagosXDepartamentos(fechaIni, fechaFin, departamentoId, colectorId)); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				
				montoTotal = montoTotal.add((BigDecimal)detalle[7]);
				detalle[14] =montoTotal;
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
	
	private static ReportQueryByCriteria queryPagosXDepartamentos(Date fechaIni, Date fechaFin, Integer departamentoId, Integer colectorId) {

		Criteria criterio = new Criteria();

		if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("fechaPago", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("fechaPago", fechaFin);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
		}
        
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetallePagos.class, criterio);
		query.setAttributes(new String[] { "pagoId",
				"carteraIdRef.departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre", "numeroContrato",
				"carteraIdRef.facturaInterna", "carteraIdRef.numeroFiscal",
				"carteraIdRef.cupon", "montoPago", "carteraIdRef.anio",
				"carteraIdRef.mes", "carteraIdRef.suscriptor", "fechaPago",
				"servicioIdRef.servicioNombre", "carteraIdRef.cuenta", "0.00" });

		query.addGroupBy(new String[] { "pagoId",
				"carteraIdRef.departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre", "numeroContrato",
				"carteraIdRef.facturaInterna", "carteraIdRef.numeroFiscal",
				"carteraIdRef.cupon", "montoPago", "carteraIdRef.anio",
				"carteraIdRef.mes", "carteraIdRef.suscriptor", "fechaPago",
				"servicioIdRef.servicioNombre", "carteraIdRef.cuenta", });

		query.addOrderBy("pagoId", true);
		query.addOrderBy("fechaPago", true);
		return query;
	}

}
