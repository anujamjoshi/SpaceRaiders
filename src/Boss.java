import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;


public class Boss extends Enemy {
	Grid<Actor> g = this.getGrid();
	
	public Boss(){
		setColor(null);
		myImage = openImage("Images/Boss.gif");
		}
	
	@Override
	public int moveme() {
		Grid <Actor> g = getGrid();
		Location down = getLocation().getAdjacentLocation(Location.SOUTH);
		
		if (g.isValid(down)){
		if (g.get(down)  == null){
			return 11;
		}
		else if (g.get(down) instanceof Missile){
			return 14;
		}
		else if (g.get(down) instanceof Ship){
			return 15;
		}
		}
		return 0;
	}

	public ArrayList<Location> getEmptyLocations() {
		Grid <Actor> g = getGrid();
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i = 0; i < g.getNumRows(); i++) {
			for(int j = 0; j < g.getNumCols(); j++) {
				locs.add(new Location(i,j));
			}
		}
		locs.removeAll(g.getOccupiedLocations());
		return locs;
	}	

	public void move() {
		moveTo(moveLoc());
	}
	public void shoot() {
		Location loc =this.getLocation();
		Location south = loc.getAdjacentLocation(Location.SOUTH);
		g.put(south,new EnemyMissile());
	}

	@Override
	public Location moveLoc() {
		Grid <Actor> g = getGrid();
		ArrayList<Location> neighbors = g.getEmptyAdjacentLocations(getLocation());
		
		int r = (int) (Math.random()*neighbors.size());
		Location move = neighbors.get(r);
		return move;
	}	
}