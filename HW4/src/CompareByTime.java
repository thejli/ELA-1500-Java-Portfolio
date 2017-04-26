import java.util.Comparator;


public class CompareByTime implements Comparator<Playable> {
	
	// compares the times of two playables
	public int compare(Playable p1, Playable p2){
		if (p1.getPlayTimeSeconds() < p2.getPlayTimeSeconds()){
			return -1;
		}
		else if(p1.getPlayTimeSeconds() > p2.getPlayTimeSeconds()){
			return 1;
		}
		else return 0;
	}
}
