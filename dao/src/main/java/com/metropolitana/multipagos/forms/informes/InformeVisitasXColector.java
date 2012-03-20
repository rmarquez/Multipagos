package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.metropolitana.multipagos.DetalleVisitas;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * 
 * @author rmarquez
 *
 */
public class InformeVisitasXColector {
	
	
	public static List getVisitasXColector(Date fechaIni, Date fechaFin, Integer departamentoId, Integer colectorId)
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
			
			if(departamentoId != null && colectorId == null) {
				
				selectQuery = "SELECT A0.VISITA_ID, A0.NUMERO_CONTRATO, A2.SUSCRIPTOR, A3.DEPARTAMENTO_NOMBRE, A1.LOCALIDAD_NOMBRE, A0.FECHA_VISITA, "+
			       "A4.SIMBOLO_NUMERO, A4.SIMBOLO_NOMBRE, A5.SERVICIO_NOMBRE, A0.COLECTOR_ID, A6.COLECTOR_NUMERO, A6.PRIMER_NOMBRE, A6.PRIMER_APELLIDO, "+
			       "A0.AVISO_COBRO, A0.OBSERVACIONES, A0.FPROG_COBRO "+ 
			"FROM ((((DETALLE_VISITAS A0 INNER JOIN LOCALIDAD A1 ON A0.LOCALIDAD_ID=A1.LOCALIDAD_ID) INNER JOIN (CARTERA_X_DEPARTAMENTO A2 "+
			"INNER JOIN DEPARTAMENTO A3 ON A2.DEPARTAMENTO_ID=A3.DEPARTAMENTO_ID) ON A0.CARTERA_ID=A2.CARTERA_ID) INNER JOIN SIMBOLO A4 ON A0.SIMBOLO_ID=A4.SIMBOLO_ID) "+ 
			"INNER JOIN SERVICIO A5 ON A0.SERVICIO_ID=A5.SERVICIO_ID) INNER JOIN COLECTOR A6 ON A0.COLECTOR_ID=A6.COLECTOR_ID "+ 
			"WHERE (((A0.FECHA_VISITA >= ?) AND A0.FECHA_VISITA <= ?) AND A1.DEPARTAMENTO_ID = ?) ORDER BY A0.AVISO_COBRO, A0.FECHA_VISITA,A0.COLECTOR_ID;"; 
				
				
			} else if(departamentoId == null && colectorId != null) {
				selectQuery = "SELECT A0.VISITA_ID, A0.NUMERO_CONTRATO, A2.SUSCRIPTOR, A3.DEPARTAMENTO_NOMBRE, A1.LOCALIDAD_NOMBRE, A0.FECHA_VISITA, "+
					       "A4.SIMBOLO_NUMERO, A4.SIMBOLO_NOMBRE, A5.SERVICIO_NOMBRE, A0.COLECTOR_ID, A6.COLECTOR_NUMERO, A6.PRIMER_NOMBRE, A6.PRIMER_APELLIDO, "+
					       "A0.AVISO_COBRO, A0.OBSERVACIONES, A0.FPROG_COBRO "+ 
					"FROM ((((DETALLE_VISITAS A0 INNER JOIN LOCALIDAD A1 ON A0.LOCALIDAD_ID=A1.LOCALIDAD_ID) INNER JOIN (CARTERA_X_DEPARTAMENTO A2 "+
					"INNER JOIN DEPARTAMENTO A3 ON A2.DEPARTAMENTO_ID=A3.DEPARTAMENTO_ID) ON A0.CARTERA_ID=A2.CARTERA_ID) INNER JOIN SIMBOLO A4 ON A0.SIMBOLO_ID=A4.SIMBOLO_ID) "+ 
					"INNER JOIN SERVICIO A5 ON A0.SERVICIO_ID=A5.SERVICIO_ID) INNER JOIN COLECTOR A6 ON A0.COLECTOR_ID=A6.COLECTOR_ID "+ 
					"WHERE (((A0.FECHA_VISITA >= ?) AND A0.FECHA_VISITA <= ?)) AND A0.COLECTOR_ID = ? ORDER BY A0.AVISO_COBRO, A0.FECHA_VISITA,A0.COLECTOR_ID;";  
			
			} else if(departamentoId == null && colectorId == null) {
				selectQuery = "SELECT A0.VISITA_ID, A0.NUMERO_CONTRATO, A2.SUSCRIPTOR, A3.DEPARTAMENTO_NOMBRE, A1.LOCALIDAD_NOMBRE, A0.FECHA_VISITA, "+
					       "A4.SIMBOLO_NUMERO, A4.SIMBOLO_NOMBRE, A5.SERVICIO_NOMBRE, A0.COLECTOR_ID, A6.COLECTOR_NUMERO, A6.PRIMER_NOMBRE, A6.PRIMER_APELLIDO, "+
					       "A0.AVISO_COBRO, A0.OBSERVACIONES, A0.FPROG_COBRO "+ 
					"FROM ((((DETALLE_VISITAS A0 INNER JOIN LOCALIDAD A1 ON A0.LOCALIDAD_ID=A1.LOCALIDAD_ID) INNER JOIN (CARTERA_X_DEPARTAMENTO A2 "+
					"INNER JOIN DEPARTAMENTO A3 ON A2.DEPARTAMENTO_ID=A3.DEPARTAMENTO_ID) ON A0.CARTERA_ID=A2.CARTERA_ID) INNER JOIN SIMBOLO A4 ON A0.SIMBOLO_ID=A4.SIMBOLO_ID) "+ 
					"INNER JOIN SERVICIO A5 ON A0.SERVICIO_ID=A5.SERVICIO_ID) INNER JOIN COLECTOR A6 ON A0.COLECTOR_ID=A6.COLECTOR_ID "+ 
					"WHERE (((A0.FECHA_VISITA >= ?) AND A0.FECHA_VISITA <= ?)) ORDER BY A0.AVISO_COBRO, A0.FECHA_VISITA,A0.COLECTOR_ID;";  
			
			} else {
				selectQuery = "SELECT A0.VISITA_ID, A0.NUMERO_CONTRATO, A2.SUSCRIPTOR, A3.DEPARTAMENTO_NOMBRE, A1.LOCALIDAD_NOMBRE, A0.FECHA_VISITA, "+
					       "A4.SIMBOLO_NUMERO, A4.SIMBOLO_NOMBRE, A5.SERVICIO_NOMBRE, A0.COLECTOR_ID, A6.COLECTOR_NUMERO, A6.PRIMER_NOMBRE, A6.PRIMER_APELLIDO, "+
					       "A0.AVISO_COBRO, A0.OBSERVACIONES, A0.FPROG_COBRO "+ 
					"FROM ((((DETALLE_VISITAS A0 INNER JOIN LOCALIDAD A1 ON A0.LOCALIDAD_ID=A1.LOCALIDAD_ID) INNER JOIN (CARTERA_X_DEPARTAMENTO A2 "+
					"INNER JOIN DEPARTAMENTO A3 ON A2.DEPARTAMENTO_ID=A3.DEPARTAMENTO_ID) ON A0.CARTERA_ID=A2.CARTERA_ID) INNER JOIN SIMBOLO A4 ON A0.SIMBOLO_ID=A4.SIMBOLO_ID) "+ 
					"INNER JOIN SERVICIO A5 ON A0.SERVICIO_ID=A5.SERVICIO_ID) INNER JOIN COLECTOR A6 ON A0.COLECTOR_ID=A6.COLECTOR_ID "+ 
					"WHERE (((A0.FECHA_VISITA >= ?) AND A0.FECHA_VISITA <= ?) AND A1.DEPARTAMENTO_ID = ?) AND A0.COLECTOR_ID = ? ORDER BY A0.AVISO_COBRO, A0.FECHA_VISITA,A0.COLECTOR_ID;"; 
			}
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            if(departamentoId != null && colectorId == null) {
				psOrigen.setInt(3, departamentoId);
				rs = psOrigen.executeQuery();
			
			} else if(departamentoId == null && colectorId != null) {
				psOrigen.setInt(3, colectorId);
				rs = psOrigen.executeQuery();
				
			} else if(departamentoId == null && colectorId == null) {
				rs = psOrigen.executeQuery();
				
			} else {
				psOrigen.setInt(3, departamentoId);
				psOrigen.setInt(4, colectorId);
				rs = psOrigen.executeQuery();
				
			}
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13), rs.getObject(14), 
					rs.getObject(15), rs.getObject(16)};              

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
