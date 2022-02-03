package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditUser implements Command {

    private final Logger log = LogManager.getLogger(EditUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = MDServiceFactory.getMDService().getUserService();
        Map<String, String> param = new HashMap<>();
        HttpSession session = request.getSession();
        String resMessage = null;
        boolean exception = false;
        String flag = request.getParameter("flag");
        User user = null;

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
            if("create".equals(flag)){
                userService.createUser(param);
                resMessage = "Create done";
            }

            if("update".equals(flag)){
                param.put("id", request.getParameter("edit_id"));
                param.put("prevUserLogin", (String) session.getAttribute("editUserLogin"));
                userService.updateUser(param);
                session.setAttribute("editUserLogin", null);
                resMessage = "Update done";
            }
        } catch (ServiceException e) {
            exception = true;
            resMessage = "Something went wrong";
            log.error("Catching: ", e);
        } catch (ValidateException e) {
            exception = true;
            resMessage = e.getMessage();
        }

        if(exception){
            try {
                user = userService.createUserEntity(param);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            session.setAttribute("wrongUser", user);
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_EDIT_USER + "&message=" +
                    resMessage + "&edit_id=" + request.getParameter("edit_id") + "&flag=" + flag);
        }else{
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_USERS_PAGE + "&message=" + resMessage);
        }
    }
}
