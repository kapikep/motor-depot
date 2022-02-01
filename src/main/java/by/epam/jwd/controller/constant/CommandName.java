package by.epam.jwd.controller.constant;

public final class CommandName {

    private CommandName(){}

    //Unauthorized command
    public static final String WELCOME_COMMAND = "welcome?command=";
    public static final String GO_TO_MAIN_PAGE = "GoToMainPage";
    public static final String GO_TO_SIGN_IN = "GoToSignIn";
    public static final String SIGN_IN = "SignIn";
    public static final String LOG_OUT = "LogOut";
    public static final String CHANGE_LOCALIZATION = "ChangeLocalization";
    public static final String GO_TO_WELCOME_CREATE_ORDER = "GoToWelcomeCreateOrder";
    public static final String CREATE_ORDER = "CreateOrder";

    //Admin command
    public static final String ADMIN_COMMAND = "admin?command=";
    public static final String GO_TO_ADMIN_CARS_PAGE = "GoToCarsPage";
    public static final String GO_TO_ADMIN_ORDERS_PAGE = "GoToOrdersPage";
    public static final String GO_TO_ADMIN_EMPLOYEES_PAGE = "GoToEmployeesPage";
    public static final String GO_TO_ADMIN_USERS_PAGE = "GoToCustomersPage";
    public static final String GO_TO_ADMIN_EDIT_ORDER = "GoToEditOrder";
    public static final String ADMIN_CREATE_CAR = "CreateCar";
    public static final String ADMIN_CREATE_CAR_MODEL = "CreateCarModel";
    public static final String ADMIN_GO_TO_EDIT_CAR = "GoToEditCar";
    public static final String ADMIN_EDIT_CAR = "UpdateCar";
    public static final String ADMIN_DELETE_CAR = "DeleteCar";
    public static final String GO_TO_MAIN_ADMIN_PAGE = "GoToMainAdminPage";
    public static final String SELECT_CAR_TO_ORDER = "SelectCarToOrder";
    public static final String UPDATE_ORDER = "UpdateOrder";
    public static final String GO_TO_EDIT_DRIVER = "GoToEditDriver";
    public static final String GO_TO_EDIT_USER = "GoToEditUser";
    public static final String EDIT_USER = "EditUser";
    public static final String EDIT_DRIVER = "EditDriver";

    //Driver command
    public static final String DRIVER_COMMAND = "driver?command=";
    public static final String GO_TO_DRIVER_MAIN_PAGE = "GoToDriverMainPage";
    public static final String GO_TO_DRIVER_ORDERS_PAGE = "GoToDriverOrdersPage";
    public static final String GO_TO_DRIVER_EDIT_ORDER = "GoToDriverEditOrder";
    public static final String UPDATE_ORDER_BY_DRIVER = "UpdateOrderByDriver";

    //Customer command
    public static final String CUSTOMER_COMMAND = "customer?command=";
    public static final String GO_TO_CUSTOMER_MAIN_PAGE = "GoToCustomerMainPage";
    public static final String GO_TO_CUSTOMER_EDIT_ORDER = "GoToCustomerEditOrder";



}
