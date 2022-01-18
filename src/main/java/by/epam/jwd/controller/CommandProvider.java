package by.epam.jwd.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.jwd.controller.impl.RegstrationCommand;
import com.epam.jwd.controller.impl.SignInCommand;

import by.epam.jwd.controller.impl.GoToSignIn;

public final class CommandProvider {
	
	private final Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put("GoToSignIn", new GoToSignIn());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
