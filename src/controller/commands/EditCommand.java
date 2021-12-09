package controller.commands;

import view.LatexEditorView;

public class EditCommand implements Command {

	@Override
	public void execute() {
		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		latexEditorView.saveContents();
	}
}
