package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import controller.LatexEditorController;
import model.Document;

public class LoadCommand implements Command {
	private LatexEditorController latexEditorController;

	@Override
	public void execute() {
		latexEditorController = LatexEditorController.getInstance();
		loadFromFile();
	}
	
	private void loadFromFile() {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(latexEditorController.getFilename()));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Document currentDocument = new Document();
		currentDocument.setContents(fileContents);
		latexEditorController.setCurrentDocument(currentDocument);
		latexEditorController.setType("emptyTemplate");
		
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			latexEditorController.setType("articleTemplate");
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			latexEditorController.setType("bookTemplate");
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			latexEditorController.setType("reportTemplate");
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
			latexEditorController.setType("letterTemplate");
		} else if (fileContents.contains("<html>")) {
			latexEditorController.setType("htmlDocument");
			currentDocument.setContents("");
			currentDocument.setHtmlContents(fileContents);
			currentDocument.convertToLateX();
		}
	}

}
