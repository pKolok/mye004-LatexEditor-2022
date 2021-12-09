package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class LatexEditorView {
	private static LatexEditorView instance;
	private LatexEditorController controller;
	private Document currentDocument;
	private String type;
	private String text;
	private String filename;
	private String strategy;
//	private VersionsManager versionsManager;
	
	// Singleton constructor is private
	private LatexEditorView() { instance = null; }	// TODO - correct?
	
//	public static LatexEditorView getInstance() {
//		if (instance == null) {
//			instance = new LatexEditorView();
//		}
//		return instance;
//	}
	
//	public VersionsManager getVersionsManager() {
//		return versionsManager;
//	}
//	public void setVersionsManager(VersionsManager versionsManager) {
//		this.versionsManager = versionsManager;
//	}
//	public String getStrategy() {
//		return strategy;
//	}
//	public void setStrategy(String strategy) {
//		this.strategy = strategy;
//	}
//	public String getText() {
//		return text;
//	}
//	public void setText(String text) {
//		this.text = text;
//	}
//	public LatexEditorController getController() {
//		return controller;
//	}
//	public void setController(LatexEditorController controller) {
//		this.controller = controller;
//	}
//	public Document getCurrentDocument() {
//		return currentDocument;
//	}
//	public void setCurrentDocument(Document currentDocument) {
//		this.currentDocument = currentDocument;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	
	// TODO - remove
//	public void saveContents() {
//		if(versionsManager.isEnabled()) {
//			versionsManager.putVersion(currentDocument);
//			currentDocument.changeVersion();
//		}
//		currentDocument.setContents(text);
//	}
	
	// TODO - remove
//	public void saveToFile() {
//		currentDocument.save(filename);
//	}
	
//	public String getFilename() {
//		return filename;
//	}
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}
	
	// TODO - remove
//	public void loadFromFile() {
//		String fileContents = "";
//		try {
//			Scanner scanner = new Scanner(new FileInputStream(filename));
//			while(scanner.hasNextLine()) {
//				fileContents = fileContents + scanner.nextLine() + "\n";
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		currentDocument = new Document();
//		currentDocument.setContents(fileContents);
//		
//		type = "emptyTemplate";
//		
//		fileContents = fileContents.trim();
//		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
//			type = "articleTemplate";
//		}
//		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
//			type = "bookTemplate";
//		}
//		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
//			type = "reportTemplate";
//		}
//		else if(fileContents.startsWith("\\documentclass{letter}")) {
//			type = "letterTemplate";
//		}
//	}
	
}
