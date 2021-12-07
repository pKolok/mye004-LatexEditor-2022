package controller.commands;

import model.VersionsManager;

public class EditCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public EditCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public EditCommand() { super(); }

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.saveContents();
	}
}
