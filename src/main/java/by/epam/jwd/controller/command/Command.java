package by.epam.jwd.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	String COMMAND = "Command";
    String CONTROLLER = "Controller";
    String DEFAULT_PATH = "/WEB-INF/jsp/";
    String INDEX_PAGE = "index.jsp";
    String SIGN_IN_PAGE = "signIn";
    String SIGN_IN = "SignIn";
    String LOG_OUT = "LogOut";
    String GO_TO_SIGN_IN = "GoToSignIn";

    String MAIN_DRIVER_PAGE = DEFAULT_PATH + "mainDriverPage.jsp";

    String MAIN_CUSTOMER_PAGE = DEFAULT_PATH + "mainCustomerPage.jsp";

    String MAIN_ADMIN_PAGE = DEFAULT_PATH + "admin/mainAdminPage.jsp";
    String ADMIN_CARS_PAGE = DEFAULT_PATH + "admin/carsPage.jsp";
    String GO_TO_ADMIN_CARS_PAGE = "GoToCarsPage";
    String ADMIN_ORDERS_PAGE = DEFAULT_PATH + "admin/ordersPage.jsp";
    String GO_TO_ADMIN_ORDERS_PAGE = "GoToOrdersPage";
    String ADMIN_EMPLOYEES_PAGE = DEFAULT_PATH + "admin/employeesPage.jsp";
    String GO_TO_ADMIN_EMPLOYEES_PAGE = "GoToEmployeesPage";
    String ADMIN_CUSTOMERS_PAGE = DEFAULT_PATH + "admin/customersPage.jsp";
    String GO_TO_ADMIN_CUSTOMERS_PAGE = "GoToCustomersPage";

	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
