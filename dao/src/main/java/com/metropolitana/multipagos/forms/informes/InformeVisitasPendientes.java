package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.DetalleVisitas;
import com.metropolitana.multipagos.forms.Util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
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
					.getReportQueryIteratorByQuery(selectVisitasPendiente(fechaIngreso,
							fechaIni, fechaFin, departamentoId, servicioId)); iterPendiente.hasNext();) {
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
			Integer servicioId) throws Exception {

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
		ReportQueryByCriteria subQuery = new ReportQueryByCriteria(DetalleVisitas.class, subCriterio);
		subQuery.setAttributes(new String[] { "numeroContrato" });

		Criteria criterio = new Criteria();
		criterio.addNotIn("contrato", subQuery);

		if (fechaIngreso != null) {
			criterio.addEqualTo("fechaIngreso", fechaIngreso);
		}
		if (departamentoId != null) {
			criterio.addEqualTo("departamentoId", departamentoId);
		}
		
		ReportQueryByCriteria query = new ReportQueryByCriteria(CarteraXDepartamento.class, criterio);
		
		query.setAttributes(new String[] { "contrato", "facturaInterna",
				"suscriptor", "departamentoIdRef.departamentoNombre",
				"localidadIdRef.localidadNombre",
				"servicioIdRef.servicioNombre", "anio", "mes", "fechaIngreso",
				"0.00" });
		query.setDistinct(true);
		
		return query;
	}
	
	public ResultSet selectVisitasPendiente(Date fechaIngreso,
			Date fechaIni, Date fechaFin, Integer departamentoId,
			Integer servicioId) throws Exception {
		
		Connection connPostgres = null;
		String selectQuery;

		try {
			// Parámetros de conexión con Postgres
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);

			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			selectQuery = "SELECT DISTINCT A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE,A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,0.00 FROM ((CARTERA_X_DEPARTAMENTO A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID WHERE ((A0.CONTRATO NOT IN  (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN CARTERA_X_DEPARTAMENTO B1 ON B0.CARTERA_ID=B1.CARTERA_ID WHERE (((B1.FECHA_INGRESO = ?) AND B0.SERVICIO_ID = ?) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ?) AND A0.DEPARTAMENTO_ID = ?"; 
			
					/**
					 stmt = con.prepareStatement("SELECT nombre, edad, profesion, ciudad FROM Personas WHERE edad > ? AND ciudad = ?");
					 
			stmt.setInt(1,25);
			stmt.setString(2,"Salamanca");
			*/
			return ejecutarSelectQuery(connPostgres, selectQuery, fechaIngreso, fechaIni, fechaFin, departamentoId, servicioId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connPostgres != null) connPostgres.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private ResultSet ejecutarSelectQuery(Connection connPostgres, String selectQuery, Date fechaIngreso,
			Date fechaIni, Date fechaFin, Integer departamentoId,
			Integer servicioId) {
		
		PreparedStatement psOrigen = null;	
		ResultSet rs = null;

		try {
			psOrigen = connPostgres.prepareStatement(selectQuery);
			java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIngreso));
			psOrigen.setDate(1,fecha);
            psOrigen.setInt(2, servicioId);
            java.sql.Date fechaI = java.sql.Date.valueOf(getFechaSQL(fechaIni));
			psOrigen.setDate(3,fechaI);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
			psOrigen.setDate(4,fechaF);
			psOrigen.setDate(5,fecha);
			psOrigen.setInt(6, departamentoId);
            
			 return rs = psOrigen.executeQuery();
						
			
		} catch (Exception e) {
			e.printStackTrace();
                        psOrigen.close();
		}
	}
        
	private String getFechaSQL(final Date asignado) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(asignado);
	}
	 
	/*private static ReportQueryByCriteria queryVisitas(Date fechaIngreso,
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
	}*/

}
