import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class PlayList implements Playable{
	private String name; 
	private ArrayList<Playable> playableList;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		this.name = newName;
	}
	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}
	public void setPlayableList(ArrayList<Playable> newPlayableList) {
		this.playableList = newPlayableList;
	}
	
	// gets a playable using int
	public Playable getPlayable(int index){
	if (index < 0 || index >= playableList.size()) {
		return null;
	}else {
		return playableList.get(index);
	}
	}
	
	// getting the time in seconds 
	public int getPlayTimeSeconds() {
		int totalSeconds = 0;
		for (int i = 0; i < playableList.size(); i++) {
			totalSeconds += playableList.get(i).getPlayTimeSeconds();
		}
		return totalSeconds;
	}

	// getting the number of songs 
	public int numberOfSongs() {
		return playableList.size();
	}
		
	// toString() method
	public String toString() { 
		String finalString = this.name;
		for (int i = 0; i < playableList.size(); i++) {
			finalString += "\n" + playableList.get(i).toString();
		}
		return finalString;
	}
	// Constructors 
	public PlayList() {
		this.name = "Untitled";
		playableList = new ArrayList<Playable>();
	}
	
	public PlayList(String newName){
		this.name = newName;
		playableList = new ArrayList<Playable>();
	}
	
	// method to load a song uses a scanner to set up the file and scan it. then 
	// the method creates the strings and cleans the whitespace so it's easier to read.
	// then the method uses parseint to get the numbers 
	// then the method checks for the seconds value to convert it to mins 
	// lastly the method creates a song and saves it into the playableList
	// the catch at the end addresses the common problem of file error
	public boolean loadSongs(String fileName) {		
		try {
			//set up scanner and file
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()){
				while(scanner.hasNextLine()){
					String title = scanner.nextLine().trim();
					String artist = scanner.nextLine().trim();
					String time = scanner.nextLine().trim();
					int m = Integer.parseInt(time.substring(0, time.indexOf(':')));
					int s = Integer.parseInt(time.substring(time.indexOf(':')+1, time.length()));
					if(s>60){
						int remainder = s/60;
						m = m + remainder;
						s = s -(remainder)*60;
					}
					Song finalSong = new Song(artist, title, m, s);
					playableList.add((Playable) finalSong);
					String finalSpace = scanner.nextLine();
					
				}
			}
			
			return true;
 
 
		} 
		catch(FileNotFoundException fileIO){			
			fileIO.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// remove a playable using int
	public Playable removePlayable(int index){
		Playable remove = playableList.get(index);
		playableList.remove(remove);
		return remove;
		}

	// remove a playable using playable
	public Playable removePlayable(Playable p){
		if (playableList.contains(p)){
			playableList.remove(p);
			return p;
		}
		return null;
		}
		
	// clears the playlist
	public boolean clear(){
		return playableList.removeAll(playableList);
	}
	
	// add a song if it's not already added
	public boolean addSong(Song s){
		playableList.add((Playable) s);
		return true;
	}
	
	// adds a playlist 
	public boolean addPlayList(PlayList pl){
		if(playableList.contains(pl)){
			return false;
		}
		else{
			playableList.add(pl);
			return true;
		}
	}
	
	// play method 
	public void play(){
		for(int i = 0; i < playableList.size(); i++) {
			playableList.get(i).play();
		}
	}
	
	// sorting by name method 
	public void sortByName(){
		Collections.sort(playableList, new CompareByName());
	}
	
	// sorting by time method 
	public void sortByTime(){
		Collections.sort(playableList, new CompareByTime());
	}

	}


