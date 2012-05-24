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

public class InformeVisitasDiarias {
	
	public static List getVisitasDiarias(Date fechaIni)
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
			
			selectQuery = " select  h.contrato, h.suscriptor, h.factura_interna, h.saldo, g.d_visita_rst, e.simbolo_nombre "+
            ",a.fecha_visita,f.c_visita_rst, f.c_causa_rst, j.departamento_nombre,k.localidad_nombre, a.aviso_cobro,upper(c.usr_full_name) "+
            ",a.colector_id, (upper(d.primer_nombre) || ' '  || upper(d.primer_apellido)),i.servicio_nombre, a.hora_registro "+
            "from detalle_visitas  a "+
		    "left outer join visitas   b on a.visita_id       = b.visita_id "+
		    "left outer join auth_user c on b.usr_id          = c.usr_id "+
		    "left outer join colector  d on a.colector_id     = d.colector_id "+
		    "left outer join simbolo   e on a.simbolo_id      = e.simbolo_id "+
		    "left outer join cartera_x_departamento  h on a.cartera_id      = h.cartera_id "+
		    "left outer join servicio  i on a.servicio_id     = i.servicio_id "+
		    "left outer join departamento j on h.departamento_id = j.departamento_id "+
		    "left outer join localidad    k on a.localidad_id    = k.localidad_id "+
		    "left outer join cat_causas_rst  f on e.simbolo_numero  = f.c_causa_rst "+
		    "left outer join cat_visitas_rst  g on f.c_visita_rst    = g.c_visita_rst  and g.c_empresa = 1 "+
		    "where a.fecha_visita = ? "+
		    "order by a.fecha_visita,  h.contrato;";
			
				
            psOrigen = connPostgres.prepareStatement(selectQuery);
            java.sql.Date fecha = java.sql.Date.valueOf(getFechaSQL(fechaIni));
            psOrigen.setDate(1,fecha);
           
				
			rs = psOrigen.executeQuery();
			
                   
            while(rs.next()){
                
			Object[] fila = { rs.getObject(1), rs.getObject(2),
					rs.getObject(3), rs.getObject(4), rs.getObject(5),
					rs.getObject(6), rs.getObject(7), rs.getObject(8),
					rs.getObject(9), rs.getObject(10), rs.getObject(11),
					rs.getObject(12), rs.getObject(13), rs.getObject(14), 
					rs.getObject(15), rs.getObject(16), rs.getObject(17)};              

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
