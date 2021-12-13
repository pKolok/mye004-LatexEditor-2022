package _tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.LatexEditorController;

public class TestEnactSave {
	private static LatexEditorController controller;
	private static String reportContents, bookContents, articleContents,
		letterContents, emptyContents;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = LatexEditorController.getInstance();
		controller.setFilename("test.tex");
		
		assignContents();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before		// runs before each test case
	public void setUp() throws Exception {
	}

	@After		// runs after every test case 
	public void tearDown() throws Exception {
	}

	private static void assignContents() {
		reportContents = "\\documentclass[11pt,a4paper]{report}\n\n"+

					"\\begin{document}\n"+
					"\\title{Report Template: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n"+
					"\\maketitle\n\n"+

					"\\begin{abstract}\n"+
					"Your abstract goes here...\n"+
					"...\n"+
					"\\end{abstract}\n\n"+

					"\\chapter{Introduction}\n"+
					"\\section{Section Title 1}\n"+
					"\\section{Section Title 2}\n"+
					"\\section{Section Title.....}\n\n"+

					"\\chapter{....}\n\n"+

					"\\chapter{Conclusion}\n\n\n"+


					"\\chapter*{References}\n\n"+

					"\\end{document}\n";
		bookContents = "\\documentclass[11pt,a4paper]{book}\n\n"+

					"\\begin{document}\n"+
					"\\title{Book: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n\n"+

					"\\maketitle\n\n"+

					"\\frontmatter\n\n"+

					"\\chapter{Preface}\n"+
					"% ...\n\n"+

					"\\mainmatter\n"+
					"\\chapter{First chapter}\n"+
					"\\section{Section Title 1}\n"+
					"\\section{Section Title 2}\n\n"+

					"\\section{Section Title.....}\n\n"+

					"\\chapter{....}\n\n"+

					"\\chapter{Conclusion}\n\n"+

					"\\chapter*{References}\n\n\n"+


					"\\backmatter\n"+
					"\\chapter{Last note}\n\n"+

					"\\end{document}\n";
		articleContents = "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n"+

					"\\begin{document}\n"+
					"\\title{Article: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n\n"+

					"\\maketitle\n\n"+

					"\\section{Section Title 1}\n\n"+

					"\\section{Section Title 2}\n\n"+

					"\\section{Section Title.....}\n\n"+

					"\\section{Conclusion}\n\n"+

					"\\section*{References}\n\n"+

					"\\end{document}\n";
		letterContents = "\\documentclass{letter}\n"+
					"\\usepackage{hyperref}\n"+
					"\\signature{Sender's Name}\n"+
					"\\address{Sender's address...}\n"+
					"\\begin{document}\n\n"+
	
					"\\begin{letter}{Destination address....}\n"+
					"\\opening{Dear Sir or Madam:}\n\n"+
	
					"I am writing to you .......\n\n\n"+
	
	
					"\\closing{Yours Faithfully,}\n"+
	
					"\\ps\n\n"+
	
					"P.S. text .....\n\n"+
	
					"\\encl{Copyright permission form}\n\n"+
	
					"\\end{letter}\n"+
					"\\end{document}\n";
		emptyContents = "";
	}
	
	public static String loadFromFile(String filename) {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			return fileContents;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Test
	public void testEnactSaveBook() {
		controller.setType("bookTemplate");
		controller.enact("create");
		controller.enact("save");
		String savedContents = loadFromFile("test.tex");
		assertEquals(bookContents, savedContents);
	}
	
	@Test
	public void testEnactSaveReport() {
		controller.setType("reportTemplate");
		controller.enact("create");
		controller.enact("save");
		String savedContents = loadFromFile("test.tex");
		assertEquals(reportContents, savedContents);
	}
	
	@Test
	public void testEnactSaveArticle() {
		controller.setType("articleTemplate");
		controller.enact("create");
		controller.enact("save");
		String savedContents = loadFromFile("test.tex");
		assertEquals(articleContents, savedContents);
	}
	
	@Test
	public void testEnactSaveLetter() {
		controller.setType("letterTemplate");
		controller.enact("create");
		controller.enact("save");
		String savedContents = loadFromFile("test.tex");
		assertEquals(letterContents, savedContents);
	}
	
	@Test
	public void testEnactSaveEmpty() {
		controller.setType("emptyTemplate");
		controller.enact("create");
		controller.enact("save");
		String savedContents = loadFromFile("test.tex");
		assertEquals(emptyContents, savedContents);
	}

}
