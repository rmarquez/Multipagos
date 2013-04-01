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

public class XlsCarteraBanpro extends UtilXls2Postgres {
	
public void leerExcel() throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		limpiarTabla();
		
		// Inicializamos los valores
		String codCliente = "";
		String cuenta = "";
		String nombre = "";
		String tarjeta = "";
		String producto = ""; 
        String antiguedadAnios = ""; 
        String deudaCor = "";
        String deudaDol = ""; 
        String totalDeudaDol = ""; 
        String empresaBa = ""; 
        String ubicacionBa = "";
        String cedula = "";
        String direccionC = "";
        String barrioC = "";
        String departamentoC = ""; 
        String ciudadC = ""; 
        String direccionT = "";
        String departamentoT = "";
        String ciudadT = ""; 
        String telefonoC = ""; 
        String telefonoT = ""; 
        String telefonoO = ""; 
        String codFiador = "";
        String nombreFiador= "";
        String telefonoFiador = ""; 
        String codMultipago = "";
        Date fecha = Calendar.getInstance().getTime();
        
         //int numD = 0;
        
		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/banpro.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
                int codigo = Util.getEmpMcodigo();
               // codMultipago="MULT"+Util.integerToString(codigo);		
					for (int i = 0; i < 25; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						codCliente = cell.getContents();
					}
					if (cell.getColumn() == 1) {
						cuenta = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						nombre = cell.getContents();
					}
					if (cell.getColumn() == 3) {
						tarjeta = cell.getContents();
					}
					if (cell.getColumn() == 4) {
						producto = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						antiguedadAnios = cell.getContents();
					}
					if (cell.getColumn() == 6) {
						deudaCor = cell.getContents();
					}
					if (cell.getColumn() == 7) {
						deudaDol = cell.getContents();
					}
					if (cell.getColumn() == 8) {
						totalDeudaDol = cell.getContents();
					}
					if (cell.getColumn() == 9) {
						empresaBa = cell.getContents();
					}
					if (cell.getColumn() == 10) {
						ubicacionBa = cell.getContents();
					}
					if (cell.getColumn() == 11) {
						cedula = cell.getContents();
					}
					if (cell.getColumn() == 12) {
						direccionC = cell.getContents();
					}
					if (cell.getColumn() == 13) {
						barrioC = cell.getContents();
					}
					if (cell.getColumn() == 14) {
						departamentoC = cell.getContents();
					}
					if (cell.getColumn() == 15) {
						ciudadC = cell.getContents();
					}
					if (cell.getColumn() == 16) {
                    	direccionT = cell.getContents();
					}
                    if (cell.getColumn() == 17) {
                    	departamentoT = cell.getContents();
					}
                    if (cell.getColumn() == 18) {
						ciudadT = cell.getContents();
					}
                    if (cell.getColumn() == 19) {
						telefonoC = cell.getContents();
					}
                    if (cell.getColumn() == 20) {
						telefonoT = cell.getContents();
					}
                    if (cell.getColumn() == 21) {
						telefonoO = cell.getContents();
					}
                    if (cell.getColumn() == 22) {
						codFiador = cell.getContents();
					}
                    if (cell.getColumn() == 23) {
						nombreFiador = cell.getContents();
					}
                    if (cell.getColumn() == 24) {
						telefonoFiador = cell.getContents();
					}
					
				
				}
			 codMultipago="MULT"+Util.integerToString(codigo); 
			// Insertamos los nuevos registros.
			insertTabla(codCliente, cuenta, nombre, tarjeta, producto,
					antiguedadAnios, deudaCor, deudaDol, totalDeudaDol,
					empresaBa, ubicacionBa, cedula, direccionC, barrioC,
					departamentoC, ciudadC, direccionT, departamentoT, ciudadT,
					telefonoC, telefonoT, telefonoO, codFiador,
					nombreFiador, telefonoFiador, codMultipago, fecha);
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
			deleteQuery = "delete from tmp_banpro";
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
	
	private void insertTabla(final String codCliente, final String cuenta,
			final String nombre, final String tarjeta, final String producto,
			final String antiguedadAnios, final String deudaCor,
			final String deudaDol, final String totalDeudaDol,
			final String empresaBa, final String ubicacionBa,
			final String cedula, final String direccionC, final String barrioC,
			final String departamentoC, final String ciudadC,
			final String direccionT, final String departamentoT,
			final String ciudadT, final String telefonoC,
			final String telefonoT, final String telefonoO,
			final String codFiador, final String nombreFiador,
			final String telefonoFiador, final String codMultipago,
			final Date fecha) throws Exception {
		
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
			insertQuery = "INSERT INTO tmp_banpro(cod_cliente, cuenta, nombre, tarjeta, producto, "+
					"antiguedad_anios, deuda_cor, deuda_dol, total_deuda_dol, "+
					"empresa_ba, ubicacion_ba, cedula, direccion_c, barrio_c,"+
					"departamento_c, ciudad_c, direccion_t, departamento_t, ciudad_t, "+
					"telefono_c, telefono_t, telefono_o, cod_fiador, "+
					"nombre_fiador, telefono_fiador, mult_codigo, fecha_asignado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			ejecutarInsertQuery(connPostgres, insertQuery, codCliente, cuenta, nombre, tarjeta, producto,
					antiguedadAnios, deudaCor, deudaDol, totalDeudaDol,
					empresaBa, ubicacionBa, cedula, direccionC, barrioC,
					departamentoC, ciudadC, direccionT, departamentoT, ciudadT,
					telefonoC, telefonoT, telefonoO, codFiador,
					nombreFiador, telefonoFiador, codMultipago, fecha);

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
			String insertQuery, final String codCliente, final String cuenta,
			final String nombre, final String tarjeta, final String producto,
			final String antiguedadAnios, final String deudaCor,
			final String deudaDol, final String totalDeudaDol,
			final String empresaBa, final String ubicacionBa,
			final String cedula, final String direccionC, final String barrioC,
			final String departamentoC, final String ciudadC,
			final String direccionT, final String departamentoT,
			final String ciudadT, final String telefonoC,
			final String telefonoT, final String telefonoO,
			final String codFiador, final String nombreFiador,
			final String telefonoFiador, final String codMultipago,
			final Date fecha) throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, codCliente.toString());
				psOrigen.setString(2, cuenta.toString());
				psOrigen.setString(3, nombre.toString());
				psOrigen.setString(4, tarjeta.toString());
				psOrigen.setString(5, producto.toString());
				psOrigen.setBigDecimal(6, Util.stringToBigDecimal(antiguedadAnios));
				psOrigen.setBigDecimal(7, Util.stringToBigDecimal(deudaCor));
				psOrigen.setBigDecimal(8, Util.stringToBigDecimal(deudaDol));
				psOrigen.setBigDecimal(9, Util.stringToBigDecimal(totalDeudaDol));
				psOrigen.setString(10, empresaBa.toString());
				psOrigen.setString(11, ubicacionBa.toString());
				psOrigen.setString(12, cedula.toString());
				psOrigen.setString(13, direccionC.toString());
				psOrigen.setString(14, barrioC.toString());
				psOrigen.setString(15, departamentoC.toString());
				if(ciudadC==""){
					psOrigen.setString(16, "0");
				} else {
					psOrigen.setString(16, ciudadC.toString());
				}
				psOrigen.setString(17, direccionT.toString());
				psOrigen.setString(18, departamentoT.toString());
				psOrigen.setString(19, ciudadT.toString());
				psOrigen.setString(20, telefonoC.toString());
				psOrigen.setString(21, telefonoT.toString());
				psOrigen.setString(22, telefonoO.toString());
				psOrigen.setString(23, codFiador.toString());
				psOrigen.setString(24, nombreFiador.toString());
				psOrigen.setString(25, telefonoFiador.toString());
                psOrigen.setString(26, codMultipago.toString());
                java.sql.Date fechab = java.sql.Date.valueOf(getFechaSQL(fecha));  
				psOrigen.setDate(27, fechab);
				
				psOrigen.executeUpdate();
						
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
private void borrarExcel() {         
     String sFichero = "/tmp/banpro.xls";
     File archivo = new File(sFichero);         
     if (archivo.delete())
             System.out.println("El archivo " + sFichero + " ha sido borrado correctamente");
     else
             System.out.println("El archivo " + sFichero + " no se ha podido borrar");

}
	
