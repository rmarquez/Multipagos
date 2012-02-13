package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.Format;
import java.text.SimpleDateFormat;

public class InformeVisitasPendientes {

	public static List getVisitasPendientes(Date fechaIngreso, Date fechaIni,
			Date fechaFin, Integer departamentoId, Integer servicioId)
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);

			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			selectQuery = "SELECT DISTINCT ON (A0.CONTRATO) A0.CONTRATO,A0.FACTURA_INTERNA,A0.SUSCRIPTOR,A1.DEPARTAMENTO_NOMBRE,A2.LOCALIDAD_NOMBRE,A3.SERVICIO_NOMBRE,A0.ANIO,A0.MES,A0.FECHA_INGRESO,0.00 FROM ((CARTERA_X_DEPARTAMENTO A0 INNER JOIN DEPARTAMENTO A1 ON A0.DEPARTAMENTO_ID=A1.DEPARTAMENTO_ID) INNER JOIN LOCALIDAD A2 ON A0.LOCALIDAD_ID=A2.LOCALIDAD_ID) INNER JOIN SERVICIO A3 ON A0.SERVICIO_ID=A3.SERVICIO_ID WHERE ((A0.CONTRATO NOT IN  (SELECT B0.NUMERO_CONTRATO FROM DETALLE_VISITAS B0 INNER JOIN CARTERA_X_DEPARTAMENTO B1 ON B0.CARTERA_ID=B1.CARTERA_ID WHERE (((B1.FECHA_INGRESO = ?) AND B0.SERVICIO_ID = ?) AND B0.FECHA_VISITA >= ?) AND B0.FECHA_VISITA <= ?) ) AND A0.FECHA_INGRESO = ?) AND A0.DEPARTAMENTO_ID = ?"; 
			
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
    
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9) };
                

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
	 
	

}
