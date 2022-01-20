package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.impl.GoToAdminPage;
import by.epam.jwd.controller.command.impl.GoToPresentationPage;
import by.epam.jwd.controller.command.impl.GoToSignIn;

public final class CommandProvider {

	private final Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(Command.GO_TO_SIGN_IN, new GoToSignIn());
		commands.put(Command.GO_TO_PRESENTATION_PAGE, new GoToPresentationPage());
		commands.put(Command.GO_TO_ADMIN_PAGE, new GoToAdminPage());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
