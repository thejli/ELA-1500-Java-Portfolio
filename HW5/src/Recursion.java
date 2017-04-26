import java.awt.Color;


public class Recursion {

	// Problem 1
	public static String reverseString(String initialString){
		 if(initialString.length()<=1 || initialString == null){ 
			 	return initialString;
		 }
		 else{
			 return "" + initialString.charAt(initialString.length()-1) + reverseString(initialString.substring(0, initialString.length()-1));                                  
		 }
	}
	
	// Problem 2
	public static int countWays(int numStairs) {
	    if (numStairs == 1 || numStairs == 2){
	    	return numStairs;
	    }
	    else {
	    return countWays(numStairs - 1) + countWays(numStairs - 2);
	    }
	}
	
	// Problem 3
	public static int Ackermann(int m, int n) {
		 if (m == 0) {
			 return n + 1;
		 }
	     if (n == 0) {
	    	 return Ackermann(m - 1, 1);
	     }
	     
	     return Ackermann(m - 1, Ackermann(m, n - 1));
	}
	
	// Problem 4
	public static void snowflake(Turtle t, int depth, double length) {
		if (depth == 0) t.forward(length);
		else {
		snowflake(t, depth-1, length/3);
		t.left(60);
		snowflake(t, depth-1, length/3);
		t.right(120);
		snowflake(t, depth-1, length/3);
		t.left(60);
		snowflake(t, depth-1, length/3);
		snowflake(t, depth-1, length/3);
		t.left(60);
		snowflake(t, depth-1, length/3);
		t.right(120);
		snowflake(t, depth-1, length/3);
		t.left(60);
		snowflake(t, depth-1, length/3);
		}
	}
	
	//MAIN
	public static void main(String[] args){
		System.out.println(reverseString("reverse"));
		System.out.println(countWays(13));
		System.out.println(Ackermann(2,2));
		World myWorld = new World(500, 500, Color.WHITE);
	    Turtle turtle1 = new Turtle(myWorld, 0, 0);
	    snowflake(turtle1, 1, 100);
	}
}
