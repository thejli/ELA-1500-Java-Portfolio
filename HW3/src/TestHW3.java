import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;


public class TestHW3 extends TestCase {
	@Test	
	public void testCheckNumCopies() {
			Library henrico = new Library("Henrico");
			Book book1 = new Book("AAA", "AA", 1, 100.0);
			Book book2 = new Book("BBB", "BB", 2, 100.0);
			Book book3 = new Book("CCC", "CC", 3, 100.0);
			Book book4 = new Book("DDD", "DD", 4, 100.0); 
			Book book5 = new Book("AAA", "AA", 5, 100.0);
			
			ArrayList<Book> henricoBooks = new ArrayList<Book>();
			henricoBooks.add(book1);
			henricoBooks.add(book2);
			henricoBooks.add(book3);
			henricoBooks.add(book4); 
			henricoBooks.add(book5); 
			henrico.setLibraryBooks(henricoBooks);
			
			Person person1 = new Person("AAA", "100AAA", 1);
			Person person2 = new Person("BBB", "100BBB", 2); 
			ArrayList<Person>  henricoPatrons = new ArrayList<Person>();
			henricoPatrons.add(person1);
			henricoPatrons.add(person2);
			henrico.setPatrons(henricoPatrons);
			
			// cncTest 1
			int cncTest1 = henrico.checkNumCopies("BBB", "BB");
			assertEquals(1, cncTest1);
			
			// cncTest 2 
			int cncTest2 = henrico.checkNumCopies("AAA", "AA");
			assertEquals(2, cncTest2);
		}
		
		@Test
		public void testCheckOut() {
			Library henrico = new Library("Henrico");
			Book book1 = new Book("AAA", "AA", 1, 100.0);
			Book book2 = new Book("BBB", "BB", 2, 100.0);
			Book book3 = new Book("CCC", "CC", 3, 100.0);
			Book book4 = new Book("DDD", "DD", 4, 100.0); 
			Book book5 = new Book("AAA", "AA", 5, 100.0);
			
			ArrayList<Book> henricoBooks = new ArrayList<Book>();
			henricoBooks.add(book1);
			henricoBooks.add(book2);
			henricoBooks.add(book3);
			henricoBooks.add(book4); 
			henricoBooks.add(book5); 
			henrico.setLibraryBooks(henricoBooks);
			
			Person person1 = new Person("AAA", "100AAA", 1);
			Person person2 = new Person("BBB", "100BBB", 2); 
			// person 3 not added to patrons
			Person person3 = new Person("CCC", "100CCC", 3); 
			ArrayList<Person>  henricoPatrons = new ArrayList<Person>();
			henricoPatrons.add(person1);
			henricoPatrons.add(person2);
			henrico.setPatrons(henricoPatrons);
						
			// coTest 1
			boolean coTest1 = henrico.checkOut(person1, book1, "01 01 2020");
			assertEquals(true, coTest1);
					
			// coTest 2 
			boolean coTest2 = henrico.checkOut(person3, book3, "01 01 2020");
			assertEquals(false, coTest2);
			}
			
		@Test
		public void testBooksDueOnDate() {
			Library henrico = new Library("Henrico");
			Book book1 = new Book("AAA", "AA", 1, 100.0);
			Book book2 = new Book("BBB", "BB", 2, 100.0);
			Book book3 = new Book("CCC", "CC", 3, 100.0);
			Book book4 = new Book("DDD", "DD", 4, 100.0); 
			Book book5 = new Book("AAA", "AA", 5, 100.0);
			
			ArrayList<Book> henricoBooks = new ArrayList<Book>();
			henricoBooks.add(book1);
			henricoBooks.add(book2);
			henricoBooks.add(book3);
			henricoBooks.add(book4); 
			henricoBooks.add(book5); 
			henrico.setLibraryBooks(henricoBooks);
			
			Person person1 = new Person("AAA", "100AAA", 1);
			Person person2 = new Person("BBB", "100BBB", 2); 
			ArrayList<Person>  henricoPatrons = new ArrayList<Person>();
			henricoPatrons.add(person1);
			henricoPatrons.add(person2);
			henrico.setPatrons(henricoPatrons);

			book1.setDueDate("01 01 2020");
			book2.setDueDate("01 01 2020");
			book3.setDueDate("01 01 2021");
			book4.setDueDate("01 01 2021");
			
			// bdodTest 1
			ArrayList<Book> bdodTest1 = henrico.booksDueOnDate("01 01 2020");
			ArrayList<Book> bdodResult1 = new ArrayList<Book>();
			bdodResult1.add(book1);
			bdodResult1.add(book2);
			assertEquals(bdodResult1, bdodTest1);
					
			// bdodTest 2
			ArrayList<Book> bdodTest2 = henrico.booksDueOnDate("01 01 2021");
			ArrayList<Book> bdodResult2 = new ArrayList<Book>();
			bdodResult2.add(book3);
			bdodResult2.add(book4);
			assertEquals(bdodResult2, bdodTest2);
		}
		
		@Test
		public void testLateFee() {
			Library henrico = new Library("Henrico");
			Book book1 = new Book("AAA", "AA", 1, 100.0);
			Book book2 = new Book("BBB", "BB", 2, 100.0);
			Book book3 = new Book("CCC", "CC", 3, 100.0);
			Book book4 = new Book("DDD", "DD", 4, 100.0); 
			
			ArrayList<Book> henricoBooks = new ArrayList<Book>();
			henricoBooks.add(book1);
			henricoBooks.add(book2);
			henricoBooks.add(book3);
			henricoBooks.add(book4); 
			henrico.setLibraryBooks(henricoBooks);
			
			Person person1 = new Person("AAA", "100AAA", 1);
			Person person2 = new Person("BBB", "100BBB", 2); 
			ArrayList<Person>  henricoPatrons = new ArrayList<Person>();
			henricoPatrons.add(person1);
			henricoPatrons.add(person2);
			henrico.setPatrons(henricoPatrons);
			
			// Test 1
			henrico.checkOut(person1, book1, "17 02 2017");
			double lfTest1 = henrico.lateFee(person1);
			assertEquals(1.0, lfTest1, 0);
			
			// Test 2
			henrico.checkOut(person2, book2, "16 02 2017"); 
			double lfTest2 = henrico.lateFee(person2); 
			assertEquals(2.0, lfTest2, 0);
	
}
}
