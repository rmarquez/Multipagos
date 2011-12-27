package com.metropolitana.multipagos.forms.logs;

import java.util.Collection;
import java.util.Date;


import com.metropolitana.multipagos.ArqueoPagos;
import com.metropolitana.multipagos.AuthUser;
import com.metropolitana.multipagos.Banco;
import com.metropolitana.multipagos.Barrio;
import com.metropolitana.multipagos.CantidadMonedas;
import com.metropolitana.multipagos.CarteraXDepartamento;
import com.metropolitana.multipagos.Colector;
import com.metropolitana.multipagos.Cuentas;
import com.metropolitana.multipagos.Departamento;
import com.metropolitana.multipagos.EstadoCorte;
import com.metropolitana.multipagos.Localidad;
import com.metropolitana.multipagos.Logs;
import com.metropolitana.multipagos.Pagos;
import com.metropolitana.multipagos.Servicio;
import com.metropolitana.multipagos.Simbolo;
import com.metropolitana.multipagos.TasaFija;
import com.metropolitana.multipagos.Visitas;
import com.metropolitana.multipagos.forms.auth_user.Auth_userHandler;
import com.metropolitana.multipagos.forms.logs.TipoDocumentoLogHandler;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;

public class LogsHandler {

	/**
	 * Crea un registro de Logs para del documento cualificación
	 *
	 * @param departamento
	 *            bean del documento departamento
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Departamento departamento,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(departamento.getDepartamentoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.DEPARTAMENTO, broker));
		logs.setLogsDescripcion(getDescripcion(departamento, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param departamento
	 *            bean del objeto departamento
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Departamento departamento,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Departamento  : "
				+ departamento.getDepartamentoNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento banco
	 *
	 * @param banco
	 *            bean del documento banco
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Banco banco,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(banco.getBancoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.BANCO, broker));
		logs.setLogsDescripcion(getDescripcion(banco, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param banco
	 *            bean del objeto banco
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Banco banco, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Banco  : " + banco.getBancoNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento servicio
	 *
	 * @param servicio
	 *            bean del documento servicio
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Servicio servicio,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(servicio.getServicioId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.SERVICIO, broker));
		logs.setLogsDescripcion(getDescripcion(servicio, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param servicio
	 *            bean del objeto servicio
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Servicio servicio,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Servicio  : "+ servicio.getServicioNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento simbolo
	 *
	 * @param simbolo
	 *            bean del documento simbolo
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Simbolo simbolo,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(simbolo.getSimboloId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.SIMBOLO, broker));
		logs.setLogsDescripcion(getDescripcion(simbolo, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param simbolo
	 *            bean del objeto simbolo
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Simbolo simbolo,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Simbolo  : "+ simbolo.getSimboloNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento simbolo
	 *
	 * @param tasa
	 *            bean del documento tasa fija
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(TasaFija tasaFija, Date fecha,
			Integer usrId, Integer estadoId, PersistenceBroker broker)
			throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(tasaFija.getTasaFecha().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.TASA_FIJA, broker));
		logs.setLogsDescripcion(getDescripcion(tasaFija, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 * 
	 * @param tasa
	 *            bean del objeto tasa fija
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(TasaFija tasaFija, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Tasa de Cambio Fija : "
				+ tasaFija.getTasaCambioMes() + " del Mes "
				+ tasaFija.getTasaFecha().getMonth();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento localidad
	 *
	 * @param localidad
	 *            bean del documento localidad
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Localidad localidad, Date fecha,
			Integer usrId, Integer estadoId, PersistenceBroker broker)
			throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(localidad.getLocalidadId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.LOCALIDAD, broker));
		logs.setLogsDescripcion(getDescripcion(localidad, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 * 
	 * @param localidad
	 *            bean del objeto localidad
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Localidad localidad, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Localidad : "+ localidad.getLocalidadNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento barrio
	 *
	 * @param barrio
	 *            bean del documento barrio
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Barrio barrio, Date fecha,
			Integer usrId, Integer estadoId, PersistenceBroker broker)
			throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(barrio.getLocalidadId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.BARRIO, broker));
		logs.setLogsDescripcion(getDescripcion(barrio, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 * 
	 * @param barrio
	 *            bean del objeto localidad.
	 * @param user
	 *            bean del objeto usuario.
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Barrio barrio, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Barrio : "+ barrio.getBarrioNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento estado corte
	 *
	 * @param estado
	 *            bean del documento estado corte
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(EstadoCorte estado,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(estado.getEstadoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.ESTADO_CORTE, broker));
		logs.setLogsDescripcion(getDescripcion(estado, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param colector
	 *            bean del objeto colector
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Colector colector,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Colector  : "
				+ colector.getPrimerNombre() + colector.getPrimerApellido();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento colector
	 *
	 * @param colector
	 *            bean del documento colector
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Colector colector,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(colector.getColectorId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.COLECTOR, broker));
		logs.setLogsDescripcion(getDescripcion(colector, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param estado
	 *            bean del objeto estado corte
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(EstadoCorte estado,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Estado Corte  : "
				+ estado.getEstadoNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento cualificación
	 *
	 * @param cartera
	 *            bean del documento cartera x departamento
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(CarteraXDepartamento cartera,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(cartera.getDepartamentoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.CARTERA_X_DEPARTAMENTO, broker));
		logs.setLogsDescripcion(getDescripcion(cartera, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param cartera
	 *            bean del objeto departamento
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(CarteraXDepartamento cartera,
			AuthUser user, Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Cartera de clientes en mora de  : "
				+ cartera.getFechaIngreso().toString();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	
	
	public static Logs setLogsDelSistema(Visitas visitas,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(visitas.getVisitaId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.CONTROL_VISTAS, broker));
		logs.setLogsDescripcion(getDescripcion(visitas, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param visitas
	 *            bean del objeto visitas
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Visitas visitas, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Control de visitas  : " + visitas.getFecha().getDate();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	
	/**
	 * Crea un registro de Logs para del documento pagos
	 *
	 * @param pagos
	 *            bean del documento pagos
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Pagos pagos,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(pagos.getPagoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.CONTROL_PAGOS, broker));
		logs.setLogsDescripcion(getDescripcion(pagos, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param pagos
	 *            bean del objeto pagos
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Pagos pagos, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Control de pagos  : " + pagos.getFecha().getDate();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento cantidad
	 *
	 * @param cantidad
	 *            bean del documento cantidad
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(CantidadMonedas cantidad,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(cantidad.getCantidadId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.CANTIDAD_MONEDAS, broker));
		logs.setLogsDescripcion(getDescripcion(cantidad, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param cantidad
	 *            bean del objeto cantidad
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(CantidadMonedas cantidad, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Cantidades Monedas  : " + cantidad.getCantidadNombre();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Crea un registro de Logs para del documento cualificación
	 *
	 * @param arqueo
	 *            bean del documento departamento
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(ArqueoPagos arqueo,
			Date fecha, Integer usrId, Integer estadoId,AuthUser usrAutoriza,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(arqueo.getArqueoId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.ARQUEO_PAGOS, broker));
		logs.setLogsDescripcion(getDescripcion(arqueo, user, estadoId, fecha, usrAutoriza));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param arqueo
	 *            bean del objeto departamento
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(ArqueoPagos arqueo,
			AuthUser user, Integer estadoId, Date fecha, AuthUser usrAutoriza) {
		String strDescripcion = "";
		strDescripcion = " Arqueo de  : "
				+ arqueo.getUsrIdRef().getUsrLogin();
		if (estadoId.equals(Integer.valueOf(1))) {
			strDescripcion += " creado por " + user.getUsrLogin() + " a las "
					+ fecha.getHours() + ":" + fecha.getMinutes();
		} else if (estadoId.equals(Integer.valueOf(2))) {
			strDescripcion += " editado por " + user.getUsrLogin() + " a las "
					+ fecha.getHours() + ":" + fecha.getMinutes();
		} else if (estadoId.equals(Integer.valueOf(3))) {
			strDescripcion += "creado por "+ user.getUsrLogin()
					+ " y " +" autorizado por " + usrAutoriza.getUsrLogin()
					+ " a las " + fecha.getHours() + ":" + fecha.getMinutes();
		}
		return strDescripcion;		
	}
	
	/**
	 * Crea un registro de Logs para del documento cuentas
	 *
	 * @param cuenta
	 *            bean del documento cuentas
	 * @param fecha
	 *            fecha en que se registra el log
	 * @param usrId
	 *            identificador del usuario
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	public static Logs setLogsDelSistema(Cuentas cuenta,
			Date fecha, Integer usrId, Integer estadoId,
			PersistenceBroker broker) throws Exception {
		Logs logs = new Logs();
		AuthUser user = Auth_userHandler.retrieve(usrId, broker);
		// Asignamos los valores a la transacción
		logs.setLogsReferencia(cuenta.getCuentaId().toString());
		logs.setLogsFecha(fecha);
		logs.setTipodLogIdRef(TipoDocumentoLogHandler.retrieve(
				TipoDocumentoLogHandler.CUENTAS, broker));
		logs.setLogsDescripcion(getDescripcion(cuenta, user, estadoId, fecha));
		return logs;
	}

	/**
	 * Log o Mensaje que se registrará .
	 *
	 * @param cuenta
	 *            bean del objeto cuenta
	 * @param user
	 *            bean del objeto usuario
	 * @param estadoId
	 * @return
	 */
	private static String getDescripcion(Cuentas cuenta, AuthUser user,
			Integer estadoId, Date fecha) {
		String strDescripcion = "";
		strDescripcion = " Cuenta #  : " + cuenta.getNumeroCuenta();
		return getEstado(strDescripcion, estadoId, fecha, user);
	}
	
