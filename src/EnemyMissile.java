import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class EnemyMissile extends Mover {
	
	public EnemyMissile(){
		setColor(null);
		myImage = openImage("Images/EnemyMissile.gif");
	}

	@Override
	public int moveme() {
		Grid <Actor> g = getGrid();
		Location down = getLocation().getAdjacentLocation(Location.SOUTH);
		if (g.isValid(down)){
			if (g.get(down) == null){
				return 11;
			}
			else if (g.get(down) instanceof Ship){
				return 15;
			}
			else if (g.get(down) instanceof Missile){
				return 13;
			}
			else if (g.get(down) instanceof Basic|| g.get(down) instanceof Boss|| g.get(down) instanceof Twin|| g.get(down) instanceof ZigZag||g.get(down) instanceof EnemyMissile){
				return 16;
			}
		}
		return 0;
	}

	@Override
	public Location moveLoc() {
		Location loc = getLocation();
		if (loc == null){
			System.out.println("loc is null");
			return null;
		}
		else{
			System.out.println(getLocation());
		}	
		return loc.getAdjacentLocation(Location.SOUTH);
	}
	
}
