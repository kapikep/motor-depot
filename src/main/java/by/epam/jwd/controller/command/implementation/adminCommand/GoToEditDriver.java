package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEditDriver implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DriverService DriverService = MDServiceFactory.getMDService().getDriverService();
        Driver driver = null;
        String edit_id = request.getParameter("edit_id");

        try {
            if (edit_id != null) {
                driver = DriverService.readDriver(edit_id);
                request.setAttribute("edit", true);
            } else {
                request.setAttribute("create", true);
            }
            request.setAttribute("driver", driver);
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_DRIVER_PAGE).forward(request, response);
    }
}
