package com.metropolitana.multipagos.forms.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformeVisitasIbw {
	
	public static List getVisitasDiarias(Date fechaIni, Date fechaFin, Integer departamentoId, Integer colectorId)
			throws Exception {
            
		Connection connPostgres = null;
		String selectQuery;
                PreparedStatement psOrigen = null;	
		ResultSet rs = null;
		try {
			
			List<Object[]> lista = new ArrayList<Object[]>();
			
            try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No se encuentra el Driver: " + e.getMessage());
			}
			String username = "dev";
			String password = "multipagos";
			String url = "jdbc:postgresql://localhost:5432/multipagos";
			connPostgres = DriverManager.getConnection(url, username, password);
			
			if(departamentoId != null && colectorId == null) {
				
			
			selectQuery = " select  h.cod_cliente, (upper(h.nombre) || ' '  || upper(h.apellido)), count(h.cod_cliente), h.saldo_dol, e.simbolo_numero, e.simbolo_nombre "+
					",a.fecha_gestion, j.i_departamento_nombre,k.municipio_nombre, a.aviso_cobro,upper(c.usr_full_name) "+
					",a.colector_id, (upper(d.primer_nombre) || ' '  || upper(d.primer_apellido)),i.tecnologia_nombre, a.hora_registro, a.observaciones "+
					"from detalle_g_ibw  a "+
					"left outer join gestion_ibw   b on a.gestion_id       = b.gestion_id "+
					"left outer join auth_user c on b.usr_id          = c.usr_id "+
					"left outer join colector  d on a.colector_id     = d.colector_id "+
					"left outer join simbolo_ibw   e on a.simbolo_id      = e.simbolo_id "+
					"left outer join cartera_ibw  h on a.cartera_id      = h.cartera_id "+
					"left outer join tecnologia  i on h.tecnologia_id     = i.tecnologia_id "+
					"left outer join i_departamento j on h.departamento_id = j.i_departamento_id "+
					"left outer join municipio    k on a.municipio_id    = k.municipio_id "+
					"where a.fecha_gestion >= ? and a.fecha_gestion <= ? and h.departamento_id = ? "+
					"group by h.cod_cliente,h.nombre,h.apellido, h.saldo_dol, e.simbolo_nombre, e.simbolo_numero,a.fecha_gestion,  "+
					"j.i_departamento_nombre, k.municipio_nombre,a.aviso_cobro,c.usr_full_name, a.colector_id, "+
					"d.primer_nombre, d.primer_apellido, i.tecnologia_nombre, a.hora_registro, a.observaciones "+
					"order by a.fecha_gestion,  h.cod_cliente;";
			
			} else if(departamentoId == null && colectorId != null) {
				selectQuery = " select  h.cod_cliente, (upper(h.nombre) || ' '  || upper(h.apellido)), count(h.cod_cliente), h.saldo_dol, e.simbolo_numero, e.simbolo_nombre "+
						",a.fecha_gestion, j.i_departamento_nombre,k.municipio_nombre, a.aviso_cobro,upper(c.usr_full_name) "+
						",a.colector_id, (upper(d.primer_nombre) || ' '  || upper(d.primer_apellido)),i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"from detalle_g_ibw  a "+
						"left outer join gestion_ibw   b on a.gestion_id       = b.gestion_id "+
						"left outer join auth_user c on b.usr_id          = c.usr_id "+
						"left outer join colector  d on a.colector_id     = d.colector_id "+
						"left outer join simbolo_ibw   e on a.simbolo_id      = e.simbolo_id "+
						"left outer join cartera_ibw  h on a.cartera_id      = h.cartera_id "+
						"left outer join tecnologia  i on h.tecnologia_id     = i.tecnologia_id "+
						"left outer join i_departamento j on h.departamento_id = j.i_departamento_id "+
						"left outer join municipio    k on a.municipio_id    = k.municipio_id "+
						"where a.fecha_gestion >= ? and a.fecha_gestion <= ? and a.colector_id = ? "+
						"group by h.cod_cliente,h.nombre,h.apellido, h.saldo_dol, e.simbolo_nombre, e.simbolo_numero,a.fecha_gestion,  "+
						"j.i_departamento_nombre, k.municipio_nombre,a.aviso_cobro,c.usr_full_name, a.colector_id, "+
						"d.primer_nombre, d.primer_apellido, i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"order by a.fecha_gestion,  h.cod_cliente;";
			} else if(departamentoId == null && colectorId == null) {
				selectQuery = " select  h.cod_cliente, (upper(h.nombre) || ' '  || upper(h.apellido)), count(h.cod_cliente), h.saldo_dol, e.simbolo_numero, e.simbolo_nombre "+
						",a.fecha_gestion, j.i_departamento_nombre,k.municipio_nombre, a.aviso_cobro,upper(c.usr_full_name) "+
						",a.colector_id, (upper(d.primer_nombre) || ' '  || upper(d.primer_apellido)),i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"from detalle_g_ibw  a "+
						"left outer join gestion_ibw   b on a.gestion_id       = b.gestion_id "+
						"left outer join auth_user c on b.usr_id          = c.usr_id "+
						"left outer join colector  d on a.colector_id     = d.colector_id "+
						"left outer join simbolo_ibw   e on a.simbolo_id      = e.simbolo_id "+
						"left outer join cartera_ibw  h on a.cartera_id      = h.cartera_id "+
						"left outer join tecnologia  i on h.tecnologia_id     = i.tecnologia_id "+
						"left outer join i_departamento j on h.departamento_id = j.i_departamento_id "+
						"left outer join municipio    k on a.municipio_id    = k.municipio_id "+
						"where a.fecha_gestion >= ? and a.fecha_gestion <= ? "+
						"group by h.cod_cliente,h.nombre,h.apellido, h.saldo_dol, e.simbolo_nombre, e.simbolo_numero,a.fecha_gestion,  "+
						"j.i_departamento_nombre, k.municipio_nombre,a.aviso_cobro,c.usr_full_name, a.colector_id, "+
						"d.primer_nombre, d.primer_apellido, i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"order by a.fecha_gestion,  h.cod_cliente;";
			} else {
				selectQuery = " select  h.cod_cliente, (upper(h.nombre) || ' '  || upper(h.apellido)), count(h.cod_cliente), h.saldo_dol, e.simbolo_numero, e.simbolo_nombre "+
						",a.fecha_gestion, j.i_departamento_nombre,k.municipio_nombre, a.aviso_cobro,upper(c.usr_full_name) "+
						",a.colector_id, (upper(d.primer_nombre) || ' '  || upper(d.primer_apellido)),i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"from detalle_g_ibw  a "+
						"left outer join gestion_ibw   b on a.gestion_id       = b.gestion_id "+
						"left outer join auth_user c on b.usr_id          = c.usr_id "+
						"left outer join colector  d on a.colector_id     = d.colector_id "+
						"left outer join simbolo_ibw   e on a.simbolo_id      = e.simbolo_id "+
						"left outer join cartera_ibw  h on a.cartera_id      = h.cartera_id "+
						"left outer join tecnologia  i on h.tecnologia_id     = i.tecnologia_id "+
						"left outer join i_departamento j on h.departamento_id = j.i_departamento_id "+
						"left outer join municipio    k on a.municipio_id    = k.municipio_id "+
						"where a.fecha_gestion >= ? and a.fecha_gestion <= ? and h.departamento_id = ? and a.colector_id = ? "+
						"group by h.cod_cliente,h.nombre,h.apellido, h.saldo_dol, e.simbolo_nombre, e.simbolo_numero,a.fecha_gestion,  "+
						"j.i_departamento_nombre, k.municipio_nombre,a.aviso_cobro,c.usr_full_name, a.colector_id, "+
						"d.primer_nombre, d.primer_apellido, i.tecnologia_nombre, a.hora_registro, a.observaciones "+
						"order by a.fecha_gestion,  h.cod_cliente;";
			}
				
			psOrigen = connPostgres.prepareStatement(selectQuery);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
            java.sql.Date fechaF = java.sql.Date.valueOf(getFechaSQL(fechaFin));
            psOrigen.setDate(2,fechaF);
            if(departamentoId != null && colectorId == null) {
				psOrigen.setInt(3, departamentoId);
				rs = psOrigen.executeQuery();
			
			} else if(departamentoId == null && colectorId != null) {
				psOrigen.setInt(3, colectorId);
				rs = psOrigen.executeQuery();
				
			} else if(departamentoId == null && colectorId == null) {
				rs = psOrigen.executeQuery();
				
			} else {
				psOrigen.setInt(3, departamentoId);
				psOrigen.setInt(4, colectorId);
				rs = psOrigen.executeQuery();
				
			}
			
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13), rs.getObject(14), 
					rs.getObject(15),rs.getObject(16)};              

            lista.add(fila);
                
            }
             
			return lista;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	private static String getFechaSQL(final Date asignado) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(asignado);
	}

}
