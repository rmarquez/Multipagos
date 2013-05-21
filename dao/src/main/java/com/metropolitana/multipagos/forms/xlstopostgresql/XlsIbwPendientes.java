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

public class XlsIbwPendientes  extends UtilXls2Postgres {
	
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
		limpiarTabla();
		
		// Inicializamos los valores
		String nombre = "";
		String apellido = "";
		String departamento = ""; 
		String municipio = ""; 
		String barrio = "";
		String direccion = ""; 
		String codCliente = "";
        String facturaIbw = "";
        Date fechaFactura = new Date();
        Date fechaVence = new Date();
        String tecnologia = "";
        String saldoDol = "";
        String telefono = ""; 
        
        
		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/ibw.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
            	// Se leen las columnas				
					for (int i = 0; i < 13; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						nombre = cell.getContents();
					}
					if (cell.getColumn() == 1) {
						apellido = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						departamento = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						municipio = cell.getContents();
					}
					if (cell.getColumn() == 4) {
						barrio = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						direccion = cell.getContents();
					}
					if (cell.getColumn() == 6) {
						codCliente = cell.getContents();
					}
					if (cell.getColumn() == 7) {
						facturaIbw = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						fechaFactura = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 9) {
						fechaVence = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 10) {
						saldoDol = cell.getContents();
					}
					if (cell.getColumn() == 11) {
						tecnologia = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						if(cell.getContents().isEmpty()==true){
							telefono = null;
						} else {
							telefono = cell.getContents();
						}
					}
					
				}
			
			// Insertamos los nuevos registros.
				insertTabla(nombre, apellido, departamento, municipio, barrio,
						direccion, codCliente, facturaIbw, fechaFactura,
						fechaVence, saldoDol, tecnologia, telefono, numLote, usrId);
			}
            
            borrarExcel();
            
			
		} catch (Exception ex) {
			System.out.println("Error!");
			ex.printStackTrace();
		}
	}

	private void insertTabla(final String nombre, final String apellido,
			final String departamento, final String municipio,
			final String barrio, final String direccion,
			final String codCliente, final String facturaIbw, 
			final Date fechaFactura, final Date fechaVence,
			final String saldoDol, final String tecnologia, final String telefono,
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
			query = "INSERT INTO pendientes_ibw(nombre, apellido, departamento, municipio, barrio, direccion, cod_cliente, factura_ibw, fecha_factura, fecha_vence,"+
			"saldo_dol, tecnologia, telefono_c, num_lote, usr_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			ejecutarInsertQuery(connPostgres, query, nombre, apellido, departamento, municipio, barrio,
					direccion, codCliente, facturaIbw, fechaFactura,
					fechaVence, saldoDol, tecnologia, telefono, numLote, usrId);

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
			String insertQuery, final String nombre, final String apellido,
			final String departamento, final String municipio,
			final String barrio, final String direccion,
			final String codCliente, final String facturaIbw, 
			final Date fechaFactura, final Date fechaVence,
			final String saldoDol, final String tecnologia, final String telefono,
			final Integer numLote, final Integer usrId) throws Exception {

		PreparedStatement psOrigen = null;
		try {
			psOrigen = connPostgres.prepareStatement(insertQuery);
			psOrigen.setString(1, nombre.toString());
			psOrigen.setString(2, apellido.toString());
			psOrigen.setString(3, departamento.toString());
			psOrigen.setString(4, municipio.toString());
			psOrigen.setString(5, barrio.toString());
			psOrigen.setString(6, direccion.toString());
			psOrigen.setString(7, codCliente.toString());
			psOrigen.setString(8, facturaIbw.toString());
			java.sql.Date fFactura = java.sql.Date.valueOf(getFechaSQL(fechaFactura));  
			psOrigen.setDate(9, fFactura);
			java.sql.Date fVence = java.sql.Date.valueOf(getFechaSQL(fechaVence));  
			psOrigen.setDate(10, fVence);
			psOrigen.setBigDecimal(11, Util.stringToBigDecimal(saldoDol));
			psOrigen.setString(12, tecnologia.toString());
			if (telefono==""){
				psOrigen.setString(13, "0");
			} else {
				psOrigen.setString(13, telefono.toString());
			}
			psOrigen.setInt(14, numLote);
			psOrigen.setInt(15, usrId);
			psOrigen.executeUpdate();
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List getPendientesIbw(int numLote, int usrId)
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
			
			
			selectQuery = "SELECT DISTINCT ON (cod_cliente) cod_cliente, (upper(nombre) || ' '  || upper(apellido)),departamento, municipio, barrio, direccion, "+
							"factura_ibw, fecha_factura, fecha_vence, saldo_dol, tecnologia, telefono_c, num_lote FROM PENDIENTES_IBW "+
							"WHERE NUM_LOTE=? AND USR_ID=? "+
							"ORDER BY cod_cliente DESC, barrio ASC";
			
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
            psOrigen.setInt(1, numLote);
            psOrigen.setInt(2, usrId);
            rs = psOrigen.executeQuery();
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13)};              

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
        String sFichero = "/tmp/ibw.xls";
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
			deleteQuery = "delete from PENDIENTES_IBW";
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
			
			query = "select num_lote from pendientes_ibw order by pendiente_id desc limit 1 ";
				
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
