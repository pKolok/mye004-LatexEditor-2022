package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;
//TODO - remove
//import model.VersionsManager;
//import view.LatexEditorView;

public class LoadCommand implements Command {
//	private LatexEditorView latexEditorView;
	private LatexEditorController latexEditorController;
//	private VersionsManager versionsManager;
	
//	public LoadCommand(VersionsManager versionsManager) {
//		super();
//		this.versionsManager = versionsManager;
//	}
	
//	public LoadCommand() { super(); }

	// TODO - remove (not used)
//	public VersionsManager getVersionsManager() {
//		return versionsManager;
//	}
//
//	public void setVersionsManager(VersionsManager versionsManager) {
//		this.versionsManager = versionsManager;
//	}

	@Override
	public void execute() {
		// TODO - remove
//		VersionsManager versionsManager = VersionsManager.getInstance();
//		versionsManager.loadFromFile();
//		latexEditorView = LatexEditorView.getInstance();
		latexEditorController = LatexEditorController.getInstance();
//		latexEditorView.loadFromFile();
		loadFromFile();
		
	}
	
	private void loadFromFile() {
		// TODO Auto-generated method stub
		String fileContents = "";
		try {
//			Scanner scanner = new Scanner(new FileInputStream(latexEditorView.getFilename()));
			Scanner scanner = new Scanner(new FileInputStream(latexEditorController.getFilename()));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Document currentDocument = new Document();
		currentDocument.setContents(fileContents);
//		latexEditorView.setCurrentDocument(currentDocument);
		latexEditorController.setCurrentDocument(currentDocument);
		
//		latexEditorView.setType("emptyTemplate");
		latexEditorController.setType("emptyTemplate");
		
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
//			latexEditorView.setType("articleTemplate");
			latexEditorController.setType("articleTemplate");
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
//			latexEditorView.setType("bookTemplate");
			latexEditorController.setType("bookTemplate");
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
//			latexEditorView.setType("reportTemplate");
			latexEditorController.setType("reportTemplate");
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
//			latexEditorView.setType("letterTemplate");
			latexEditorController.setType("letterTemplate");
		}
	}

}
