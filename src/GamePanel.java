import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
	GameWorld gw;
	
	public GamePanel(){
		setPreferredSize(new Dimension(520,480));
		setVisible(true);
		setFocusable(true);
		addKeyListener(this);
		gw = new GameWorld(this);
	}
	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		Image img = openImage("Images/SpaceBack.jpg");
		int h = img.getHeight(null);
	    int w = img.getWidth(null);
	    if (w < this.getWidth()) {
	        img = img.getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT );
	        h = img.getHeight(this);
	    }
	    if (h < this.getHeight()) {
	        img = img.getScaledInstance(-1, getHeight(), Image.SCALE_DEFAULT );
	    }
	    int x = (getWidth() - img.getWidth(null)) / 2;
	    int y = (getHeight() - img.getHeight(null)) / 2;
	    g.drawImage(img, x, y, null);
		gw.paint(g);
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
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i == 37){
			gw.keyPressed("LEFT", null);
		}
		if(i == 38){
			gw.keyPressed("UP", null);
		}
		if(i == 39){
			gw.keyPressed("RIGHT", null);
		}
		if(i == 40){
			gw.keyPressed("DOWN", null);
		}
		if(i == 32){
			gw.keyPressed("SPACE", null);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
