package com.metropolitana.multipagos.forms.xlstopostgresql;

import com.metropolitana.multipagos.forms.Util;
import java.io.File;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XlsCarteraIbw extends UtilXls2PostgresIbw {
	
public void leerExcel() throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		limpiarTabla();
		
		// Inicializamos los valores
		String nombre = "";
		String apellido = "";
		String departamento = ""; 
        String municipio = ""; 
        String barrio = "";
        String direccion = ""; 
        String telefonoC = ""; 
        String celular = ""; 
        String telefonoT = "";
        String codCliente = "";
        String facturaIbw = "";
        String serie = "";
        Date fechaFactura = new Date();
        Date fechaVence = new Date();
        String saldoDol = "";
        String TotalDol = "";
        String tecnologia = "";
        Date fechaAsignado = Calendar.getInstance().getTime();
        
         //int numD = 0;
        
		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/ibw.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
                int codigo = Util.getEmpMcodigo();
               // codMultipago="MULT"+Util.integerToString(codigo);		
					for (int i = 0; i < 17; i++) {
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
						telefonoC = cell.getContents();
					}
					if (cell.getColumn() == 7) {
						celular = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						telefonoT = cell.getContents();
					}
					if (cell.getColumn() == 9) {
						codCliente = cell.getContents();
					}
					if (cell.getColumn() == 10) {
						facturaIbw = cell.getContents();
					}
					if (cell.getColumn() == 11) {
						serie = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						fechaFactura = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 13) {
						fechaVence = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 14) {
						saldoDol = cell.getContents();
					}
					if (cell.getColumn() == 15) {
						tecnologia = cell.getContents();
					}
					if (cell.getColumn() == 16) {
						TotalDol = cell.getContents();
					}
					
         		
				}
			 // Insertamos los nuevos registros.
			insertTabla(nombre, apellido, departamento, municipio, barrio,
					direccion, telefonoC, celular, telefonoT,
					codCliente, facturaIbw, serie, fechaFactura, fechaVence,
					saldoDol, TotalDol, tecnologia,  fechaAsignado);
			}
            //borrarRegistrosBasura();
            borrarExcel();
		} catch (Exception ex) {
			System.out.println("Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo que limpia la tabla tmp_banpro para poder insertar los registros.
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
			deleteQuery = "delete from tmp_ibw";
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
	
	/*private void borrarRegistrosBasura() throws Exception {
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
*/
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
	
	private void insertTabla(final String nombre, final String apellido,
			final String departamento, final String municipio,
			final String barrio, final String direccion,
			final String telefonoC, final String celular,
			final String telefonoT, final String codCliente,
			final String facturaIbw, final String serie,
			final Date fechaFactura, final Date fechaVence,
			final String saldoDol, final String TotalDol,
			final String tecnologia, final Date fechaAsignado) throws Exception {
		
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
			insertQuery = "INSERT INTO tmp_ibw(nombre, apellido, departamento, municipio, barrio, "+
					"direccion, telefono_c, celular, telefono_t, "+
					"cod_cliente, factura_ibw, serie, fecha_factura, fecha_vence, "+
					"saldo_dol, total_saldo_dol, tecnologia,  f_asignado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertQuery(connPostgres, insertQuery, nombre, apellido, departamento, municipio, barrio,
					direccion, telefonoC, celular, telefonoT,
					codCliente, facturaIbw, serie, fechaFactura, fechaVence,
					saldoDol, TotalDol, tecnologia,  fechaAsignado);

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
			final String telefonoC, final String celular,
			final String telefonoT, final String codCliente,
			final String facturaIbw, final String serie,
			final Date fechaFactura, final Date fechaVence,
			final String saldoDol, final String TotalDol,
			final String tecnologia, final Date fechaAsignado) throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, nombre.toString());
				psOrigen.setString(2, apellido.toString());
				psOrigen.setString(3, departamento.toString());
				psOrigen.setString(4, municipio.toString());
				psOrigen.setString(5, barrio.toString());
				psOrigen.setString(6, direccion.toString());
				if (telefonoC==""){
					psOrigen.setString(7, "0");
				} else {
					psOrigen.setString(7, telefonoC.toString());
				}
				if (celular==""){
					psOrigen.setString(8, "0");
				} else {
					psOrigen.setString(8, celular.toString());
				}
				if (telefonoT==""){
					psOrigen.setString(9, "0");
				} else {
					psOrigen.setString(9, telefonoT.toString());
				}
				psOrigen.setString(10, codCliente.toString());
				psOrigen.setString(11, facturaIbw.toString());
				psOrigen.setString(12, serie.toString());
				java.sql.Date fFactura = java.sql.Date.valueOf(getFechaSQL(fechaFactura));  
				psOrigen.setDate(13, fFactura);
				java.sql.Date fVence = java.sql.Date.valueOf(getFechaSQL(fechaVence));  
				psOrigen.setDate(14, fVence);
				psOrigen.setBigDecimal(15, Util.stringToBigDecimal(saldoDol));
				psOrigen.setBigDecimal(16, Util.stringToBigDecimal(TotalDol));
				psOrigen.setString(17, tecnologia.toString());
				java.sql.Date fsignado = java.sql.Date.valueOf(getFechaSQL(fechaAsignado));  
				psOrigen.setDate(18, fsignado);
				
				
				
				psOrigen.executeUpdate();
						
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	
}
