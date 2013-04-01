package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.metropolitana.multipagos.forms.xlstopostgresql.UtilXls2Postgres;

public class InformePromesasBanpro extends UtilXls2Postgres {
	
	public List getPromesasBanpro(Date fechaIni, Date fechaFin)
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
			
			query = "select ap.fecha, ap.nombre, ad.producto, ad.tarjeta, ad.cuenta, ad.deuda_cor, ad.deuda_dol, ad.total_deuda_dol, ap.descuento, ap.plazo, ap.cuota, ac.fecha_cuota "+
					"from arreglo_pago ap inner join arreglo_deuda ad on ap.arreglo_id=ad.arreglo_id "+
					"inner join arreglo_calendario ac on ap.arreglo_id=ac.arreglo_id "+
					"where ac.fecha_cuota >=? and ac.fecha_cuota <=?;";
			
			psOrigen = connPostgres.prepareStatement(query);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            rs = psOrigen.executeQuery();
			while(rs.next()){
                
				Object[] fila = { rs.getObject(1), rs.getObject(2),
						rs.getObject(3), rs.getObject(4), rs.getObject(5),
						rs.getObject(6), rs.getObject(7), rs.getObject(8),
						rs.getObject(9),rs.getObject(10), rs.getObject(11), 
						rs.getObject(12) };

                lista.add(fila);
                    
                }
				return lista;
			} catch (Exception e) {
				throw e;
			} 
	}

}
