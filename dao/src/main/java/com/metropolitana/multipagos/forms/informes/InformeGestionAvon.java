package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformeGestionAvon extends UtilXls2Postgres {
	
	public List getGestionAvon(Date fechaIni, Date fechaFin)
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
			
			query = " select d.codigo, c.zona, c.consejero, d.fecha_gestion, dp.departamento_nombre, l.localidad_nombre, d.observaciones,"+
					" s.simbolo_nombre, (upper(co.primer_nombre) || ' '  || upper(co.primer_apellido)), u.usr_full_name, d.hora_registro, d.aviso_cobro"+
					" from detalle_gestion d inner join cartera_avon c on c.cavon_id=d.cavon_id"+
					" inner join departamento dp on dp.departamento_id=c.departamento_id"+
					" inner join localidad l on l.localidad_id=c.localidad_id"+
					" inner join simbolo_avon s on s.simbolo_id=d.simbolo_id"+
					" inner join colector co on co.colector_id=d.colector_id"+
					" inner join gestion_avon g on g.gestion_id=d.gestion_id"+
					" inner join auth_user u on u.usr_id=g.usr_id"+
					" where fecha_gestion >= ? and fecha_gestion <= ?;";
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();;
            while(rs.next()){
                
				Object[] fila = { rs.getObject(1), rs.getObject(2),
						rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8),
						rs.getObject(9), rs.getObject(10), rs.getObject(11),
						rs.getObject(12) };

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}

}
