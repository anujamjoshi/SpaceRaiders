import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GamePanel p;
	
	public GameFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		p = new GamePanel();
		add(p);
		pack();
		setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		File soundFile = new File("theme.wav"); 
	    Line.Info linfo = new Line.Info(Clip.class);
	    Line line = AudioSystem.getLine(linfo);
	    Clip clip = (Clip) line;
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
	    clip.open(ais);
	    clip.loop(20); 
 	 } 
 } 
        