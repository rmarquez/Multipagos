package com.metropolitana.multipagos.forms.xlstopostgresql;

import com.metropolitana.multipagos.forms.Util;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XlsCarteraAvon extends UtilXls2Postgres {
	
public void leerExcel() throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		limpiarTabla();
		
		// Inicializamos los valores
		String codigo = "";
		String consejero = "";
		String cedula = "";
		String direccion = ""; 
		String departamento = ""; 
        String localidad = ""; 
        String barrio = "";
        String telefono = ""; 
        String zona = ""; 
        String factura = ""; 
        String saldo = "";
        String mes = "";
        String anio = "";
        String agencia = ""; 
        String campania = ""; 
        Date fbaja = new Date();
        Date ffactura = new Date();
        Date asignado = new Date();
        
      

		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/avon.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
            	// Se leen las columnas				
					for (int i = 0; i < 18; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						codigo = cell.getContents();						
					}
					if (cell.getColumn() == 1) {
						consejero = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						cedula = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						direccion = cell.getContents();
					}
					if (cell.getColumn() == 4) {
						departamento = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						localidad = cell.getContents();
					}
					if (cell.getColumn() == 6) {
						barrio = cell.getContents();
					}
					if (cell.getColumn() == 7) {
						telefono = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						zona = cell.getContents();
					}
					if (cell.getColumn() == 9) {
						factura = cell.getContents();
					}
					if (cell.getColumn() == 10) {
						saldo = cell.getContents();
					}
					if (cell.getColumn() == 11) {
						mes = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						anio = cell.getContents();
					}
					if (cell.getColumn() == 13) {
						agencia = cell.getContents();
					}
					if (cell.getColumn() == 14) {
						campania = cell.getContents();
					}
					if (cell.getColumn() == 15) {
						fbaja = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 16) {
						ffactura = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 17) {
						asignado = ((DateCell)cell).getDate();
					}
				
				}
			
			// Insertamos los nuevos registros.
				insertTabla(codigo, consejero, cedula, direccion, departamento,
						localidad, barrio, telefono, zona, factura, saldo, mes,
						anio, agencia, campania, fbaja, ffactura, asignado);
			}
            //borrarRegistrosBasura();
            borrarExcel();
		} catch (Exception ex) {
			System.out.println("Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo que limpia la tabla tmp_cartera para poder insertar los registros.
	 * 
	 * @throws Exception
	 */
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
			deleteQuery = "delete from tmp_cartera_avon";
			ejecutarQuery(connPostgres, deleteQuery);
			
			// Borramos la tabla temporal tmp_excluidos
			deleteQuery = "drop table tmp_excluidos;";
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
	
	private void borrarRegistrosBasura() throws Exception {
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
			deleteQuery = "delete from tmp_cartera where factura_interna='#N/A' ";
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
	
	private void insertTabla(final String codigo, final String consejero, final String cedula,
			final String direccion, final String departamento, final String localidad,
			final String barrio, final String telefono, final String zona, final String factura,
			final String saldo, final String mes, final String anio, final String agencia,
			final String campania, final Date fbaja, final Date ffactura, final Date asignado)
			throws Exception {
		
		java.lang.System.out.println("codigo = " + codigo);
		java.lang.System.out.println("consejero = " + consejero);
		java.lang.System.out.println("cedula = " + cedula);
		java.lang.System.out.println("direccion = " + direccion);
		java.lang.System.out.println("departamento = " + departamento);
		java.lang.System.out.println("municipio = " + localidad);
		java.lang.System.out.println("barrio = " + barrio);
		java.lang.System.out.println("telefono = " + telefono);
		java.lang.System.out.println("zona = " + zona);
		java.lang.System.out.println("saldo = " + saldo);
		java.lang.System.out.println("mes = " + mes);
		java.lang.System.out.println("anio = " + anio);
		java.lang.System.out.println("agencia = " + agencia);
		java.lang.System.out.println("campania = " + campania);
		java.lang.System.out.println("fbaja = " + fbaja);
		java.lang.System.out.println("ffactura = " + ffactura);
		java.lang.System.out.println("asignado = " + asignado);
		
		java.lang.System.out.println("*************************************************************");
		
		Connection connPostgres = null;
		String insertQuery;

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
			insertQuery = "INSERT INTO tmp_cartera_avon(codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertQuery(connPostgres, insertQuery, codigo, consejero, cedula, direccion, departamento,
					localidad, barrio, telefono, zona, factura, saldo, mes,
					anio, agencia, campania,Util.fechaDias(fbaja, 1),
					Util.fechaDias(ffactura, 1),Util.fechaDias(asignado, 1));

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
			String insertQuery, final String codigo, final String consejero,
			final String cedula, final String direccion,
			final String departamento, final String localidad,
			final String barrio, final String telefono, final String zona,
			final String factura, final String saldo, final String mes,
			final String anio, final String agencia, final String campania,
			final Date fbaja, final Date ffactura, final Date asignado)
			throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, codigo.toString());
				psOrigen.setString(2, consejero.toString());
				if(cedula==""){
					psOrigen.setString(3, null);
				} else {
					psOrigen.setString(3, cedula.toString());
				}			
				psOrigen.setString(4, direccion.toString());
				psOrigen.setString(5, departamento.toString());
				psOrigen.setString(6, localidad.toString());
				psOrigen.setString(7, barrio.toString());
				if(telefono==""){
					psOrigen.setString(8, null);
				} else {
					psOrigen.setString(8, telefono.toString());
				}
				psOrigen.setString(9, zona.toString());
						
				psOrigen.setString(10, factura);
				psOrigen.setBigDecimal(11, Util.stringToBigDecimal(saldo));
				
				psOrigen.setString(12, mes.toString());
				psOrigen.setString(13, anio.toString());
				psOrigen.setString(14, agencia.toString());
				psOrigen.setString(15, campania.toString());
				
				
				java.sql.Date fechab = java.sql.Date.valueOf(getFechaSQL(fbaja));  
				psOrigen.setDate(16, fechab);
				
				java.sql.Date fechaf = java.sql.Date.valueOf(getFechaSQL(ffactura));  
				psOrigen.setDate(17, fechaf);
				
				java.sql.Date fechaa = java.sql.Date.valueOf(getFechaSQL(asignado));  
				psOrigen.setDate(18, fechaa);
				
				psOrigen.executeUpdate();
						
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
	private void borrarExcel() {         
         String sFichero = "/tmp/avon.xls";
         File archivo = new File(sFichero);         
         if (archivo.delete())
                 System.out.println("El archivo " + sFichero + " ha sido borrado correctamente");
         else
                 System.out.println("El archivo " + sFichero + " no se ha podido borrar");

 }

}
