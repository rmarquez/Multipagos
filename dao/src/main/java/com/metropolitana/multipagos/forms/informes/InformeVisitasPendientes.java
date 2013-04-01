package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.text.Format;
import java.text.SimpleDateFormat;

public class InformeVisitasPendientes {

	public static List getVisitasPendientes(Date fechaIngreso, Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer localidadId, Integer barrioId
			, Integer numAsignacion)
			throws Exception {
            
		Connection connPostgres = null;
		String selectQuery;
                PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
            try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			//System.out.println("Departamento ID = " + departamentoId);
			
			if(departamentoId != null && localidadId == null && barrioId == null){
				// Limpiamos la tabla tmp_cartera antes de insertar los datos
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO " +
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?)  AND A0.DEPARTAMENTO_ID = ?";
			} else if (departamentoId == null && localidadId != null && barrioId == null) {
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?) AND A0.LOCALIDAD_ID = ?";
			} else if (departamentoId == null && localidadId == null && barrioId != null) {
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?) AND A0.BARRIO_ID = ?";
			} else if (departamentoId != null && localidadId != null && barrioId == null) {
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?)AND A0.DEPARTAMENTO_ID = ? AND A0.LOCALIDAD_ID = ?";
			} else if (departamentoId != null && localidadId != null && barrioId != null) {
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?)AND A0.DEPARTAMENTO_ID = ? AND A0.LOCALIDAD_ID = ? AND A0.BARRIO_ID = ?";
			} else {
				selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE, " +
				"A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,A0.DIRECCION, A0.TELEFONO, B1.BARRIO_NOMBRE, A0.SALDO FROM ((asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) " +
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID " +
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION =?)";
			}
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
                    
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIngreso));
            psOrigen.setDate(1,fecha);
            //psOrigen.setInt(2, servicioId);
            java.sql.Date fechaI = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(2,fechaI);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(3,fechaF);
            psOrigen.setDate(4,fecha);
            psOrigen.setInt(5, numAsignacion);
            
            if(departamentoId != null && localidadId == null && barrioId == null){
            	psOrigen.setInt(6, departamentoId);
            } 
            if(departamentoId == null && localidadId != null && barrioId == null){
            	psOrigen.setInt(6, localidadId);
            }
            if(departamentoId == null && localidadId == null && barrioId != null){
            	psOrigen.setInt(6, barrioId);
            }
            if(departamentoId != null && localidadId != null && barrioId == null){
            	psOrigen.setInt(6, departamentoId);
            	psOrigen.setInt(7, localidadId);
            }
            if(departamentoId != null && localidadId != null && barrioId != null){
            	psOrigen.setInt(6, departamentoId);
            	psOrigen.setInt(7, localidadId);
            	psOrigen.setInt(8, barrioId);
            }
            
    
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
        
	private static String getFechaSQL(final Date asignado) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(asignado);
	}
	 
	public static Integer contarPendientes(Date fechaIngreso, Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer localidadId, Integer barrioId
			, Integer numAsignacion)
			throws Exception {
            
		Connection connPostgres = null;
		String selectQuery;
        PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		int max = 0;
		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
            try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			/*selectQuery = "select distinct A0.CONTRATO into tmp_count FROM (("+ 
			"asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) "+
			"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID "+
			"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
			"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 "+
			"INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
			"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?)) "+
			"AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?)AND A0.DEPARTAMENTO_ID = ? AND A0.LOCALIDAD_ID = ? "+
			"AND A0.BARRIO_ID = ? ";*/
			
			
			selectQuery = "SELECT count(*) from ("+
				"SELECT DISTINCT A0.CONTRATO FROM (("+
				"asignacion_claro A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) "+
				"INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID "+
				"INNER JOIN BARRIO B1 ON A0.BARRIO_ID=B1.BARRIO_ID "+
				"WHERE ((A0.CONTRATO NOT IN (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 "+
				"INNER JOIN asignacion_claro B1 ON B0.NUMERO_CONTRATO=B1.CONTRATO "+
				"WHERE (((B1.FECHA_INGRESO = ?)) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?)) "+
				"AND A0.FECHA_INGRESO = ? AND A0.NUM_ASIGNACION = ?)AND A0.DEPARTAMENTO_ID = ? AND A0.LOCALIDAD_ID = ? "+
				"AND A0.BARRIO_ID = ?) as parametros;";
			
			psOrigen = connPostgres.prepareStatement(selectQuery);
			
			java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIngreso));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaI = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(2,fechaI);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(3,fechaF);
            psOrigen.setDate(4,fecha);
            psOrigen.setInt(5, numAsignacion);
            psOrigen.setInt(6, departamentoId);
        	psOrigen.setInt(7, localidadId);
        	psOrigen.setInt(8, barrioId);
			
            rs = psOrigen.executeQuery();
            
            rs.next();
            int count = rs.getInt(1);
            
            rs.close();
			psOrigen.close();
			
            return count;
            
            
          /*  while(rs.next()) {
             max = rs.getInt(1);
            }
	             
            return max;*/
		} catch (Exception e) {
			throw e;
		} 
	}
	
	/**
	public String verificarFacturasPendiente(Integer cliente) throws Exception {
         PersistenceBroker broker = null;
         try {
             broker = PersistenceBrokerFactory.defaultPersistenceBroker();

            Criteria criterio1 = new Criteria();
            // cliente a buscar
            criterio1.addEqualTo("cliId", cliente);
            ReportQueryByCriteria subQuery = new ReportQueryByCriteria(Factura.class, criterio1);
            subQuery.setAttributes(new String[] { "ventaId" });
            //Indicamos que filtre solo las facturas creadas
            Criteria criterioHaving = new Criteria();
            criterioHaving.addEqualTo("max(facturaEstadoList.facEstadoId)", FacEstadoHandler.FACEST_ELABORADA);
            subQuery.setHavingCriteria(criterioHaving);
            subQuery.addGroupBy(new String[] { "ventaId" });

            Criteria criterio = new Criteria();
            criterio.addIn("ventaId", subQuery);
            criterio.addEqualTo("tipofId", TipoFacturaHandler.TIPOFAC_CREDITO);
            ReportQueryByCriteria query = new ReportQueryByCriteria(Factura.class, criterio);
            query.setAttributes(new String[] {"COUNT(*)"});
            //Recorrer el array que retorna la consultaFormulario para subir base de datos Banpro --> de 6 a 8 horas (necesitamos la base con el numero unico)
Simbologia Banpro --> 3 horas
Formulario de visitas banpro --> 8 horas (en este formulario se ingresaran los descuentos y los plazos ?)

Reportes de sistema
-Promesas de pago  --> 3 horas
-Estado de gestion --> 3 horas
    -Fue gestionado resultado
    -No fue gestionado porque

-Informe de mensual consolide gestiones y pagos, considerados irrecuperables. --> 4 horas 


-Pantalla consulta que muestre historial de gestion --> 6 horas (esto es un formulario).

- Proceso para reducir montos cuando el cliente ha pagado --> 6 horas
            Iterator iter = broker.getReportQueryIteratorByQuery(query);
            if (iter.hasNext()) {
                Object val = ((Object[]) iter.next())[0];
                if (val != null) {
                    Long cantidad = (Long) val;
                    if (cantidad.longValue()  > 0){
                        String msg = "¡Este cliente, tiene " + cantidad + " facturas pendientes de pagar!";
                        return  msg;
                    }
                }
            }
            return null;

         } catch (Exception e) {
             throw e;
         } finally {
             if (broker != null && !broker.isClosed()) {
                 broker.close();
             }
         }
    }
	 */
}
