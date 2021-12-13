package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Document {
	private String title = "";
	private String author = "";
	private String authorHTML = "";
	private String date;
	private String copyright;
	private String versionID = "0";
	private String contents = "";
	private String contentsHTML = "";
	private String sendersName = "";
	private String sendersAddress = "";
	private boolean isConvertingTable = false;
//	private String[] latexCommands;
//	private String[] HTMLCommands;
	
	public Document(String author, String date, String copyright, String versionID, String contents) {
		this.author = author;
		this.date = date;
		this.copyright = copyright;
		this.versionID = versionID;
		this.contents = contents;
	}
	
	public Document() {}

	public String getContents() { return contents; }

	public void setContents(String contents) { this.contents = contents; }

	public void save(String filename) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename));
			printWriter.write(contents);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveHTML(String filename) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename));
			printWriter.write(contentsHTML);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	public Document clone() {
		return new Document(author, date, copyright, versionID, contents);
	}

	public void changeVersion() {
		int n = Integer.parseInt(versionID);
		versionID = (n + 1) + "";
	}

	public String getVersionID() { return versionID; }

	public void convertToHTML() {
//		setCommandCorrespondance();
		
		contentsHTML = "";
		
		extractDate();
		
		BufferedReader bufferReader = new BufferedReader(new StringReader(contents));
		try {
			String line = null;
			while ( (line = bufferReader.readLine()) != null)
			{
				convertLineToHTML(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void convertLineToHTML(String line) {
		if (canSkipLine(line)) {
			return;
		} else if (line.contains("\\begin{document}")){
			line = line.replace("\\begin{document}", "<html>");
			contentsHTML += line;
		} else if (line.contains("\\begin{abstract}")){
			line = line.replace("\\begin{abstract}", alignCenter("Abstract"));
			contentsHTML += line + "\n";
			contentsHTML += "<p style=\"text-align:center;\"> ";
		} else if (line.contains("\\end{abstract}")){
			contentsHTML += "</p>" + "\n";
			contentsHTML += "<p style=\"text-align:center;\"> ";
		} else if (line.contains("\\signature{")){
			line = line.replace("\\signature{", "");
			line = line.replace("}", "");
			sendersName = line;
		} else if (line.contains("\\address{")){
			line = line.replace("\\address{", "");
			line = line.replace("}", "");
			sendersAddress = line;
		} else if (line.contains("\\begin{letter}")){
			contentsHTML += "<body>" + "\n";
			contentsHTML += alignRight(sendersAddress);
			contentsHTML += alignRight(date);
			line = line.replace("\\begin{letter}{", "<p>");
			line = line.replace("}", "</p>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\opening{")){		
			line = line.replace("\\opening{", "<p>");
			line = line.replace("}", "</p>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\closing{")){		
			line = line.replace("\\closing{", "");
			line = line.replace("}", "");
			contentsHTML += alignRight(line);
			contentsHTML += alignRight(sendersName);
		} else if (line.contains("\\encl{")){	
			line = line.replace("\\encl{", "");
			line = line.replace("}", "");
			contentsHTML += "<p> encl: " + line + "</p>\n";
		} else if (line.contains("\\author{")){
			extractAuthors(line);
			authorHTML = alignCenter(author);
		} else if (line.contains("\\title")){
			line = line.replace("\\title{", "");
			line = line.replace("}", "");
			title = line;
		} else if (line.contains("\\maketitle")){
			// title
			line = line.replace("\\maketitle", "<head>\n");
			line += "<title> " + title + " </title>\n";
			line += "</head>\n";
			line += "<body>\n";
			line += alignCenter("<B>" + title + "</B>");
			contentsHTML += line + "\n";
			contentsHTML += authorHTML + "\n";
			contentsHTML += alignCenter(date);
		} else if (line.contains("\\chapter{")){
			line = line.replace("\\chapter{", "<H1> ");
			line = line.replace("}", " </H1>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\chapter*{")){
			line = line.replace("\\chapter*{", "<H1> ");
			line = line.replace("}", " </H1>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\section{")){
			line = line.replace("\\section{", "<H2> ");
			line = line.replace("}", " </H2>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\section*{")){
			line = line.replace("\\section*{", "<H2> ");
			line = line.replace("}", " </H2>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\subsection{")) {
			line = line.replace("\\subsection{", "<H3> ");
			line = line.replace("}", " </H3>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\subsubsection{")) {
			line = line.replace("\\subsubsection{", "<H4> ");
			line = line.replace("}", " </H4>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\end{document}")) {
			line = line.replace("\\end{document}", "</body></html>");
			contentsHTML += line + "\n";

		// Itemise
		} else if (line.contains("\\begin{itemize}")) {
			line = line.replace("\\begin{itemize}", "<UL>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\item")) {
			line = line.replace("\\item", "<LI>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\end{itemize}")) {
			line = line.replace("\\end{itemize}", "</UL>");
			contentsHTML += line + "\n";
			
		// Enumerate
		} else if (line.contains("\\begin{enumerate}")) {
			line = line.replace("\\begin{enumerate}", "<OL>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\item")) {
			line = line.replace("\\item", "<LI>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\end{enumerate}")) {
			line = line.replace("\\end{enumerate}", "</OL>");
			contentsHTML += line + "\n";
			
		// table
		} else if (line.contains("\\begin{table}")) {
			line = line.replace("\\begin{table}", "<table>");
			contentsHTML += line + "\n";
			isConvertingTable = true;
		} else if (line.contains("\\caption{") && isConvertingTable) {
			line = line.replace("\\caption{", "<caption>");
			contentsHTML += line.substring(0, line.indexOf("}")) + "</caption>\n";
		} else if (line.contains("\\end{table}")) {
			line = line.replace("\\end{table}{", "</table>");
			isConvertingTable = false;
			
		// figure
		} else if (line.contains("\\begin{figure}")) {
			line = line.replace("\\begin{figure}", "<figure>");
			contentsHTML += line + "\n";
		} else if (line.contains("\\includegraphics[")) {
			String src = line.substring(line.indexOf("{")+1,line.indexOf("}"));
			String width = line.substring(line.indexOf("width=")+6,line.indexOf(",height"));
			String height = line.substring(line.indexOf("height=")+7, line.indexOf("]"));
			contentsHTML += "<img src=\"" + src + "\" WIDTH = " + width 
					+ ", HEIGHT = " + height + "\">" + "\n";
		} else if (line.contains("\\caption{") && !isConvertingTable) {
			line = line.replace("\\caption{", "<figcaption>");
			contentsHTML += line.substring(0, line.indexOf("}")) + "</figcaption>\n";
		} else if (line.contains("\\end{figure}")) {
			line = line.replace("\\end{figure}{", "</figure>");
			isConvertingTable = false;
			
		} else {
			if (isConvertingTable) {
				contentsHTML += "<tr>\n";
				line = line.replace("\\\\", "");
				line = line.replace("&", "\n<td>");
				contentsHTML += "<td>" + line + "\n";
				contentsHTML += "</tr>\n";
			} else 
				contentsHTML += line + "\n";
		}
		
	}
	
//	void setCommandCorrespondance(){
//		latexCommands = new String[10];
//		HTMLCommands = new String[10];
//		
//		latexCommands[0] = "\\chapter{";
//		HTMLCommands[0] = " </H1>";
//		latexCommands[1] = "\\chapter*{";
//		HTMLCommands[1] = " </H1>";
//		latexCommands[2] = "\\section{";
//		HTMLCommands[2] = " </H2>";
//		latexCommands[3] = "\\section*{";
//		HTMLCommands[3] = " </H2>";
//		latexCommands[3] = "\\subsection{";
//		HTMLCommands[3] = " </H3>";
//		latexCommands[3] = "\\subsubsection{";
//		HTMLCommands[3] = " </H4>";
//	}
	
	String alignCenter(String text) {
		return "<p style=\"text-align:center;\"> " + text + "</p>" + "\n";
	}
	
	String alignRight(String text) {
		return "<p style=\"text-align:right;\"> " + text + "</p>" + "\n";
	}

	void extractAuthors(String line) {
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
			author += authors[i] + "\t";
		}		
	}
	
	void extractDate() {
		@SuppressWarnings("unused")
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d, yyyy");
		Date tmpDate = new Date();
		date = tmpDate.toString();
	}
	
	boolean canSkipLine(String line) {
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
				|| line.contains("\\end{tabular}");
	}
}