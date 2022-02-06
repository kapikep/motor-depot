package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EditDriver implements Command {

    private final Logger log = LogManager.getLogger(EditDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute("bundle");
        DriverService driverService = MDServiceFactory.getMDService().getDriverService();
        HttpSession session = request.getSession();
        Map<String, String> param = new HashMap<>();
        String resMessage = null;
        boolean exception = false;
        String flag = request.getParameter("flag");
        Driver driver = null;

        param.put("name", request.getParameter("name"));
        param.put("surname", request.getParameter("surname"));
        param.put("login", request.getParameter("login"));
        param.put("password", request.getParameter("password"));
        param.put("phoneNumber", request.getParameter("phoneNumber"));
        param.put("status", request.getParameter("status"));
        param.put("eMail", request.getParameter("eMail"));
        param.put("additionalInfo", request.getParameter("additionalInfo"));
        param.put("category", request.getParameter("category"));
        param.put("drivingExperience", request.getParameter("drivingExperience"));
        param.put("dateOfEmployment", request.getParameter("dateOfEmployment"));
        param.put("dateOfDismissal", request.getParameter("dateOfDismissal"));
        param.put("attachedCarId", request.getParameter("attachedCarId"));
        param.put("userId", request.getParameter("userId"));
        param.put("locale", (String) request.getSession().getAttribute("locale"));

        try {
            if("create".equals(flag)){
                driverService.createDriver(param);
            }

            if("update".equals(flag)){
                param.put("id", request.getParameter("id"));
                param.put("prevUserLogin", (String) session.getAttribute("editUserLogin"));
                driverService.updateDriver(param);
                session.setAttribute("editUserLogin", null);
                resMessage = bundle.getString("message.updateDone");
            }
        } catch (ServiceException e) {
            exception = true;
            resMessage = bundle.getString("message.somethingWrong");
            log.error("Catching: ", e);
        } catch (ValidateException e) {
            exception = true;
            resMessage = e.getLocalizedMessage();
        }

        if (exception) {
            try {
                driver = driverService.createDriverEntity(param);
            } catch (ServiceException e) {
                log.error("Catching: ", e);
            }

            session.setAttribute("wrongDriver", driver);

            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_EDIT_DRIVER + "&message=" +
                    URLEncoder.encode(resMessage, "UTF-8") + "&edit_id=" + request.getParameter("id") + "&flag=" + flag);
        } else {
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_EMPLOYEES_PAGE+ "&message=" + URLEncoder.encode(resMessage, "UTF-8"));
        }
    }
}
