package by.epam.jwd.controller.constant;

public final class PagePath {

    private PagePath(){}

    private static final String DEFAULT_PATH = "/WEB-INF/jsp/";
    //Unauthorized zone
    public static final String MAIN_PAGE = DEFAULT_PATH + "mainPage.jsp";
    public static final String SIGN_IN_PAGE = DEFAULT_PATH + "signInPage.jsp";
    public static final String WELCOME_CREATE_ORDER = DEFAULT_PATH + "welcomeCreateOrder.jsp";
    //Driver zone
    public static final String MAIN_DRIVER_PAGE = DEFAULT_PATH + "driver/mainDriverPage.jsp";
    public static final String ORDERS_DRIVER_PAGE = DEFAULT_PATH + "driver/ordersForDriver.jsp";
    public static final String DRIVER_EDIT_ORDER = DEFAULT_PATH + "driver/driverEditOrder.jsp";
    //Customer zone
    public static final String MAIN_CUSTOMER_PAGE = DEFAULT_PATH + "customer/mainCustomerPage.jsp";
    public static final String CUSTOMER_EDIT_ORDER_PAGE = DEFAULT_PATH + "customer/customerEditOrder.jsp";

    //Admin zone
    public static final String MAIN_ADMIN_PAGE = DEFAULT_PATH + "admin/mainAdminPage.jsp";
    public static final String ADMIN_CARS_PAGE = DEFAULT_PATH + "admin/carsPage.jsp";
    public static final String ADMIN_ORDERS_PAGE = DEFAULT_PATH + "admin/ordersPage.jsp";
    public static final String ADMIN_DRIVERS_PAGE = DEFAULT_PATH + "admin/driversPage.jsp";
    public static final String ADMIN_USERS_PAGE = DEFAULT_PATH + "admin/usersPage.jsp";
    public static final String ADMIN_EDIT_CAR_PAGE = DEFAULT_PATH + "admin/editCarPage.jsp";
    public static final String ADMIN_EDIT_ORDER_PAGE = DEFAULT_PATH + "admin/editOrderPage.jsp";
    public static final String ADMIN_EDIT_DRIVER_PAGE = DEFAULT_PATH + "admin/editDriverPage.jsp";
    public static final String ADMIN_EDIT_USER_PAGE = DEFAULT_PATH + "admin/editUserPage.jsp";
}
