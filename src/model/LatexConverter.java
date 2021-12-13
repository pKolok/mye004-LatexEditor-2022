package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LatexConverter {
	private String contents;
	private String contentsHTML;
	private String title, sendersName, sendersAddress, authorHTML, author, date;
	BufferedReader bufferReader;
	
	public LatexConverter(String contentsHTML) {
		this.contentsHTML = contentsHTML;
		contents = "";
		title = "sdfsdfds";
	}

	public String convert() {
		
		extractDate();
		
		bufferReader = new BufferedReader(new StringReader(contentsHTML));
		try {
			String line = null;
			while ( (line = bufferReader.readLine()) != null)
				convertLineToHTML(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;

	}

	private void extractDate() {
		@SuppressWarnings("unused")
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d, yyyy");
		Date tmpDate = new Date();
		date = tmpDate.toString();		
	}

	private void convertLineToHTML(String line) {
		if (canSkipLine(line)) {
			return;
		} else if (line.contains("<html>")){
			contents += "\\begin{document}\n";
		} else if (line.contains("<title>")) {
			title = wordWithinInequalitySigns(line);
			contents += "\n\\title{" + wordWithinInequalitySigns(line) + "}\n";
		} else if (line.contains(title)) {
			contents += "\\maketitle \n";
		} else if (line.contains("<H1>")) {
			contents += "\n\\chapter{" + wordWithinInequalitySigns(line) + "}\n";
		} else if (line.contains("<H2>")) {
			contents += "\n\\section{" + wordWithinInequalitySigns(line) + "}\n";
		} else if (line.contains("<H3>")) {
			contents += "\n\\subsection{" + wordWithinInequalitySigns(line) + "}\n";
		} else if (line.contains("<H4>")) {
			contents += "\n\\subsubsection{" + wordWithinInequalitySigns(line) + "}\n";
		} else if (line.contains("</html>")) {
			contents += "\n\\end{document}\n";
		} else if (line.toLowerCase().contains("<p>")) {
			contents += wordWithinInequalitySigns(line) + "\n";
		} else if (line.contains("<BR>")) {
			contents += "\n";
		} 
		
		
		else if (line.contains("<UL>")) {
			contents += "\\begin{itemize}\n";
		} else if (line.contains("<LI>")) {
			contents += "\\item " + wordAfterInequalSign(line) + "\n";
		} else if (line.contains("</UL>")) {
			contents += "\\end{itemize}\n\n";
		}
		
		else if (line.contains("<OL>")) {
			contents += "\\begin{enumerate}\n";
		} else if (line.contains("</OL>")) {
			contents += "\\end{enumerate}\n\n";
		}
		
		// figure
		else if (line.contains("<figure>")) {
			contents += "\n\\begin{figure}\n";
		} else if (line.contains("<img src")) {
			int i = line.indexOf("\"")+1;
			String filename = line.substring(i, line.indexOf("\"", i));
			String width = line.substring(line.indexOf("WIDTH = ")+8, 
					line.indexOf(", HEIGHT = "));
			String height = line.substring(line.indexOf("HEIGHT = ")+9, 
					line.indexOf(">"));
			contents += "\\includegraphics[width="+width+",height="+height+"]{"+filename+"}\n";
		} else if (line.contains("<figcaption>")) {
			contents += "\\caption{"+ wordWithinInequalitySigns(line) +"}\n";
		} else if (line.contains("</figure>")) {
			contents += "\\end{figure}\n";
		}
		
		// table
		else if (line.contains("<table>")) {
			contents += "\n\\begin{table}\n";
		} else if (line.contains("<caption>")) {
			contents += "\\caption{"+ wordWithinInequalitySigns(line) +"}\n";
			contents += "\\begin{tabular}\n";
			contents += "\\hline\n";
			String nextLine;
			try {
				while ( !(nextLine = bufferReader.readLine()).equals("</table>") ) {
					if (nextLine.contains("<td>")) {
						contents += wordAfterInequalSign(nextLine) + " &";
					} else if (nextLine.contains("</tr>")) {
						contents = contents.substring(0, contents.length() - 2);
						contents += "\\\\\n";
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			contents += "\\hline\n";
			contents += "\\end{tabular}\n";
			contents += "\\end{table}\n";
		}
		
		
		else {
			contents += line + "\n";
		}
		
		
		
		
		
		
	}


	boolean canSkipLine(String line) {
		return line.contains("<head>") 
				|| line.contains("</head>")
				|| line.contains("<body>");
	}

	private String wordWithinInequalitySigns(String line) {
		return line.substring(line.indexOf(">")+1, line.indexOf("</"));
	}

	private String wordAfterInequalSign(String line) {
		return line.substring(line.indexOf(">")+1);
	}



}

