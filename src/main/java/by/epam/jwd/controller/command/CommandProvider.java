package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.*;
import by.epam.jwd.controller.command.implementation.adminCommand.*;

public final class CommandProvider {
    private static final CommandProvider INSTANCE = new CommandProvider();

    private final Map<String, Command> WELCOME_COMMANDS = new HashMap<>();
    private final Map<String, Command> ADMIN_COMMANDS = new HashMap<>();
    private final Map<String, Command> DRIVER_COMMANDS = new HashMap<>();
    private final Map<String, Command> CUSTOMER_COMMANDS = new HashMap<>();

    private CommandProvider() {
        WELCOME_COMMANDS.put(Command.SIGN_IN, new SignIn());
        WELCOME_COMMANDS.put(Command.LOG_OUT, new LogOut());
        WELCOME_COMMANDS.put(Command.GO_TO_SIGN_IN, new GoToSignIn());

        ADMIN_COMMANDS.put(Command.GO_TO_ADMIN_CARS_PAGE, new GoToManageCars());
        ADMIN_COMMANDS.put(Command.GO_TO_ADMIN_CUSTOMERS_PAGE, new GoToCustomers());
        ADMIN_COMMANDS.put(Command.GO_TO_ADMIN_ORDERS_PAGE, new GoToOrders());
        ADMIN_COMMANDS.put(Command.GO_TO_ADMIN_EMPLOYEES_PAGE, new GoToEmployees());
        ADMIN_COMMANDS.put(Command.GO_TO_ADMIN_CREATE_CAR, new GoToCreateCar());
        ADMIN_COMMANDS.put(Command.ADMIN_CREATE_CAR, new CreateCar());
        ADMIN_COMMANDS.put(Command.ADMIN_CREATE_CAR_MODEL, new CreateCarModel());
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
