
public class Student extends Person {

	private String campusAddress; 
	private double gpa = 0.0; 
	
	// I added a constructor and a toString to make the code work.
	public Student(String n, String ha, String ca) {
		super(n, ha);
		this.campusAddress = ca;
	}
	
	public String toString() {
		return "{Stud: n=" + name + ", ha=" + homeAddress + ", ca=" + campusAddress + "}";
	}
	
}

