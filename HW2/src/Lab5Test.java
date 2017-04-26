import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
// John Li jl3rz
// Sreekar Kandlakunta sbk2hn

public class Lab5Test {

	@Test
	public void testAddBook1() {
		Person rafael = new Person("Rafael", 17);
		Person alesky = new Person("Alesky", 69); 
		Person mamaMia = new Person("MamaMiaPizzaeria", 42);
		Person why = new Person("MoneyMakerXXX420", 21);

		Book bookieTookie = new Book("AAA", "AA");
		Book bookieTookie2 = new Book("A Book About Love", "Romeo");
		Book bookieTookie3 = new Book("A Book About Sadness", "Juliet");
		Book bookieTookie4 = new Book("A Beginner's Guide To Losing Deloitte Case Competition", "Dragon Consulting");
		Book repeat = new Book ("AAA", "AA"); 
		
		// addBook(); tests
		// Test 1: Add one Book to a newly created Person. Does this return true? Is the Book stored in the Person?
		rafael.addBook(bookieTookie);
		ArrayList<Book> readOrNot = rafael.getRead();
		assertTrue(rafael.addBook(bookieTookie2));
		assertTrue(readOrNot.contains(bookieTookie));
		
		// Test 2: Add a two different books to a Person. Does each call return true? Are two books stored?
		assertTrue(alesky.addBook(bookieTookie)); 
		assertTrue(alesky.addBook(bookieTookie2)); 
		assertTrue(alesky.hasRead(bookieTookie)); 
		assertTrue(alesky.hasRead(bookieTookie2));
		
		// Test 3: Add one Book and then try to add a second Book that is equals() to the first. Does the second call return false? Is just one book stored?
		why.addBook(bookieTookie); 
		assertFalse(why.addBook(repeat)); 
		int numberBookies = why.numBooksRead(); 
		assertEquals(1, numberBookies); 
		
		// forgetBook(); tests 
		// Test 4: Take a person with a book. Remove the book. Does this return true? 
		assertTrue(why.forgetBook(bookieTookie)); 
		
		// Test 5: Take a person with no book. Remove the book. Does this return false? 
		assertFalse(mamaMia.forgetBook(bookieTookie)); 
		
		// Test 6: Add two books to a person. Remove the two books. Are the books still stored in Person? 
		assertTrue(why.addBook(bookieTookie3)); 
		assertTrue(why.addBook(bookieTookie4));
		assertTrue(why.forgetBook(bookieTookie3));
		assertTrue(why.forgetBook(bookieTookie4));
		int whyNumBooksTest = why.numBooksRead(); 
		assertEquals(0, whyNumBooksTest); 
	
		
		
	}
}
