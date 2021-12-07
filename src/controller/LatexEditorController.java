package controller;

import java.util.HashMap;

//import controller.commands.AddLatexCommand;
//import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
//import controller.commands.CreateCommand;
//import controller.commands.DisableVersionsManagementCommand;
//import controller.commands.EditCommand;
//import controller.commands.EnableVersionsManagementCommand;
//import controller.commands.LoadCommand;
//import controller.commands.RollbackToPreviousVersionCommand;
//import controller.commands.SaveCommand;
//import model.VersionsManager;

public class LatexEditorController{
	private HashMap<String, Command> commands;
	private String[] commandNames = {
			"addLatex", "create", "edit", "load", "save", 
			"changeVersionsStrategy", "enableVersionsManagement", 
			"disableVersionsManagement", "rollbackToPreviousVersion"};
	
//	public LatexEditorController(VersionsManager versionsManager) {
	public LatexEditorController() {
//		CommandFactory commandFactory = new CommandFactory(versionsManager);
		CommandFactory commandFactory = new CommandFactory();
		
		commands = new HashMap<String, Command>(); 
//		commands.put("addLatex", commandFactory.createCommand("addLatex"));
//		commands.put("changeVersionsStrategy",
//		commandFactory.createCommand("changeVersionsStrategy"));
//		commands.put("create", commandFactory.createCommand("create"));
//		commands.put("disableVersionsManagement",
//		commandFactory.createCommand("disableVersionsManagement"));
//		commands.put("edit", commandFactory.createCommand("edit"));
//		commands.put("enableVersionsManagement",
//		commandFactory.createCommand("enableVersionsManagement"));
//		commands.put("load", commandFactory.createCommand("load"));
//		commands.put("rollbackToPreviousVersion",
//		commandFactory.createCommand("rollbackToPreviousVersion"));
//		commands.put("save", commandFactory.createCommand("save"));
		 
		for (int i = 0; i < commandNames.length; ++i)
			commands.put(commandNames[i], 
					commandFactory.createCommand(commandNames[i]));
		
	}
	
	public void enact(String command) {
		commands.get(command).execute();
	}
	
}
