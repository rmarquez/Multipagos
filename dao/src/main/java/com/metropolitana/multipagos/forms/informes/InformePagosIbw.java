package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformePagosIbw extends UtilXls2Postgres {
	
	
	public List getConsolidadosPagos(Date fechaIni, Date fechaFin)
			throws Exception {
		Connection connPostgres = null;
		String query;
                PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
            try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			 
            query = " select distinct on (d.municipio_id) d.municipio_id, m.municipio_nombre, count(d.pago_id), d.fecha_pago, sum(d.salgo_pagar), sum(d.monto_pago) "+
            		"from detalle_ibw_pagos d inner join municipio m on m.municipio_id = d.municipio_id "+
            		"where fecha_pago >= ? and fecha_pago <= ? "+
            		"group by d.municipio_id, m.municipio_nombre, d.fecha_pago;";
            psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();
            while(rs.next()){
                
    			Object[] fila = { rs.getObject(1), rs.getObject(2),
    					rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6)};              

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}
	
	public List getDetallesPagos(Date fechaIni, Date fechaFin)
			throws Exception {
		Connection connPostgres = null;
		String query;
                PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
            try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			query = " select d.codigo, c.nombre, c.apellido, m.municipio_nombre, d.factura, d.monto_pago, d.fecha_pago, d.observaciones "+
					"from detalle_ibw_pagos d inner join cartera_ibw c on c.cartera_id=d.cartera_id "+
					"inner join municipio m on m.municipio_id = d.municipio_id "+
					"where d.fecha_pago >= ? and d.fecha_pago <= ?;";
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();
            while(rs.next()){
                
				Object[] fila = { rs.getObject(1), rs.getObject(2),
						rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8) };

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}
	
	private void ejecutarQuery(Connection connPostgres, String query)
			throws Exception {
		PreparedStatement psOrigen = null;
		ResultSet rsOrigen = null;

		try {

			psOrigen = connPostgres.prepareStatement(query);
			rsOrigen = psOrigen.executeQuery();

			rsOrigen.close();
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
