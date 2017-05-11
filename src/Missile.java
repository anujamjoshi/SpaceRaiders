import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Missile extends Mover {
	Grid<Actor>g = getGrid();
	
	public Missile(){
		setColor(null);
		myImage = openImage("Images/Missile.gif");
	}
	
	@Override
	public int moveme(){
		Grid <Actor> g = getGrid();
		Location l = getLocation();
		if(l==null){
			return 45;
		}
		Location up = getLocation().getAdjacentLocation(Location.NORTH);
		Location twoUp = up.getAdjacentLocation(Location.NORTH);
		if (g.isValid(up)){
			if (g.get(up) == null){
				return 1;
			}
			else if (g.get(up) instanceof Basic|| g.get(up) instanceof Boss || g.get(up) instanceof Twin || g.get(up) instanceof ZigZag){
				return 2;
			}
			else if (g.get(up) instanceof EnemyMissile||g.get(twoUp) instanceof EnemyMissile){
				return 3;
			}
			else if (g.get(up) instanceof Missile){
				return 1;
			}
		}
		return 0;
	}
	
	public Location nextMove(){
		return getLocation().getAdjacentLocation(Location.NORTH);
	}

	@Override
	public Location moveLoc() {
		return getLocation().getAdjacentLocation(Location.NORTH);
	}

}
