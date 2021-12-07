package controller.commands;

import model.Document;
import model.DocumentManager;
// TODO - remove
//import model.VersionsManager;
import view.LatexEditorView;

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
		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		// TODO - remove
//		VersionsManager versionsManager = VersionsManager.getInstance();
//		String type = versionsManager.getType();
		String type = latexEditorView.getType();
		Document document = documentManager.createDocument(type);
//		versionsManager.setCurrentVersion(document);
		latexEditorView.setCurrentDocument(document);
	}

}
