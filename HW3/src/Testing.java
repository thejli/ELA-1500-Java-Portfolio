import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Testing {

	public static void main(String[] args) {
		Library test = new Library("Alderman");
		Book book1 = new Book("Harry Potter", "JK", 69, 100.0);
		Book book2 = new Book("Harry Potter", "JK", 70, 100.0);
		Book book3 = new Book("The Art of the Deal", "Pres. Trump", 50, 1.0);
		
		ArrayList<Book> aldermanBooks = new ArrayList<Book>();
		aldermanBooks.add(book1);
		aldermanBooks.add(book2);
		aldermanBooks.add(book3);
		test.setLibraryBooks(aldermanBooks);
		
		Person person1 = new Person("sam", "1600 penn ave", 111111);
		ArrayList<Person>  aldermanPatrons = new ArrayList<Person>();
		aldermanPatrons.add(person1);
		test.setPatrons(aldermanPatrons);

		book2.setDueDate("01 01 2018");
		book3.setDueDate("01 01 2018");
		
		Person person69 = new Person("sam", "1738 broad st", 1010101);
		aldermanPatrons.add(person69);
		test.checkOut(person69, book1, "17 02 2017");
		double lateFeeTest1 = test.lateFee(person69);
		System.out.println(lateFeeTest1);
		Calendar today = new GregorianCalendar();
		Calendar dueDate = new GregorianCalendar(2017, 01, 17);
		/*System.out.println(dueDate.getTime());
		System.out.println(today.getTime());
		System.out.println(test.daysBetween(dueDate.getTime(), today.getTime()));
		System.out.println(book1.getBookValue());
		System.out.println(today.after(dueDate));
		System.out.println(.01 * book1.getBookValue() * test.daysBetween(dueDate.getTime(), today.getTime()) );*/
		
		Library test2 = new Library("Clem");
		Book book4 = new Book("Potter Harry", "KJ", 121, 20.0); 
		Book book5 = new Book("Potter Harry1", "KJ1", 120, 19.0); 
		Book book6 = new Book("Potter Harry2", "KJ2", 119, 18.0); 
		Person person2 = new Person("John", "100 Gooch", 101);
		ArrayList<Book> clemBooks = new ArrayList<Book>();
		clemBooks.add(book4);
		clemBooks.add(book5);
		clemBooks.add(book6);
		test2.setLibraryBooks(clemBooks);
		ArrayList<Person> clemPatrons = new ArrayList<Person>();
		clemPatrons.add(person2);
		test2.setPatrons(clemPatrons);
		
		System.out.println(test2.getNumBooks());
		test2.checkOut(person2, book4, "17 02 2017");
		System.out.println(test2.getNumBooks());
		System.out.println(17/3);
		
		for (int val = 0; val < 4; val ++) {
			System.out.print("+");
			for (int num = 0; num < val; num++) {
				System.out.print("0");
			}
		}
		
		System.out.println();
		int val = 1; 
		int sum = 0; 
		while (val < 5) {
			sum = 0; 
			sum = sum + val; 
			val++;
		} System.out.println(sum);
	}


}
