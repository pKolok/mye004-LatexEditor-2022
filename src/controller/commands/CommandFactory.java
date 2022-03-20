package controller.commands;

import model.DocumentManager;

public class CommandFactory {
	private DocumentManager documentManager;

	public CommandFactory() {
		documentManager = new DocumentManager();
	}

	// TODO Instead of a big if structure i could use "Java reflection"
	public Command createCommand(String type) {
		if(type.equals("addLatex")) {
			return new AddLatexCommand();
		}
		if(type.equals("changeVersionsStrategy")) {
			return new ChangeVersionsStrategyCommand();
		}
		if(type.equals("create")) {
			return new CreateCommand(documentManager);
		}
		if(type.equals("disableVersionsManagement")) {
			return new DisableVersionsManagementCommand();
		}
		if(type.equals("enableVersionsManagement")) {
			return new EnableVersionsManagementCommand();
		}
		if(type.equals("load")) {
			return new LoadCommand();
		}
		if(type.equals("rollbackToPreviousVersion")) {
			return new RollbackToPreviousVersionCommand();
		}
		if(type.equals("save")) {
			return new SaveCommand();
		}
		if (type.equals("saveAsHTML")) {
			return new SaveHTMLCommand();
		}
		return null;
	}
}
