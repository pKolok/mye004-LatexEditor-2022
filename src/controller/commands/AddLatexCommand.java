package controller.commands;

// TODO - remove
//import model.VersionsManager;
import view.LatexEditorView;

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
//		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.saveContents();
		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		latexEditorView.saveContents();
	}
}
