import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class ZigZag extends Enemy {
	int x;
	int counter = 0;
	boolean right;
		
	public ZigZag(){
	setColor(null);
	x = 2;
	right = true;
	myImage = openImage("Images/ZigZag.gif");
	}
	
	@Override
	public int moveme() {
		Location loc = getLocation();
		if(loc == null){
			return 45;
		}
		Grid <Actor> g = getGrid();
		Location down = loc.getAdjacentLocation(Location.SOUTH);
		
		if (down == null){
			System.out.println("down is null");
			return 45;
		}
		
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
			
	public void move(){
		Grid<Actor> g = getGrid();
			moveTo(moveLoc());
			if (getLocation().getCol()==0){
				right=true;
			}
			if (getLocation().getCol()==g.getNumCols()){
				right=false;
			}
	}	
		
	public void shoot(){
		Grid<Actor>g = getGrid();
		Location loc = getLocation();
		Location south = loc.getAdjacentLocation(Location.SOUTH);
		g.put(south, new EnemyMissile());
	}
		
	public boolean checkLeft(){
		if (getLocation().getCol()==0){
			right = true;
			return true;
		}
		return false;
	}
	public boolean checkRight(){
		Grid <Actor> g = getGrid();
		if (getLocation().getCol()==g.getNumCols()-1){
			right = false;
			return true;
		}
		return false;
	}

	@Override
	public Location moveLoc() {
		Grid<Actor>g = getGrid();
		Location current = getLocation();
		Location move;
		int c = g.getNumCols()-1;
		
		if (checkLeft()&&counter<2){
			move=current.getAdjacentLocation(Location.SOUTH);
			counter++;
		}
		else if (checkRight()&&counter<2){
			move=current.getAdjacentLocation(Location.SOUTH);
			counter++;
		}
		
		else if (counter>=2){
			if (right){
				move = current.getAdjacentLocation(Location.EAST);
				if (current.getAdjacentLocation(Location.EAST).getCol()==c){
					counter = 0;
				}
			}
			else if (!right){
				move = current.getAdjacentLocation(Location.WEST);
				if (current.getAdjacentLocation(Location.WEST).getCol()==0){
					counter = 0;
				}
			}
			else{
				move = null;
			}
		}
		else{
			move = current.getAdjacentLocation(Location.EAST);
	}
		return move;
	}

}
