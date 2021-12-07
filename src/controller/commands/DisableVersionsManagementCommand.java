package controller.commands;

import model.VersionsManager;

public class DisableVersionsManagementCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public DisableVersionsManagementCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public DisableVersionsManagementCommand() { super(); }

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.disable();
	}

}
