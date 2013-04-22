package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
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
	
	public static Integer getCantidadVisitas(Integer departamentoId, Integer usrId) {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaVisita", fechaHoy);
		if(departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		if(usrId != null){
			criterio.addEqualTo("visitaIdRef.usrId", usrId);			
		}
		ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleVisitas.class, criterio);
		Integer cantidad = Integer.valueOf(broker.getCount(query));
		broker.close();
		return cantidad;
	}
	
	
	
	public static Integer getCantidadPagos(Integer departamentoId, Integer usrId) {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaPago", fechaHoy);
		if(departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		if(usrId != null){
			criterio.addEqualTo("pagoIdRef.usrId", usrId);			
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
				
				Integer visitas = getCantidadVisitas((Integer)detalle[0], null);
				Integer pagos = getCantidadPagos((Integer)detalle[0], null);
				
				
				detalle[2] =visitas;
				detalle[3] =pagos;
				
				Integer total = visitas + pagos;
				
				detalle[4] =total;
				Integer meta = 0;
				if((Integer)detalle[0] == 1){
					meta = Integer.valueOf(3100);
				}
				if((Integer)detalle[0] == 2){
					meta = Integer.valueOf(350);
				}
				if((Integer)detalle[0] == 3){
					meta = Integer.valueOf(300);
				}
				if((Integer)detalle[0] == 4){
					meta = Integer.valueOf(70);
				}
				if((Integer)detalle[0] == 5){
					meta = Integer.valueOf(490);
				}
				if((Integer)detalle[0] == 6){
					meta = Integer.valueOf(70);
				}
				if((Integer)detalle[0] == 7){
					meta = Integer.valueOf(70);
				}
				if((Integer)detalle[0] == 10){
					meta = Integer.valueOf(70);
				}
				Integer diferencia = (total - meta);
				
				detalle[5] = meta;
				detalle[6] =diferencia;
				
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
				"departamentoNombre", "0.00", "0.00", "0.00", "0.00", "0.00"});

		query.addGroupBy(new String[] { "departamentoId",
				"departamentoNombre" });

		query.addOrderBy("departamentoId", true);
		return query;
	}
	
	public static List getControlSupervisor() throws Exception {
		PersistenceBroker broker = null;
		try {
			BigDecimal montoTotal = BigDecimal.ZERO;
			List<Object[]> lista = new ArrayList<Object[]>();
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			for (Iterator iter = broker.getReportQueryIteratorByQuery(queryCantidadesSupervisor(Integer.valueOf(1))); iter.hasNext();) {
				Object[] detalle = (Object[]) iter.next();
			
				Integer meta = Integer.valueOf(600);
				//if((Integer)detalle[2] == 1){
						
					Integer visitas = getCantidadVisitas((Integer)detalle[2], (Integer)detalle[0]);
					Integer pagos = getCantidadPagos((Integer)detalle[2], (Integer)detalle[0]);
					
					
					detalle[5] =visitas;
					detalle[6] =pagos;
					
					Integer total = visitas + pagos;
					
					detalle[7] =total;
					String nombre = "";
					if(((Integer)detalle[4]) != null){
						nombre = "Equipo " + ((Integer)detalle[4]).toString();
					}
					
					Integer diferencia = (total - meta);
					detalle[1] = nombre;
					detalle[8] = meta;
					detalle[9] =diferencia;
					
	                lista.add(detalle);
				//}
				
				
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
	
	private static ReportQueryByCriteria queryCantidadesSupervisor(
			Integer departamentoId) {

		Criteria criterio = new Criteria();
		criterio.addEqualTo("visitaIdRef.usrIdRef.usrEnable", Boolean.TRUE);
		if (departamentoId != null) {
			criterio.addEqualTo("localidadIdRef.departamentoId", departamentoId);
		}
		ReportQueryByCriteria query = new ReportQueryByCriteria(
				DetalleVisitas.class, criterio);
		query.setAttributes(new String[] { "visitaIdRef.usrId",
				"visitaIdRef.usrIdRef.usrLogin",
				"localidadIdRef.departamentoId",
				"visitaIdRef.usrIdRef.usrPinicio",
				"visitaIdRef.usrIdRef.usrOrden", "0.00", "0.00", "0.00",
				"0.00", "0.00" });

		query.addGroupBy(new String[] { "visitaIdRef.usrId",
				"visitaIdRef.usrIdRef.usrLogin",
				"localidadIdRef.departamentoId",
				"visitaIdRef.usrIdRef.usrPinicio",
				"visitaIdRef.usrIdRef.usrOrden" });

		//query.addOrderBy("visitaIdRef.usrId", true);
		query.addOrderBy("visitaIdRef.usrIdRef.usrOrden", true);
		return query;
	}
	
}
