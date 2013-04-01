package com.metropolitana.multipagos.forms.clientes_banpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilBanpro {
	
	public static List getPagosClientes(String codCliente)
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
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
		
			selectQuery = " select cuenta, fec_pago_cord, pago_cor, fec_pago_dol, pago_dol, observaciones "+
							"from pagos_banpro where cuenta in (select cuenta from cartera_banpro where cod_cliente=?);";
			
			psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setString(1,codCliente);
            rs = psOrigen.executeQuery();
			
			
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getArreglosClientes(String codCliente)
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
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
		
			selectQuery = "select fecha, descuento, plazo, cuota from arreglo_pago  where cod_cliente=?;";
			
			psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setString(1,codCliente);
            rs = psOrigen.executeQuery();
			
			
                   
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
	
	
	public static List getCuotasClientes(String codCliente)
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
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
		
			selectQuery = "select ac.fecha_cuota, ap.cuota, pb.pago_dol, pb.fec_pago_dol "+
			"from arreglo_calendario ac inner join arreglo_pago ap on ac.arreglo_id=ap.arreglo_id "+
			"inner join arreglo_deuda ad on ac.arreglo_id=ad.arreglo_id "+
			"inner join pagos_banpro pb on ad.cuenta=pb.cuenta "+
			"where ap.cod_cliente=?;";
			
			psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setString(1,codCliente);
            rs = psOrigen.executeQuery();
			
			
                   
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
