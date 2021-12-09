package controller.commands;

import controller.LatexEditorController;

public class SaveCommand implements Command {
	
	@Override
	public void execute() {
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		latexEditorController.getCurrentDocument().save(latexEditorController.getFilename());
	}
}