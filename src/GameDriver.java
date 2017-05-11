import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import info.gridworld.grid.Location;

public class GameDriver {
	
	public GameDriver(){
	}
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Title splash = new Title(10000);
		splash.showSplash();
		GameFrame gf = new GameFrame();
	}
	
	
	

}
