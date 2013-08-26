package com.metropolitana.multipagos.forms.xlstopostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilXls2PostgresIbw {
	
	public void procesoSql() throws Exception {
		prepararDmunicipio();
		prepararBarrios();
		prepararTecnologia();
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
			           
            query = "insert into cartera_ibw ( "+
					"nombre, apellido, departamento_id, municipio_id, barrio_id, direccion, telefono_c, celular, telefono_t, cod_cliente, "+
					"factura_ibw, serie, fecha_factura, fecha_vence, saldo_dol, total_saldo_dol, tecnologia_id, f_asignado )"+	
			"select  a.nombre, a.apellido, a.i_departamento_id, a.municipio_id, a.barrio_id, a.direccion, a.telefono_c, a.celular, a.telefono_t, a.cod_cliente, "+
					"a.factura_ibw, a.serie, a.fecha_factura, a.fecha_vence, a.saldo_dol, a.total_saldo_dol, a.tecnologia_id, a.f_asignado  "+
			"from 	tmp_con_dpts_lcldds_barrios_3 a; ";
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
	
	
	
	private void prepararDmunicipio() throws Exception {
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
			
			query = "update tmp_ibw set departamento = upper(trim(departamento));";
            ejecutarQuery(connPostgres, query);
            
            query = "select * into tmp_excluidos from tmp_ibw where departamento='';";
            ejecutarQuery(connPostgres, query);
            
            query = "INSERT INTO tmp_excluidos(tmp_id, nombre, apellido, departamento, municipio, barrio, direccion, telefono_c, celular, telefono_t, " +
            		"cod_cliente, factura_ibw, serie, fecha_factura, fecha_vence, saldo_dol, total_saldo_dol, tecnologia, f_asignado)  " +
            		"SELECT tmp_id, nombre, apellido, departamento, municipio, barrio, direccion, telefono_c, celular, telefono_t, cod_cliente, factura_ibw, " +
            		"serie, fecha_factura, fecha_vence, saldo_dol, total_saldo_dol, tecnologia, f_asignado  " +
            		" FROM tmp_ibw where factura_ibw in (select factura_ibw from cartera_ibw);";
            ejecutarQuery(connPostgres, query);
            
            query = "delete from tmp_ibw where departamento='';";
            ejecutarQuery(connPostgres, query);
            
            query = "delete from tmp_ibw where factura_ibw in (select factura_ibw from cartera_ibw);";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento = 'MANAGUA' where departamento like '%MANAGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento = 'ESTELI' where departamento like '%ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento='LEON' where departamento like 'LEÓN';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento = 'LEON' where departamento like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento = 'MATAGALPA' where departamento like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_ibw set departamento = 'JINOTEGA' where departamento like '%JINOTEGA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into i_departamento (i_departamento_nombre) " +
            "(select distinct departamento as departamento_nombre from tmp_ibw "+
            "where not departamento ='' and upper(departamento) not in ( select upper(i_departamento_nombre) from i_departamento) order by 1);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.i_departamento_id, a.* "+
        	"into tmp_con_dpts " +
        	"from tmp_ibw a " +
        	"left outer join i_departamento b on upper(trim(ltrim(a.departamento))) = upper(trim(ltrim(b.i_departamento_nombre))) " +
        	"ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = upper(trim(municipio));";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MANAGUA' where municipio like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MANAGUA' where municipio like '.MANA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MANAGUA' where municipio like 'MAA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MANAGUA' where municipio like 'MGUA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'CIUDAD SANDINO' where municipio like '%SANDINO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'CIUDAD DARIO' where municipio like '%DARIO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'CIUDAD DARIO' where municipio like 'CIUDAD DARÍO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'ESTELI' where municipio like 'ESTEL%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'LEON' where municipio like '%LEÓN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'LEON' where municipio like '%LE�N%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'LEON' where municipio like 'LÉON';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'TOTOGALPA' where municipio like '%TOTOGALPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'EL TUMA - LA DALIA' where municipio like '%TUMA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MUY MUY' where municipio like 'MUYMUY';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SEBACO' where municipio like '%SEBACO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SEBACO' where municipio like 'SÉBACO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN RAMON' where municipio like '%SAN RAMON%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'RANCHO GRANDE' where municipio like '%RANCHO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'RENE BARRANTES' where municipio like '%RENE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN FERNANDO' where municipio like '%SAN FERNANDO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN NICOLAS' where municipio like '%SAN NICOLAS%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN ISIDRO' where municipio like '%SAN ISIDRO%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN JOSE DE CUSMAPA' where municipio like 'SAN JOSÉ DE CUSMAPA';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN JUAN DE RIO COCO' where municipio like 'SAN JUAN DEL RIO COCO';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'JALAPA' where municipio like '%JALAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MATAGALPA' where municipio like '%MATA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'LA TRINIDAD' where municipio like '%TRIN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MULUKUKU' where municipio like '%MULU%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'SAN RAFAEL DEL SUR' where municipio like '%RAFAEL DEL SUR%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'MANAGUA' where municipio like 'MAN%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'NAGAROTE' where municipio like '%NAGAROTE%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'NINDIRI' where municipio like '%NINDIRI%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'TIPITAPA' where municipio like '%TIPITAPA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "update tmp_con_dpts set municipio = 'LA TRINIDAD' where municipio like '%LA TIRNIDAD%';";
            ejecutarQuery(connPostgres, query);	
            
            query = "update tmp_con_dpts set municipio = 'JINOTEGA' where municipio like '%JINOTEGA%';";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
                        
            query = "select i_departamento_id, municipio as municipio_nombre, factura_ibw	" +
        	"into	tmp__ " +
        	"from 	tmp_con_dpts " + 
        	"order 	by 2;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_ibw) a.factura_ibw, a.i_departamento_id, a.municipio_nombre " +
            "into tmp__2 "+ 
            "from tmp__ a order by 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into municipio (departamento_id, municipio_nombre) ( " +	
        	"select distinct i_departamento_id, municipio_nombre "+	
        	"from tmp__2 " +
        	"where upper(municipio_nombre) not in (select upper(municipio_nombre) from municipio ));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp__2;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.municipio_id, a.* "+
        	"into tmp_con_dpts_lcldds "+
        	"from tmp_con_dpts a "+
        	"left outer join municipio b on upper(trim(ltrim(a.municipio))) = upper(trim(ltrim(b.municipio_nombre))) "+
        	"ORDER BY 1;"; 
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_ibw) a.factura_ibw, a.municipio_id, a.i_departamento_id, a.tmp_id, a.nombre, "+
            "a.apellido, a.departamento, a.municipio, a.barrio, a.direccion, a.telefono_c, a.celular, a.telefono_t, a.cod_cliente, a.serie, "+
            "a.fecha_factura, a.fecha_vence, a.saldo_dol, a.total_saldo_dol, a.tecnologia, a.f_asignado "+
            "into tmp_con_dpts_lcldds_2 "+ 
            "from tmp_con_dpts_lcldds a "+
            "where a.municipio_id is not null;";
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
            
            query = "update tmp_con_dpts_lcldds set barrio = upper(trim(barrio));";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct barrio, municipio_id, i_departamento_id, count(*) as cant into tmp_ from tmp_con_dpts_lcldds where tmp_id not in "+ 
        	"(select tmp_id from tmp_con_dpts_lcldds a, i_barrio b where a.municipio_id= b.municipio_id and a.barrio = b.barrio_nombre) and municipio_id is not null "+
        	"group by barrio, municipio_id, i_departamento_id order by barrio, municipio_id, i_departamento_id;";
            ejecutarQuery(connPostgres, query);
            
            
            query = "insert into i_barrio (municipio_id, barrio_nombre) (select municipio_id, barrio as barrio_nombre from tmp_ order by municipio_id, barrio);";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_;";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_con_dpts_lcldds_barrios;";
            ejecutarQuery(connPostgres, query);
            
            query = "select	b.barrio_id, a.* into tmp_con_dpts_lcldds_barrios from tmp_con_dpts_lcldds a "+
            "left outer join i_barrio b on upper(rtrim(ltrim(a.barrio)))=upper(rtrim(ltrim(b.barrio_nombre))) ORDER BY 1;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_ibw) a.factura_ibw, a.barrio_id, a.municipio_id, a.i_departamento_id, a.tmp_id, a.nombre, "+
            "a.apellido, a.departamento, a.municipio, a.barrio, a.direccion, a.telefono_c, a.celular, a.telefono_t, a.cod_cliente, a.serie, "+
            "a.fecha_factura, a.fecha_vence, a.saldo_dol, a.total_saldo_dol, a.tecnologia, a.f_asignado into tmp_con_dpts_lcldds_barrios_2 "+ 
            "from tmp_con_dpts_lcldds_barrios a where  barrio_id is not null;";
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
	
	private void prepararTecnologia() throws Exception {
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
			
            query = "update tmp_con_dpts_lcldds_barrios set tecnologia = upper(trim(tecnologia));";
            ejecutarQuery(connPostgres, query);
            
            query = "insert into  tecnologia(tecnologia_nombre ) ( "+
    		"select distinct tecnologia as tecnologia_nombre "+
    		"from tmp_con_dpts_lcldds_barrios "+
    		"where tecnologia not in ( select tecnologia_nombre from tecnologia ) "+
    		"order by  1 );";
            ejecutarQuery(connPostgres, query);
            
            query = "drop table tmp_p1;";
            ejecutarQuery(connPostgres, query);
            
           query = "select	a.factura_ibw, a.i_departamento_id, a.municipio_id, a.barrio_id, e.tecnologia_id "+
        	"into 	tmp_p1 "+
        	"from 	tmp_con_dpts_lcldds_barrios a "+ 
        	"left 	outer join tecnologia e on trim(ltrim(a.tecnologia)) = trim(ltrim(e.tecnologia_nombre)) "+
        	"order 	by a.factura_ibw;";
            ejecutarQuery(connPostgres, query);
            
            query = "select distinct on (a.factura_ibw) "+ 
    	    "a.factura_ibw, a.barrio_id, a.municipio_id, a.i_departamento_id, a.nombre, a.apellido, a.departamento, a.municipio, a.barrio, a.direccion, "+
            "a.telefono_c, a.celular, a.telefono_t, a.cod_cliente, a.serie, a.fecha_factura, a.fecha_vence, a.saldo_dol, a.total_saldo_dol, "+
    	    "a.tecnologia, a.f_asignado, b.tecnologia_id "+
            "into tmp_con_dpts_lcldds_barrios_3 "+ 
            "from tmp_con_dpts_lcldds_barrios a "+
            "inner join tmp_p1 b on a.factura_ibw = b.factura_ibw;";
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
	
	
	
	public int contarExcluidosIbw() throws Exception {
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

			query = "select count(factura_ibw) from tmp_excluidos;";
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
}
