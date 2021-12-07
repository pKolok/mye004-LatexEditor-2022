package controller.commands;

import model.VersionsManager;

public class SaveCommand implements Command {
	// TODO Remove
//	private VersionsManager versionsManager;
	
//	public SaveCommand(VersionsManager versionsManager) {
//		// TODO Auto-generated constructor stub
//		this.versionsManager = versionsManager;
//	}
	
	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.saveToFile();
	}
}
