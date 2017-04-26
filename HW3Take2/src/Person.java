// John Li jl3rz HW3
import java.util.ArrayList;

public class Person {
	
	// Person class fields
	private String name;
	private int id;
	private ArrayList<Book> checkedOut;
	private String address;
	private int libraryCardNum;
	
	// Person class constructor
	public Person(String name, String address, int libraryCardNum) {
		this.name = name;
		this.address = address;
		this.libraryCardNum = libraryCardNum; 
		this.checkedOut = new ArrayList<Book>();
	}
	
	// Person class getters
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Book> getCheckedOut() {
		return checkedOut;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getLibraryCardNum() {
		return libraryCardNum;
	}
	
	// Person class setters 
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	// addBook method adds a book to the person's list of books
	public boolean addBook(Book b) {
		if (!(this.checkedOut.contains(b))){
			this.checkedOut.add(b);
			return true;
		}
		else return false; 
	}
}
