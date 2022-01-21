package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.implementation.SignIn;
import by.epam.jwd.controller.command.implementation.GoToPresentationPage;
import by.epam.jwd.controller.command.implementation.GoToSignIn;

public final class CommandProvider {

	private final Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(Command.GO_TO_SIGN_IN, new GoToSignIn());
		commands.put(Command.GO_TO_PRESENTATION_PAGE, new GoToPresentationPage());
		commands.put(Command.SIGN_IN, new SignIn());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
