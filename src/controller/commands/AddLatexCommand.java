package controller.commands;

import java.util.HashMap;
import javax.swing.JEditorPane;
import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class AddLatexCommand implements Command  {
	private HashMap<String, String> latexSyntax;

	public AddLatexCommand() {
		latexSyntax = new HashMap<String, String>();
		loadLatexSyntax();
	}

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		Document currentDocument = latexEditorController.getCurrentDocument();
		
		if(versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
		currentDocument.setContents(latexEditorController.getText());
	}
	
	public void editContents(JEditorPane editorPane, String type) {
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		String contents = editorPane.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		
		for (String key : latexSyntax.keySet())
			if (key.equals(type))
				contents = before + latexSyntax.get(key) + after;
		
		latexEditorController.setText(contents);
		latexEditorController.enact("addLatex");
		editorPane.setText(contents);
	}
	
	private void loadLatexSyntax() {
		latexSyntax.put("chapter", "\n\\chapter{...}"+"\n");
		latexSyntax.put("section", "\n\\section{...}"+"\n");
		latexSyntax.put("subsection", "\n\\subsection{...}"+"\n");
		latexSyntax.put("enumerate", "\\begin{enumerate}\n"+
									"\\item ...\n"+
									"\\item ...\n"+
									"\\end{enumerate}\n");
		latexSyntax.put("itemize", "\\begin{itemize}\n"+
									"\\item ...\n"+
									"\\item ...\n"+
									"\\end{itemize}\n");
		latexSyntax.put("table", "\\begin{table}\n"+
								"\\caption{....}\\label{...}\n"+
								"\\begin{tabular}{|c|c|c|}\n"+
								"\\hline\n"+
								"... &...&...\\\\\n"+
								"... &...&...\\\\\n"+
								"... &...&...\\\\\n"+
								"\\hline\n"+
								"\\end{tabular}\n"+
								"\\end{table}\n");
		latexSyntax.put("figure", "\\begin{figure}\n"+
								"\\includegraphics[width=...,height=...]{...}\n"+
								"\\caption{....}\\label{...}\n"+
								"\\end{figure}\n");
	}

}