package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	
	public CreateCommand(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}

	@Override
	public void execute() {
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		String type = latexEditorController.getType();
		Document document = documentManager.createDocument(type);
		latexEditorController.setCurrentDocument(document);
	}

}
