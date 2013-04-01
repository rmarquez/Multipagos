package com.metropolitana.multipagos.forms.xlstopostgresql;

import com.metropolitana.multipagos.forms.Util;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jxl.*;
import jxl.read.biff.BiffException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.metropolitana.multipagos.forms.Util;

public class XlsVisitasPendientes  extends UtilXls2Postgres {
	
	public int numeroLote(int usrId) throws Exception{
		Integer numLote = getNumLote();
        if(numLote != null){
        	numLote = numLote+1;
        } else {
        	numLote = 1;
        }
        System.out.println(" numLote = " + numLote);
        leerExcel(numLote, usrId);
        return numLote;
	}
	
private void leerExcel(int numLote, int usrId) throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		//limpiarTabla();
		
		// Inicializamos los valores
		String contrato = "";
		String suscriptor = "";
		String nit = ""; 
        String direccion = ""; 
        String barrio = ""; 
        String factura = ""; 
        String anio = ""; 
        String mes = ""; 
        String saldo = "";
        String departamento = ""; 
        String localidad = "";
        String telefono = "";
        String servicio = ""; 
        String movil1 = ""; 
        String movil2 = "";
        Date fechaInstalacion = new Date();
        String canal = "";
        
        
        
		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/enitel.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
            	// Se leen las columnas				
					for (int i = 0; i < 17; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						contrato = cell.getContents();						
					}
					if (cell.getColumn() == 1) {
						suscriptor = cell.getContents();						
					}
					if (cell.getColumn() == 2) {
						nit = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						direccion = cell.getContents();
					}
					if (cell.getColumn() == 4) {
						barrio = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						factura = cell.getContents();
					}
					if (cell.getColumn() == 6) {
						anio = cell.getContents();
					}
					if (cell.getColumn() == 7) {
						mes = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						saldo = cell.getContents();
					}
					if (cell.getColumn() == 9) {
						departamento = cell.getContents();
					}
					if (cell.getColumn() == 10) {
						localidad = cell.getContents();
					}
					if (cell.getColumn() == 11) {
						telefono = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						servicio = cell.getContents();
					}
					if (cell.getColumn() == 13) {
						if(cell.getContents().isEmpty()==true){
							movil1 = null;
						} else {
							movil1 = cell.getContents();
						}
					}
					if (cell.getColumn() == 14) {
						if(cell.getContents().isEmpty()==true){
							movil2 = null;
						} else {
							movil2 = cell.getContents();
						}
					}
					if (cell.getColumn() == 15) {
						if(cell.getContents().isEmpty()==true){
							fechaInstalacion = null;
						} else {
							fechaInstalacion = ((DateCell)cell).getDate();
						}
					}
					if (cell.getColumn() == 16) {
						if(cell.getContents().isEmpty()==true){
							canal = null;
						} else {
							canal = cell.getContents();
						}
					}
					
				}
			
			// Insertamos los nuevos registros.
			insertTabla(contrato, suscriptor, nit, direccion, barrio, factura,
					anio, mes, saldo, departamento, localidad,
					telefono, servicio, movil1, movil2, fechaInstalacion, canal, numLote, usrId);
			}
            
            borrarExcel();
            
			
		} catch (Exception ex) {
			System.out.println("Error!");
			ex.printStackTrace();
		}
	}

	private void insertTabla(final String contrato, final String suscriptor,
			final String nit, final String direccion, final String barrio,
			final String facturaInterna, final String anio, final String mes,
			final String saldo, final String departamento,
			final String localidad, final String telefono,
			final String servicio, final String movil1, final String movil2,
			final Date fechaInstalacion, final String canal,
			final Integer numLote, final Integer usrId) throws Exception {

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
			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			query = "INSERT INTO pendientes_claro(contrato, suscriptor, nit, direccion, barrio, factura_interna, anio, mes, saldo, departamento, localidad, "+
			"telefono, servicio, movil1, movil2, fecha_instalacion, canal, num_lote, usr_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			ejecutarInsertQuery(connPostgres, query, contrato, suscriptor, nit,
					direccion, barrio, facturaInterna, anio, mes, saldo,
					departamento, localidad, telefono, servicio, movil1,
					movil2, fechaInstalacion, canal, numLote, usrId);//Util.stringToInt(numLote));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connPostgres != null) connPostgres.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ejecutarInsertQuery(Connection connPostgres,
			String insertQuery, final String contrato, final String suscriptor,
			final String nit, final String direccion, final String barrio,
			final String facturaInterna, final String anio, final String mes,
			final String saldo, final String departamento,
			final String localidad, final String telefono,
			final String servicio, final String movil1, final String movil2,
			final Date fechaInstalacion, final String canal,
			final Integer numLote, final Integer usrId) throws Exception {

		PreparedStatement psOrigen = null;
		try {
			psOrigen = connPostgres.prepareStatement(insertQuery);
			psOrigen.setString(1, contrato.toString());
			psOrigen.setString(2, suscriptor.toString());
			if(nit==""){
				psOrigen.setString(3, null);
			} else {
				psOrigen.setString(3, nit.toString());
			}			
			psOrigen.setString(4, direccion.toString());
			psOrigen.setString(5, barrio.toString());
			psOrigen.setString(6, facturaInterna.toString());
			if(anio==""){
				psOrigen.setString(7, null);
			} else {
				psOrigen.setString(7, anio.toString());
			}
			if(mes==""){
				psOrigen.setString(8, null);
			} else {
				psOrigen.setString(8, mes.toString());
			}
			psOrigen.setBigDecimal(9, Util.stringToBigDecimal(saldo));
			psOrigen.setString(10, departamento.toString());
			psOrigen.setString(11, localidad.toString());
			if(telefono==""){
				psOrigen.setString(12, null);
			} else {
				psOrigen.setString(12, telefono.toString());
			}
			psOrigen.setString(13, servicio.toString());
			if(movil1=="" || movil1==null){
				psOrigen.setString(14, null);
			} else {
				psOrigen.setString(14, movil1.toString());
			}
			if(movil2=="" || movil2==null){
				psOrigen.setString(15, null);
			} else {
				psOrigen.setString(15, movil2.toString());
			}
			
			if(fechaInstalacion != null){
                java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaInstalacion));  
				psOrigen.setDate(16, fecha);
			} else {
                psOrigen.setDate(16, null);
           }
			
			if(canal==""){
				psOrigen.setString(17, null);
			} else {
				psOrigen.setString(17, canal.toString());
			}
			psOrigen.setInt(18, numLote);
			psOrigen.setInt(19, usrId);
			psOrigen.executeUpdate();
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List getPendientesClaro(int numLote, int usrId)
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
				System.out.println("No se encuentra el Driver: "
						+ e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			
			selectQuery = "SELECT DISTINCT ON (CONTRATO) CONTRATO,FACTURA_INTERNA,SUSCRIPTOR,DEPARTAMENTO,LOCALIDAD, "+
							"SERVICIO,ANIO,MES,FECHA_INSTALACION,DIRECCION, TELEFONO, BARRIO, SALDO, NUM_LOTE FROM PENDIENTES_CLARO "+
							"WHERE NUM_LOTE=? AND USR_ID=?"+
							"ORDER BY CONTRATO DESC, BARRIO ASC";
			
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setInt(1, numLote);
            psOrigen.setInt(2, usrId);
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13), rs.getObject(14)};              

            lista.add(fila);
                
            }
            rs.close();
			psOrigen.close(); 
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	private void borrarExcel() {         
        String sFichero = "/tmp/enitel.xls";
        File archivo = new File(sFichero);         
        if (archivo.delete())
                System.out.println("El archivo " + sFichero + " ha sido borrado correctamente");
        else
                System.out.println("El archivo " + sFichero + " no se ha podido borrar");

	}
	
	private void limpiarTabla() throws Exception {
		Connection connPostgres = null;
		String deleteQuery;

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

			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			deleteQuery = "delete from PENDIENTES_CLARO";
			ejecutarQuery(connPostgres, deleteQuery);
			

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

	private void ejecutarQuery(Connection connPostgres, String deleteQuery)
			throws Exception {
		PreparedStatement psOrigen = null;
		ResultSet rsOrigen = null;

		try {

			psOrigen = connPostgres.prepareStatement(deleteQuery);
			rsOrigen = psOrigen.executeQuery();

			rsOrigen.close();
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getNumLote()
			throws Exception {
            
		Connection connPostgres = null;
		String query;
        PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		int max = 0;
		try {
			
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
			
			query = "select num_lote from pendientes_claro order by pendiente_id desc limit 1 ";
				
			psOrigen = connPostgres.prepareStatement(query);
			
            rs = psOrigen.executeQuery();
            
            while(rs.next()) {  
            	max = rs.getInt(1);
            }
             
			return max;
		} catch (Exception e) {
			throw e;
		} 
	}
	
}
