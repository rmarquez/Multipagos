package com.metropolitana.multipagos.forms.informes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.ArqueoPagos;
import com.metropolitana.multipagos.DetalleVisitas;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class InformeArqueoDiario {
	
	public static List getVisitasXColector(Date fecha, Integer colectorId) throws Exception {
		PersistenceBroker broker = null;
		try {
			
			int numRepetidos = 0;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker
					.getReportQueryIteratorByQuery(queryArqueo(fecha, colectorId)); iter.hasNext();) {
				Object[] arqueo = (Object[]) iter.next();
				
				for (Iterator iterDetalle = broker
						.getReportQueryIteratorByQuery(queryArqueoCantidad((Integer)arqueo[0])); iterDetalle.hasNext();) {
					Object[] cantidad = (Object[]) iterDetalle.next();
	                
				}
				
                lista.add(arqueo);
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
	
	private static ReportQueryByCriteria queryArqueo(Date fecha,
			Integer colectorId) {

		Criteria criterio = new Criteria();

		if (fecha != null) {
			criterio.addEqualTo("pagoFecha", fecha);
		}
		if (colectorId != null) {
			criterio.addEqualTo("colectorId", colectorId);
		}

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				ArqueoPagos.class, criterio);
		query.setAttributes(new String[] { "arqueoId", "totalRecibo",
				"totalGeneral", "diferencia" });

		query.addGroupBy(new String[] { "arqueoId", "totalRecibo",
				"totalGeneral", "diferencia" });

		query.addOrderBy("arqueoId", true);
		return query;
	}
	
	private static ReportQueryByCriteria queryArqueoCantidad(Integer arqueoId) {

		Criteria criterio = new Criteria();

		if (arqueoId != null) {
			criterio.addEqualTo("arqueoId", arqueoId);
		}

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				ArqueoPagos.class, criterio);
		query.setAttributes(new String[] { "cantidadId",
				"cantidadIdRef.cantidadNombre", "cantidad", "total" });

		query.addGroupBy(new String[] { "cantidadId",
				"cantidadIdRef.cantidadNombre", "cantidad", "total" });

		query.addOrderBy("cantidadId", true);
		return query;
	}

	private static ReportQueryByCriteria queryArqueoCantidadUs(Integer arqueoId) {

		Criteria criterio = new Criteria();

		if (arqueoId != null) {
			criterio.addEqualTo("arqueoId", arqueoId);
		}

		ReportQueryByCriteria query = new ReportQueryByCriteria(
				ArqueoPagos.class, criterio);
		query.setAttributes(new String[] { "cantidadId",
				"cantidadIdRef.cantidadNombre", "cantidad", "total" });

		query.addGroupBy(new String[] { "cantidadId",
				"cantidadIdRef.cantidadNombre", "cantidad", "total" });

		query.addOrderBy("cantidadId", true);
		return query;
	}

}
