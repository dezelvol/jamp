
public class Book {
	String id;
	String author;
	String title;
	String genre;
	float price;
	String publishDate;
	String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void showMe() {
		System.out.println("id: " + id);
		System.out.println("author: " + author);
		System.out.println("title: "+title);
		System.out.println("price: "+price);
		System.out.println("publish date: "+publishDate);
		System.out.println("description: " + description);
		System.out.println();
	}
	
	
}
