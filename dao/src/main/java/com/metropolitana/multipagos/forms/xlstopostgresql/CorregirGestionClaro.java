package com.metropolitana.multipagos.forms.xlstopostgresql;

import com.metropolitana.multipagos.AsignacionClaro;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.TmpCartera;
import com.metropolitana.multipagos.forms.cartera.CarteraXDepartamentoHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

public class CorregirGestionClaro {
	
	private CarteraXDepartamentoHandler carteraHandler;
	
	public int corregirGestio(final Integer usrId) throws Exception{
	PersistenceBroker broker = null;	
            try {
            	int cantidad = 0;
                broker = PersistenceBrokerFactory.defaultPersistenceBroker();
                for (Iterator iter = broker.getReportQueryIteratorByQuery(queryFacturaInterna()); iter.hasNext();) {
                        Object[] temp = (Object[]) iter.next();
                        nuevosValores((String)temp[0], usrId);
                        cantidad=cantidad+1;
                }
                return cantidad;
            } catch (Exception e) {
                    throw e;
            } finally {
                    if (broker != null && !broker.isClosed()) {
                            broker.close();
                    }
            }
	}
	
	private static ReportQueryByCriteria queryFacturaInterna() {
		Criteria criterio = new Criteria();
		ReportQueryByCriteria query = new ReportQueryByCriteria(TmpCartera.class, criterio);
		query.setAttributes(new String[] { "facturaInterna" });
		query.addGroupBy(new String[] { "facturaInterna"});
		return query;
	}
	
	private static TmpCartera tmpXFactura(final String factura) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return tmpXFactura(factura, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	private static TmpCartera tmpXFactura(final String factura, final PersistenceBroker broker) {
		TmpCartera criterio = new TmpCartera();
		criterio.setFacturaInterna(factura);
		Query query = new QueryByCriteria(criterio);
		return (TmpCartera) broker.getObjectByQuery(query);
	}
	
	private static CarteraXDepartamento carteraXFactura(final String factura) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return carteraXFactura(factura, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	
	private static CarteraXDepartamento carteraXFactura(final String factura, final PersistenceBroker broker) {
		CarteraXDepartamento criterio = new CarteraXDepartamento();
		criterio.setFacturaInterna(factura);
		Query query = new QueryByCriteria(criterio);
		return (CarteraXDepartamento) broker.getObjectByQuery(query);
	}

	public void nuevosValores(final String factura, final Integer usrId) throws Exception {	
		carteraHandler = new CarteraXDepartamentoHandler();
        TmpCartera temp = tmpXFactura(factura);
        CarteraXDepartamento cartera = carteraXFactura(factura);
        if (cartera != null){
			cartera.setContrato(temp.getContrato());
			cartera.setSuscriptor(temp.getSuscriptor());
			cartera.setNit(temp.getNit());
			cartera.setDireccion(temp.getDireccion());
			cartera.setNumeroFiscal(temp.getNumeroFiscal());
			cartera.setAnio(temp.getAnio());
			cartera.setMes(temp.getMes());
			cartera.setSaldo(temp.getSaldo());
			cartera.setCupon(temp.getCupon());
			cartera.setTelefono(temp.getTelefono());
			cartera.setPagado(cartera.getPagado());
			cartera.setFechaPago(cartera.getFechaPago());
			cartera.setDepartamentoId(cartera.getDepartamentoId());
			cartera.setLocalidadId(cartera.getLocalidadId());
			cartera.setBarrioId(cartera.getBarrioId());
			cartera.setServicioId(cartera.getServicioId());
			cartera.setEstadoId(cartera.getEstadoId());
			cartera.setFechaIngreso(cartera.getFechaIngreso());
			carteraHandler.update(cartera, usrId);
		}
	
		
	}
}
