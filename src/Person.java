// JL3RZ John Li HW 2
import java.util.ArrayList;

public class Person {
	
	public static void main(String[] args) {
		
		Person rafael = new Person("Rafael", 17);
		Person alesky = new Person("Alesky", 69); 
		Person mamaMia = new Person("MamaMiaPizzaeria", 42);
		Person why = new Person("MoneyMakerXXX420", 21);

		Book bookieTookie = new Book("A Book About Jack and Jill Going Down the Hill To Sip Some Periwinkle Water", "Jack");
		Book bookieTookie2 = new Book("A Book About Love", "Romeo");
		Book bookieTookie3 = new Book("A Book About Sadness", "Juliet");
		Book bookieTookie4 = new Book("A Beginner's Guide To Losing Deloitte Case Competition", "Dragon Consulting");
		Book aifSucks = new Book("Reasons why investment funds at UVA are bad", "me");
		
		// Testing person class getter
		System.out.println(rafael.getName());
		System.out.println(rafael.getID());
		System.out.println(rafael.getRead());
	
		// Testing book class getter
		System.out.println(bookieTookie.getAuthor());
		System.out.println(bookieTookie.getTitle());
		
		// Testing person class setter 
		System.out.println(alesky.name);
		alesky.setName("Killy Billy");
		System.out.println(alesky.name);
		
		// Testing add book
		System.out.println(mamaMia.getRead());
		mamaMia.addBook(bookieTookie4);
		System.out.println(mamaMia.getRead());
		
		// Testing forget book
		System.out.println(why.getRead());
		why.addBook(aifSucks); 
		why.addBook(bookieTookie2);
		System.out.println(why.getRead());
		why.forgetBook(bookieTookie2);
		System.out.println(why.getRead());
		
		// Testing common book 
		why.addBook(bookieTookie3);
		mamaMia.addBook(bookieTookie3);
		System.out.println(commonBooks(why, mamaMia));
	}
	
	// Fields
	private String name;
	private int id;
	private ArrayList<Book> read;
	
	// Constructor that takes two arguments 
	public Person(String personName, int idNumber) {
		name = personName;
		id = idNumber;
		read = new ArrayList<Book>(); 
	}
	
	// Getters
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Book> getRead() {
		return read;
	}
	
	// Setter
	public void setName(String newName) {
		this.name = newName;
	}
	
	// Adds book to ArrayList of books read, if its not already present
	public boolean addBook(Book b) {
		if (read.contains(b)) {
			return false;
		}else {
			read.add(b);
			return true;
		}
	}
	
	// Returns true if a book is in the ArrayList of books read
	public boolean hasRead(Book b) {
		if(read.contains(b)) {
			return true;
		}else return false;
	}
	
	// Removes book from ArrayList of books read
	public boolean forgetBook(Book b) {
		if (read.contains(b)) {
			read.remove(b);
			return true;
		}else return false;
	}
	
	// Tells us how many books a person has read by returning the size of the read ArrayList
	public int numBooksRead() {
		return read.size();
	}
	// Check name/id same or nah 
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p1 = (Person) o;
			return (this.id == p1.id);
		}else return false;
	}
	// Changes to a string 
	public String toString() {
		return name + "'s" + " ID is: " + id + " and they have read: " + read;
	}
	
	public static ArrayList<Book> commonBooks(Person a, Person b) {
		ArrayList<Book> sameBooks  = new ArrayList<Book>();
		for (int i = 0; i < b.getRead().size(); i++) {
			if (a.getRead().contains(b.getRead().get(i))) {
				sameBooks.add(b.getRead().get(i));
			}
		} return sameBooks;
	}
	
	// Finding similarity between books that two people read
	public static double similarity(Person a, Person b) {
		if (a.read.size() < b.read.size()) {
			return ((double) commonBooks(a,b).size() / a.read.size());
		} 
		else if ((a.read.size() == 0) || (b.read.size() == 0)) {
			return 0.0;
		}
		else {
			return ((double) commonBooks(a,b).size() / (double) b.read.size());
		}
	}
	
	
	
}