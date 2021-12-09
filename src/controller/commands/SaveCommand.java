package controller.commands;

import controller.LatexEditorController;
// TODO - remove
//import model.VersionsManager;
//import view.LatexEditorView;

public class SaveCommand implements Command {
	// TODO Remove
//	private VersionsManager versionsManager;
	
//	public SaveCommand(VersionsManager versionsManager) {
//		// TODO Auto-generated constructor stub
//		this.versionsManager = versionsManager;
//	}
	
	@Override
	public void execute() {
		// TODO - remove
//		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.saveToFile();
//		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
//		latexEditorView.saveToFile();
//		latexEditorView.getCurrentDocument().save(latexEditorView.getFilename());
//		latexEditorController.getCurrentDocument().save(latexEditorView.getFilename());
		latexEditorController.getCurrentDocument().save(latexEditorController.getFilename());
	}
}
