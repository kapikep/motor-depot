package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditUser implements Command {

    private final Logger log = LogManager.getLogger(EditUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = MDServiceFactory.getMDService().getUserService();
        Map<String, String> param = new HashMap<>();
        String resMessage = null;

        param.put("name", request.getParameter("name"));
        param.put("surname", request.getParameter("surname"));
        param.put("login", request.getParameter("login"));
        param.put("password", request.getParameter("password"));
        param.put("phoneNumber", request.getParameter("phoneNumber"));
        param.put("status", request.getParameter("status"));
        param.put("eMail", request.getParameter("eMail"));
        param.put("additionalInfo", request.getParameter("additionalInfo"));
        param.put("role", request.getParameter("role"));

        try {
            if("true".equals(request.getParameter("create"))){
                userService.createUser(param);
                resMessage = "Update done";
            }

            if("true".equals(request.getParameter("update"))){
                param.put("id", request.getParameter("id"));
                userService.updateUser(param);
                resMessage = "Update done";
            }
        } catch (ServiceException e) {
            resMessage = "Something wrong";
            log.error("Catching: ", e);
        }
        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_USERS_PAGE + "&message=" + resMessage);
    }
}
