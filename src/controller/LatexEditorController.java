package controller;

import java.util.HashMap;

import javax.swing.JEditorPane;

import controller.commands.AddLatexCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
// TODO - remove
//import controller.commands.AddLatexCommand;
//import controller.commands.ChangeVersionsStrategyCommand;
//import controller.commands.CreateCommand;
//import controller.commands.DisableVersionsManagementCommand;
//import controller.commands.EditCommand;
//import controller.commands.EnableVersionsManagementCommand;
//import controller.commands.LoadCommand;
//import controller.commands.RollbackToPreviousVersionCommand;
//import controller.commands.SaveCommand;
//import model.VersionsManager;
import model.Document;
import model.VersionsManager;

public class LatexEditorController{
	private static LatexEditorController instance;
	private HashMap<String, Command> commands;
	private String[] commandNames = {	"addLatex", 
										"create", 
										"load", 
										"save", 
										"changeVersionsStrategy", 
										"enableVersionsManagement",
										"disableVersionsManagement", 
										"rollbackToPreviousVersion" };
	private Document currentDocument;
	private String strategy;
	private String type;
	private String text;
	private String filename;
	
//	public LatexEditorController(VersionsManager versionsManager) {
	private LatexEditorController() {
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
	
	public static LatexEditorController getInstance() {
		if (instance == null) {
			instance = new LatexEditorController();
		}
		return instance;
	}
		
	public void enact(String command) {
		commands.get(command).execute();
	}
	
	public String getStrategy() {
		return strategy;
	}
	
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void editContents(JEditorPane editorPane, String string) {
		((AddLatexCommand) commands.get("addLatex")).editContents(editorPane, string);
	}
	
}
