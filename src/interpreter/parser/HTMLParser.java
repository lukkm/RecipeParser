package interpreter.parser;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {

	private File file;
	private Document document;
	
	public HTMLParser(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public HTMLParser(File file) throws IOException {
		this.file = file;
		document = Jsoup.parse(file, "UTF-8");
	}
	
	public String getText() {
		return document.text();
	}
	
}
