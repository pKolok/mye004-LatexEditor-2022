package controller.commands;

import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public CreateCommand(DocumentManager documentManager, VersionsManager versionsManager) {
//		super();
//		this.documentManager = documentManager;
//		this.versionsManager = versionsManager;
//	}
	
	public CreateCommand(DocumentManager documentManager) {
//		super();
		this.documentManager = documentManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsManager versionsManager = VersionsManager.getInstance();
		String type = versionsManager.getType();
		Document document = documentManager.createDocument(type);
		versionsManager.setCurrentVersion(document);
	}

}
