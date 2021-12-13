package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Document {
	
	private String author = "";
	private String date;
	private String copyright;
	private String versionID = "0";
	private String contents = "";
	private String contentsHTML = "";
	
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
	
	public void setHtmlContents(String contents) { this.contentsHTML = contents; }

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
		contentsHTML = "";
		HtmlExporter htmlExporter = new HtmlExporter(contents);
		contentsHTML = htmlExporter.convert();
	}
	
	public void convertToLateX() {
		LatexConverter latexConverter = new LatexConverter(contentsHTML);
		contents = latexConverter.convert();
	}


}