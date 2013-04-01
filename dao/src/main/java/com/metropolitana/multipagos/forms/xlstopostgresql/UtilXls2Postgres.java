package com.metropolitana.multipagos.forms.xlstopostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilXls2Postgres {
	
	public int procesoSql() throws Exception {
            sqlExcluidos();
            Integer numero = getNumAsignacion();
            System.out.println("NUMERO ASIGNACION = " +numero);
            if(numero != null){
            	numero = numero+1;
            } else {
            	numero = 1;
            }
            insertValor(numero);
            prepararDLocalidad();
            prepararBarrios();
            prepararEstados();
            
            return numero;
            
	}
	
	public static Integer getNumAsignacion()
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
			
			query = "select num_asignacion from asignacion_claro order by cartera_id desc limit 1 ";
				
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
	
private void insertValor(final Integer numero) throws Exception {
		
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
			query = "update tmp_asignacion_claro set num_asignacion=? where num_asignacion is null;";
			ejecutarInsertValor(connPostgres, query, numero );
			
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
	
	private void ejecutarInsertValor(Connection connPostgres,
			String insertQuery, final Integer numero) throws Exception {
		PreparedStatement psOrigen = null;

		try {
			psOrigen = connPostgres.prepareStatement(insertQuery);

			psOrigen.setInt(1, numero);

			psOrigen.executeUpdate();

			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    	
	public void sqlExcluidos() throws Exception {
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
			
			query = "select count (*) from cartera_x_departamento;";
			int antes = countQuery(connPostgres, query);

			query = "select count (factura_interna) from tmp_cartera where factura_interna in (select factura_interna from cartera_x_departamento); ";
			int count = countQuery(connPostgres, query);
			
			query = "select a.*, date_part('year', f_asignado) as anio_asignacion, date_part('month', f_asignado) as mes_asignacion into tmp_asignacion_claro from tmp_cartera a;";
			ejecutarQuery(connPostgres, query);
			
			query = "alter table tmp_asignacion_claro add column num_asignacion int4;";
			ejecutarQuery(connPostgres, query);
			
			query = "select a.* into tmp_excluidos from tmp_cartera a where factura_interna in (select factura_interna from cartera_x_departamento);";
            ejecutarQuery(connPostgres, query);

            if(count > 0){
                query = "delete from tmp_cartera  where tmp_id in (select tmp_id from tmp_cartera where factura_interna in (select factura_interna from cartera_x_departamento));";
                ejecutarQuery(connPostgres, query);   
            }
           
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
        
    public int cantidadInsertados() throws Exception {
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
			           
            query = "insert into cartera_x_departamento ( "+
					"contrato, suscriptor, nit, direccion, barrio_id, factura_interna, numero_fiscal, anio, mes, saldo, estado_id, departamento_id, "+
					"localidad_id, cupon, telefono, descuento, servicio_id, empleador, direccion_empleador, fecha_ingreso, cuenta, concepto_diferido, "+
					"es_diferido )"+	
			"select  a.contrato,	a.suscriptor, a.nit, a.direccion, a.barrio_id, a.factura_interna, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado_id, "+
					"a.departamento_id, a.localidad_id, a.cupon, a.telefono, 0 as descuento, a.servicio_id, a.empleador, a.direccion_empleador, "+
					"a.f_asignado as fecha_ingreso,a.cuenta, a.concepto_diferido, a.es_diferido "+
			"from 	tmp_con_dpts_lcldds_barrios_3 a; ";
            ejecutarQuery(connPostgres, query);
            
            query = "select a.* ,date_part('year', fecha_ingreso) as anio_asignacion, date_part('month', fecha_ingreso) as mes_asignacion into tmp_asignacion_claro_2 " +
			"from cartera_x_departamento a where factura_interna in (select factura_interna from tmp_asignacion_claro);";
			ejecutarQuery(connPostgres, query);

			query = "update tmp_asignacion_claro_2 set anio_asignacion=( " +
			"select anio_asignacion from tmp_asignacion_claro " +
			"where factura_interna in (select factura_interna from tmp_asignacion_claro_2) " +
			"group by anio_asignacion order by anio_asignacion);";
			ejecutarQuery(connPostgres, query);

			query = "update tmp_asignacion_claro_2 set mes_asignacion=( " +
			"select mes_asignacion from tmp_asignacion_claro " +
			"where factura_interna in (select factura_interna from tmp_asignacion_claro_2) " +
			"group by mes_asignacion order by mes_asignacion);";
			ejecutarQuery(connPostgres, query);
			
			query = "insert into asignacion_claro (" +
					"contrato, suscriptor, nit, direccion, barrio_id, factura_interna, numero_fiscal, anio, mes, saldo, estado_id, departamento_id, "+
					"localidad_id, cupon, telefono, descuento, servicio_id, empleador, direccion_empleador, fecha_ingreso, cuenta, concepto_diferido, "+
					"anio_asignacion, mes_asignacion, num_asignacion) "+
			"select a.contrato,	a.suscriptor, a.nit, a.direccion, a.barrio_id, a.factura_interna, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado_id, "+
					"a.departamento_id, a.localidad_id, a.cupon, a.telefono, 0 as descuento, a.servicio_id, a.empleador, a.direccion_empleador, "+
					"b.f_asignado,a.cuenta, a.concepto_diferido, a.anio_asignacion, a.mes_asignacion, b.num_asignacion " +
					"from 	tmp_asignacion_claro_2 a "+
			"inner join tmp_asignacion_claro b on a.factura_interna = b.factura_interna;";
		    ejecutarQuery(connPostgres, query);

			query = "drop table tmp_asignacion_claro;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_asignacion_claro_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_barrios_3; ";
            int cantidad = countQuery(connPostgres, query);
			
            eliminarTemporales();
			
            return cantidad;

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
		return 0;
	}
	
	public int sqlProcessAvon() throws Exception {
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

			query = "select count (factura) from tmp_cartera_avon where factura in (select factura from cartera_avon); ";
			int count = countQuery(connPostgres, query);
			
			query = "select a.* into tmp_excluidos from tmp_cartera_avon a where factura in (select factura from cartera_avon);";
            ejecutarQuery(connPostgres, query);
                        
            if(count > 0){
                query = "delete from tmp_cartera_avon  where tmp_id in (select tmp_id from tmp_cartera_avon where factura in (select factura from cartera_avon));";
                ejecutarQuery(connPostgres, query);   
            }
            
            query = "update tmp_cartera_avon set departamento = upper(departamento);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera_avon set departamento = 'MANAGUA' where departamento like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera_avon set departamento = 'ESTELI' where departamento like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera_avon set departamento='LEON' where departamento like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera_avon set departamento = 'LEON' where departamento like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera_avon set departamento = 'MATAGALPA' where departamento like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into departamento (departamento_nombre) " +
            "(select distinct departamento as departamento_nombre from tmp_cartera_avon "+
            "where upper(departamento) not in ( select upper(departamento_nombre) from departamento ) order by 1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.departamento_id, a.* "+
        	"into	tmp_con_dpts " +
        	"from 	tmp_cartera_avon a " +
        	"left 	outer join departamento b on upper(rtrim(ltrim(a.departamento))) = upper(rtrim(ltrim(b.departamento_nombre))) " +
        	"ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = upper(localidad);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like '.MANA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD SANDINO' where localidad like '%SANDINO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD DARIO' where localidad like '%DARIO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD DARIO' where localidad like 'CIUDAD DARÍO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'ESTELI' where localidad like 'ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like '%LEÓN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like '%LE�N%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'TOTOGALPA' where localidad like '%TOTOGALPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'EL TUMA - LA DALIA' where localidad like '%TUMA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MUY MUY' where localidad like 'MUYMUY';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SEBACO' where localidad like '%SEBACO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SEBACO' where localidad like 'SÉBACO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN RAMON' where localidad like '%SAN RAMON%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'RANCHO GRANDE' where localidad like '%RANCHO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'RENE BARRANTES' where localidad like '%RENE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN FERNANDO' where localidad like '%SAN FERNANDO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN NICOLAS' where localidad like '%SAN NICOLAS%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN ISIDRO' where localidad like '%SAN ISIDRO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN JOSE DE CUSMAPA' where localidad like 'SAN JOSÉ DE CUSMAPA';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN JUAN DE RIO COCO' where localidad like 'SAN JUAN DEL RIO COCO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'JALAPA' where localidad like '%JALAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MATAGALPA' where localidad like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LA TRINIDAD' where localidad like '%TRIN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MULUKUKU' where localidad like '%MULU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN RAFAEL DEL SUR' where localidad like '%RAFAEL DEL SUR%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'NAGAROTE' where localidad like '%NAGAROTE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'NINDIRI' where localidad like '%NINDIRI%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'TIPITAPA' where localidad like '%TIPITAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LA TRINIDAD' where localidad like '%LA TIRNIDAD%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select departamento_id, localidad as localidad_nombre	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into localidad (departamento_id, localidad_nombre) ( " +	
        	"select 	departamento_id, localidad_nombre "+	
        	"from	tmp__ " +
        	"where 	upper(localidad_nombre) not in ( select  upper(localidad_nombre)  from localidad ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.localidad_id, a.* "+
        	"into	tmp_con_dpts_lcldds "+
        	"from 	tmp_con_dpts a "+
        	"left 	outer join localidad b on a.departamento_id = b.departamento_id and upper(trim(ltrim(a.localidad))) = upper(trim(ltrim(b.localidad_nombre))) "+
        	"ORDER BY 1;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set barrio = 'BO. CARLOS NUNEZ'  where barrio like 'BO. CARLOS NU%';";
            ejecutarQuery(connPostgres, query);
            
            //Valores Null
            
            query = "select count (*) from tmp_con_dpts_lcldds where localidad_id is null; ";
			int count2 = countQuery(connPostgres, query);
                        
            if(count2 > 0){
                  
	            query = "select localidad_id, localidad_nombre "+
				"into tmp_localidad "+
				"from localidad " +
				"where localidad_nombre IN ( SELECT departamento FROM tmp_con_dpts_lcldds where localidad_id is null );";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	b.localidad_id, a.tmp_id, a.departamento_id, a.codigo, a.consejero, a.cedula, a.direccion, a.departamento, a.localidad, a.barrio, a.telefono, a.zona, a.factura, a.saldo, a.mes, a.anio, a.agencia, a.campania, a.fecha_baja, a.fecha_factura, a.fecha_asignado "+
			    "into	tmp_con_dpts_lcldds_2 "+
				"from 	tmp_con_dpts_lcldds a "+
				"inner join tmp_localidad b on a.departamento = b.localidad_nombre "+
				"where a.localidad_id is null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	a.* into tmp_con_dpts_lcldds_3 from tmp_con_dpts_lcldds a where a.localidad_id is not null;";
	            ejecutarQuery(connPostgres, query);
	            
	            
	            query = "drop table tmp_con_dpts_lcldds;";
	            ejecutarQuery(connPostgres, query);
					
	            query = "select	localidad_id, tmp_id, departamento_id, codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado "+
				"into	tmp_con_dpts_lcldds "+
				"from 	tmp_con_dpts_lcldds_2 "+
				"union "+
				"select	localidad_id, tmp_id, departamento_id, codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado  "+
				"from 	tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);
	            
					
	            query = "drop table tmp_localidad;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_2;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);  
            
            }
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set barrio = upper(barrio);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "select 	distinct barrio, localidad_id, departamento_id, count(*) as cant "+ 
        	"into	tmp_ "+
        	"from 	tmp_con_dpts_lcldds "+ 
        	"where 	tmp_id not in "+ 
        	"		("+
        				"select 	tmp_id "+
        				"from 	tmp_con_dpts_lcldds a, barrio b "+
        				"where	a.localidad_id	= b.localidad_id "+
        				"and	a.barrio = b.barrio_nombre "+
        	"		) "+
        	"and	localidad_id is not null "+
        	"group 	by barrio, localidad_id, departamento_id "+
        	"order	by barrio, localidad_id, departamento_id;";
            ejecutarQuery(connPostgres, query);
            
            
            query = "insert into barrio (localidad_id, barrio_nombre) ( "+	
    		"select 	localidad_id, barrio as barrio_nombre "+	
    		"from	tmp_ "+
    		"order 	by localidad_id, barrio	);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.barrio_id, a.* "+
        	"into	tmp_con_dpts_lcldds_barrios "+
        	"from 	tmp_con_dpts_lcldds a "+
        	"left 	outer join barrio b on  a.localidad_id = b.localidad_id and upper(trim(ltrim(a.barrio))) = upper(trim(ltrim(b.barrio_nombre))) "+
        	"ORDER BY 1;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_barrios where barrio_id is null; ";
			int countBarrio = countQuery(connPostgres, query);
                        
            if(countBarrio >0) {
            	
            System.out.println("********* Dentro del Count ************");
            
		            query = "select barrio_id, barrio_nombre "+ 
		            "into tmp_barrio "+
		            "from barrio "+
		            "where barrio_nombre IN ( SELECT barrio FROM tmp_con_dpts_lcldds_barrios  where barrio_id is null);";
		            ejecutarQuery(connPostgres, query);	
		
		            query = "select	b.barrio_id,a.localidad_id,	a.departamento_id, tmp_id, codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado  "+ 
		            "into	tmp_con_dpts_lcldds_barrios_n "+ 
		            "from 	tmp_con_dpts_lcldds_barrios a "+
		            "inner join tmp_barrio b on a.barrio = b.barrio_nombre "+
		            "where a.barrio_id is null;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "select	a.* into tmp_con_dpts_lcldds_barrios_n2 from tmp_con_dpts_lcldds_barrios a where a.barrio_id is not null;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios;";
		            ejecutarQuery(connPostgres, query);
		
		            query = "select	barrio_id, localidad_id, departamento_id, tmp_id, codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado "+ 
		            "into	tmp_con_dpts_lcldds_barrios "+ 
		            "from 	tmp_con_dpts_lcldds_barrios_n "+ 
		            "union "+ 
		            "select	barrio_id, localidad_id, departamento_id, tmp_id, codigo, consejero, cedula, direccion, departamento, localidad, barrio, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado "+ 
		            "from 	tmp_con_dpts_lcldds_barrios_n2;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_barrio;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios_n;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios_n2;";
		            ejecutarQuery(connPostgres, query);
            }
            
            query = "select distinct on (a.factura) a.factura, a.barrio_id, a.localidad_id, a.departamento_id, a.codigo, a.consejero, a.cedula, a.direccion, a.departamento, a.localidad, a.barrio, a.telefono, a.zona, a.saldo, a.mes, a.anio, a.agencia, a.campania, a.fecha_baja, a.fecha_factura, a.fecha_asignado " +
            "into tmp_con_dpts_lcldds_barrios_2 "+ 
            "from tmp_con_dpts_lcldds_barrios a";
            ejecutarQuery(connPostgres, query);
            
           
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);

            query = "select * into tmp_con_dpts_lcldds_barrios from tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
           
            query = "insert into cartera_avon ( "+
					"barrio_id, localidad_id, departamento_id, codigo, consejero, cedula, direccion, telefono, zona, factura, saldo, mes, anio, agencia, campania, fecha_baja, fecha_factura, fecha_asignado )"+	
			"select  a.barrio_id, a.localidad_id, a.departamento_id, a.codigo, a.consejero, a.cedula, a.direccion, a.telefono, a.zona, a.factura, a.saldo, a.mes, a.anio, a.agencia, a.campania, a.fecha_baja, a.fecha_factura, a.fecha_asignado "+
			"from 	tmp_con_dpts_lcldds_barrios a ";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_barrios; ";
			int cantidad = countQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_3;";
            ejecutarQuery(connPostgres, query);
            
            return cantidad;

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
		return 0;
	}
	
	
	
	private void ejecutarQuery(Connection connPostgres, String query)
			throws Exception {
		PreparedStatement psOrigen = null;
		ResultSet rsOrigen = null;

		try {

			psOrigen = connPostgres.prepareStatement(query);
			rsOrigen = psOrigen.executeQuery();

			rsOrigen.close();
			psOrigen.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int contarExcluidos() throws Exception {
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

			query = "select count (factura_interna) from tmp_excluidos;";
			int cantidad = countQuery(connPostgres, query);
			
			query = "update cartera_x_departamento set servicio_id=8 where servicio_id=1 and factura_interna in (select factura_interna from tmp_excluidos);";
			ejecutarQuery(connPostgres, query);
			
			query = "update cartera_x_departamento set servicio_id=10 where servicio_id=5 and factura_interna in (select factura_interna from tmp_excluidos);";
			ejecutarQuery(connPostgres, query);
			
			query = "update cartera_x_departamento set servicio_id=12 where servicio_id=7 and factura_interna in (select factura_interna from tmp_excluidos);";
			ejecutarQuery(connPostgres, query);
			
			query = "update cartera_x_departamento set servicio_id=11 where servicio_id=2 and factura_interna in (select factura_interna from tmp_excluidos);";
			ejecutarQuery(connPostgres, query);
            
            return cantidad;

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
		return 0;
	}
	
	public int contarExcluidosAvon() throws Exception {
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

			query = "select count (factura) from tmp_excluidos;";
			int cantidad = countQuery(connPostgres, query);
			
            return cantidad;

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
		return 0;
	}
	
	public void pendientesClaro() throws Exception {
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

			corregirClaroDep();
			
			query = "drop table tmp_claro_dpts;";
			ejecutarQuery(connPostgres, query);
			
			query = "select	b.departamento_id, a.* " +
			"into	tmp_claro_dpts " +
			"from 	tmp_carteraclaro a " +
			"left 	outer join departamento b on upper(rtrim(ltrim(a.departamento))) = upper(rtrim(ltrim(b.departamento_nombre)ltrim)ltrim) "+
			"ORDER BY 1;";
			ejecutarQuery(connPostgres, query);			
			
			query = "select a.* into tmp_claro_dpts_2 from cartera_x_departamento a where departamento_id in (select departamento_id from tmp_claro_dpts);";
			ejecutarQuery(connPostgres, query);
			
			query = "delete from tmp_claro_dpts_2 where factura_interna in (select factura_interna from tmp_claro_dpts);";
			ejecutarQuery(connPostgres, query);			

			query = "update cartera_x_departamento set pagado_claro=true where factura_interna in (select factura_interna from tmp_claro_dpts_2);";
			ejecutarQuery(connPostgres, query);
			
			query = "drop table tmp_claro_dpts_2;";
			ejecutarQuery(connPostgres, query);
			
			query = "drop table tmp_claro_dpts;";
			ejecutarQuery(connPostgres, query);		
			
            //return cantidad;

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
		//return 0;
	}
	
	
	public int pagosClaro() throws Exception {
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
			
			query = "select a.* into tmp_claro_dpts_2 from cartera_x_departamento a where factura_interna in (select factura_interna from tmp_carteraclaro) and pagado_claro=false and pagado=false;";
			ejecutarQuery(connPostgres, query);
			
			query = "UPDATE cartera_x_departamento SET fecha_pago=tmp_carteraclaro.fecha_pago FROM tmp_carteraclaro WHERE cartera_x_departamento.factura_interna=tmp_carteraclaro.factura_interna AND cartera_x_departamento.pagado=false;";
			ejecutarQuery(connPostgres, query);
			
			query = "update cartera_x_departamento set pagado_claro=true where factura_interna in (select factura_interna from tmp_carteraclaro) and pagado_claro=false and pagado=false;";
			ejecutarQuery(connPostgres, query);
			
			query = "select count (factura_interna) from cartera_x_departamento where factura_interna in (select factura_interna from tmp_claro_dpts_2);";
			int cantidad = countQuery(connPostgres, query);
			
			query = "drop table tmp_claro_dpts_2;";
			ejecutarQuery(connPostgres, query);
			
            return cantidad;

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
		return 0;
	}
	
	private void corregirClaroDep() throws Exception {
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

			query = "update tmp_carteraclaro set departamento = upper(departamento);";
			ejecutarQuery(connPostgres, query);
			
			query = "update tmp_carteraclaro set departamento = 'MANAGUA' where departamento like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_carteraclaro set departamento = 'ESTELI' where departamento like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_carteraclaro set departamento='LEON' where departamento like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_carteraclaro set departamento = 'LEON' where departamento like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_carteraclaro set departamento = 'MATAGALPA' where departamento like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
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
		//return 0;
	}
	public static int countQuery(Connection connPostgres, String query) throws Exception {
		PreparedStatement psOrigen = null;
		ResultSet rsOrigen = null;

		try {

			psOrigen = connPostgres.prepareStatement(query);
			rsOrigen = psOrigen.executeQuery();
			rsOrigen.next();
            int count = rsOrigen.getInt(1);

			rsOrigen.close();
			psOrigen.close();
			return count;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public String getFechaSQL(final Date asignado) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(asignado);
    }
	
	
	
	private void prepararDLocalidad() throws Exception {
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
			
			query = "update tmp_cartera set departamento = upper(departamento);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento = 'MANAGUA' where departamento like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento = 'ESTELI' where departamento like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento='LEON' where departamento like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento = 'LEON' where departamento like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento = 'MATAGALPA' where departamento like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_cartera set departamento = 'JINOTEGA' where departamento like '%JINOTEGA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into departamento (departamento_nombre) " +
            "(select distinct departamento as departamento_nombre from tmp_cartera "+
            "where upper(departamento) not in ( select upper(departamento_nombre) from departamento ) order by 1);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.departamento_id, a.* "+
        	"into tmp_con_dpts " +
        	"from tmp_cartera a " +
        	"left outer join departamento b on upper(trim(ltrim(a.departamento))) = upper(trim(ltrim(b.departamento_nombre))) " +
        	"ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = upper(localidad);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like '.MANA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD SANDINO' where localidad like '%SANDINO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD DARIO' where localidad like '%DARIO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'CIUDAD DARIO' where localidad like 'CIUDAD DARÍO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'ESTELI' where localidad like 'ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like '%LEÓN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like '%LE�N%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LEON' where localidad like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'TOTOGALPA' where localidad like '%TOTOGALPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'EL TUMA - LA DALIA' where localidad like '%TUMA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MUY MUY' where localidad like 'MUYMUY';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SEBACO' where localidad like '%SEBACO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SEBACO' where localidad like 'SÉBACO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN RAMON' where localidad like '%SAN RAMON%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'RANCHO GRANDE' where localidad like '%RANCHO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'RENE BARRANTES' where localidad like '%RENE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN FERNANDO' where localidad like '%SAN FERNANDO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN NICOLAS' where localidad like '%SAN NICOLAS%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN ISIDRO' where localidad like '%SAN ISIDRO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN JOSE DE CUSMAPA' where localidad like 'SAN JOSÉ DE CUSMAPA';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN JUAN DE RIO COCO' where localidad like 'SAN JUAN DEL RIO COCO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'JALAPA' where localidad like '%JALAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MATAGALPA' where localidad like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LA TRINIDAD' where localidad like '%TRIN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MULUKUKU' where localidad like '%MULU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'SAN RAFAEL DEL SUR' where localidad like '%RAFAEL DEL SUR%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'MANAGUA' where localidad like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'NAGAROTE' where localidad like '%NAGAROTE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'NINDIRI' where localidad like '%NINDIRI%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'TIPITAPA' where localidad like '%TIPITAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set localidad = 'LA TRINIDAD' where localidad like '%LA TIRNIDAD%';";
            ejecutarQuery(connPostgres, query);	
            
            query = "update tmp_con_dpts set localidad = 'JINOTEGA' where localidad like '%JINOTEGA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select departamento_id, localidad as localidad_nombre, factura_interna	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_interna) a.factura_interna, a.departamento_id, a.localidad_nombre " +
            "into tmp__2 "+ 
            "from tmp__ a order by 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into localidad (departamento_id, localidad_nombre) ( " +	
        	"select 	departamento_id, localidad_nombre "+	
        	"from tmp__2 " +
        	"where upper(localidad_nombre) not in (select upper(localidad_nombre) from localidad ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.localidad_id, a.* "+
        	"into tmp_con_dpts_lcldds "+
        	"from tmp_con_dpts a "+
        	"left outer join localidad b on upper(trim(ltrim(a.localidad))) = upper(trim(ltrim(b.localidad_nombre))) "+
        	"ORDER BY 1;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_interna) a.factura_interna, a.localidad_id, a.departamento_id, a.tmp_id, a.contrato, a.suscriptor, a.nit, "+
            "a.direccion, a.barrio, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado, a.departamento, a.localidad, a.cupon, a.telefono, a.descuento, "+
            "a.servicio, a.empleador, a.direccion_empleador, a.f_asignado, a.cuenta, a.concepto_diferido, a.es_diferido "+
            "into tmp_con_dpts_lcldds_2 "+ 
            "from tmp_con_dpts_lcldds a "+
            "where a.localidad_id is not null;";
            ejecutarQuery(connPostgres, query);

            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);

            query = "select * into tmp_con_dpts_lcldds from tmp_con_dpts_lcldds_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_2;";
            ejecutarQuery(connPostgres, query);
			

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
	
	private void prepararBarrios() throws Exception {
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
			
			query = "drop table tmp_con_dpts_lcldds_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set barrio = 'BO. CARLOS NUNEZ'  where barrio like 'BO. CARLOS NU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set barrio = upper(barrio);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct barrio, localidad_id, departamento_id, count(*) as cant into tmp_ from tmp_con_dpts_lcldds where tmp_id not in "+ 
        	"(select tmp_id from tmp_con_dpts_lcldds a, barrio b where a.localidad_id= b.localidad_id and a.barrio = b.barrio_nombre) and localidad_id is not null "+
        	"group by barrio, localidad_id, departamento_id order by barrio, localidad_id, departamento_id;";
            ejecutarQuery(connPostgres, query);
            
            
            query = "insert into barrio (localidad_id, barrio_nombre) (select localidad_id, barrio as barrio_nombre from tmp_ order by localidad_id, barrio);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.barrio_id, a.* into tmp_con_dpts_lcldds_barrios from tmp_con_dpts_lcldds a "+
            "left outer join barrio b on upper(rtrim(ltrim(a.barrio)))=upper(rtrim(ltrim(b.barrio_nombre))) ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_interna) a.factura_interna, a.barrio_id, a.localidad_id, a.departamento_id, a.tmp_id, a.contrato, a.suscriptor, a.nit, a.direccion, a.barrio, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado, a.departamento, a.localidad, a.cupon, a.telefono, a.descuento, "+
            "a.servicio, a.empleador, a.direccion_empleador, a.f_asignado, a.cuenta, a.concepto_diferido, a.es_diferido into tmp_con_dpts_lcldds_barrios_2 from tmp_con_dpts_lcldds_barrios a where  barrio_id is not null;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);

            query = "select * into tmp_con_dpts_lcldds_barrios from tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
			
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
	
	private void prepararEstados() throws Exception {
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
			
			query = "update tmp_con_dpts_lcldds_barrios set estado = upper(estado);";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into  estado_corte(estado_nombre ) ( "+
    		"select distinct estado as estado_nombre "+
    		"from tmp_con_dpts_lcldds_barrios "+
    		"where estado not in ( select estado_nombre from estado_corte ) "+
    		"order by  estado );";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds_barrios set servicio = upper(servicio);";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into  servicio(servicio_nombre ) ( "+
    		"select distinct servicio as servicio_nombre "+
    		"from tmp_con_dpts_lcldds_barrios "+
    		"where servicio not in ( select servicio_nombre from servicio ) "+
    		"order by  1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_p1;";
            ejecutarQuery(connPostgres, query);
            
           query = "select	a.factura_interna, a.departamento_id, a.localidad_id, a.barrio_id, e.servicio_id, f.estado_id, substr(descuento,1,2) as descuento "+
        	"into 	tmp_p1 "+
        	"from 	tmp_con_dpts_lcldds_barrios a "+ 
        	"left 	outer join servicio e on trim(ltrim(a.servicio)) = trim(ltrim(e.servicio_nombre)) "+
        	"left 	outer join estado_corte f on trim(ltrim(a.estado)) = trim(ltrim(f.estado_nombre)) "+
        	"order 	by a.factura_interna;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_interna) "+ 
    	    "a.factura_interna, a.barrio_id, a.localidad_id, a.departamento_id, a.contrato, a.suscriptor, "+ 
                "a.nit, a.direccion, a.barrio, a.tmp_id, a.numero_fiscal, a.anio, "+ 
                "a.mes, a.saldo, a.estado, a.departamento, a.localidad, a.cupon, a.telefono, "+ 
                "a.descuento, a.servicio, a.empleador, a.direccion_empleador, a.f_asignado, "+ 
                "a.cuenta, a.concepto_diferido, a.es_diferido, b.estado_id, b.servicio_id "+
            "into tmp_con_dpts_lcldds_barrios_3 "+ 
            "from tmp_con_dpts_lcldds_barrios a "+
            "inner join tmp_p1 b on a.factura_interna = b.factura_interna;";
            ejecutarQuery(connPostgres, query);	
			
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
	
	private void eliminarTemporales() throws Exception {
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
			
			query = "drop table tmp_p1;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_3;";
            ejecutarQuery(connPostgres, query);
            
            query = "update cartera_x_departamento set es_diferido=true where es_diferido=false and concepto_diferido is not null;";
            ejecutarQuery(connPostgres, query);
			
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
	
	
	public int sqlProcessBanpro() throws Exception {
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

			query = "update tmp_banpro set departamento_c = trim(departamento_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = trim(departamento_t);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set ciudad_c = trim(ciudad_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set ciudad_t = trim(ciudad_t);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set direccion_c = trim(direccion_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set barrio_c = trim(barrio_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set direccion_t = trim(direccion_t);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set ubicacion_ba = trim(ubicacion_ba);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set producto = trim(producto);";
            ejecutarQuery(connPostgres, query);
			
			query = "select count (tarjeta) from tmp_banpro where tarjeta in (select tarjeta from cartera_banpro); ";
			int count = countQuery(connPostgres, query);
			
			query = "select a.* into tmp_excluidos from tmp_banpro a where tarjeta in (select tarjeta from cartera_banpro);";
            ejecutarQuery(connPostgres, query);
                        
            if(count > 0){
                query = "delete from tmp_banpro  where tmp_id in (select tmp_id from tmp_banpro where tarjeta in (select tarjeta from cartera_banpro));";
                ejecutarQuery(connPostgres, query);   
            }
            
            query = "update tmp_banpro set departamento_c = upper(departamento_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = upper(departamento_t);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c = 'MANAGUA' where departamento_c like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = 'MANAGUA' where departamento_t like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c = 'ESTELI' where departamento_c like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = 'ESTELI' where departamento_t like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c='LEON' where departamento_c like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t='LEON' where departamento_t like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c = 'MATAGALPA' where departamento_c like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = 'MATAGALPA' where departamento_t like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c = 'R. AUTONOMA DE ATLANTICO NORTE' where departamento_c like 'REG AUTONOMA ATLANTICO N';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = 'R. AUTONOMA DE ATLANTICO NORTE' where departamento_t like 'REG AUTONOMA ATLANTICO N';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_c = 'R. AUTONOMA DE ATLANTICO SUR' where departamento_c like 'REG AUTONOMA ATLANTICO S';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_banpro set departamento_t = 'R. AUTONOMA DE ATLANTICO SUR' where departamento_t like 'REG AUTONOMA ATLANTICO S';";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into b_departamento (b_departamento_nombre) " +
            "(select distinct departamento_c as departamento_nombre from tmp_banpro "+
            "where upper(departamento_c) not in ( select upper(b_departamento_nombre) from b_departamento ) order by 1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into b_departamento (b_departamento_nombre) " +
            "(select distinct departamento_t as departamento_nombre from tmp_banpro "+
            "where upper(departamento_t) not in ( select upper(b_departamento_nombre) from b_departamento ) order by 1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.b_departamento_id as departamento_id_c, a.* "+
        	"into	tmp_con_dpts " +
        	"from 	tmp_banpro a " +
        	"left 	outer join b_departamento b on upper(trim(trim(a.departamento_c))) = upper(trim(trim(b.b_departamento_nombre))) " +
        	"ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.b_departamento_id as departamento_id_t, a.* "+
        	"into	tmp_con_dpts_2 " +
        	"from 	tmp_con_dpts a " +
        	"left 	outer join b_departamento b on upper(trim(trim(a.departamento_t))) = upper(trim(trim(b.b_departamento_nombre))) " +
        	"ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.mult_codigo) a.mult_codigo, a.departamento_id_c, a.departamento_id_t, a.cod_cliente, a.cuenta,a.nombre,a.tarjeta,a.producto,a.antiguedad_anios,a.deuda_cor,a.deuda_dol,a.total_deuda_dol,a.empresa_ba,a.ubicacion_ba,a.cedula, a.direccion_c, a.barrio_c, a.departamento_c,a.ciudad_c,a.direccion_t, a.departamento_t,a.ciudad_t,a.telefono_c,a.telefono_t,a.telefono_o,a.cod_fiador,a.nombre_fiador,a.telefono_fiador, fecha_asignado " +
            "into tmp_con_dpts "+ 
            "from tmp_con_dpts_2 a";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = upper(ciudad_c);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MANAGUA' where ciudad_c like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MANAGUA' where ciudad_c like '.MANA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MANAGUA' where ciudad_c like 'MAA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MANAGUA' where ciudad_c like 'MGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'CIUDAD SANDINO' where ciudad_c like '%SANDINO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'CIUDAD DARIO' where ciudad_c like '%DARIO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'CIUDAD DARIO' where ciudad_c like 'CIUDAD DARÍO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'ESTELI' where ciudad_c like 'ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'LEON' where ciudad_c like '%LEÓN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'LEON' where ciudad_c like '%LE�N%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'LEON' where ciudad_c like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'TOTOGALPA' where ciudad_c like '%TOTOGALPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'EL TUMA - LA DALIA' where ciudad_c like '%TUMA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MUY MUY' where ciudad_c like 'MUYMUY';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SEBACO' where ciudad_c like '%SEBACO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SEBACO' where ciudad_c like 'SÉBACO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN RAMON' where ciudad_c like '%SAN RAMON%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'RANCHO GRANDE' where ciudad_c like '%RANCHO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'RENE BARRANTES' where ciudad_c like '%RENE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN FERNANDO' where ciudad_c like '%SAN FERNANDO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN NICOLAS' where ciudad_c like '%SAN NICOLAS%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN ISIDRO' where ciudad_c like '%SAN ISIDRO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN JOSE DE CUSMAPA' where ciudad_c like 'SAN JOSÉ DE CUSMAPA';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN JUAN DE RIO COCO' where ciudad_c like 'SAN JUAN DEL RIO COCO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'JALAPA' where ciudad_c like '%JALAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MATAGALPA' where ciudad_c like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'LA TRINIDAD' where ciudad_c like '%TRIN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MULUKUKU' where ciudad_c like '%MULU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'SAN RAFAEL DEL SUR' where ciudad_c like '%RAFAEL DEL SUR%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'MANAGUA' where ciudad_c like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'NAGAROTE' where ciudad_c like '%NAGAROTE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'NINDIRI' where ciudad_c like '%NINDIRI%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'TIPITAPA' where ciudad_c like '%TIPITAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set ciudad_c = 'LA TRINIDAD' where ciudad_c like '%LA TIRNIDAD%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select departamento_id_c, ciudad_c as ciudad_c_nombre, mult_codigo	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.mult_codigo) a.mult_codigo, a.departamento_id_c, a.ciudad_c_nombre " +
            "into tmp__2 "+ 
            "from tmp__ a order by 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into ciudad (ciudad_nombre, departamento_id) ( 	"+
    		"select 	distinct on (ciudad_c_nombre) ciudad_c_nombre, departamento_id_c  "+	
    		"from	tmp__2 "+
    		"where 	upper(ciudad_c_nombre) not in ( select  upper(ciudad_nombre)  from ciudad ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.ciudad_id as ciudad_id_c, a.* "+
        	"into	tmp_con_dpts_lcldds "+
        	"from 	tmp_con_dpts a "+
        	"left 	outer join ciudad b on a.departamento_id_c = b.departamento_id and upper(trim(ltrim(a.ciudad_c))) = upper(trim(ltrim(b.ciudad_nombre))) "+
        	"ORDER BY 2;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds where ciudad_id_c is null; ";
			int count2 = countQuery(connPostgres, query);
                        
            if(count2 > 0){
                  
	            query = "select ciudad_id, ciudad_nombre "+
				"into tmp_ciudad "+
				"from ciudad " +
				"where ciudad_nombre IN (  select ciudad_c from tmp_con_dpts where tarjeta in(select tarjeta from tmp_con_dpts_lcldds where ciudad_id_c is null)  );";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	b.ciudad_id as ciudad_id_c, a.mult_codigo,a.departamento_id_c,a.departamento_id_t,a.cod_cliente,a.cuenta,a.nombre,a.tarjeta,a.producto,a.antiguedad_anios,a.deuda_cor,a.deuda_dol,a.total_deuda_dol,a.empresa_ba,a.ubicacion_ba,a.cedula,a.direccion_c, a.barrio_c,a.departamento_c,a.ciudad_c,a.direccion_t,a.departamento_t,a.ciudad_t,a.telefono_c,a.telefono_t,a.telefono_o,a.cod_fiador,a.nombre_fiador,a.telefono_fiador, fecha_asignado "+
			    "into	tmp_con_dpts_lcldds_2 "+
				"from 	tmp_con_dpts_lcldds a "+
				"left 	outer join tmp_ciudad b on a.ciudad_c = b.ciudad_nombre "+
				"where a.ciudad_id_c is null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	a.* into tmp_con_dpts_lcldds_3 from tmp_con_dpts_lcldds a where a.ciudad_id_c is not null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds;";
	            ejecutarQuery(connPostgres, query);
					
	            query = "select	ciudad_id_c, mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado "+
				"into	tmp_con_dpts_lcldds "+
				"from 	tmp_con_dpts_lcldds_2 "+
				"union "+
				"select	ciudad_id_c, mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado  "+
				"from 	tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);
	            
					
	            query = "drop table tmp_ciudad;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_2;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);  
            
            }
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = upper(ciudad_t);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MANAGUA' where ciudad_t like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MANAGUA' where ciudad_t like '.MANA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MANAGUA' where ciudad_t like 'MAA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MANAGUA' where ciudad_t like 'MGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'CIUDAD SANDINO' where ciudad_t like '%SANDINO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'CIUDAD DARIO' where ciudad_t like '%DARIO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'CIUDAD DARIO' where ciudad_t like 'CIUDAD DARÍO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'ESTELI' where ciudad_t like 'ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'LEON' where ciudad_t like '%LEÓN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'LEON' where ciudad_t like '%LE�N%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'LEON' where ciudad_t like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'TOTOGALPA' where ciudad_t like '%TOTOGALPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'EL TUMA - LA DALIA' where ciudad_t like '%TUMA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MUY MUY' where ciudad_t like 'MUYMUY';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SEBACO' where ciudad_t like '%SEBACO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SEBACO' where ciudad_t like 'SÉBACO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN RAMON' where ciudad_t like '%SAN RAMON%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'RANCHO GRANDE' where ciudad_t like '%RANCHO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'RENE BARRANTES' where ciudad_t like '%RENE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN FERNANDO' where ciudad_t like '%SAN FERNANDO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN NICOLAS' where ciudad_t like '%SAN NICOLAS%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN ISIDRO' where ciudad_t like '%SAN ISIDRO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN JOSE DE CUSMAPA' where ciudad_t like 'SAN JOSÉ DE CUSMAPA';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN JUAN DE RIO COCO' where ciudad_t like 'SAN JUAN DEL RIO COCO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'JALAPA' where ciudad_t like '%JALAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MATAGALPA' where ciudad_t like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'LA TRINIDAD' where ciudad_t like '%TRIN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MULUKUKU' where ciudad_t like '%MULU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'SAN RAFAEL DEL SUR' where ciudad_t like '%RAFAEL DEL SUR%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'MANAGUA' where ciudad_t like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'NAGAROTE' where ciudad_t like '%NAGAROTE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'NINDIRI' where ciudad_t like '%NINDIRI%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'TIPITAPA' where ciudad_t like '%TIPITAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts_lcldds set ciudad_t = 'LA TRINIDAD' where ciudad_t like '%LA TIRNIDAD%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select departamento_id_t, ciudad_t as ciudad_t_nombre, mult_codigo	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.mult_codigo) a.mult_codigo, a.departamento_id_t, a.ciudad_t_nombre " +
            "into tmp__2 "+ 
            "from tmp__ a order by 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into ciudad (ciudad_nombre, departamento_id) ( " +	
        	"select 	distinct on (ciudad_t_nombre) ciudad_t_nombre, departamento_id_t "+	
        	"from	tmp__2 " +
        	"where 	upper(ciudad_t_nombre) not in ( select  upper(ciudad_nombre)  from ciudad ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.ciudad_id as ciudad_id_t, a.* "+
        	"into	tmp_con_dpts_lcldds_2 "+
        	"from 	tmp_con_dpts_lcldds a "+
        	"left 	outer join ciudad b on a.departamento_id_t = b.departamento_id and upper(trim(ltrim(a.ciudad_t))) = upper(trim(ltrim(b.ciudad_nombre))) "+
        	"ORDER BY 2;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_3;";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_2 where ciudad_id_t is null; ";
			int count3 = countQuery(connPostgres, query);
                        
            if(count3 > 0){
                  
	            query = "select ciudad_id, ciudad_nombre "+
				"into tmp_ciudad "+
				"from ciudad " +
				"where ciudad_nombre IN ( select ciudad_t from tmp_con_dpts where tarjeta in(	select tarjeta 	from tmp_con_dpts_lcldds_2 where ciudad_id_t is null)  );";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	b.ciudad_id as ciudad_id_t, a.mult_codigo,ciudad_id_c, a.departamento_id_c,a.departamento_id_t,a.cod_cliente,a.cuenta,a.nombre,a.tarjeta,a.producto,a.antiguedad_anios,a.deuda_cor,a.deuda_dol,a.total_deuda_dol,a.empresa_ba,a.ubicacion_ba,a.cedula,a.direccion_c, a.barrio_c,a.departamento_c,a.ciudad_c,a.direccion_t,a.departamento_t,a.ciudad_t,a.telefono_c,a.telefono_t,a.telefono_o,a.cod_fiador,a.nombre_fiador,a.telefono_fiador, fecha_asignado "+
			    "into	tmp_con_dpts_lcldds "+
				"from 	tmp_con_dpts_lcldds_2 a "+
				"left 	outer join tmp_ciudad b on a.ciudad_t = b.ciudad_nombre "+
				"where a.ciudad_id_t is null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	a.* into tmp_con_dpts_lcldds_3 from tmp_con_dpts_lcldds_2 a where a.ciudad_id_t is not null;";
	            ejecutarQuery(connPostgres, query);
	            
	            
	            query = "drop table tmp_con_dpts_lcldds_2;";
	            ejecutarQuery(connPostgres, query);
					
	            query = "select	ciudad_id_c, ciudad_id_t,mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado "+
				"into	tmp_con_dpts_lcldds_2 "+
				"from 	tmp_con_dpts_lcldds "+
				"union "+
				"select	ciudad_id_c, ciudad_id_t, mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado  "+
				"from 	tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query); 
	            
	            query = "select distinct on (tarjeta) tarjeta,  mult_codigo, cuenta, nombre, cod_cliente, producto, antiguedad_anios, deuda_cor, deuda_dol, total_deuda_dol, "+
	            "empresa_ba, ubicacion_ba, cedula, direccion_c, barrio_c, departamento_id_c, ciudad_id_c, direccion_t, departamento_id_t, ciudad_id_t, telefono_c, "+
	            "telefono_t, telefono_o, cod_fiador, nombre_fiador, telefono_fiador, fecha_asignado "+
	            "into tmp_con_dpts_lcldds from tmp_con_dpts_lcldds_2";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_ciudad;";
	            ejecutarQuery(connPostgres, query);
            
            }
            
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select ciudad_id_c, barrio_c as barrio_c_nombre, tarjeta	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts_lcldds_2 " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.tarjeta) a.tarjeta, a.ciudad_id_c, a.barrio_c_nombre " +
            "into tmp__2 "+ 
            "from tmp__ a order by 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into b_barrio (barrio_nombre, ciudad_id) ( 	"+
    		"select 	distinct on (barrio_c_nombre) barrio_c_nombre, ciudad_id_c  "+	
    		"from	tmp__2 "+
    		"where 	upper(barrio_c_nombre) not in ( select  upper(barrio_nombre)  from b_barrio ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_3;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.barrio_id as barrio_id_c, a.* "+
        	"into	tmp_con_dpts_lcldds_3 "+
        	"from 	tmp_con_dpts_lcldds_2 a "+
        	"left 	outer join b_barrio b on a.ciudad_id_c = b.ciudad_id and upper(trim(ltrim(a.barrio_c))) = upper(trim(ltrim(b.barrio_nombre))) "+
        	"ORDER BY 2;"; 
            ejecutarQuery(connPostgres, query);
            
			query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (tarjeta) tarjeta, mult_codigo,cuenta, nombre, cod_cliente, producto, antiguedad_anios, deuda_cor, deuda_dol, "+
            "total_deuda_dol,empresa_ba, ubicacion_ba, cedula, direccion_c, barrio_c, barrio_id_c, departamento_id_c, ciudad_id_c, direccion_t, departamento_id_t, "+
            "ciudad_id_t, telefono_c, telefono_t, telefono_o, cod_fiador, nombre_fiador, telefono_fiador, fecha_asignado  "+
            "into tmp_con_dpts_lcldds from tmp_con_dpts_lcldds_3";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_3 where barrio_id_c is null; ";
			int count4 = countQuery(connPostgres, query);
			
			if(count4 > 0){
			  
			    query = "drop table tmp_barrio";
			    ejecutarQuery(connPostgres, query);
                
	            query = "select barrio_id, barrio_nombre "+
				"into tmp_barrio "+
				"from b_barrio " +
				"where barrio_nombre IN ( select barrio_c from tmp_con_dpts where tarjeta in(	select tarjeta 	from tmp_con_dpts_lcldds_3 where barrio_id_c is null)  );";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	b.barrio_id as barrio_id_c, a.mult_codigo,ciudad_id_c, ciudad_id_t, a.departamento_id_c,a.departamento_id_t,a.cod_cliente,a.cuenta,a.nombre,a.tarjeta,a.producto,a.antiguedad_anios,a.deuda_cor,a.deuda_dol,a.total_deuda_dol,a.empresa_ba,a.ubicacion_ba,a.cedula,a.direccion_c, a.barrio_c,a.departamento_c,a.ciudad_c,a.direccion_t,a.departamento_t,a.ciudad_t,a.telefono_c,a.telefono_t,a.telefono_o,a.cod_fiador,a.nombre_fiador,a.telefono_fiador, fecha_asignado "+
			    "into	tmp_con_dpts_lcldds_4 "+
				"from 	tmp_con_dpts_lcldds_3 a "+
				"left 	outer join tmp_barrio b on a.barrio_c = b.barrio_nombre "+
				"where a.barrio_id_c is null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	a.* into tmp_con_dpts_lcldds_5 from tmp_con_dpts_lcldds_3 a where a.barrio_id_c is not null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_2;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_3;";
	            ejecutarQuery(connPostgres, query);
					
	            query = "select	barrio_id_c, ciudad_id_c, ciudad_id_t,mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado "+
				"into	tmp_con_dpts_lcldds "+
				"from 	tmp_con_dpts_lcldds_4 "+
				"union "+
				"select	barrio_id_c, ciudad_id_c, ciudad_id_t, mult_codigo,departamento_id_c,departamento_id_t,cod_cliente,cuenta,nombre,tarjeta,producto,antiguedad_anios,deuda_cor,deuda_dol,total_deuda_dol,empresa_ba,ubicacion_ba,cedula,direccion_c, barrio_c,departamento_c,ciudad_c,direccion_t,departamento_t,ciudad_t,telefono_c,telefono_t,telefono_o,cod_fiador,nombre_fiador,telefono_fiador, fecha_asignado  "+
				"from 	tmp_con_dpts_lcldds_5;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_4;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_con_dpts_lcldds_5;";
	            ejecutarQuery(connPostgres, query); 
	            
	            query = "select distinct on (tarjeta) tarjeta,  mult_codigo, cuenta, nombre, cod_cliente, producto, antiguedad_anios, deuda_cor, deuda_dol, total_deuda_dol, "+
	            "empresa_ba, ubicacion_ba, cedula, direccion_c, barrio_c, barrio_id_c, departamento_id_c, ciudad_id_c, direccion_t, departamento_id_t, ciudad_id_t, telefono_c, "+
	            "telefono_t, telefono_o, cod_fiador, nombre_fiador, telefono_fiador, fecha_asignado "+
	            "into tmp_con_dpts_lcldds_2 from tmp_con_dpts_lcldds";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "drop table tmp_barrio;";
	            ejecutarQuery(connPostgres, query);
            
            }
            
           //********************************************************************
           query = "insert into cartera_banpro ( "+
			"cod_cliente, cuenta, nombre, tarjeta, producto, antiguedad_anios, deuda_cor, deuda_dol, total_deuda_dol, empresa_ba, ubicacion_ba, cedula,"+
			 "direccion_c, barrio_c, departamento_c, ciudad_c, direccion_t, departamento_t, ciudad_t, telefono_c, telefono_t, telefono_o, cod_fiador, "+
			 "nombre_fiador, telefono_fiador, mult_codigo, fecha_asignado )	"+
			"select a.cod_cliente, a.cuenta, a.nombre, a.tarjeta, a.producto, a.antiguedad_anios, a.deuda_cor, a.deuda_dol, a.total_deuda_dol, "+
			"a.empresa_ba, a.ubicacion_ba, a.cedula, a.direccion_c, a.barrio_id_c, a.departamento_id_c, a.ciudad_id_c, a.direccion_t, a.departamento_id_t, "+
			"a.ciudad_id_t, a.telefono_c, a.telefono_t, a.telefono_o, a.cod_fiador, a.nombre_fiador, a.telefono_fiador, a.mult_codigo, a.fecha_asignado "+
			"from 	tmp_con_dpts_lcldds_2 a ";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_2; ";
			int cantidad = countQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_2; ";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds; ";
            ejecutarQuery(connPostgres, query);
            
           return cantidad;

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
		return 0;
	}
	
	public int contarExcluidosBanpro() throws Exception {
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

			query = "select count(tarjeta) from tmp_excluidos;";
			int cantidad = countQuery(connPostgres, query);
			
            return cantidad;

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
		return 0;
	}
	
	
}
