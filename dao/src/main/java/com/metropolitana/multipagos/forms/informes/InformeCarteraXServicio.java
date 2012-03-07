package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformeCarteraXServicio {
	
	public static List getCarteraXServicio(Integer departamentoId, Integer servicioId, Boolean pagado, Boolean pagadoClaro)
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			//System.out.println("Departamento ID = " + departamentoId);
			
			if(departamentoId != null && servicioId == null) {
				
				selectQuery = "SELECT C.CONTRATO, C.SUSCRIPTOR, C.NIT, C.DIRECCION, B.BARRIO_NOMBRE, C.FACTURA_INTERNA, C.NUMERO_FISCAL, C.ANIO, C.MES, C.SALDO, "+
			       "E.ESTADO_NOMBRE, D.DEPARTAMENTO_NOMBRE, L.LOCALIDAD_NOMBRE, C.CUPON, C.TELEFONO, C.DESCUENTO, S.SERVICIO_NOMBRE, C.FECHA_INGRESO, C.CUENTA "+ 
			    "FROM (((CARTERA_X_DEPARTAMENTO C INNER JOIN DEPARTAMENTO D ON C.DEPARTAMENTO_ID=D.DEPARTAMENTO_ID) "+ 
							"INNER JOIN LOCALIDAD L ON C.LOCALIDAD_ID=L.LOCALIDAD_ID) "+
							"INNER JOIN BARRIO B ON C.BARRIO_ID=B.BARRIO_ID) "+ 
							"INNER JOIN SERVICIO S ON C.SERVICIO_ID=S.SERVICIO_ID "+
							"INNER JOIN ESTADO_CORTE E ON C.ESTADO_ID=E.ESTADO_ID "+
			    "WHERE C.PAGADO = ? AND PAGADO_CLARO = ? AND D.DEPARTAMENTO_ID = ?;"; 
				
				
			} else if(departamentoId == null && servicioId != null) {
				selectQuery = "SELECT C.CONTRATO, C.SUSCRIPTOR, C.NIT, C.DIRECCION, B.BARRIO_NOMBRE, C.FACTURA_INTERNA, C.NUMERO_FISCAL, C.ANIO, C.MES, C.SALDO, "+
					       "E.ESTADO_NOMBRE, D.DEPARTAMENTO_NOMBRE, L.LOCALIDAD_NOMBRE, C.CUPON, C.TELEFONO, C.DESCUENTO, S.SERVICIO_NOMBRE, C.FECHA_INGRESO, C.CUENTA "+ 
					    "FROM (((CARTERA_X_DEPARTAMENTO C INNER JOIN DEPARTAMENTO D ON C.DEPARTAMENTO_ID=D.DEPARTAMENTO_ID) "+ 
									"INNER JOIN LOCALIDAD L ON C.LOCALIDAD_ID=L.LOCALIDAD_ID) "+
									"INNER JOIN BARRIO B ON C.BARRIO_ID=B.BARRIO_ID) "+ 
									"INNER JOIN SERVICIO S ON C.SERVICIO_ID=S.SERVICIO_ID "+
									"INNER JOIN ESTADO_CORTE E ON C.ESTADO_ID=E.ESTADO_ID "+
					    "WHERE C.PAGADO = ? AND PAGADO_CLARO = ? AND S.SERVICIO_ID = ?;"; 
			} else {
				selectQuery = "SELECT C.CONTRATO, C.SUSCRIPTOR, C.NIT, C.DIRECCION, B.BARRIO_NOMBRE, C.FACTURA_INTERNA, C.NUMERO_FISCAL, C.ANIO, C.MES, C.SALDO, "+
					       "E.ESTADO_NOMBRE, D.DEPARTAMENTO_NOMBRE, L.LOCALIDAD_NOMBRE, C.CUPON, C.TELEFONO, C.DESCUENTO, S.SERVICIO_NOMBRE, C.FECHA_INGRESO, C.CUENTA "+ 
					    "FROM (((CARTERA_X_DEPARTAMENTO C INNER JOIN DEPARTAMENTO D ON C.DEPARTAMENTO_ID=D.DEPARTAMENTO_ID) "+ 
									"INNER JOIN LOCALIDAD L ON C.LOCALIDAD_ID=L.LOCALIDAD_ID) "+
									"INNER JOIN BARRIO B ON C.BARRIO_ID=B.BARRIO_ID) "+ 
									"INNER JOIN SERVICIO S ON C.SERVICIO_ID=S.SERVICIO_ID "+
									"INNER JOIN ESTADO_CORTE E ON C.ESTADO_ID=E.ESTADO_ID "+
					    "WHERE C.PAGADO = ? AND PAGADO_CLARO = ? AND D.DEPARTAMENTO_ID = ? AND S.SERVICIO_ID = ?;"; 
			}
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setBoolean(1, pagado);
            psOrigen.setBoolean(2, pagadoClaro);
			if(departamentoId != null && servicioId == null) {
				psOrigen.setInt(3, departamentoId);
				rs = psOrigen.executeQuery();
			
			} else if(departamentoId == null && servicioId != null) {
				psOrigen.setInt(3, servicioId);
				rs = psOrigen.executeQuery();
			} else {
				psOrigen.setInt(3, departamentoId);
				psOrigen.setInt(4, servicioId);
				rs = psOrigen.executeQuery();
				
			}
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13), rs.getObject(14), 
					rs.getObject(15), rs.getObject(16), rs.getObject(17),
					rs.getObject(18), rs.getObject(18)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}

}
