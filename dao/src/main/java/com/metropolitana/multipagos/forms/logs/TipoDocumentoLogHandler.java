package com.metropolitana.multipagos.forms.logs;

import java.util.Collection;
import java.util.Locale;

import com.metropolitana.multipagos.TipoDocumentoLogs;

import org.apache.commons.collections.IteratorUtils;
import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByIdentity;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

/**
 * @author Rafael Márquez
 */
public class TipoDocumentoLogHandler {

	public final static Integer DEPARTAMENTO = Integer.valueOf(1);
	public final static Integer LOCALIDAD = Integer.valueOf(2);
	public final static Integer BARRIO = Integer.valueOf(3);
	public final static Integer TASA_FIJA = Integer.valueOf(4);
	public final static Integer BANCO = Integer.valueOf(5);
	public final static Integer SERVICIO = Integer.valueOf(6);
	public final static Integer SIMBOLO = Integer.valueOf(7);
	public final static Integer ESTADO_CORTE = Integer.valueOf(8);
	public final static Integer COLECTOR = Integer.valueOf(9);
	public final static Integer CARTERA_X_DEPARTAMENTO = Integer.valueOf(10);
	public final static Integer CONTROL_VISTAS = Integer.valueOf(11);
	public final static Integer CONTROL_PAGOS = Integer.valueOf(12);
	public final static Integer CANTIDAD_MONEDAS = Integer.valueOf(13);
	public final static Integer ARQUEO_PAGOS = Integer.valueOf(14);
	public final static Integer CUENTAS = Integer.valueOf(15);

	/**
	 * Recupera un tipo de documento log
	 *
	 * @param tipodoc_log_id
	 *            Identificador del tipo de documento log
	 * @param pb
	 * @return TipoDocumentoLog
	 */
	public static TipoDocumentoLogs retrieve(Integer tipodoc_log_id,
			PersistenceBroker pb) {
		TipoDocumentoLogs criterio = new TipoDocumentoLogs();
		criterio.setTipodLogId(tipodoc_log_id);
		Query query = new QueryByIdentity(criterio);
		return (TipoDocumentoLogs) pb.getObjectByQuery(query);
	}

	public static TipoDocumentoLogs retrieve(final Integer tipodoc_log_id)
			throws Exception {
		PersistenceBroker broker = null;

		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			return retrieve(tipodoc_log_id, broker);
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}


	/**
	 * Genera el listado de tipos de documentos log
	 *
	 * @param tipodLogNombre
	 *            Nombre o parte del nombre del tipo de documento
	 * @return Collection o null
	 * @throws Exception
	 */
	public Collection getList(final String tipodLogNombre)
		throws Exception {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			Criteria criterio = new Criteria();
			criterio.addLike("upper(tipodLogNombre)", "*"
					+ tipodLogNombre.toUpperCase(Locale.getDefault())
					+ "*");
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					TipoDocumentoLogs.class, criterio);
			query.setAttributes(new String[] { "tipodLogId",
					"tipodLogNombre" });
			query.addOrderByAscending("tipodLogNombre");
			return IteratorUtils.toList(broker
					.getReportQueryIteratorByQuery(query));
		} catch (Exception e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}


	/**
	 * Genera el listado de todos los tipos de documentos
	 *
	 * @return Collection o null
	 * @throws Exception
	 */
	public Collection getList() throws PBFactoryException {
		PersistenceBroker broker = null;
		try {
			broker = PersistenceBrokerFactory.defaultPersistenceBroker();
			ReportQueryByCriteria query = new ReportQueryByCriteria(
					TipoDocumentoLogs.class, new Criteria());
			query.setAttributes(new String[] { "tipodLogId",
					"tipodLogNombre" });
			query.addOrderBy("tipodLogNombre", true);
			return IteratorUtils.toList(broker
					.getReportQueryIteratorByQuery(query));
		} catch (PBFactoryException e) {
			throw e;
		} finally {
			if (broker != null && !broker.isClosed()) {
				broker.close();
			}
		}
	}

}