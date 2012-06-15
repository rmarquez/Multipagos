package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformeAvonConsolidadoVisitas extends UtilXls2Postgres {
	
	public void crearTabla() throws Exception {
		Connection connPostgres = null;
		String query;

		try {
			// Parámetros de conexión con Postgres
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
 			
			
			query = "drop table tmp_consolidado;";
            ejecutarQuery(connPostgres, query); 
            
            query = " select c.zona, a.fecha_gestion as fecha, ( "+ 
					" select case when s.simbolo_numero in ('21','22','23','24','25','26') then 1 "+
					" else 0 "+
					" end "+
					" from detalle_gestion d inner join simbolo_avon s on s.simbolo_id=d.simbolo_id "+
					" inner join cartera_avon c2 on c2.cavon_id=d.cavon_id "+
					" where s.simbolo_numero=s2.simbolo_numero "+
					" group by s.simbolo_numero "+
					" ) as localizados, 0 as monto "+
					" into tmp_consolidado "+
					" from detalle_gestion a inner join cartera_avon c on c.cavon_id=a.cavon_id "+
					" inner join simbolo_avon s2 on s2.simbolo_id=a.simbolo_id "+
					" union all "+
					" select c.zona as zona, a.fecha_pago as fecha, 1 as localizados, a.monto_pago as monto "+
					" from detalle_avon_pagos a inner join cartera_avon c on c.cavon_id=a.cavon_id;";
			ejecutarQuery(connPostgres, query); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connPostgres != null)
					connPostgres.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public List getConsolidadosVisitas(Date fechaIni)
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
			 
            query = " select distinct on (zona) zona, fecha, count(zona), sum(localizados), sum(monto) "+
            		" from tmp_consolidado "+
            		" where fecha = ?"+
            		" group by zona, fecha;";
            psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            //java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            //psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();;
            while(rs.next()){
                
    			Object[] fila = { rs.getObject(1), rs.getObject(2),
    					rs.getObject(3), rs.getObject(4), rs.getObject(5)};              

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}
	
	public List getDetallesVisitas(Date fechaIni)
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
			
			query = " select d.codigo, c.consejero, c.saldo, d.observaciones "+
					" from detalle_gestion d inner join cartera_avon c on c.cavon_id=d.cavon_id "+
					" inner join simbolo_avon s on s.simbolo_id=d.simbolo_id "+
					" where s.simbolo_numero in ('27','28','29','210','211','212','36','37') "+
					" and fecha_gestion = ?;";
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            //java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            //psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();;
            while(rs.next()){
                
    			Object[] fila = { rs.getObject(1), rs.getObject(2),
    					rs.getObject(3), rs.getObject(4)};              

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
