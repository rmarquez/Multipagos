package com.metropolitana.multipagos.forms.xlstopostgresql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.metropolitana.multipagos.forms.Util;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class XlsCarteraClaro extends UtilXls2Postgres {
	
public void leerExcel() throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		limpiarTabla();
		
		// Inicializamos los valores
		String contrato = "";
		String suscriptor = "";
        String factura = ""; 
        String nfiscal = ""; 
        String anio = ""; 
        String mes = ""; 
        Date asignado = new Date();
        String servicio = ""; 
        Date pagado = new Date();

		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/cartera.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
            	// Se leen las columnas				
					for (int i = 0; i < 9; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						contrato = cell.getContents();						
					}
					if (cell.getColumn() == 1) {
						suscriptor = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						factura = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						nfiscal = cell.getContents();
					}					
					if (cell.getColumn() == 4) {
						anio = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						mes = cell.getContents();
					}
					if (cell.getColumn() == 6) {
						asignado = ((DateCell)cell).getDate();
					}
					if (cell.getColumn() == 7) {
						servicio = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						pagado = ((DateCell)cell).getDate();
					}				
				}
			
			// Insertamos los nuevos registros.
			insertTabla(contrato, suscriptor, factura, nfiscal, anio, mes, asignado, servicio, pagado);
			}
            borrarRegistrosBasura();
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);

			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			deleteQuery = "delete from tmp_carteraclaro";
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);

			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			deleteQuery = "delete from tmp_carteraclaro where factura_interna='#N/A' ";
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
	
	private void insertTabla(final String contrato, final String suscriptor,
			final String factura, final String nfiscal, final String anio,
			final String mes, final Date asignado,final String servicio, final Date pagado) throws Exception {
		
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			// Limpiamos la tabla tmp_cartera antes de insertar los datos
			insertQuery = "INSERT INTO tmp_carteraclaro(contrato, suscriptor, factura_interna, numero_fiscal, anio, mes, f_asignado, servicio,fecha_pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertQuery(connPostgres, insertQuery, contrato, suscriptor, factura, nfiscal, anio, mes, Util.fechaDias(asignado, 1), servicio, Util.fechaDias(pagado, 1) );

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
	
	private void ejecutarInsertQuery(Connection connPostgres, String insertQuery, final String contrato, final String suscriptor,
			final String factura, final String nfiscal, final String anio,
			final String mes, final Date asignado, final String servicio, final Date pagado)	throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, contrato.toString());
				psOrigen.setString(2, suscriptor.toString());
							
				psOrigen.setString(3, factura.toString());
				if(nfiscal==""){
					psOrigen.setString(4, null);
				} else {
					psOrigen.setString(4, nfiscal.toString());
				}
				if(anio==""){
					psOrigen.setString(5, null);
				} else {
					psOrigen.setString(5, anio.toString());
				}
				if(mes==""){
					psOrigen.setString(6, null);
				} else {
					psOrigen.setString(6, mes.toString());
				}			
				java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(asignado));  
				psOrigen.setDate(7, fecha);				
							
				psOrigen.setString(8, servicio.toString());
				
				java.sql.Date fecha_pago = java.sql.Date.valueOf(getFechaSQL(pagado));  
				psOrigen.setDate(9, fecha_pago);
				
				
				
				psOrigen.executeUpdate();
						
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFechaSQL(final Date asignado) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(asignado);
    }	
	
	private void borrarExcel() {         
         String sFichero = "/tmp/cartera.xls";
         File archivo = new File(sFichero);         
         if (archivo.delete())
                 System.out.println("El archivo " + sFichero + " ha sido borrado correctamente");
         else
                 System.out.println("El archivo " + sFichero + " no se ha podido borrar");

 }

}
