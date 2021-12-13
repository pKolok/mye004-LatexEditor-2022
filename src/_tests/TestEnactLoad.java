package _tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.LatexEditorController;

public class TestEnactLoad {
	private static LatexEditorController controller;
	private static String reportContents, bookContents, articleContents,
		letterContents, emptyContents;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = LatexEditorController.getInstance();

		assignContents();
		saveAllTemplatesToDisk();
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
	
	private static void saveAllTemplatesToDisk()
	{
		String template[] = {"bookTemplate", "reportTemplate", "articleTemplate"
				, "letterTemplate", "emptyTemplate"};
		String filename[] = {"book.tex", "report.tex", "article.tex", 
				"letter.tex", "empty.tex"};
		
		for (int i = 0; i < 5; ++i) {
			controller.setFilename(filename[i]);
			controller.setType(template[i]);
			controller.enact("create");
			controller.enact("save");
		}
	}
	
	@Test
	public void testEnactLoadBook() {
		controller.setFilename("book.tex");
		controller.enact("load");
		String loadedContents = controller.getCurrentDocument().getContents();
		assertEquals(bookContents, loadedContents);
	}

	@Test
	public void testEnactLoadReport() {
		controller.setFilename("report.tex");
		controller.enact("load");
		String loadedContents = controller.getCurrentDocument().getContents();
		assertEquals(reportContents, loadedContents);
	}
	
	@Test
	public void testEnactLoadArticle() {
		controller.setFilename("article.tex");
		controller.enact("load");
		String loadedContents = controller.getCurrentDocument().getContents();
		assertEquals(articleContents, loadedContents);
	}
	
	@Test
	public void testEnactLoadLetter() {
		controller.setFilename("letter.tex");
		controller.enact("load");
		String loadedContents = controller.getCurrentDocument().getContents();
		assertEquals(letterContents, loadedContents);
	}
	
	@Test
	public void testEnactLoadEmpty() {
		controller.setFilename("empty.tex");
		controller.enact("load");
		String loadedContents = controller.getCurrentDocument().getContents();
		assertEquals(emptyContents, loadedContents);
	}
}
