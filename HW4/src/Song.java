
public class Song implements Comparable<Song>, Playable{
	private String artist;
	private String title;
	private int minutes;
	private int seconds;
	
	// Getters and Setters
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public String getName() {
		return this.title;
	}

	public int getPlayTimeSeconds() {
		return seconds + (minutes * 60);
	}
		
	public int numberOfSongs() {
		return 1;
	}
		
	
	// Constructors
	public Song(String artist, String title){
		this.artist = artist;
		this.title = title;
		this.minutes = 0; 
		this.seconds =0; 
	}
	
	public Song(String artist, String title, int minutes, int seconds) {
		this.artist = artist; 
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Song(Song s) {
		this.artist = s.getArtist();
		this.title = s.getTitle();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();
	}
	
	// equals method 
	public boolean equals(Object o) {
		if (o instanceof Song) {
			Song songInstance = (Song) o; 
			return ((this.artist.equals(songInstance.artist) &&
					this.title.equals(songInstance.title)&&
					this.minutes == songInstance.minutes &&
					this.seconds == songInstance.seconds));
			
		}else {
			return false;
		}
	}
	// to string method
	public String toString() { // Use this code for toString EXACTLY
		   return "{Song: title = " + title + " artist = " + artist + "}";
		}
	
	// play method
	public void play() { 
		   System.out.printf("Playing Song: artist = %-20s title = %s\n", artist, title);
	}
	
	// comparable method
	public int compareTo(Song o) {
		int returnNum = o.artist.compareTo(this.artist);
		if (returnNum != 0) {
			return returnNum;
		}
		else if (returnNum == 0) {
			return this.title.compareTo(o.title);
		}
		else
			return this.title.compareTo(o.title);
	}
	
	
}
