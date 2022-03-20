package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlExporter {
	private String contents;
	private String contentsHTML;
	private String sendersName, sendersAddress, title, authorHTML, author, date;
	private boolean isConvertingTable;
	
	public HtmlExporter(String pContents) {
		this.contents = pContents;
		contentsHTML = "";
		isConvertingTable = false;
	}
	
	public String convert() {
		
		extractDate();
		
		BufferedReader bufferReader = new BufferedReader(new StringReader(contents));
		try {
			String line = null;
			while ( (line = bufferReader.readLine()) != null)
				convertLineToHTML(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentsHTML;
	}
	
	
	private void convertLineToHTML(String line) {
		if (canSkipLine(line)) {
			return;
		} else if (line.contains("\\begin{document}")){
			contentsHTML += "<html>\n";
		} else if (line.contains("\\begin{abstract}")){
			contentsHTML += newLines(2) + alignCenter(bold(font("Abstract",4)));
		} else if (line.contains("\\signature{")){
			sendersName = wordWithinCurlyBrackets(line);
		} else if (line.contains("\\address{")){
			sendersAddress = wordWithinCurlyBrackets(line);
		} else if (line.contains("\\begin{letter}")){
			contentsHTML += "\n<body>" + "\n" + newLines(4);
			contentsHTML += "\n" + alignRight(sendersAddress);
			contentsHTML += "\n" + alignRight(date);
			line = line.replace("\\begin{letter}", "<p>");
			contentsHTML += "\n" + paragraph(wordWithinCurlyBrackets(line));
		} else if (line.contains("\\opening{")){		
			contentsHTML += "\n" + paragraph(wordWithinCurlyBrackets(line));
		} else if (line.contains("\\closing{")){		
			contentsHTML += "\n" + alignRight(wordWithinCurlyBrackets(line));
			contentsHTML += "\n" + alignRight(sendersName) + "\n";
		} else if (line.contains("\\encl{")){	
			contentsHTML += "\n" + paragraph("encl: " + wordWithinCurlyBrackets(line));
		} else if (line.contains("\\author{")){
			extractAuthors(line);
			authorHTML = paragraph(alignCenter(author));
		} else if (line.contains("\\title")){
			title = wordWithinCurlyBrackets(line);
		} else if (line.contains("\\maketitle")){
			contentsHTML +="<head>\n";
			contentsHTML += surroundWith(bold(alignCenter(title)),"title");
			contentsHTML += "</head>\n";
			contentsHTML += "\n<body>\n";
			contentsHTML += paragraph(bold(alignCenter(font(title, 6))));
			contentsHTML += newLines(2) + authorHTML;
//			contentsHTML += paragraph(alignCenter(date));
		} else if (line.contains("\\chapter")){
			contentsHTML += "\n" + surroundWith(wordWithinCurlyBrackets(line),"H1");
		} else if (line.contains("\\section")){
			contentsHTML += "\n" + surroundWith(wordWithinCurlyBrackets(line),"H2");
		} else if (line.contains("\\subsection{")) {
			contentsHTML += "\n" + surroundWith(wordWithinCurlyBrackets(line),"H3");
		} else if (line.contains("\\subsubsection{")) {
			contentsHTML += "\n" + surroundWith(wordWithinCurlyBrackets(line),"H4");
		} else if (line.contains("\\end{document}")) {
			contentsHTML += "\n" + "</body></html>" + "\n";

		// Itemise
		} else if (line.contains("\\begin{itemize}")) {
			contentsHTML += "\n<UL>" + "\n";
		} else if (line.contains("\\item")) {
			contentsHTML += "<LI>" + line.substring(line.indexOf("\\item")+5) + "\n";
		} else if (line.contains("\\end{itemize}")) {
			contentsHTML += "</UL>" + "\n";
			
		// Enumerate
		} else if (line.contains("\\begin{enumerate}")) {
			contentsHTML += "\n<OL>" + "\n";
		} else if (line.contains("\\end{enumerate}")) {
			contentsHTML += "</OL>" + "\n";
			
		// table
		} else if (line.contains("\\begin{table}")) {
			contentsHTML += "\n<table>" + "\n";
			isConvertingTable = true;
		} else if (line.contains("\\caption{") && isConvertingTable) {
			contentsHTML += surroundWith(wordWithinCurlyBrackets(line),"caption");
		} else if (line.contains("\\end{table}")) {
			contentsHTML += "</table>\n";
			isConvertingTable = false;
			
		// figure
		} else if (line.contains("\\begin{figure}")) {
			contentsHTML += "\n<figure>" + "\n";
		} else if (line.contains("\\includegraphics[")) {
			String src = line.substring(line.indexOf("{")+1,line.indexOf("}"));
			String width = line.substring(line.indexOf("width=")+6,line.indexOf(",height"));
			String height = line.substring(line.indexOf("height=")+7, line.indexOf("]"));
			contentsHTML += "<img src=\"" + src + "\" WIDTH = " + width 
					+ ", HEIGHT = " + height + ">" + "\n";
		} else if (line.contains("\\caption{") && !isConvertingTable) {
			contentsHTML += surroundWith(wordWithinCurlyBrackets(line),"figcaption");
		} else if (line.contains("\\end{figure}")) {
			contentsHTML += "</figure>" + "\n";
			isConvertingTable = false;
			
		} else {
			if (isConvertingTable) {
				contentsHTML += "<tr>\n";
				line = line.replace("\\\\", "");
				line = line.replace("&", "\n<td>");
				contentsHTML += "<td> " + line + "\n";
				contentsHTML += "</tr>\n";
			} else 
				if (!line.equals(""))
					contentsHTML += paragraph(line);
		}
		
	}
	
	private void extractDate() {
		@SuppressWarnings("unused")
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d, yyyy");
		Date tmpDate = new Date();
		date = tmpDate.toString();
	}
	
	private boolean canSkipLine(String line) {
		return line.contains("\\documentclass") 
				|| line.contains("\\usepackage")
				|| line.contains("\\frontmatter")
				|| line.contains("\\mainmatter")
				|| line.contains("\\backmatter")
				|| line.contains("\\date{\\today}")
				|| line.contains("\\ps") 
				|| line.contains("\\end{letter}")
				|| line.contains("\\begin{tabular}")
				|| line.contains("\\hline")
				|| line.contains("\\end{tabular}")
				|| line.contains("\\end{abstract}");
	}
	
	private String alignCenter(String text) {
		return "<p style=\"text-align:center;\"> " + text + "</p>";
	}
	
	private String alignRight(String text) {
		return "<p style=\"text-align:right;\"> " + text + "</p>";
	}
	
	private String bold(String text) {
		return "<B> " + text + " </B>";
	}
	
	private String newLines(int noLines) {
		String output = "";
		for (int i = 0; i < noLines; ++i)
			output += "<BR>\n";
		return output;
	}

	private void extractAuthors(String line) {
		author = "";
		String[] authors = new String[0];
		int noAuthors = 1;
		String tmpLine = line;
		while(tmpLine.contains("\\and")) {
			tmpLine = tmpLine.replaceFirst("\\\\and", "");
			noAuthors++;
		}
		authors = new String[noAuthors];
		line = line.replace("\\author{", "");
		authors[0] = line.substring(0, line.indexOf("\\"));
		for (int i = 1; i < noAuthors; ++i){
			line = line.replace(authors[i-1], "");
			line = line.replaceFirst("\\\\and ", "");
			if (i == noAuthors-1)	// last author
				authors[i] = line.substring(0, line.indexOf("}"));
			else
				authors[i] = line.substring(0, line.indexOf("\\"));
		}
		for (int i = 0; i < noAuthors; ++i) {
			author += authors[i] + "&emsp;";
		}		
	}
	
	private String wordWithinCurlyBrackets(String line) {
		return line.substring(line.indexOf("{")+1, line.indexOf("}"));
	}
	
	private String paragraph(String line) {
		return "<P> " + line + " </P>\n";
	}
	
	private String font(String line, int n) {
		return "<font size="+ n + ">" + line + "</font>";
	}
	
	private String surroundWith(String line, String syntax) {
		return "<"+syntax+"> " + line + " </"+syntax+">\n";
	}
}
