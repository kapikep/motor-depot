package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.impl.GoToPresentationPage;
import by.epam.jwd.controller.impl.GoToSignIn;

public final class CommandProvider {
	
	public final String GO_TO_SIGN_IN = "GoToSignIn";
	public final String GO_TO_PRESENTATION_PAGE = "GoToPresentationPage";

	private final Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(GO_TO_SIGN_IN, new GoToSignIn());
		commands.put(GO_TO_PRESENTATION_PAGE, new GoToPresentationPage());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
