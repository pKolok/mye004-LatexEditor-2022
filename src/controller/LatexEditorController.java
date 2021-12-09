package controller;

import java.util.HashMap;
import javax.swing.JEditorPane;
import controller.commands.AddLatexCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import model.Document;

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
	
	private LatexEditorController() {
		instance = null;
		CommandFactory commandFactory = new CommandFactory();
		
		commands = new HashMap<String, Command>(); 
	
		for (int i = 0; i < commandNames.length; ++i)
			commands.put(commandNames[i], 
					commandFactory.createCommand(commandNames[i]));
	}
	
	public static LatexEditorController getInstance() {
		if (instance == null)
			instance = new LatexEditorController();
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
