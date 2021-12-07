package controller.commands;

import model.VersionsManager;

public class ChangeVersionsStrategyCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public ChangeVersionsStrategyCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public ChangeVersionsStrategyCommand() { super(); }

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.changeStrategy();
	}
}
