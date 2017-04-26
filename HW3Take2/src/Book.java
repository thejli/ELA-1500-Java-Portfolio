// John Li jl3rz HW3

public class Book {
	
	// Book class fields
	private String title;
	private String author;
	private String dueDate;
	private boolean checkedOut;
	private int bookId;
	private double bookValue;
	
	// Book class constructor 
	public Book(String title, String author, int bookId, double bookValue){
		this.title = title;
		this.author = author;
		this.bookId = bookId;
		this.bookValue = bookValue;
	}
	
	// Book class getters 
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getDueDate() {
		return this.dueDate;
	}
	
	public boolean isCheckedOut() {
		return this.checkedOut;
	}
	
	public int getBookId() {
		return this.bookId;
	}
	
	public double getBookValue() {
		return this.bookValue;
	}
	
	// Book class setters
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	public void setBookValue(double bookValue) {
		this.bookValue = bookValue;
	}
	
	// Equals method compares bookIds between two Book objects
	public boolean equals(Object o) {
		if (o instanceof Book) {
			Book bookInstance = (Book) o;
			return (this.bookId == bookInstance.bookId);
		}
		else {
			return false;
		} 
	}
	
	// toString method puts stuff into a nice format to read 
	public String toString(){
		return "The title is: " + title + "."
				+ "The author is: " + author + "."
				+ "The due date is: " + dueDate + "."
				+ "Book checked out:  " + checkedOut + "."
				+ "The book ID is: " + bookId + "."
				+ "The book value is: " + bookValue + ".";
	}
}
