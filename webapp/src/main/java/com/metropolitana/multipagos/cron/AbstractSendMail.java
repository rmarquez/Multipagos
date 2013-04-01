package com.metropolitana.multipagos.cron;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.CascadingRuntimeException;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.parameters.Parameters;
import org.apache.cocoon.components.ContextHelper;
import org.apache.cocoon.components.cron.ConfigurableCronJob;
import org.apache.cocoon.components.cron.CronJob;
import org.apache.cocoon.components.cron.ServiceableCronJob;
import org.apache.cocoon.components.flow.FlowHelper;
import org.apache.cocoon.components.flow.javascript.JavaScriptFlowHelper;
import org.apache.commons.io.IOUtils;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceResolver;

public abstract class AbstractSendMail extends ServiceableCronJob
		implements Contextualizable, CronJob, Configurable, ConfigurableCronJob {

	private static final String PIPELINE_PARAM = "pipeline";
	private Context context;
	private String configuredPipeline;
	private String pipeline;

	/**
	 * Este método es llamado diariamente a las 17:00:00.
	 */
	public void execute(String name) {
		if (sendEmail()) {
			Map mapa = new HashMap();
			SourceResolver resolver = null;
			checkSetup();
			Map objectModel = ContextHelper.getObjectModel(this.context);
			Object oldViewData = FlowHelper.getContextObject(objectModel);
			Source src = null;
			InputStream is = null;
			OutputStream output = null;
			try {
				FlowHelper.setContextObject(objectModel,
						JavaScriptFlowHelper.unwrap(mapa));
				resolver = (SourceResolver) this.manager
						.lookup(SourceResolver.ROLE);
				src = resolver.resolveURI("cocoon://" + pipeline
						+ "?source=cron");
				is = src.getInputStream();
				output = new java.io.ByteArrayOutputStream();
				IOUtils.copy(is, output);
				is.close();
				output.close();
			} catch (Exception e) {
				throw new CascadingRuntimeException("CronJob " + name
						+ " raised an exception.", e);
			} finally {
				try {
					if (is != null)
						is.close();
					if (output != null)
						output.close();
					FlowHelper.setContextObject(objectModel, oldViewData);
				} catch (IOException e) {
					throw new CascadingRuntimeException(
							"CocoonPipelineCronJob: " + name
									+ ", raised an exception: ", e);
				}
				if (resolver != null) {
					resolver.release(src);
					this.manager.release(resolver);
					resolver = null;
					src = null;
				}
			}
		} else {
			getLogger().info("No hay notificaciones que enviar por email");
		}
	}

	/* Retorna true/false indicando si vamos a enviar el email */
	protected abstract boolean sendEmail();

	protected void checkSetup() {
		if (this.manager == null) {
			throw new IllegalStateException(
					"Instances of "
							+ getClass().getName()
							+ " must be setup using either cocoon.createObject() or cocoon.setupObject().");
		}
	}

	public void contextualize(Context context) throws ContextException {
		this.context = context;
	}

	public void configure(final Configuration config)
			throws ConfigurationException {
		this.configuredPipeline = config.getChild(PIPELINE_PARAM)
				.getValue(null);
	}

	public void setup(Parameters params, Map objects) {
		if (params != null) {
			pipeline = params.getParameter(PIPELINE_PARAM, configuredPipeline);
		} else {
			pipeline = configuredPipeline;
		}
	}
}