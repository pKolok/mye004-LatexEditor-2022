package controller.commands;

import model.VersionsManager;

public class AddLatexCommand implements Command  {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public AddLatexCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public AddLatexCommand() {super(); }

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.saveContents();
	}
}
