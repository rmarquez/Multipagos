package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformeNoPagoAvon extends UtilXls2Postgres {
	
	public List getConsolidadosNoPago(Date fechaIni)
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
			
			query = " select count(s.simbolo_nombre), s.simbolo_nombre, c.zona "+
					" from detalle_gestion d inner join cartera_avon c on c.cavon_id=d.cavon_id "+
					" inner join simbolo_avon s on s.simbolo_id=d.simbolo_id "+
					" where s.simbolo_numero in ('11','12','13','14','15','16') "+
					" and fecha_gestion = ? "+
					" group by s.simbolo_nombre, c.zona;";
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            //java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            //psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();
            while(rs.next()){
                
    			Object[] fila = { rs.getObject(1), rs.getObject(2), rs.getObject(3)};              

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}
	
	public List getDetallesNoPago(Date fechaIni)
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
					" where s.simbolo_numero in ('11','12','13','14','15','16') "+
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

}
