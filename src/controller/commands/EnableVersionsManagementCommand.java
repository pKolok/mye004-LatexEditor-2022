package controller.commands;

import model.VersionsManager;

public class EnableVersionsManagementCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public EnableVersionsManagementCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public EnableVersionsManagementCommand() { super();	}

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.enableStrategy();
	}
}
