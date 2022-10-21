package de.byedev.rpgtavern.webapi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

public class WebActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<LoggerFactory> sr = context.getServiceReference(org.osgi.service.log.LoggerFactory.class);
        LoggerFactory loggerFactory = context.getService(sr);
        Logger logger = loggerFactory.getLogger(WebActivator.class);
        logger.info("Web Bundle started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
