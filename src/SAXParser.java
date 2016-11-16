import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SAXParser {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
		SAXHandler handler = new SAXHandler();
		saxParser.parse(DOMParser.class.getClassLoader().getResourceAsStream("XML_TASK.xml"), handler);
		handler.catalog.showMe();
	}
	
	static class SAXHandler extends DefaultHandler {
		
		Catalog catalog = new Catalog();
		boolean inner = false;
		Book book;
		String content;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			switch(qName) {
				case "book":
					book = new Book();
					book.setId(attributes.getValue("id"));
					catalog.getBooklist().add(book);
					break;
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch(qName) {
				case "author":
					book.setAuthor(content);
					break;
				case "title":
					if(!inner) {
						catalog.setTitle(content);
						inner = true;
					} else 
						book.setTitle(content);
					break;
				case "genre":
					book.setGenre(content);
					break;
				case "price":
					book.setPrice(Float.parseFloat(content));
					break;
				case "publish_date":
					book.setPublishDate(content);
					break;
				case "description":
					book.setDescription(content);
					break;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			content = String.copyValueOf(ch, start, length);
		}
		
	}
}
