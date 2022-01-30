package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.*;
import by.epam.jwd.controller.command.implementation.adminCommand.*;
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
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_CUSTOMERS_PAGE, new GoToCustomers());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_ORDERS_PAGE, new GoToOrdersPage());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_EMPLOYEES_PAGE, new GoToEmployees());
        ADMIN_COMMANDS.put(CommandName.GO_TO_ADMIN_EDIT_ORDER, new GoToEditOrder());
        ADMIN_COMMANDS.put(CommandName.ADMIN_CREATE_CAR, new CreateCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_CREATE_CAR_MODEL, new CreateCarModel());
        ADMIN_COMMANDS.put(CommandName.ADMIN_GO_TO_EDIT_CAR, new GoToEditCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_EDIT_CAR, new UpdateCar());
        ADMIN_COMMANDS.put(CommandName.ADMIN_DELETE_CAR, new DeleteCar());
        ADMIN_COMMANDS.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
        ADMIN_COMMANDS.put(CommandName.CREATE_ORDER, new CreateOrder());
        ADMIN_COMMANDS.put(CommandName.GO_TO_MAIN_ADMIN_PAGE, new GoToMainAdminPage());
        ADMIN_COMMANDS.put(CommandName.SELECT_CAR_TO_ORDER, new SelectCarToOrder());
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
