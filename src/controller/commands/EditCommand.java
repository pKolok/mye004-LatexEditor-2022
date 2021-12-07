package controller.commands;

// TODO - remove
//import model.VersionsManager;
import view.LatexEditorView;

public class EditCommand implements Command {
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public EditCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public EditCommand() { super(); }

	@Override
	public void execute() {
		// TODO - remove
//		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.saveContents();
		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		latexEditorView.saveContents();
	}
}
