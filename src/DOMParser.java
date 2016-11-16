import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.print.attribute.standard.PresentationDirection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = DOMParser.class.getClassLoader().getResourceAsStream("XML_TASK.xml");
		Document document = builder.parse(is);
		Element documentElement = document.getDocumentElement();
		Node item = documentElement;
		NodeList nodeList = item.getChildNodes();
		
		Catalog catalog = new Catalog();
		
		catalog.setTitle(((Element)((Element)nodeList).getElementsByTagName("title").item(0)).getTextContent());
		NodeList booklist = ((Element)((Element)nodeList).getElementsByTagName("booksList").item(0)).getElementsByTagName("book");
		
		for(int i = 0; i < booklist.getLength();i++) {
			if(!(booklist.item(i) instanceof Element)) {
				continue;
			}
			Book book = new Book();
			Element bookNode = (Element) booklist.item(i);
			book.setId(bookNode.getAttribute("id"));
			book.setAuthor(bookNode.getElementsByTagName("author").item(0).getTextContent());
			book.setTitle(bookNode.getElementsByTagName("title").item(0).getTextContent());
			book.setGenre(bookNode.getElementsByTagName("genre").item(0).getTextContent());
			book.setPublishDate(bookNode.getElementsByTagName("publish_date").item(0).getTextContent());
			book.setDescription(bookNode.getElementsByTagName("description").item(0).getTextContent());
			book.setPrice(Float.parseFloat(bookNode.getElementsByTagName("price").item(0).getTextContent()));
			
			catalog.getBooklist().add(book);
		}
		catalog.showMe();
	}
}
