package com.metropolitana.multipagos.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.DetallePagos;
import com.metropolitana.multipagos.DetalleVisitas;


import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class ContadorVisitasPagos {
	
	public static Integer getCantidadVisitas() {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaVisita", fechaHoy);
		
		ReportQueryByCriteria query = new ReportQueryByCriteria(DetalleVisitas.class, criterio);
		Integer cantidad = Integer.valueOf(broker.getCount(query));
		broker.close();
		return cantidad;
	}
	
	public static Integer getCantidadPagos() {
		
		PersistenceBroker broker = null;

		broker = PersistenceBrokerFactory.defaultPersistenceBroker();
		Date fechaHoy = Calendar.getInstance().getTime();
		Criteria criterio = new Criteria();
		criterio.addEqualTo("fechaPago", fechaHoy);
		
		ReportQueryByCriteria query = new ReportQueryByCriteria(DetallePagos.class, criterio);
		Integer cantidad = Integer.valueOf(broker.getCount(query));
		broker.close();
		return cantidad;
	}

	
	
}
