package interpreter.parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	
	public Document getDocument() {
		return document;
	}
	
	public String getText() {
		return document.text().toLowerCase();
	}
	
	public List<String> getListedItems() {
		Elements elements = document.getElementsByTag("li");
		List<String> elementList = new LinkedList<String>();
		for (Element elem : elements) {
			elementList.add(elem.text().toLowerCase());
		}
		return elementList;
	}
	
}
