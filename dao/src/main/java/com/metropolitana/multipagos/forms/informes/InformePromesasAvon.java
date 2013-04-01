package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformePromesasAvon extends UtilXls2Postgres {
	
	public List getPromesasAvon(Date fechaIni, Date fechaFin, Integer colectorId)
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
			
			if (colectorId != null ){
				query = " select d.codigo, c.consejero, c.factura, c.mes, c.saldo, c.direccion, dp.departamento_nombre, l.localidad_nombre, "+
						" (upper(co.primer_nombre) || ' '  || upper(co.primer_apellido)), d.fprog_cobro, d.observaciones "+
						" from detalle_gestion d inner join cartera_avon c on c.cavon_id=d.cavon_id "+
						" inner join departamento dp on dp.departamento_id=c.departamento_id "+
						" inner join localidad l on l.localidad_id=c.localidad_id "+
						" inner join colector co on co.colector_id=d.colector_id "+
						" where d.fprog_cobro >= ? and d.fprog_cobro <= ? and d.colector_id = ?";
			} else {
				query = " select d.codigo, c.consejero, c.factura, c.mes, c.saldo, c.direccion, dp.departamento_nombre, l.localidad_nombre, "+
						" (upper(co.primer_nombre) || ' '  || upper(co.primer_apellido)), d.fprog_cobro, d.observaciones "+
						" from detalle_gestion d inner join cartera_avon c on c.cavon_id=d.cavon_id "+
						" inner join departamento dp on dp.departamento_id=c.departamento_id "+
						" inner join localidad l on l.localidad_id=c.localidad_id "+
						" inner join colector co on co.colector_id=d.colector_id "+
						" where d.fprog_cobro >= ? and d.fprog_cobro <= ?;";
			}
			
			
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            if(colectorId != null) {
				psOrigen.setInt(3, colectorId);
				rs = psOrigen.executeQuery();
			
			} else {
				rs = psOrigen.executeQuery();
			}
            while(rs.next()){
                
				Object[] fila = { rs.getObject(1), rs.getObject(2),
						rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8),
						rs.getObject(9),rs.getObject(10), rs.getObject(11) };

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}

}
