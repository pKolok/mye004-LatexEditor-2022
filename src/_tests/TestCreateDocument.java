package _tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Document;
import model.DocumentManager;

public class TestCreateDocument {
	private static DocumentManager documentManager;
	private static String reportContents, bookContents, articleContents,
							letterContents, emptyContents;

	@BeforeClass	// runs once, before any other method of the test class
	public static void setUpBeforeClass() throws Exception {
		assignContents();
		documentManager = new DocumentManager();

	}

	@AfterClass		//  runs after all the tests have been run.
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

	
	//////////////////////////////////////////////////////////
	//////////////////////  tests  ///////////////////////////
	//////////////////////////////////////////////////////////
	
	@Test
	public void testCreateDocumentReportType() {
		//fail("Not yet implemented");
		String type = "reportTemplate";
		Document document = documentManager.createDocument(type);
		assertEquals(reportContents, document.getContents());
	}
	
	@Test
	public void testCreateDocumentBookType() {
		String type = "bookTemplate";
		Document document = documentManager.createDocument(type);
		assertEquals(bookContents, document.getContents());
	}
	
	@Test
	public void testCreateDocumentArticleType() {
		String type = "articleTemplate";
		Document document = documentManager.createDocument(type);
		assertEquals(articleContents, document.getContents());
	}
	
	@Test
	public void testCreateDocumentLetterType() {
		String type = "letterTemplate";
		Document document = documentManager.createDocument(type);
		assertEquals(letterContents, document.getContents());
	}
	
	@Test
	public void testCreateDocumentEmptyType() {
		String type = "emptyTemplate";
		Document document = documentManager.createDocument(type);
		assertEquals(emptyContents, document.getContents());
	}
	
	@Test
	public void testCreateDocumentInvalideType() {
		String type = "";
		try {
			Document document = documentManager.createDocument(type);
			assert(false);
		} catch (NullPointerException e){
			assert(true);
		}
	}	

}
