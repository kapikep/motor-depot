package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.GoToManageCars;
import by.epam.jwd.controller.command.implementation.GoToSignIn;
import by.epam.jwd.controller.command.implementation.LogOut;
import by.epam.jwd.controller.command.implementation.SignIn;

public final class CommandProvider {

	private final Map<String, Command> COMMANDS = new HashMap<>();
	private static final CommandProvider INSTANCE = new CommandProvider();

	private CommandProvider() {
		COMMANDS.put(Command.SIGN_IN_PAGE, new SignIn());
		COMMANDS.put(Command.LOG_OUT, new LogOut());
		COMMANDS.put(Command.GO_TO_SIGN_IN, new GoToSignIn());
		COMMANDS.put(Command.SIGN_IN, new SignIn());
		COMMANDS.put(Command.GO_TO_MANAGE_CARS, new GoToManageCars());
	}

	public static CommandProvider getCommandProvider() {
		return INSTANCE;
	}

	public Command getCommand(String commandName) {
		Command command = COMMANDS.get(commandName);
		return command;
	}
}
