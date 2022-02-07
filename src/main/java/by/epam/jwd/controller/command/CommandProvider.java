package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.*;
import by.epam.jwd.controller.command.implementation.adminCommand.*;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerMainPage;
import by.epam.jwd.controller.command.implementation.driverCommand.GoToDriverMainPage;
import by.epam.jwd.controller.command.implementation.driverCommand.GoToDriverOrdersPage;
import by.epam.jwd.controller.command.implementation.driverCommand.GoToDriverEditOrder;
import by.epam.jwd.controller.command.implementation.driverCommand.UpdateOrderByDriver;
import by.epam.jwd.controller.constant.CommandName;

public final class CommandProvider {
    private static final CommandProvider INSTANCE = new CommandProvider();

    private final Map<String, Command> WELCOME_COMMANDS = new HashMap<>();
    private final Map<String, Command> ADMIN_COMMANDS = new HashMap<>();
    private final Map<String, Command> DRIVER_COMMANDS = new HashMap<>();
    private final Map<String, Command> CUSTOMER_COMMANDS = new HashMap<>();

    private CommandProvider() {
        WELCOME_COMMANDS.put(CommandName.SIGN_IN, new SignIn());
        WELCOME_COMMANDS.put(CommandName.LOG_OUT, new LogOut());
        WELCOME_COMMANDS.put(CommandName.GO_TO_SIGN_IN, new GoToSignIn());
        WELCOME_COMMANDS.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
        WELCOME_COMMANDS.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        WELCOME_COMMANDS.put(CommandName.GO_TO_WELCOME_CREATE_ORDER, new GoToWelcomeCreateOrder());
        WELCOME_COMMANDS.put(CommandName.CREATE_ORDER, new CreateOrder());

        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_CARS_PAGE, new GoToCarsPage());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_USERS_PAGE, new GoToUsersPage());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_ORDERS_PAGE, new GoToOrdersPage());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_EMPLOYEES_PAGE, new GoToDriversPage());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_EDIT_ORDER, new GoToEditOrder());
        ADMIN_COMMANDS.put(CommandName.ADMIN_CREATE_CAR, new CreateCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_CREATE_CAR_MODEL, new CreateCarModel());
        ADMIN_COMMANDS.put(CommandName.ADMIN_GO_TO_EDIT_CAR, new GoToEditCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_EDIT_CAR, new UpdateCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_DELETE_CAR, new DeleteCar());
        ADMIN_COMMANDS.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
        ADMIN_COMMANDS.put(CommandName.CREATE_ORDER, new CreateOrder());
        ADMIN_COMMANDS.put(CommandName.GO_TO_MAIN_ADMIN_PAGE, new GoToMainAdminPage());
        ADMIN_COMMANDS.put(CommandName.EDIT_ORDER, new EditOrder());
        ADMIN_COMMANDS.put(CommandName.GO_TO_EDIT_USER, new GoToEditUser());
        ADMIN_COMMANDS.put(CommandName.GO_TO_EDIT_DRIVER, new GoToEditDriver());
        ADMIN_COMMANDS.put(CommandName.EDIT_USER, new EditUser());
        ADMIN_COMMANDS.put(CommandName.EDIT_DRIVER, new EditDriver());
        ADMIN_COMMANDS.put(CommandName.SELECT_CAR_TO_ORDER, new SelectCarToOrder());

        DRIVER_COMMANDS.put(CommandName.GO_TO_DRIVER_MAIN_PAGE, new GoToDriverMainPage());
        DRIVER_COMMANDS.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
        DRIVER_COMMANDS.put(CommandName.GO_TO_DRIVER_ORDERS_PAGE, new GoToDriverOrdersPage());
        DRIVER_COMMANDS.put(CommandName.GO_TO_DRIVER_EDIT_ORDER, new GoToDriverEditOrder());
        DRIVER_COMMANDS.put(CommandName.UPDATE_ORDER_BY_DRIVER, new UpdateOrderByDriver());

        CUSTOMER_COMMANDS.put(CommandName.GO_TO_CUSTOMER_MAIN_PAGE, new GoToCustomerMainPage());
        CUSTOMER_COMMANDS.put(CommandName.GO_TO_CUSTOMER_EDIT_ORDER, new GoToCustomerEditOrder());
        CUSTOMER_COMMANDS.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
    }

    public static CommandProvider getCommandProvider() {
        return INSTANCE;
    }

    public Command getWelcomeCommand(String commandName) {
        Command command = WELCOME_COMMANDS.get(commandName);
        return command;
    }

    public Command getAdminCommand(String commandName) {
        Command command = ADMIN_COMMANDS.get(commandName);
        return command;
    }

    public Command getDriverCommand(String commandName) {
        Command command = DRIVER_COMMANDS.get(commandName);
        return command;
    }

    public Command getCustomerCommand(String commandName) {
        Command command = CUSTOMER_COMMANDS.get(commandName);
        return command;
    }

}
