package com.metropolitana.multipagos.forms.informes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.CarteraXDepartamento;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * 
 * @author rmarquez 2012-03-05
 *
 */

public class InformePagosClaro {
	
	public static List getPagosClaro(Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer servicioId) throws Exception {
		PersistenceBroker broker = null;
		try {
			BigDecimal montoTotal = BigDecimal.ZERO;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryPagosClaro(fechaIni, fechaFin, departamentoId, servicioId)); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				
				montoTotal = montoTotal.add((BigDecimal)detalle[5]);
				detalle[9] =montoTotal;
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
	
	private static ReportQueryByCriteria queryPagosClaro(
			Date fechaIni, Date fechaFin, Integer departamentoId,
			Integer servicioId) {

		Criteria criterio = new Criteria();

		if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("fechaPago", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("fechaPago", fechaFin);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("departamentoId", departamentoId);
		}
		if (servicioId != null) {
			criterio.addEqualTo("servicioId", servicioId);
		}
		criterio.addEqualTo("pagadoClaro", true);

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				CarteraXDepartamento.class, criterio);
		query.setAttributes(new String[] { "departamentoIdRef.departamentoNombre", "contrato", "suscriptor",
				"facturaInterna", "servicioIdRef.servicioNombre", "saldo", "anio", "mes", "fechaPago",
				"0.00" });

		query.addGroupBy(new String[] { "departamentoIdRef.departamentoNombre", "contrato", "suscriptor",
				"facturaInterna", "servicioIdRef.servicioNombre", "saldo", "anio", "mes", "fechaPago" });

		query.addOrderBy("departamentoIdRef.departamentoNombre", true);
		query.addOrderBy("fechaPago", true);
		return query;
	}

}
