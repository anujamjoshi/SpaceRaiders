import java.awt.Graphics;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public abstract class Mover extends Actor {
	
	public abstract int moveme();
    Image myImage;
    final int SQ = 48;
	public void paint(Grid<Actor> g2) {
	}

	protected Image openImage(String fn) {
		Image img = null;
		try{
			img = ImageIO.read(new File(fn));
		}
		catch(Exception e){
			System.out.println("Problem opening: "+this+" fn "+e);
		}
		
		return img;
	}
	public void paint(Graphics g2) {
		Location loc = getLocation();
		int x = loc.getCol()*SQ,
			y = loc.getRow()*SQ;
		
		g2.drawImage(myImage, x, y, SQ, SQ, null);
	}
	public abstract Location moveLoc();

}
