package controller.commands;

import model.VersionsManager;

public class EnableVersionsManagementCommand implements Command {

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.enableStrategy();
	}
}
