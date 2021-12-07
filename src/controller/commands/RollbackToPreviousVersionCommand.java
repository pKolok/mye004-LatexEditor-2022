package controller.commands;

import model.VersionsManager;

public class RollbackToPreviousVersionCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
//	
//	public RollbackToPreviousVersionCommand(VersionsManager versionsManager) {
//		this.versionsManager = versionsManager;
//	}

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.rollback();
	}
}
