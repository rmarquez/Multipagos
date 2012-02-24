package com.metropolitana.multipagos.forms.xlstopostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilXls2Postgres {
	
	public int sqlProcess() throws Exception {
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
			String username = "postgres";
			String password = "";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);

			query = "select count (factura_interna) from tmp_cartera where factura_interna in (select factura_interna from cartera_x_departamento); ";
			int count = countQuery(connPostgres, query);
			
			query = "select * into tmp_excluidos from tmp_cartera where tmp_id in (select tmp_id from tmp_cartera where factura_interna in (select factura_interna from cartera_x_departamento));";
            ejecutarQuery(connPostgres, query);
                        
            if(count > 0){
                query = "delete from tmp_cartera  where tmp_id in (select tmp_id from tmp_cartera where factura_interna in (select factura_interna from cartera_x_departamento));";
                ejecutarQuery(connPostgres, query);   
            }
            
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
            
            query = "insert into departamento (departamento_nombre) " +
            "(select distinct departamento as departamento_nombre from tmp_cartera "+
            "where upper(departamento) not in ( select upper(departamento_nombre) from departamento ) order by 1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.departamento_id, a.* "+
        	"into	tmp_con_dpts " +
        	"from 	tmp_cartera a " +
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
        	"left 	outer join localidad b on a.departamento_id = b.departamento_id and upper(rtrim(ltrim(a.localidad))) = upper(rtrim(ltrim(b.localidad_nombre))) "+
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
	            
	            query = "select	b.localidad_id, a.tmp_id, a.departamento_id, a.contrato, a.suscriptor, a.nit, a.direccion, a.barrio, a.factura_interna, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado, a.departamento, a.localidad, a.cupon, a.telefono, a.descuento, a.servicio, a.empleador, a.direccion_empleador, a.f_asignado, a.cuenta, a.concepto_diferido, a.es_diferido "+
				"into	tmp_con_dpts_lcldds_2 "+
				"from 	tmp_con_dpts_lcldds a "+
				"inner join tmp_localidad b on a.departamento = b.localidad_nombre "+
				"where a.localidad_id is null;";
	            ejecutarQuery(connPostgres, query);
	            
	            query = "select	a.* into	tmp_con_dpts_lcldds_3 from 	tmp_con_dpts_lcldds a where a.localidad_id is not null;";
	            ejecutarQuery(connPostgres, query);
	            
	            
	            query = "drop table tmp_con_dpts_lcldds;";
	            ejecutarQuery(connPostgres, query);
					
	            query = "select	localidad_id, tmp_id, departamento_id, contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asignado, cuenta, concepto_diferido, es_diferido "+
				"into	tmp_con_dpts_lcldds "+
				"from 	tmp_con_dpts_lcldds_2 "+
				"union "+
				"select	localidad_id, tmp_id, departamento_id, contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asignado, cuenta, concepto_diferido, es_diferido "+
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
        	"left 	outer join barrio b on  a.localidad_id = b.localidad_id and upper(rtrim(ltrim(a.barrio))) = upper(rtrim(ltrim(b.barrio_nombre))) "+
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
		
		            query = "select	b.barrio_id,a.localidad_id,	a.departamento_id, a.tmp_id, a.contrato,a.suscriptor,a.nit,a.direccion,a.barrio,a.factura_interna,	a.numero_fiscal, "+ 
		            "a.anio,a.mes,a.saldo,a.estado,a.departamento,a.localidad,a.cupon,a.telefono,a.descuento,a.servicio,a.empleador,a.direccion_empleador, "+ 
		            "a.f_asignado, a.cuenta,	a.concepto_diferido,a.es_diferido "+ 
		            "into	tmp_con_dpts_lcldds_barrios_n "+ 
		            "from 	tmp_con_dpts_lcldds_barrios a "+
		            "inner join tmp_barrio b on a.barrio = b.barrio_nombre "+
		            "where a.barrio_id is null;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "select	a.* into tmp_con_dpts_lcldds_barrios_n2 from tmp_con_dpts_lcldds_barrios a where a.barrio_id is not null;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios;";
		            ejecutarQuery(connPostgres, query);
		
		            query = "select	barrio_id, localidad_id, departamento_id, tmp_id, contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asignado, cuenta, concepto_diferido, es_diferido "+ 
		            "into	tmp_con_dpts_lcldds_barrios "+ 
		            "from 	tmp_con_dpts_lcldds_barrios_n "+ 
		            "union "+ 
		            "select	barrio_id, localidad_id, departamento_id, tmp_id, contrato, suscriptor, nit, direccion, barrio, factura_interna, numero_fiscal, anio, mes, saldo, estado, departamento, localidad, cupon, telefono, descuento, servicio, empleador, direccion_empleador, f_asignado, cuenta, concepto_diferido, es_diferido "+ 
		            "from 	tmp_con_dpts_lcldds_barrios_n2;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_barrio;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios_n;";
		            ejecutarQuery(connPostgres, query);
		            
		            query = "drop table tmp_con_dpts_lcldds_barrios_n2;";
		            ejecutarQuery(connPostgres, query);
            }
            
            query = "select distinct on (a.factura_interna) a.factura_interna, a.barrio_id, a.localidad_id, a.departamento_id, a.contrato, a.suscriptor, a.nit, a.direccion, a.barrio, a.tmp_id, a.numero_fiscal, a.anio, a.mes, a.saldo, a.estado, a.departamento, a.localidad, a.cupon, a.telefono, a.descuento, a.servicio, a.empleador, a.direccion_empleador, a.f_asignado, a.cuenta, a.concepto_diferido, a.es_diferido " +
            "into tmp_con_dpts_lcldds_barrios_2 "+ 
            "from tmp_con_dpts_lcldds_barrios a";
            ejecutarQuery(connPostgres, query);
            
           
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);

            query = "select * into tmp_con_dpts_lcldds_barrios from tmp_con_dpts_lcldds_barrios_2;";
            ejecutarQuery(connPostgres, query);
            
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
        	"left 	outer join servicio 	e on rtrim(ltrim(a.servicio)) = rtrim(ltrim(e.servicio_nombre)) "+
        	"left 	outer join estado_corte 	f on rtrim(ltrim(a.estado)) = rtrim(ltrim(f.estado_nombre)) "+
        	"order 	by a.factura_interna;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into cartera_x_departamento ( "+
					"contrato, suscriptor, nit, direccion, barrio_id, factura_interna, numero_fiscal, anio, mes, saldo, estado_id, departamento_id, "+
					"localidad_id, cupon, telefono, descuento, servicio_id, empleador, direccion_empleador, fecha_ingreso, cuenta, concepto_diferido, "+
					"es_diferido )"+	
			"select  a.contrato,	a.suscriptor, a.nit, a.direccion, b.barrio_id, a.factura_interna, a.numero_fiscal, a.anio, a.mes, a.saldo, b.estado_id, "+
					"b.departamento_id, b.localidad_id, a.cupon, a.telefono, 0 as descuento, b.servicio_id, a.empleador, a.direccion_empleador, "+
					"a.f_asignado as fecha_ingreso,a.cuenta, a.concepto_diferido, a.es_diferido "+
			"from 	tmp_con_dpts_lcldds_barrios a "+
			"inner join tmp_p1 b on a.factura_interna = b.factura_interna;";
            ejecutarQuery(connPostgres, query);
            
            query = "select count (*) from tmp_con_dpts_lcldds_barrios; ";
			int cantidad = countQuery(connPostgres, query);
            
            query = "drop table tmp_p1;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios_2;";
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
			String username = "postgres";
			String password = "";
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
			
            if(cantidad > 0){
            	query = "drop table tmp_excluidos;";
                ejecutarQuery(connPostgres, query);   
            }
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
	
	private static int countQuery(Connection connPostgres, String query) throws Exception {
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

}
