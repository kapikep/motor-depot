package by.epam.jwd.controller.listener;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class PoolInitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public PoolInitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MariaDBConnectionPool.initPool("db");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        try {
            MariaDBConnectionPool.closeConnectionQueue();
        } catch (DAOException e) {
            e.printStackTrace();
            //TODO logger
        }
    }
}
