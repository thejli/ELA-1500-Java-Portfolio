import java.util.Comparator;


public class CompareByName implements Comparator<Playable>{
	
	// compares the names of two playables
	public int compare(Playable p1, Playable p2){
		if(p1.getName().compareTo(p2.getName()) > 0){ 
			return 1;
		} 
		else if(p1.getName().compareTo(p2.getName()) < 0){ 
			return -1;
		} 
		else return 0;
	} 
}
