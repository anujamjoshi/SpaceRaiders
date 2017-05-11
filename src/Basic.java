import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Basic extends Enemy{
	Grid<Actor> g = getGrid();
	
	public Basic() {
		setColor(null);
		myImage = openImage("Images/Basic.gif");
	}
	
	@Override
	public int moveme() {
		Location loc = getLocation();
		Grid <Actor> g = getGrid();
		if(loc == null){
			System.out.println("loc is null");
			return 45;
		}
		Location down = loc.getAdjacentLocation(Location.SOUTH);
		
		if (g.isValid(down)){
			if (g.get(down) == null){
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
	
	public void move() {
		Location l = new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		if (g.isValid(l) && g.get(l) == null){
			this.moveTo(l);
		}
	}

	public void shoot() {
		getGrid().put(new Location(getLocation().getRow()+2, getLocation().getCol()), new EnemyMissile());
			}

	@Override
	public Location moveLoc() {
		Location loc = this.getLocation();
		if(loc == null){
			return null; 
		}
		return getLocation().getAdjacentLocation(Location.SOUTH);
	}
}
