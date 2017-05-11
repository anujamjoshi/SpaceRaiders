import java.util.ArrayList;

public class Level {
	int num;
	public Level(){
		num =0;
	}
	
	public int getLevel(){
		return num;
	}
	public void incrementLevel(){
		num++;
	}
	
	
	public ArrayList<Mover> getEnemyList(){
		ArrayList<Mover> enemies = new ArrayList<Mover>();
		if(getLevel()==0){
			enemies.add(new Boss());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new ZigZag());
			System.out.println ("HEy you finished level 0!");
		}
		if(getLevel()==1) {
			System.out.println("Hey STARTING LEVEL 1 ");
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new ZigZag());
			enemies.add(new Basic());
			enemies.add(new ZigZag());
			enemies.add(new Basic());
			enemies.add(new Twin(1));
			enemies.add(new ZigZag());
		}
		if (getLevel()==2) {
			enemies.add(new ZigZag());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new ZigZag());
			enemies.add(new ZigZag());
			enemies.add(new Basic ());
			enemies.add(new Boss());
		}
		if(getLevel()==3) {
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new ZigZag());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Twin(1));
			enemies.add(new Boss());			
			enemies.add(new Twin(2));
			enemies.add(new ZigZag());
			}
		if(getLevel()==4) {
			enemies.add(new Boss());
			enemies.add(new Basic());
			enemies.add(new Basic());	
			enemies.add(new Basic());
			enemies.add(new Basic());
			enemies.add(new Boss());
			enemies.add(new Twin(1));
			enemies.add(new Twin(2));
			enemies.add(new Boss());
			enemies.add(new ZigZag());
			enemies.add(new ZigZag());
		}
		return enemies;
	}
	
}
