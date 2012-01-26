package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.DetalleVisitas;


import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class ContadorVisitasPagos {
	
	public static Integer getCantidadVisitas(Integer departamentoId) {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaVisita", fechaHoy);
		if(departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		
		ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleVisitas.class, criterio);
		Integer cantidad = Integer.valueOf(broker.getCount(query));
		broker.close();
		return cantidad;
	}
	
	public static Integer getCantidadPagos(Integer departamentoId) {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaPago", fechaHoy);
		if(departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		
		ReportQueryByCriteria query = new ReportQueryByCriteria(DetallePagos.class, criterio);
		Integer cantidad = Integer.valueOf(broker.getCount(query));
		broker.close();
		return cantidad;
	}

	public static List getControlDiario() throws Exception {
		PersistenceBroker broker = null;
		try {
			BigDecimal montoTotal = BigDecimal.ZERO;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker.getReportQueryIteratorByQuery(queryCantidadesDepartamentos()); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
				
				Integer visitas = getCantidadVisitas((Integer)detalle[0]);
				Integer pagos = getCantidadPagos((Integer)detalle[0]);
				
				
				detalle[2] =visitas;
				detalle[3] =pagos;
				
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
	
	private static ReportQueryByCriteria queryCantidadesDepartamentos() {

		Criteria criterio = new Criteria();		
		criterio.addEqualTo("inactivo", Boolean.FALSE);		
        
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				Departamento.class, criterio);
		query.setAttributes(new String[] { "departamentoId",
				"departamentoNombre", "0.00", "0.00" });

		query.addGroupBy(new String[] { "departamentoId",
				"departamentoNombre" });

		query.addOrderBy("departamentoId", true);
		return query;
	}
	
}
