package com.metropolitana.multipagos.forms;

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
import java.util.Date;

public class Xls2Postgres {

	public void leerExcel() throws Exception {

		// Limpiamos la tabla donde insertaremos los registros.
		limpiarTabla();

		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/multipagos.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();

			// Inicializamos los valores
			String contrato = "";
			String suscriptor = "";
            String nit = ""; 
            String direccion = ""; 
            String barrio = ""; 
            String factura = ""; 
            String nfiscal = ""; 
            String anio = ""; 
            String mes = ""; 
            String estado = ""; 
            String departamento = ""; 
            String localidad = ""; 
            String cupon = ""; 
            String telefono = ""; 
            String descuento = ""; 
            String servicio = ""; 
            String empleador = ""; 
            String dempleador = ""; 
            String cuenta = ""; 
            String concepto = "";
            String saldo = "";
            Date asignado = new Date();

			// Se leen las columnas
			for (int i = 0; i < 22; i++) {
				// Se leen las filas
				for (int j = 0; j < 4; j++) {
					// Se obtiene la celda i-esimay(Xls2Postgres.java:224)

					Cell cell = sheet.getCell(i, j);

					if (cell.getColumn() == 0) {
						contrato = cell.getContents();
						//System.out.println("contrato = " + contrato);
					}
					if (cell.getColumn() == 1) {
						suscriptor = cell.getContents();
						//System.out.println("suscriptor = " + suscriptor);
					}
					if (cell.getColumn() == 2) {
						nit = cell.getContents();
						//System.out.println("nit = " + nit);
					}
					if (cell.getColumn() == 3) {
						direccion = cell.getContents();
						//System.out.println("direccion = " + direccion);
					}
					if (cell.getColumn() == 4) {
						barrio = cell.getContents();
						//System.out.println("barrio = " + barrio);
					}
					if (cell.getColumn() == 5) {
						factura = cell.getContents();
						//System.out.println("factura = " + factura);
					}
					if (cell.getColumn() == 6) {
						nfiscal = cell.getContents();
						//System.out.println("nfiscal = " + nfiscal);
					}
					if (cell.getColumn() == 7) {
						anio = cell.getContents();
						//System.out.println("anio = " + anio);
					}
					if (cell.getColumn() == 8) {
						mes = cell.getContents();
						//System.out.println("mes = " + mes);
					}
					if (cell.getColumn() == 9) {
						saldo = cell.getContents();
						//System.out.println("saldo = " + saldo);
					}
					if (cell.getColumn() == 10) {
						estado = cell.getContents();
						//System.out.println("estado = " + estado);
					}
					if (cell.getColumn() == 11) {
						departamento = cell.getContents();
						//System.out.println("departamento = " + departamento);
					}
					if (cell.getColumn() == 12) {
						localidad = cell.getContents();
						//System.out.println("localidad = " + localidad);
					}
					if (cell.getColumn() == 13) {
						cupon = cell.getContents();
						//System.out.println("cupon = " + cupon);
					}
					if (cell.getColumn() == 14) {
						telefono = cell.getContents();
						//System.out.println("telefono = " + telefono);
					}
					if (cell.getColumn() == 15) {
						descuento = cell.getContents();
						//System.out.println("descuento = " + descuento);
					}
					if (cell.getColumn() == 16) {
						servicio = cell.getContents();
						//System.out.println("servicio = " + servicio);
					}
					if (cell.getColumn() == 17) {
						empleador = cell.getContents();
						//System.out.println("empleador = " + empleador);
					}
					if (cell.getColumn() == 18) {
						dempleador = cell.getContents();
						//System.out.println("dempleador = " + dempleador);
					}
					if (cell.getColumn() == 19) {
						asignado = ((DateCell)cell).getDate();
						//System.out.println("Asignado = " + asignado);
					}
					if (cell.getColumn() == 20) {
						cuenta = cell.getContents();
						//System.out.println("cuenta = " + cuenta);
					}
					if (cell.getColumn() == 21) {
						concepto = cell.getContents();
						//System.out.println("concepto = " + concepto);
					}

				}
			}
			//for(){
				
			//}
			// Insertamos los nuevos registros.
			insertTabla(contrato, suscriptor, nit, direccion, barrio, factura,
					nfiscal, anio, mes, saldo, estado, departamento, localidad,
					cupon, telefono, descuento, servicio, empleador,
					dempleador, asignado, cuenta, concepto);

		} catch (Exception ex) {
			//System.out.println("Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo que limpia la tabla tmp_cartera para poder insertar los registros.
	 * 
	 * @throws Exception
	 */
	private void limpiarTabla() throws Exception {
		System.out.println("******* LIMPIAR ********");
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
			deleteQuery = "delete from tmp_cartera";
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
			final String nit, final String direccion, final String barrio,
			final String factura, final String nfiscal, final String anio,
			final String mes, final String saldo, final String estado,
			final String departamento, final String localidad,
			final String cupon, final String telefono, final String descuento,
			final String servicio, final String empleador,
			final String dempleador, final Date asignado, final String cuenta,
			final String concepto) throws Exception {
		System.out.println("******* INSERTAR ********");
		
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
			insertQuery = "INSERT INTO tmp_cartera(contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asignado, cuenta, concepto_diferido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertQuery(connPostgres, insertQuery, contrato,
					suscriptor, nit, direccion, barrio, factura, nfiscal, anio,
					mes, saldo, estado, departamento, localidad, cupon,
					telefono, descuento, servicio, empleador, dempleador,
					asignado, cuenta, concepto);

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
	
	private void ejecutarInsertQuery(Connection connPostgres, String insertQuery, final String contrato, final String suscriptor,
			final String nit, final String direccion, final String barrio,
			final String factura, final String nfiscal, final String anio,
			final String mes, final String saldo, final String estado,
			final String departamento, final String localidad,
			final String cupon, final String telefono, final String descuento,
			final String servicio, final String empleador,
			final String dempleador, final Date asignado, final String cuenta,
			final String concepto)	throws Exception {
		
		PreparedStatement psOrigen = null;
		ResultSet rsOrigen = null;
		

		try {			
			//while (rsOrigen.next()) {
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
				psOrigen.setString(6, factura.toString());
				if(nfiscal==""){
					psOrigen.setString(7, null);
				} else {
					psOrigen.setString(7, nfiscal.toString());
				}
				if(anio==""){
					psOrigen.setString(8, null);
				} else {
					psOrigen.setString(8, anio.toString());
				}
				if(mes==""){
					psOrigen.setString(9, null);
				} else {
					psOrigen.setString(9, mes.toString());
				}			
				psOrigen.setBigDecimal(10, Util.stringToBigDecimal(saldo));
				psOrigen.setString(11, estado.toString());
				psOrigen.setString(12, departamento.toString());
				psOrigen.setString(13, localidad.toString());
				if(cupon==""){
					psOrigen.setString(14, null);
				} else {
					psOrigen.setString(14, cupon.toString());
				}
				if(telefono==""){
					psOrigen.setString(15, null);
				} else {
					psOrigen.setString(15, telefono.toString());
				}
				if(descuento==""){
					psOrigen.setString(16, null);
				} else {
					psOrigen.setString(16, descuento.toString());
				}			
				psOrigen.setString(17, servicio.toString());
				if(empleador==""){
					psOrigen.setString(18, null);
				} else {
					psOrigen.setString(18, empleador.toString());
				}
				if(dempleador==""){
					psOrigen.setString(19, null);
				} else {
					psOrigen.setString(19, dempleador.toString());
				}			
				java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(asignado));                        
				psOrigen.setDate(20, fecha);
				if(cuenta==""){
					psOrigen.setString(21, null);
				} else {
					psOrigen.setString(21, cuenta.toString());
				}
				if(concepto==""){
					psOrigen.setString(22, null);
				} else {
					psOrigen.setString(22, concepto.toString());
				}
				
				rsOrigen = psOrigen.executeQuery();
			//}
			
			psOrigen.close();
			rsOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFechaSQL(final Date asignado) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(asignado);
    }	
	
}