	/**
	 * Regresa lista de logs, se puede filtrar por rago de fecha y tipo de 
	 * documento.
	 * 
	 * @param strfechaini
	 * 		fecha de inicio.
	 * @param strfechafin
	 * 		fecha final.
	 * @param tipodoc_id
	 * 		identificador del tipo de documento.
	 * @return
	 * @throws Exception
	 */
	public Collection<Logs> getList(Date strfechaini, Date strfechafin,
			Integer tipodoc_id) throws Exception {
		PersistenceBroker broker = null;
		try {
			// 1. Get the PersistenceBroker
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			// 2. Define criterio
			Criteria criterio = new Criteria();
			if (strfechaini != null) {
				criterio.addGreaterOrEqualThan("logsFecha", strfechaini);
			}
			if (strfechafin != null) {
				criterio.addLessOrEqualThan("logsFecha", strfechafin);
			}
			if (tipodoc_id != null) {
				criterio.addEqualTo("tipodLogId", tipodoc_id);
			}
			QueryByCriteria query = new QueryByCriteria(Logs.class, criterio);
			query.addOrderBy("logsFecha", true);
			return broker.getCollectionByQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 * Query para obtener los registros por página que se requiere.
	 *
	 * @param criterio
	 *            Criterio.
	 * @param pagina
	 *            Página requerida.
	 * @param longPagina
	 *            Cantidad de registros a mostrar por página.
	 * @param broker
	 * @return
	 * @throws Exception
	 */
	private static Collection getListPaginado(final Criteria criterio,
			final int pagina, final int longPagina, PersistenceBroker broker)
			throws Exception {
		int first = 1;
		if (pagina > 0 && longPagina > 0) {
			if (pagina != 1) {
				first = (longPagina * (pagina - 1)) + 1;
			}
		}
		QueryByCriteria query = new QueryByCriteria(Logs.class,
				criterio);
		query.addOrderBy("logsFecha", true);
		query.setStartAtIndex(first);
		query.setEndAtIndex(first + longPagina - 1);
		return broker.getCollectionByQuery(query);
	}

	/**
	 * Obtener la lista de logs para la paginación.
	 *
	 * @param fechaIni
	 * 		fecha de Inicio del filtro de busqueda
	 * @param fechaFin
	 * 		fecha final del filtro de busqueda
	 * @param tipodocId
	 * 		identificador del tipo de documento
	 * @param pagina
	 *            Página requerida.
	 * @param longPagina
	 *            Cantidad de registros popr página.
	 * @return
	 * @throws Exception
	 */
	public Collection getListPaginado(final Date fechaIni, final Date fechaFin,
			final Integer tipodocId, final int pagina,
			final int longPagina) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return getListPaginado(getCriterio(fechaIni, fechaFin,
					tipodocId), pagina, longPagina, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

	/**
	 *Construir el criterio de la consulta para generar las listas de elementos
	 * para los métodos de paginado.
	 *
	 * @param fechaIni
	 * 		fecha de Inicio del filtro de busqueda
	 * @param fechaFin
	 * 		fecha final del filtro de busqueda
	 * @param tipodocId
	 * 		identificador del tipo de documento
	 * @return
	 */
	private static Criteria getCriterio(final Date fechaIni, final Date fechaFin,
			final Integer tipodocId) {
		Criteria criterio = new Criteria();
		if (fechaIni != null) {
			criterio.addGreaterOrEqualThan("logsFecha", fechaIni);
		}
		if (fechaFin != null) {
			criterio.addLessOrEqualThan("logsFecha", fechaFin);
		}
		if (tipodocId != null) {
			criterio.addEqualTo("tipodLogId", tipodocId);
		}
		return criterio;
	}

	/**
	 * Regresa la cantidad de registros que retorna la consulta.
	 *
	 * @param fechaIni
	 * 		fecha de Inicio del filtro de busqueda
	 * @param fechaFin
	 * 		fecha final del filtro de busqueda
	 * @param tipodocId
	 * 		identificador del tipo de documento
	 * @return
	 * @throws Exception
	 */
	public int getContador(final Date fechaIni, final Date fechaFin,
			final Integer tipodocId) throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			QueryByCriteria query = new QueryByCriteria(
					Logs.class, getCriterio(
							fechaIni, fechaFin, tipodocId));
			return broker.getCount(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}
	
	/**
	 * 	Regresa mensaje que registrará los logs para el formulario correspondiente
	 *  
	 * @param strDescripcion
	 * 		Mensaje inicial del log, varia segun el formulario
	 * @param estadoId
	 * 		Identificar del estado puede ser: creado, editado o desactivado.
	 * @param fecha
	 * 		Fecha en la que se registra el logs
	 * @param user
	 * 		Bean del usuario que realiza la acción
	 * @return
	 */
	private static String getEstado(String strDescripcion, Integer estadoId,
			Date fecha, AuthUser user) {
		if (estadoId.equals(Integer.valueOf(1))) {
			strDescripcion += " creado por " + user.getUsrLogin() + " a las "
					+ fecha.getHours() + ":" + fecha.getMinutes();
		} else if (estadoId.equals(Integer.valueOf(2))) {
			strDescripcion += " editado por " + user.getUsrLogin() + " a las "
					+ fecha.getHours() + ":" + fecha.getMinutes();
		} else if (estadoId.equals(Integer.valueOf(3))) {
			strDescripcion += " desactivado por " + user.getUsrLogin()
					+ " a las " + fecha.getHours() + ":" + fecha.getMinutes();
		}
		return strDescripcion;

	}

}
