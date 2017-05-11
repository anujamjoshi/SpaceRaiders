import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Ship extends Mover{
	Grid<Actor> g = getGrid();
	public Ship(){
	setColor(null);
	myImage = openImage("Images/Ship.gif");

	}
	@Override
	public int moveme() {
		return 0;
	}
	@Override
	public Location moveLoc() {
		return null;
	}


}