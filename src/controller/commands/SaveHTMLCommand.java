package controller.commands;

import controller.LatexEditorController;

public class SaveHTMLCommand implements Command {

	@Override
	public void execute() {
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		latexEditorController.getCurrentDocument().convertToHTML();
		latexEditorController.getCurrentDocument().saveHTML(latexEditorController.getFilename());
	}

}
