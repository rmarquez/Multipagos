package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InformesUtil {
	
	public static List getNumAsignacion()
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			
			query = "select distinct num_asignacion from asignacion_claro ";
			
			psOrigen = connPostgres.prepareStatement(query);
			
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getLocalidadXDepartamento(Integer departamentoId)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			if(departamentoId != null){
				
				query = "select distinct a.localidad_id, b.localidad_nombre "+
						"from asignacion_claro a inner join localidad b on b.localidad_id=a.localidad_id "+
						"where a.departamento_id= ?";
				
				psOrigen = connPostgres.prepareStatement(query);
				psOrigen.setInt(1,departamentoId);
			} else {
				query = "select distinct a.localidad_id, b.localidad_nombre "+
						"from asignacion_claro a inner join localidad b on b.localidad_id=a.localidad_id ";
				
				psOrigen = connPostgres.prepareStatement(query);
			}
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getBarrioXLocalidad(Integer localidadId)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			if(localidadId != null){
				
				query = "select distinct a.barrio_id, b.barrio_nombre "+
						"from asignacion_claro a inner join barrio b on b.barrio_id=a.barrio_id "+
						"where a.localidad_id= ?";
				
				psOrigen = connPostgres.prepareStatement(query);
				psOrigen.setInt(1,localidadId);
			} else {
				query = "select distinct a.barrio_id, b.barrio_nombre "+
						"from asignacion_claro a inner join barrio b on b.barrio_id=a.barrio_id ";
				
				psOrigen = connPostgres.prepareStatement(query);
			}
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getDepartamentoXAsignacion(Date fechaIngreso)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			if(fechaIngreso != null){
				
				query = "select distinct a.departamento_id, b.departamento_nombre "+
						"from asignacion_claro a inner join departamento b on b.departamento_id=a.departamento_id "+
						"where a.fecha_ingreso= ?";
				
				psOrigen = connPostgres.prepareStatement(query);
				java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIngreso));
	            psOrigen.setDate(1,fecha);
			} else {
				query = "select distinct a.departamento_id, b.departamento_nombre "+
						"from asignacion_claro a inner join departamento b on b.departamento_id=a.departamento_id ";
				
				psOrigen = connPostgres.prepareStatement(query);
			}
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getSaldoPendienteXContrato(final String contrato)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			query = "select sum(saldo) from cartera_x_departamento where contrato=? and pagado=false;";
			psOrigen = connPostgres.prepareStatement(query);
			psOrigen.setString(1, contrato.toString());
			
            rs = psOrigen.executeQuery();
            while(rs.next()){
            Object[] fila = { rs.getObject(1)};              
            lista.add(fila);
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static List getMesesXContrato(final String contrato)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			query = "select mes, anio from cartera_x_departamento where contrato=? and pagado=false;";
			psOrigen = connPostgres.prepareStatement(query);
			psOrigen.setString(1, contrato.toString());
			
            rs = psOrigen.executeQuery();
            while(rs.next()){
            Object[] fila = { rs.getObject(1), rs.getObject(2)};              
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
