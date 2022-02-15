package by.epam.jwd.controller.listener;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class PoolInitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private final Logger log = LogManager.getLogger(PoolInitListener.class);

    public PoolInitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MariaDBConnectionPool.initPool("db");
        } catch (DAOException e) {
            log.error("Catching: ", e);
            throw new Error("Connection pool creation error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        try {
            MariaDBConnectionPool.closeConnectionQueue();
        } catch (DAOException e) {
            log.error("Catching: ", e);
        }
    }

    private void testUsers(){

    }
}
