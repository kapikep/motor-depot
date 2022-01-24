package by.epam.jwd.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    //Unauthorized zone
    String COMMAND = "?command=";
    String DEFAULT_PATH = "/WEB-INF/jsp/";
    String INDEX_PAGE = "index.jsp";
    String SIGN_IN_PAGE = "signIn";
    String SIGN_IN = "SignIn";
    String LOG_OUT = "LogOut";
    String GO_TO_SIGN_IN = "GoToSignIn";
    //Driver zone
    String MAIN_DRIVER_PAGE = DEFAULT_PATH + "mainDriverPage.jsp";
    //Customer zone
    String MAIN_CUSTOMER_PAGE = DEFAULT_PATH + "mainCustomerPage.jsp";
    //Admin zone
    String DEFAULT_ADMIN_PATH = "admin";
    String MAIN_ADMIN_PAGE = DEFAULT_PATH + "admin/mainAdminPage.jsp";
    String ADMIN_CARS_PAGE = DEFAULT_PATH + "admin/carsPage.jsp";
    String GO_TO_ADMIN_CARS_PAGE = "GoToCarsPage";
    String ADMIN_ORDERS_PAGE = DEFAULT_PATH + "admin/ordersPage.jsp";
    String GO_TO_ADMIN_ORDERS_PAGE = "GoToOrdersPage";
    String ADMIN_EMPLOYEES_PAGE = DEFAULT_PATH + "admin/employeesPage.jsp";
    String GO_TO_ADMIN_EMPLOYEES_PAGE = "GoToEmployeesPage";
    String ADMIN_CUSTOMERS_PAGE = DEFAULT_PATH + "admin/customersPage.jsp";
    String GO_TO_ADMIN_CUSTOMERS_PAGE = "GoToCustomersPage";
    String GO_TO_ADMIN_CREATE_CAR = "GoToCreateCar";
    String ADMIN_CREATE_CAR = "CreateCar";
    String ADMIN_CREATE_CAR_PAGE = DEFAULT_PATH + "admin/createCarPage.jsp";
    String ADMIN_CREATE_CAR_MODEL = "CreateCarModel";
    String ADMIN_EDIT_CAR = "EditCar";

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
