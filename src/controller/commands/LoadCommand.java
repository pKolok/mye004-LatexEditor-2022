package controller.commands;

import model.VersionsManager;

public class LoadCommand implements Command {
//	private VersionsManager versionsManager;
	
//	public LoadCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public LoadCommand() { super(); }

	// TODO - remove (not used)
//	public VersionsManager getVersionsManager() {
//		return versionsManager;
//	}
//
//	public void setVersionsManager(VersionsManager versionsManager) {
//		this.versionsManager = versionsManager;
//	}

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.loadFromFile();
	}

}
