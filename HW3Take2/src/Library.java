// John Li jl3rz HW3

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
public class Library {

	// Library class fields
	private ArrayList<Book> libraryBooks;
	private ArrayList<Person> patrons;
	private String name;
	private int numBooks;
	private int numPeople;
	private String currentDate;
	
	// Library class constructor
	public Library(String name) {
		this.name = name;
		this.numBooks = 0;
		this.numPeople = 0;
		this.libraryBooks = new ArrayList<Book>();
		this.patrons = new ArrayList<Person>();
	}
	
    // Library class getters 
	public int getNumBooks() 
	{ int count = 0;
		for (int i = 0; i < libraryBooks.size(); i++){
			if ((libraryBooks.get(i).isCheckedOut() == false)){
				count++; 
			}
		}
		return count;
	}

	public int getNumPeople() {
		return patrons.size();
	}

	public ArrayList<Book> getLibraryBooks() {
		return libraryBooks;
	}

	public ArrayList<Person> getPatrons() {
		return patrons;
	}

	public String getName() {
		return name;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	// Library class setters 
	public void setLibraryBooks(ArrayList<Book> libraryBooks) {
		this.libraryBooks = libraryBooks;
	}

	public void setPatrons(ArrayList<Person> patrons) {
		this.patrons = patrons;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	// checkNumCopies gives number of copies for a book in a library
	public int checkNumCopies(String title, String author) {
		int copies = 0;
		for (int i = 0; i < libraryBooks.size(); i++) {
			if (libraryBooks.get(i).getTitle() == title) {
				if (libraryBooks.get(i).getAuthor() == author) {
					copies++;
				}
			}
		}
		return copies;
	}
	
	// totalNumBooks gives the total number of books a library has 
	public int totalNumBooks() {
		return libraryBooks.size();
	}
	
	// checkOut checks if person is a patron,
	// updates the due date for the book,
	// checks if book is in the correct library and isn't checked out, 
	// adds the book to the person P's list of books 
	public boolean checkOut(Person p, Book b, String dueDate) {
		if (!(patrons.contains(p)) || (!(libraryBooks.contains(b))) || (b.isCheckedOut() == true)){
			return false;
		} else {
			for (int i = 0; i < libraryBooks.size(); i++) {
				if (libraryBooks.get(i).equals(b)) 
				{ libraryBooks.get(i).setDueDate(dueDate);
					libraryBooks.get(i).setCheckedOut(true);
					p.addBook(b);
					return true;
				}
			} 
			return false;
		}
	}
	
	// returns an arraylist of books that are due on some day
	public ArrayList<Book> booksDueOnDate(String date) {
		ArrayList<Book> bookieTookie = new ArrayList<Book>();
		for (int i = 0; i < libraryBooks.size(); i++) 
		{
			if (libraryBooks.get(i).getDueDate() == date) {
				bookieTookie.add(libraryBooks.get(i));
			}
		}
		return bookieTookie;
	}
	
	// daysBetween calculates the difference between the due date and the checkout date
	public long daysBetween(GregorianCalendar start, GregorianCalendar end) {
		long end1 = end.getTimeInMillis();
		long start1 = start.getTimeInMillis(); 
		return TimeUnit.MILLISECONDS.toDays(Math.abs(end1-start1));
	}
	
	// lateFee calculates the late fee the person owes and is based 
	// at 1% of the book value. daysBetween is used to calculate 
	// the number of times the lateFee needs to be charged. 
	public double lateFee(Person p) {
		double trumpTax = 0.0; 
	
		int currentYear = Integer.parseInt(this.getCurrentDate().substring(6));
		int currentMonth = Integer.parseInt(this.getCurrentDate().substring(3, 5)) - 1;
		int currentDay = Integer.parseInt(this.getCurrentDate().substring(0,2)); 
		GregorianCalendar current = new GregorianCalendar(currentYear, currentMonth, currentDay);
		
		for(int q = 0; q < p.getCheckedOut().size(); q++) {
			int dueYear = Integer.parseInt(p.getCheckedOut().get(q).getDueDate().substring(6));
			int dueMonth = Integer.parseInt(p.getCheckedOut().get(q).getDueDate().substring(3,5)) - 1;
			int dueDay = Integer.parseInt(p.getCheckedOut().get(q).getDueDate().substring(0,2));
			
		GregorianCalendar dueDate = new GregorianCalendar(dueYear, dueMonth, dueDay);
		if(current.after(dueDate)) {
			trumpTax += daysBetween(current, dueDate) * p.getCheckedOut().get(q).getBookValue() * .01;
			}
	}
	return (double) trumpTax;
}
}