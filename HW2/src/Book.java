// JL3RZ John Li HW 2
public class Book {
	
	private String title;
	private String author;
		
	// Constructor that takes two arguments
	public Book(String constructTitle, String constructAuthor){
		title = constructTitle;
		author = constructAuthor;
	}
		
	// Getters for author and title

	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
		
	// Checks to see if the title and author of two book objects are the same
	public boolean equals(Object o) {
		if (o instanceof Book) {
		Book otherBook = (Book) o;
		return (this.title == otherBook.title) && (this.author == otherBook.author);
		}
		else {
			return false;
		}
	}
		
	// Returns a string that contains the values of title and author
	public String toString(){
		return title + " by " + author;
	}
}
