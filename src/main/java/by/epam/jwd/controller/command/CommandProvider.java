package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.*;

public final class CommandProvider {

	private final Map<String, Command> COMMANDS = new HashMap<>();
	private static final CommandProvider INSTANCE = new CommandProvider();

	private CommandProvider() {
		COMMANDS.put(Command.SIGN_IN, new SignIn());
		COMMANDS.put(Command.LOG_OUT, new LogOut());
		COMMANDS.put(Command.GO_TO_SIGN_IN, new GoToSignIn());
		COMMANDS.put(Command.GO_TO_ADMIN_CARS_PAGE, new GoToManageCars());
		COMMANDS.put(Command.GO_TO_ADMIN_CUSTOMERS_PAGE, new GoToCustomers());
		COMMANDS.put(Command.GO_TO_ADMIN_ORDERS_PAGE, new GoToOrders());
		COMMANDS.put(Command.GO_TO_ADMIN_EMPLOYEES_PAGE, new GoToEmployees());
		COMMANDS.put(Command.GO_TO_ADMIN_CREATE_CAR, new GoToCreateCar());
		COMMANDS.put(Command.ADMIN_CREATE_CAR, new CreateCar());
	}

	public static CommandProvider getCommandProvider() {
		return INSTANCE;
	}

	public Command getCommand(String commandName) {
		Command command = COMMANDS.get(commandName);
		return command;
	}
}
