import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Twin extends Enemy{
		int count;
		boolean direction;
		
		public Twin(int side) {
		setColor (null);
		count = side;
		direction=true;
		myImage = openImage("Images/Twin.gif");
		} 
		
		@Override
		public int moveme() {
			
			Grid <Actor> g = getGrid();
			Location moveLoc = moveLoc();
			
			if (g.isValid(moveLoc)){
				if (g.get(moveLoc) == null){
					return 11;
				}
				else if (g.get(moveLoc) instanceof Missile){
					return 14;
				}
				else if (g.get(moveLoc) instanceof Ship){
					return 15;
				}
			}
			return 0;
		}
			
		public Location moveLoc(){
			Grid<Actor> g = getGrid();
			Location myLoc = getLocation();
			if (count ==1){
				if (myLoc.getRow()<g.getNumRows()){
					if (myLoc.getCol()<g.getNumCols()-1&&direction){
						return myLoc.getAdjacentLocation(Location.SOUTHEAST);
					}
					else if (myLoc.getCol()==g.getNumCols()-1){
						changeDir();
						return myLoc.getAdjacentLocation(Location.SOUTHWEST);
					}
					else if (myLoc.getCol()>0&&!direction){
						return myLoc.getAdjacentLocation(Location.SOUTHWEST);
					}
				}
				else
					return null;
			}
			else if (count == 2){
				if (myLoc.getRow()<g.getNumRows()){
					if (myLoc.getCol()>0&&direction){
						return myLoc.getAdjacentLocation(Location.SOUTHWEST);
					}
					else if (myLoc.getCol()==0){
						changeDir();
						return myLoc.getAdjacentLocation(Location.SOUTHEAST);
					}
					else if (myLoc.getCol()<g.getNumCols()-1&&!direction){
						return myLoc.getAdjacentLocation(Location.SOUTHEAST);
					}
				}
				else
					return null;
			}
			 return null;
		}
		
		private void changeDir() {
			direction=false;
		}

		public void move(){
			moveTo(moveLoc());
		}

		@Override
		public void shoot() {
		}


}