public void leerPagos() throws Exception {
		
		// Limpiamos la tabla donde insertaremos los registros.
		//limpiarTabla();
		
		// Inicializamos los valores
		String cuenta = "";
		String nombre = "";
		Date fecPagoCord = new Date();
		Date fecPagoDol = new Date();
        String pagoCor = "";
        String pagoDol = ""; 
        String observaciones = "";
        Date fechaAlta = Calendar.getInstance().getTime();
        
         //int numD = 0;
        
		try {
			// Se abre el fichero Excel
			Workbook workbook = Workbook.getWorkbook(new File("/tmp/pagos.xls"));
			// Se obtiene la primera hoja
			Sheet sheet = workbook.getSheet(0);

			int filas = sheet.getRows();
			// Se leen las filas
            for (int j = 0; j < filas; j++) {
            		for (int i = 0; i<7; i++) {
					// Se obtiene la celda i-esimay
					Cell cell = sheet.getCell(i,j);

					if (cell.getColumn() == 0) {
						cuenta = cell.getContents();
					}
					if (cell.getColumn() == 1) {
						nombre = cell.getContents();
					}
					if (cell.getColumn() == 2) {
						if(cell.getContents().isEmpty()==true){
							fecPagoCord = null;
						} else {
							fecPagoCord = ((DateCell)cell).getDate();
						}
					}
					if (cell.getColumn() == 3) {
						if(cell.getContents().isEmpty()==true){
							fecPagoDol = null;
						} else {
							fecPagoDol = ((DateCell)cell).getDate();
						}
					}
					if (cell.getColumn() == 4) {
						pagoCor = cell.getContents();
					}
					if (cell.getColumn() == 5) {
						pagoDol = cell.getContents();
					}
					if (cell.getColumn() == 6) {
							observaciones = cell.getContents();
					}
					
				
				}
			// Insertamos los nuevos registros.
			insertTablaPagos(cuenta, nombre, fecPagoCord, fecPagoDol, pagoCor, pagoDol, observaciones, fechaAlta);
			}
            borrarExcelPagos();
		} catch (Exception ex) {
			System.out.println("Error!");
			ex.printStackTrace();
		}
	}
	
	private void insertTablaPagos(final String cuenta, final String nombre,
			final Date fecPagoCord, final Date fecPagoDol,
			final String pagoCor, final String pagoDol,
			final String observaciones, final Date fechaAlta) throws Exception {
	
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
			insertQuery = "INSERT INTO pagos_banpro(cuenta, nombre, fec_pago_cord, fec_pago_dol, pago_cor, pago_dol, observaciones, fecha_alta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			ejecutarInsertPagosQuery(connPostgres, insertQuery, cuenta, nombre, fecPagoCord, fecPagoDol, pagoCor, pagoDol, observaciones, fechaAlta);
	
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
	
	private void ejecutarInsertPagosQuery(Connection connPostgres,
			String insertQuery, final String cuenta, final String nombre,
			final Date fecPagoCord, final Date fecPagoDol,
			final String pagoCor, final String pagoDol,
			final String observaciones, final Date fechaAlta) throws Exception {
		
		PreparedStatement psOrigen = null;		

		try {
				psOrigen = connPostgres.prepareStatement(insertQuery);
				
				psOrigen.setString(1, cuenta.toString());
				psOrigen.setString(2, nombre.toString());
				if(fecPagoCord==null){
					psOrigen.setDate(3, null);
				} else {
					//java.sql.Date fechaC = java.sql.Date.valueOf(fecPagoCord);  
                                    //Date fechaC =Util.stringToDate(fecPagoCord);
                    java.sql.Date fechaC2 = java.sql.Date.valueOf(getFechaSQL(fecPagoCord));  
				    psOrigen.setDate(3, fechaC2);
					//psOrigen.setString(3, fecPagoCord);
				}
				if(fecPagoDol==null){
					psOrigen.setDate(4, null);
				} else {
                                    //Date fechaD = Util.stringToDate(fecPagoDol);  
	                java.sql.Date fechaD2 = java.sql.Date.valueOf(getFechaSQL(fecPagoDol));  
	                psOrigen.setDate(4, fechaD2);
					//psOrigen.setString(4, fecPagoDol);
				}
				
				psOrigen.setBigDecimal(5, Util.stringToBigDecimal(pagoCor));
				psOrigen.setBigDecimal(6, Util.stringToBigDecimal(pagoDol));
                if(observaciones==""){
					psOrigen.setString(7, null);
				} else {
					psOrigen.setString(7, observaciones.toString());
				}
				java.sql.Date fechaA = java.sql.Date.valueOf(getFechaSQL(fechaAlta));  
				psOrigen.setDate(8, fechaA);
				
				psOrigen.executeUpdate();
						
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void borrarExcelPagos() {         
	     String sFichero = "/tmp/pagos.xls";
	     File archivo = new File(sFichero);         
	     if (archivo.delete())
	             System.out.println("El archivo " + sFichero + " ha sido borrado correctamente");
	     else
	             System.out.println("El archivo " + sFichero + " no se ha podido borrar");

	}
	
	public Integer contarPagosBanpro()
			throws Exception {
            
		Connection connPostgres = null;
		String selectQuery;
        PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		int max = 0;
		Date fechaAlta = Calendar.getInstance().getTime();
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
			
			selectQuery = "select count(*) from pagos_banpro where fecha_alta=?";
			
			psOrigen = connPostgres.prepareStatement(selectQuery);
			
			java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaAlta));
            psOrigen.setDate(1,fecha);
            
			
            rs = psOrigen.executeQuery();
            
            rs.next();
            int count = rs.getInt(1);
            
            rs.close();
			psOrigen.close();
			
            return count;
         
		} catch (Exception e) {
			throw e;
		} 
	}
}
