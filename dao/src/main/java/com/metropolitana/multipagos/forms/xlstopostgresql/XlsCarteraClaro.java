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
        String direccion = ""; 
        String barrio = ""; 
        String factura = ""; 
        String nfiscal = ""; 
        String anio = ""; 
        String mes = ""; 
        String saldo = "";        
        String estado = ""; 
        String departamento = ""; 
        String servicio = ""; 
        Date asignado = new Date();

		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/cartera.xls"));
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
						contrato = cell.getContents();						
					}
					if (cell.getColumn() == 1) {
						suscriptor = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						direccion = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						barrio = cell.getContents();
					}
					if (cell.getColumn() == 4) {
						factura = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						nfiscal = cell.getContents();
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
						estado = cell.getContents();
					}
					if (cell.getColumn() == 10) {
						departamento = cell.getContents();
					}					
					if (cell.getColumn() == 11) {
						servicio = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						asignado = ((DateCell)cell).getDate();
					}				
				}
			
			// Insertamos los nuevos registros.
			insertTabla(contrato, suscriptor, direccion, barrio, factura,
					nfiscal, anio, mes, saldo, estado, departamento,
					servicio,  asignado);
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
			final String direccion, final String barrio,
			final String factura, final String nfiscal, final String anio,
			final String mes, final String saldo, final String estado,
			final String departamento,final String servicio, final Date asignado) throws Exception {
		
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
			insertQuery = "INSERT INTO tmp_carteraclaro(contrato, suscriptor, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, servicio,f_asignado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertQuery(connPostgres, insertQuery, contrato,
					suscriptor, direccion, Util.sinAcentos(barrio), factura, nfiscal, anio,
					mes, saldo, estado, Util.sinAcentos(departamento), servicio, 
					Util.fechaDias(asignado, 1));

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
			final String direccion, final String barrio,
			final String factura, final String nfiscal, final String anio,
			final String mes, final String saldo, final String estado,
			final String departamento, final String servicio, final Date asignado)	throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, contrato.toString());
				psOrigen.setString(2, suscriptor.toString());
							
				psOrigen.setString(3, direccion.toString());
				psOrigen.setString(4, barrio.toString());
				psOrigen.setString(5, factura.toString());
				if(nfiscal==""){
					psOrigen.setString(6, null);
				} else {
					psOrigen.setString(6, nfiscal.toString());
				}
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
				psOrigen.setString(10, estado.toString());
				psOrigen.setString(11, departamento.toString());
				
							
				psOrigen.setString(12, servicio.toString());
				
				java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(asignado));  
				psOrigen.setDate(13, fecha);
				
				
				
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
