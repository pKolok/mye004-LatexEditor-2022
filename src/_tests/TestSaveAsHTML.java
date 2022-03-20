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

public class TestSaveAsHTML {
	private static LatexEditorController latexEditorController;
	private static String reportLatexContents, bookLatexContents, 
	articleLatexContents, letterLatexContents;
	private static String reportHTMLContents, bookHTMLContents, 
	articleHTMLContents, letterHTMLContents;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		latexEditorController = LatexEditorController.getInstance();
		
		
		assignLatexContents();
		assignHTMLContents();
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

	
	private static void assignLatexContents() {
		reportLatexContents = "\\documentclass[11pt,a4paper]{report}\n\n"+

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
		bookLatexContents = "\\documentclass[11pt,a4paper]{book}\n\n"+

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
		articleLatexContents = "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n"+

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
		letterLatexContents = "\\documentclass{letter}\n"+
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
	}
	
	private static void assignHTMLContents() {
		reportHTMLContents = "<html>\r\n"
				+ "<head>\r\n"
				+ "<title> <B> <p style=\"text-align:center;\"> Report Template: How to Structure a LaTeX Document</p> </B> </title>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<P> <B> <p style=\"text-align:center;\"> <font size=6>Report Template: How to Structure a LaTeX Document</font></p> </B> </P>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<P> <p style=\"text-align:center;\"> Author1 &emsp;Author2 &emsp;...&emsp;</p> </P>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<p style=\"text-align:center;\"> <B> <font size=4>Abstract</font> </B></p><P> Your abstract goes here... </P>\r\n"
				+ "<P> ... </P>\r\n"
				+ "\r\n"
				+ "<H1> Introduction </H1>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 1 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 2 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title..... </H2>\r\n"
				+ "\r\n"
				+ "<H1> .... </H1>\r\n"
				+ "\r\n"
				+ "<H1> Conclusion </H1>\r\n"
				+ "\r\n"
				+ "<H1> References </H1>\r\n"
				+ "\r\n"
				+ "</body></html>\r\n"
				+ "";
		bookHTMLContents = "<html>\r\n"
				+ "<head>\r\n"
				+ "<title> <B> <p style=\"text-align:center;\"> Book: How to Structure a LaTeX Document</p> </B> </title>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<P> <B> <p style=\"text-align:center;\"> <font size=6>Book: How to Structure a LaTeX Document</font></p> </B> </P>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<P> <p style=\"text-align:center;\"> Author1 &emsp;Author2 &emsp;...&emsp;</p> </P>\r\n"
				+ "\r\n"
				+ "<H1> Preface </H1>\r\n"
				+ "<P> % ... </P>\r\n"
				+ "\r\n"
				+ "<H1> First chapter </H1>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 1 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 2 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title..... </H2>\r\n"
				+ "\r\n"
				+ "<H1> .... </H1>\r\n"
				+ "\r\n"
				+ "<H1> Conclusion </H1>\r\n"
				+ "\r\n"
				+ "<H1> References </H1>\r\n"
				+ "\r\n"
				+ "<H1> Last note </H1>\r\n"
				+ "\r\n"
				+ "</body></html>\r\n"
				+ "";
		articleHTMLContents = "<html>\r\n"
				+ "<head>\r\n"
				+ "<title> <B> <p style=\"text-align:center;\"> Article: How to Structure a LaTeX Document</p> </B> </title>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<P> <B> <p style=\"text-align:center;\"> <font size=6>Article: How to Structure a LaTeX Document</font></p> </B> </P>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<P> <p style=\"text-align:center;\"> Author1 &emsp;Author2 &emsp;...&emsp;</p> </P>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 1 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title 2 </H2>\r\n"
				+ "\r\n"
				+ "<H2> Section Title..... </H2>\r\n"
				+ "\r\n"
				+ "<H2> Conclusion </H2>\r\n"
				+ "\r\n"
				+ "<H2> References </H2>\r\n"
				+ "\r\n"
				+ "</body></html>\r\n"
				+ "";
		letterHTMLContents = "<html>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "\r\n"
				+ "<p style=\"text-align:right;\"> Sender's address...</p>\r\n"
				+ "<p style=\"text-align:right;\"> Tue Dec 14 19:14:52 EET 2021</p>\r\n"
				+ "<P> Destination address.... </P>\r\n"
				+ "\r\n"
				+ "<P> Dear Sir or Madam: </P>\r\n"
				+ "<P> I am writing to you ....... </P>\r\n"
				+ "\r\n"
				+ "<p style=\"text-align:right;\"> Yours Faithfully,</p>\r\n"
				+ "<p style=\"text-align:right;\"> Sender's Name</p>\r\n"
				+ "<P> P.S. text ..... </P>\r\n"
				+ "\r\n"
				+ "<P> encl: Copyright permission form </P>\r\n"
				+ "\r\n"
				+ "</body></html>\r\n"
				+ "";
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
	public void testSaveAsHTMLBook() {
		latexEditorController.setType("bookTemplate");
		latexEditorController.enact("create");
		latexEditorController.setFilename("Book.html");
		latexEditorController.enact("saveAsHTML");
		String convertedBookHTML = loadFromFile("Book.html");
		System.out.println(bookHTMLContents);
		System.out.println(convertedBookHTML);
		assertEquals(bookHTMLContents, convertedBookHTML);
	}

}
