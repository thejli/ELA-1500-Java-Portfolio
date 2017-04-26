import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class HW4Test {

	Song s1 = new Song("Artist", "Song");
	Song s2 = new Song("Bootist", "Bong");
	Song s3 = new Song("Artist", "Song");
	Song s4 = new Song("Doodist", "Dong"); 
	Song s5 = new Song("John", "Jong", 1, 1);
	Song s6 = new Song("Li", "Long", 2, 1);
	Song s7 = new Song("A", "A");
	Song s8 = new Song("B", "B");
	Song s9 = new Song("C", "C");
	Song s10 = new Song("Ecks", "Dee", 3, 10);

	PlayList play1 = new PlayList("Ping Pong");
	PlayList play2 = new PlayList();
	
	// song equals method test
	@Test
	public void songEqualsTest() {
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void songEqualsTest2(){
		assertTrue(s1.equals(s3));
	}
	
	// song toString method test
	@Test
	public void songToStringTest(){
		String t1 = s1.toString();
		assertEquals("{Song: title = Song artist = Artist}", t1);
	}
	
	@Test
	public void songToStringTest2(){
		String t2 = s4.toString();
		assertEquals("{Song: title = Dong artist = Doodist}", t2);
	}
	
	// song compareTo method
	@Test
	public void songCompareToTest(){
		int t1 = s1.compareTo(s2);
		assertEquals(1, t1);
	}
	
	@Test
	public void songCompareToTest2(){
		int t2 = s2.compareTo(s1);
		assertEquals(-1,t2);
	}
	
	// song get method 
	@Test
	public void songGetPlayTimeSecondsTest() {
		int t1 = s5.getPlayTimeSeconds();
		assertEquals(61, t1);
	}
	
	@Test public void songGetPlayTimeSecondsTest2() {
		int t2 = s6.getPlayTimeSeconds();
		assertEquals(121, t2);
	}
	
	// song num songs method 
	@Test 
	public void numberOfSongsTest() {
		int t1 = s2.numberOfSongs();
		assertEquals(1, t1);
	}
	
	@Test 
	public void numberofSongsTest2() {
		int t2 = s3.numberOfSongs();
		assertEquals(1, t2);
	}

	// playlist playtime seconds method 
	@Test
	public void playListGetSecondsTest() {
		play1.addSong(s5);
		play1.addSong(s6);
		int t1 = play1.getPlayTimeSeconds();
		assertEquals(182, t1);
	}
	
	@Test 
	public void playListGetSecondsTest2() {
		play1.addSong(s5);
		int t2 = play1.getPlayTimeSeconds();
		assertEquals(61, t2);
	}
	
	// playlist number of songs method 
	@Test 
	public void playListNumberOfSongsTest() {
		play1.addSong(s1);
		play1.addSong(s2);
		play1.addSong(s3);
		int t1 = play1.numberOfSongs();
		assertEquals(3, t1);
	}
		
	@Test 
	public void playListNumberOfSongsTest2() {
		play2.addSong(s2);
		int t2 = play2.numberOfSongs();
		assertEquals(1, t2);
	}
	
	// playlist toString method 
	@Test 
	public void playlistToStringTest() {
		assertEquals("Ping Pong", play1.toString());
		
	}
	
	// playlist loadSong
		@Test 
		public void loadSongTest() {
			PlayList play3 = new PlayList();
			play3.loadSongs("loadSongTest");
			System.out.println(play3.getPlayableList());
		}
		
		@Test 
		public void loadSongTest2() {
			PlayList play1 = new PlayList();
			play1.loadSongs("loadSongTest");
			System.out.println(play1.getPlayableList());
		}
	
	@Test
	public void playListToStringTest2() {
		assertEquals("Untitled", play2.toString());
	}
	// playlist remove with int method
	@Test 
	public void playListRemoveWithIntTest() {
		play1.addSong(s2);
		System.out.println(play1);
		play1.addSong(s3);
		System.out.println(play1);
		play1.removePlayable(0);
		System.out.println(play1);
		assertEquals(1, play1.getPlayableList().size());
	}
	
	@Test 
	public void playListRemoveWithIntTest2() {
		play2.addSong(s2);
		System.out.println(play2);
		play2.removePlayable(0);
		System.out.println(play2);
		assertFalse(play2.getPlayableList().contains(s2));
	}
	// playlist remove with playlist method 
	@Test
	public void playListRemoveWithPlaylistTest() {
		play1.addSong(s3);
		System.out.println(play1);
		play1.removePlayable(s3);
		System.out.println(play1);
		assertFalse(play1.getPlayableList().contains(s3));
		
	}
	@Test 
	public void playListRemoveWithPlaylistTest2() {
		play2.addSong(s4);
		System.out.println(play2);
		Playable t1 = play2.removePlayable(s4);
		assertEquals(s4, t1);
	}
	
	// playlist clear method 
	@Test
	public void playListClearTest() {
		play1.addSong(s1); 
		play2.addSong(s2);
		System.out.println(play1);
		play1.clear();
		assertEquals(0, play1.getPlayableList().size());
	}
	
	@Test 
	public void playListClearTest2() {
		play2.addSong(s1);
		System.out.println(play2);
		play2.clear();
		assertEquals(0, play2.getPlayableList().size());
	}
	
	// playlist add a song method 
	@Test
	public void playListAddSongTest(){
		play1.addSong(s5);
		assertTrue(play1.getPlayableList().contains(s5));
	}
	
	@Test
	public void playListAddSongTest2() {
		play1.addSong(s4);
		assertFalse(play1.getPlayableList().contains(s5));
	}
	
	// playlist add a playlist method 
	@Test 
	public void playListAddPlaylistTest() {
		play1.addSong(s1);
		play2.addSong(s2);
		boolean t1 = play1.addPlayList(play2);
		assertEquals(true, t1);
	}
	
	@Test 
	public void playListAddPlaylistTest2() {
		play1.addSong(s1);
		play1.addSong(s3);
		play1.addSong(s4);
		play2.addSong(s2);
		play1.addPlayList(play2);
		assertTrue(play1.getPlayableList().contains(play2));
	}
	
	
	// playlist name sort method 
	@Test 
	public void playListNameSortTest() {
		
		play1.addSong(s7);
		play1.addSong(s8);
		play1.sortByName();
		assertEquals("[{Song: title = A artist = A}, {Song: title = B artist = B}]", play1.getPlayableList().toString());
	}
	
	@Test
	public void playListNameSortTest2() {
		play1.addSong(s7); 
		play1.addSong(s8);
		play1.addSong(s9); 
		play1.sortByName();
		assertEquals("[{Song: title = A artist = A}, {Song: title = B artist = B}, {Song: title = C artist = C}]", play1.getPlayableList().toString());
	}
	
	// playlist time sort method
	@Test
	public void playListTimeSortTest() {
		play1.addSong(s5);
		play1.addSong(s6);
		play1.sortByTime();
		assertEquals("[{Song: title = Jong artist = John}, {Song: title = Long artist = Li}]", play1.getPlayableList().toString());
	}
	
	@Test
	public void playListTimeSortTest2() {
		play1.addSong(s6); 
		play1.addSong(s10);
		play1.sortByTime();
		assertEquals("[{Song: title = Long artist = Li}, {Song: title = Dee artist = Ecks}]", play1.getPlayableList().toString());
	}
	
	
	
}
