package testing;

import org.junit.Test;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class squareTest extends TestCase {
	@Test 
	public void test() {
		JunitTesting test = new JunitTesting();
		int output = test.square(5); 
		assertEquals(25, output);
		
	}
}
