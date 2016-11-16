import java.util.ArrayList;
import java.util.List;

public class Catalog {
	String title;
	List<Book> booklist = new ArrayList<>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Book> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}
	
	public void showMe() {
		System.out.println("");
		System.out.println(title);
		System.out.println("");
		System.out.println("books amount: " + this.getBooklist().size());
		for (int i = 0; i < this.getBooklist().size(); i++) {
			System.out.println("Book " + i + 1);
			this.getBooklist().get(i).showMe();
		}
	}
}
