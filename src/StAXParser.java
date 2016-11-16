import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXParser {
	public static void main(String[] args) throws XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(DOMParser.class.getClassLoader().getResourceAsStream("XML_TASK.xml"));
		
		String content = null;
		Book book = null;
		Catalog catalog = new Catalog();
		boolean inner = false;
		
		while(reader.hasNext()) {
			int event = reader.next();
			
			switch(event) {
				case XMLStreamConstants.START_ELEMENT:
					if(reader.getLocalName().equals("book")) {
						book = new Book();
						book.setId(reader.getAttributeValue(0));
						catalog.getBooklist().add(book);
					}
					break;
			
				case XMLStreamConstants.CHARACTERS:
					content = reader.getText().trim();
					break;
				
				case XMLStreamConstants.END_ELEMENT:
					switch(reader.getLocalName()) {
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
					break;
			}
		}
		catalog.showMe();
	}
}
