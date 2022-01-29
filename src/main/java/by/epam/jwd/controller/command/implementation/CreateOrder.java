package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        String message = null;

        if("/welcome".equals(request.getServletPath())){
            int countRequest = 0;
            if (request.getSession().getAttribute("countRequest") != null){
                countRequest = (int) request.getSession().getAttribute("countRequest");
            }
            request.getSession().setAttribute("countRequest", ++countRequest);
            if(countRequest < 2) {
                try {
                    orderService.createNotApproveOrder(request.getParameter("fullName"),
                            request.getParameter("phone"), request.getParameter("criteria"));
                    message = "The order has been created, wait for the administrator to call";
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }else {
                message = "To much requests";
            }
            response.sendRedirect(CommandName.WELCOME_COMMAND + CommandName.GO_TO_MAIN_PAGE + "&message=" + message);
        }

        if("/admin".equals(request.getServletPath())){
            System.out.println(request.getParameter("car"));
        }


    }
}
