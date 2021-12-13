package _tests;

import static org.junit.Assert.*;

import javax.swing.JEditorPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.LatexEditorController;
import controller.commands.AddLatexCommand;
import model.Document;
import model.DocumentManager;
import view.MainWindow;

public class TestEditContents {
	private static DocumentManager documentManager;
	private static String addChapter, addSection, addSubSection, 
		addSubSubSection, itemize, enumerate, addTable, addFigure;
	private static Document document;
	private static LatexEditorController controller;
	private static JEditorPane editorPane;
	private static AddLatexCommand addLatexCommand;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = LatexEditorController.getInstance();
		editorPane = new JEditorPane();
		addLatexCommand = new AddLatexCommand();
		controller.setType("emptyTemplate");
		controller.enact("create");
		documentManager = new DocumentManager();
		
		assignCommands();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before		// runs before each test case
	public void setUp() throws Exception {
		document = documentManager.createDocument("emptyTemplate");
		controller.setCurrentDocument(document);
	}

	@After		// runs after every test case 
	public void tearDown() throws Exception {
	}
	
	private static void assignCommands(){
		addChapter = "\n\\chapter{...}\n";
		addSection=  "\n\\section{...}\n";
		addSubSection =  "\n\\subsection{...}\n";
		addSubSubSection = "\n\\subsubsection{...}\n";
		enumerate = 	"\\begin{enumerate}\n"+
					"\\item ...\n"+
					"\\end{enumerate}\n";
		itemize = "\\begin{itemize}\n"+
					"\\item ...\n"+
					"\\item ...\n"+
					"\\end{itemize}\n";
		addTable = "\\begin{table}\n"+
					"\\caption{....}\\label{...}\n"+
					"\\begin{tabular}{|c|c|c|}\n"+
					"\\hline\n"+
					"... &...&...\\\\\n"+
					"... &...&...\\\\\n"+
					"... &...&...\\\\\n"+
					"\\hline\n"+
					"\\end{tabular}\n"+
					"\\end{table}\n";
		addFigure = "\\begin{figure}\n"+
					"\\includegraphics[width=...,height=...]{...}\n"+
					"\\caption{....}\\label{...}\n"+
					"\\end{figure}\n";
	}

	@Test
	public void testEditContentsAddChapter() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "chapter");
		assertEquals(addChapter, document.getContents());
	}
	
	@Test
	public void testEditContentsAddSection() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "section");
		assertEquals(addSection, document.getContents());
	}
	
	@Test
	public void testEditContentsAddSubSection() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "subsection");
		assertEquals(addSubSection, document.getContents());
	}
	
	@Test
	public void testEditContentsAddSubSubSection() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "subsubsection");
		assertEquals(addSubSubSection, document.getContents());
	}
	
	@Test
	public void testEditContentsAddEnumerate() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "enumerate");
		assertEquals(enumerate, document.getContents());
	}
	
	@Test
	public void testEditContentsAddItemise() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "itemize");
		assertEquals(itemize, document.getContents());
	}
	
	@Test
	public void testEditContentsAddTable() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "table");
		assertEquals(addTable, document.getContents());
	}
	
	@Test
	public void testEditContentsAddFigure() {
		editorPane.setText("");
		addLatexCommand.editContents(editorPane, "figure");
		assertEquals(addFigure, document.getContents());
	}

}
