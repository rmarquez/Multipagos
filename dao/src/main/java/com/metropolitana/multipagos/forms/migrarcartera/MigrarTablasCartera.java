package com.metropolitana.multipagos.forms.migrarcartera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MigrarTablasCartera {
	
	
	public static void migracion(Connection connPostgres, String selectOrigenQuery, String countOrigenQuery,
            String insertQuery) throws Exception {
        PreparedStatement psOrigen = null;
        PreparedStatement psDestino = null;
        ResultSet rsOrigen = null;
        ResultSet rsDestino = null;
        try {
            // Obtener la cantidad de registros a insertar, cantidad de registros
            // en la tabla origen.
            psOrigen = connPostgres.prepareStatement(countOrigenQuery);
            rsOrigen = psOrigen.executeQuery();
            rsOrigen.next();
            int countOrigen = rsOrigen.getInt(1);
            psOrigen = connPostgres.prepareStatement(selectOrigenQuery);
            rsOrigen= psOrigen.executeQuery();
            int countDestino = 0;
            
            String codPrueba, clave, nombreItem = null;
            int cont = 1, repetidos = 0;
            boolean cancel = false;
            while (rsOrigen.next()) {
                psDestino = connPostgres.prepareStatement(insertQuery);
               
                if (rsOrigen.getString(3).equals("ASC")) {
                    psDestino.setString(3, "Impacto e Influencia");
                }
                if (rsOrigen.getString(3).equals("AUT")) {
                    psDestino.setString(3, "Autoestima");
                }
                if (rsOrigen.getString(3).equals("VIT")) {
                    psDestino.setString(3, "Dinamismo y Energia");
                }
                if (rsOrigen.getString(3).equals("REP")) {
                    psDestino.setString(3, "Compromiso-Disciplina");
                }
                if (rsOrigen.getString(3).equals("RET")) {
                    psDestino.setString(3, "Orientacion a resultados");
                }
                if (rsOrigen.getString(3).equals("REC")) {
                    psDestino.setString(3, "Reconocimiento");
                }
                if (rsOrigen.getString(3).equals("IND")) {
                    psDestino.setString(3, "Autonomia");
                }
                if (rsOrigen.getString(3).equals("VAR")) {
                    psDestino.setString(3, "Innovacion");
                }
                if (rsOrigen.getString(3).equals("BEN")) {
                    psDestino.setString(3, "Colaboración");
                }
                if (rsOrigen.getString(3).equals("CAU")) {
                    psDestino.setString(3, "Cautela");
                }
                if (rsOrigen.getString(3).equals("ORI")) {
                    psDestino.setString(3, "Creatividad");
                }
                if (rsOrigen.getString(3).equals("PRA")) {
                    psDestino.setString(3, "Practicidad");
                }
                if (rsOrigen.getString(3).equals("DEC")) {
                    psDestino.setString(3, "Toma de Decisiones");
                }
                if (rsOrigen.getString(3).equals("ORD")) {
                    psDestino.setString(3, "Adaptabilidad al Cambio");
                }
                if (rsOrigen.getString(3).equals("MET")) {
                    psDestino.setString(3, "Iniciativa");
                }
                if (rsOrigen.getString(3).equals("SOC")) {
                    psDestino.setString(3, "Desarrollo de Relaciones");
                }
                if (rsOrigen.getString(3).equals("COM")) {
                    psDestino.setString(3, "Empatia");
                }
                if (rsOrigen.getString(3).equals("CON")) {
                    psDestino.setString(3, "Cumplimiento de Normas");
                }
                if (rsOrigen.getString(3).equals("LID")) {
                    psDestino.setString(3, "Liderazgo");
                }
            
       
                if (!cancel) {
                    // Execute the INSERT
                    psDestino.executeUpdate();
                    // Contar los registros que se van insertando en la tabla.
                    countDestino+= psDestino.getUpdateCount();
                }

            }
            
            rsOrigen.close();
            psOrigen.close();
            psDestino.close();
            System.out.print("copiados: "+countDestino+" de: "+countOrigen+" \n");
            if (repetidos > 0) {
                System.out.print("Registros con nombre repetidos: " +repetidos+" \n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public static void migracionAccessToPostgres() throws Exception {

		Connection connPostgres = null;
        String selectOrigenQuery;
        String countOrigenQuery;
        String insertQuery;
        
        try {
            // Parámetros de conexión con Postgres
        	try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra el Driver: " + e.getMessage());
            }
            String username = "postgres";
            String password = "";
            String url = "jdbc:postgresql://localhost:5432/multipagos";
            connPostgres = DriverManager.getConnection(url, username, password);
            System.out.println("**************  CATALOGOS **************");
            
            // Tabla universidades
            selectOrigenQuery = "SELECT contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asiganado FROM tmp_cartera";
            countOrigenQuery = "SELECT COUNT(*) FROM tmp_cartera";
            insertQuery = "INSERT INTO cartera_x_departamento(cartera_id, contrato, suscriptor, nit, direccion, barrio_id,factura_interna, numero_fiscal, ano, mes, saldo, estado_id, departamento_id,localidad_id, cupon, telefono, descuento, servicio_id, empleador, direccion_empleador, pagado, fecha_pago, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            migracion(connPostgres, selectOrigenQuery, countOrigenQuery, insertQuery);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	if(connPostgres != null) connPostgres.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
