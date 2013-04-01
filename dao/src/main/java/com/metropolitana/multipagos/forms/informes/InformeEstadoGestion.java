package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformeEstadoGestion extends UtilXls2Postgres {
	
	public List getEstadoGestion(Date fechaIni, Date fechaFin, Integer simboloId)
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
			
			if(simboloId != null){
				query = "select ca.nombre, ca.producto, ca.cuenta, ca.tarjeta, ci.ciudad_nombre, ci2.ciudad_nombre, s.simbolo_numero, "+
						"s.simbolo_nombre, d.observaciones, d.fecha_gestion, s.simbolo_id "+
						"from detalle_g_banpro d inner join cartera_banpro ca on d.tmp_id=ca.tmp_id "+
						"inner join simbolo_banpro s on d.simbolo_id=s.simbolo_id "+
						"inner join ciudad ci on d.ciudad_c_id=ci.ciudad_id "+
						"inner join ciudad ci2 on d.ciudad_c_id=ci2.ciudad_id "+
						"where d.fecha_gestion >= ? and d.fecha_gestion <= ? and d.simbolo_id=?;";
			} else {
				query = "select ca.nombre, ca.producto, ca.cuenta, ca.tarjeta, ci.ciudad_nombre, ci2.ciudad_nombre, s.simbolo_numero, "+
						"s.simbolo_nombre, d.observaciones, d.fecha_gestion, s.simbolo_id "+
						"from detalle_g_banpro d inner join cartera_banpro ca on d.tmp_id=ca.tmp_id "+
						"inner join simbolo_banpro s on d.simbolo_id=s.simbolo_id "+
						"inner join ciudad ci on d.ciudad_c_id=ci.ciudad_id "+
						"inner join ciudad ci2 on d.ciudad_c_id=ci2.ciudad_id "+
						"where d.fecha_gestion >= ? and d.fecha_gestion <= ?";	
			}
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            if(simboloId != null){
            	psOrigen.setInt(3, simboloId);
            }
            rs = psOrigen.executeQuery();
			while(rs.next()){
                
				Object[] fila = { rs.getObject(1), rs.getObject(2),
						rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8),
						rs.getObject(9),rs.getObject(10),rs.getObject(11)};

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}

}
