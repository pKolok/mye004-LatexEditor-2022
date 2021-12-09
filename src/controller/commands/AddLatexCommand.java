package controller.commands;

import java.util.HashMap;

import javax.swing.JEditorPane;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
//import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	private HashMap<String, String> latexSyntax;
	// TODO - Remove
//	private VersionsManager versionsManager;
	
//	public AddLatexCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public AddLatexCommand() {super(); }

	public AddLatexCommand() {
		latexSyntax = new HashMap<String, String>();
		loadLatexSyntax();
	}

	@Override
	public void execute() {
		// TODO - remove
		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.saveContents();
//		LatexEditorView latexEditorView = LatexEditorView.getInstance();
//		latexEditorView.saveContents();
//		Document currentDocument = latexEditorView.getCurrentDocument();
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		Document currentDocument = latexEditorController.getCurrentDocument();
		
		if(versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
//		currentDocument.setContents(latexEditorView.getText());
		currentDocument.setContents(latexEditorController.getText());
	}
	
	public void editContents(JEditorPane editorPane, String type) {
		LatexEditorController latexEditorController = LatexEditorController.getInstance();
		String contents = editorPane.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		
//		if(type.equals("chapter")) {
//			contents = before + "\n\\chapter{...}"+"\n"+after;
//		}
//		else if(type.equals("section")) {
//			contents = before + "\n\\section{...}"+"\n"+after;
//		}
//		else if(type.equals("subsection")) {
//			contents = before + "\n\\subsection{...}"+"\n"+after;
//		}
//		else if(type.equals("subsubsection")) {
//			contents = before + "\n\\subsubsection{...}"+"\n"+after;
//		}
//		else if(type.equals("enumerate")) {
//			contents = before + 
//					"\\begin{enumerate}\n"+
//					"\\item ...\n"+
//					"\\item ...\n"+
//					"\\end{enumerate}\n"+after;
//		}
//		else if(type.equals("itemize")) {
//			contents = before + 
//					"\\begin{itemize}\n"+
//					"\\item ...\n"+
//					"\\item ...\n"+
//					"\\end{itemize}\n"+after;
//		}
//		else if(type.equals("table")) {
//			contents = before + 
//					"\\begin{table}\n"+
//					"\\caption{....}\\label{...}\n"+
//					"\\begin{tabular}{|c|c|c|}\n"+
//					"\\hline\n"+
//					"... &...&...\\\\\n"+
//					"... &...&...\\\\\n"+
//					"... &...&...\\\\\n"+
//					"\\hline\n"+
//					"\\end{tabular}\n"+
//					"\\end{table}\n"+after;
//		}
//		else if(type.equals("figure")) {
//			contents = before + 
//					"\\begin{figure}\n"+
//					"\\includegraphics[width=...,height=...]{...}\n"+
//					"\\caption{....}\\label{...}\n"+
//					"\\end{figure}\n"+after;
//		}
		
		for (String key : latexSyntax.keySet())
			if (key.equals(type))
				contents = before + latexSyntax.get(key) + after;
		
		
//		latexEditorView.setText(contents);
		latexEditorController.setText(contents);
//		latexEditorView.getController().enact("addLatex");
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
