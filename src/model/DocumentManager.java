package model;

import java.util.HashMap;

public class DocumentManager {
	private HashMap<String, Document> templates;
	private HashMap<String, String> contents;
	
	public DocumentManager() {
		
		contents = new HashMap<String, String>();
		loadContents();
		
		templates = new HashMap<String, Document>();		
		createDocumentTemplates();
		
		// TODO - Remove
//		Document document = new Document();
//		document.setContents(getContents("reportTemplate"));
//		templates.put("reportTemplate", document);
//		
//		document = new Document();
//		document.setContents(getContents("bookTemplate"));
//		templates.put("bookTemplate", document);
//		
//		document = new Document();
//		document.setContents(getContents("articleTemplate"));
//		templates.put("articleTemplate", document);
//		
//		document = new Document();
//		document.setContents(getContents("letterTemplate"));
//		templates.put("letterTemplate", document);
	}
	
	public Document createDocument(String type) 
	{
		return templates.get(type).clone();
	}
	
	
	private void loadContents() 
	{
		contents.put("articleTemplate", 
				"\\documentclass[11pt,twocolumn,a4paper]{article}\n\n"+

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

				"\\end{document}\n");
	
	contents.put("bookTemplate", 
				"\\documentclass[11pt,a4paper]{book}\n\n"+

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

				"\\end{document}\n");
	
	contents.put("letterTemplate",
				"\\documentclass{letter}\n"+
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
				"\\end{document}\n");
	
	contents.put("reportTemplate", 
				"\\documentclass[11pt,a4paper]{report}\n\n"+

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

				"\\end{document}\n");
	}
	
	private void createDocumentTemplates()
	{
		Document document;
		for (String key : contents.keySet()) {
			document = new Document();
			document.setContents(contents.get(key));
			templates.put(key, document);
		}
		
		document = new Document();
		templates.put("emptyTemplate", document);
	}
	
	
//	public String getContents(String type) {
//		if(type.equals("articleTemplate")){
//			return "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n"+
//
//					"\\begin{document}\n"+
//					"\\title{Article: How to Structure a LaTeX Document}\n"+
//					"\\author{Author1 \\and Author2 \\and ...}\n"+
//					"\\date{\\today}\n\n"+
//
//					"\\maketitle\n\n"+
//
//					"\\section{Section Title 1}\n\n"+
//
//					"\\section{Section Title 2}\n\n"+
//
//					"\\section{Section Title.....}\n\n"+
//
//					"\\section{Conclusion}\n\n"+
//
//					"\\section*{References}\n\n"+
//
//					"\\end{document}\n";
//		}
//		else if(type.equals("bookTemplate")) {
//			return "\\documentclass[11pt,a4paper]{book}\n\n"+
//
//					"\\begin{document}\n"+
//					"\\title{Book: How to Structure a LaTeX Document}\n"+
//					"\\author{Author1 \\and Author2 \\and ...}\n"+
//					"\\date{\\today}\n\n"+
//
//					"\\maketitle\n\n"+
//
//					"\\frontmatter\n\n"+
//
//					"\\chapter{Preface}\n"+
//					"% ...\n\n"+
//
//					"\\mainmatter\n"+
//					"\\chapter{First chapter}\n"+
//					"\\section{Section Title 1}\n"+
//					"\\section{Section Title 2}\n\n"+
//
//					"\\section{Section Title.....}\n\n"+
//
//					"\\chapter{....}\n\n"+
//
//					"\\chapter{Conclusion}\n\n"+
//
//					"\\chapter*{References}\n\n\n"+
//
//
//					"\\backmatter\n"+
//					"\\chapter{Last note}\n\n"+
//
//					"\\end{document}\n";
//		}
//		else if(type.equals("letterTemplate")) {
//			return "\\documentclass{letter}\n"+
//					"\\usepackage{hyperref}\n"+
//					"\\signature{Sender's Name}\n"+
//					"\\address{Sender's address...}\n"+
//					"\\begin{document}\n\n"+
//
//					"\\begin{letter}{Destination address....}\n"+
//					"\\opening{Dear Sir or Madam:}\n\n"+
//
//					"I am writing to you .......\n\n\n"+
//
//
//					"\\closing{Yours Faithfully,}\n"+
//
//					"\\ps\n\n"+
//
//					"P.S. text .....\n\n"+
//
//					"\\encl{Copyright permission form}\n\n"+
//
//					"\\end{letter}\n"+
//					"\\end{document}\n";
//		}
//		else {
//			return "\\documentclass[11pt,a4paper]{report}\n\n"+
//
//					"\\begin{document}\n"+
//					"\\title{Report Template: How to Structure a LaTeX Document}\n"+
//					"\\author{Author1 \\and Author2 \\and ...}\n"+
//					"\\date{\\today}\n"+
//					"\\maketitle\n\n"+
//
//					"\\begin{abstract}\n"+
//					"Your abstract goes here...\n"+
//					"...\n"+
//					"\\end{abstract}\n\n"+
//
//					"\\chapter{Introduction}\n"+
//					"\\section{Section Title 1}\n"+
//					"\\section{Section Title 2}\n"+
//					"\\section{Section Title.....}\n\n"+
//
//					"\\chapter{....}\n\n"+
//
//					"\\chapter{Conclusion}\n\n\n"+
//
//
//					"\\chapter*{References}\n\n"+
//
//					"\\end{document}\n";
//		}
//	}
	
}
