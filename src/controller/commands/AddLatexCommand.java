package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
//import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public AddLatexCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public AddLatexCommand() {super(); }

	@Override
	public void execute() {
		// TODO - remove
		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.saveContents();
//		LatexEditorView latexEditorView = LatexEditorView.getInstance();
//		latexEditorView.saveContents();
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		
//		Document currentDocument = latexEditorView.getCurrentDocument();
		Document currentDocument = latexEditorController.getCurrentDocument();
		
		if(versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
//		currentDocument.setContents(latexEditorView.getText());
		currentDocument.setContents(latexEditorController.getText());
	}
}
