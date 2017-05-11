import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GameWorld extends ActorWorld {
	Level n = new Level();
	Ship p;
	Location myLoc;
	JPanel panel;
	int count = 0;
	int enemyCount = 0;
	int levelCounter = 0;
	ArrayList<ArrayList<Mover>> levelList = new ArrayList<ArrayList<Mover>>();
	ArrayList<Mover> MoverList = new ArrayList<Mover>();
	ArrayList<Mover> MissileList = new ArrayList<Mover>();
	
	
	public GameWorld(JPanel pan){
		super(new BoundedGrid<Actor>(10,11));
		//Level level = new Level ();
		panel = pan;
		p = new Ship();
		add(new Location(9,5), p);
		myLoc = p.getLocation();
		makeLevelLists();
		Timer t = new Timer(300, new MoveObject());
		t.start();
		//level.incrementLevel();
	}
	
	public void makeLevelLists(){
		for (int i = 0; i<5; i++){
			 levelList.add(new ArrayList<Mover>());
		 }
		
	}
	
	
	class MoveObject implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("MOVER LIST CONTAINS: " + MoverList);
			removeMovers();
			moveMissiles();
			if (count%2==0){
				moveMovers();
			}
			if (count%7==0){
				moversShoot();
			}
			addEnemiesToGrid();
			panel.repaint();
			count++;
			if (count%50==0){
				n.incrementLevel();
				enemyCount=0;
			}
		}

		private void moveMissiles() {
			for (Mover m : MissileList){
				Location moveLoc = m.moveLoc();
				if(moveLoc == null){
					m.removeSelfFromGrid();
					return; 
				}
				int moveNum = m.moveme();
				
				if (moveNum == 11||moveNum == 1){
					m.moveTo(moveLoc);
				}
				else if(moveNum == 0){
					m.removeSelfFromGrid();
				}
				else if(moveNum == 15){
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				
				else if(moveNum == 13){
				}
			}
			
		}

		private void moversShoot() {
			Grid <Actor> g = getGrid();
			for (Mover m : MoverList){
				Location l = m.getLocation();
				if(l == null){
					return; 
				}
				Location south = m.getLocation().getAdjacentLocation(Location.SOUTH);
				if(south == null){
					System.out.println("south is null");
					return;
				}
				Location target = south.getAdjacentLocation(Location.SOUTH);
				EnemyMissile eM = new EnemyMissile();
				if (g.isValid(target)){
					if (g.get(target) instanceof Ship){
						JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
						System.exit(1);
					}
					else{
				add(target, eM);
				MissileList.add(eM);
					}
				}
			}
		}

		public void moveMovers(){
			for (Mover m : MoverList){
				Location moveLoc = m.moveLoc();
				int moveNum = m.moveme();
				
				if(moveNum == 1){
					m.moveTo(moveLoc);
					}
				else if(moveNum == 11){
					m.moveTo(moveLoc);
				}
				else if(moveNum == 15){
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
			}
		}
	public void removeMovers(){
		ArrayList<Mover> mList = new ArrayList<Mover>();
		ArrayList<Mover> miList = new ArrayList<Mover>();
		
		for (Mover m : MoverList){
			if(m.getLocation() == null){
				mList.add(m);
			}
			else{
				int moveNum = m.moveme();
				if(moveNum == 0){
					mList.add(m);
				}
				
				else if( moveNum==14){
					mList.add(m);
				}
				else if (moveNum == 15){
					mList.add(m);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
			}
		}	
		for (Mover mi : MissileList){
				if(mi.getLocation() == null){
					miList.add(mi);
				}
				else{
					int moveNum = mi.moveme();
					
					if(moveNum == 0){
						miList.add(mi);
					}
					else if(moveNum == 2|| moveNum==3){
						miList.add(mi);
						}
					else if(moveNum == 12 || moveNum == 13|| moveNum == 16){
						miList.add(mi);
					}
				}
		}
		
		ArrayList<Mover> comboList = new ArrayList<Mover>();
		comboList.addAll(mList);
		comboList.addAll(miList);
					
		for (Mover a : comboList){
			System.out.println("REMOVING NOW: "+ a);
			
			if(a.getLocation() != null){
				a.removeSelfFromGrid();
			}
			
		}
		MoverList.removeAll(mList);
		MissileList.removeAll(miList);
	}
	
	public void addEnemiesToGrid(){
		Grid <Actor> g = getGrid();
		ArrayList <Mover> temp =  n.getEnemyList();
		System.out.println ("Count is this: " + count);
		Location mine;
		if (count < 50){
			if(count%5 == 0 && enemyCount<temp.size()){
				Mover m = temp.get(enemyCount);
				if(m instanceof Basic||m instanceof ZigZag||m instanceof Boss){
					mine = new Location(0, (int)(g.getNumCols()*Math.random()));
					add(mine, m);
					MoverList.add(m);
					enemyCount++;
				}
				else if (m instanceof Twin){
					mine = new Location(0, g.getNumCols()/2); 
					add(mine,m);
					MoverList.add(m);
					enemyCount++;
				}
			}
		}
			else if (count>=50 && count <= 150){
				if(count%5 == 0 && enemyCount<temp.size()){
					Mover n = temp.get(enemyCount);
					if(n instanceof Basic||n instanceof ZigZag||n instanceof Boss){
						mine = new Location(0, (int)(g.getNumCols()*Math.random()));
						add(mine, n);
						MoverList.add(n);
						enemyCount++;
					}
					else if (n instanceof Twin){
						mine = new Location(0, g.getNumCols()/2); 
						add(mine,n);
						MoverList.add(n);
						enemyCount++;
					}
				}
		}
			else if (count>=150 && count <= 250){
				if(count%5 == 0 && enemyCount<temp.size()){
					Mover n = temp.get(enemyCount);
					if(n instanceof Basic||n instanceof ZigZag||n instanceof Boss){
						mine = new Location(0, (int)(g.getNumCols()*Math.random()));
						add(mine, n);
						MoverList.add(n);
						enemyCount++;
					}
					else if (n instanceof Twin){
						mine = new Location(0, g.getNumCols()/2); 
						add(mine,n);
						MoverList.add(n);
						enemyCount++;
					}
				}
		}
			else if (count>=250 && count <= 350){
				if(count%5 == 0 && enemyCount<temp.size()){
					Mover n = temp.get(enemyCount);
					if(n instanceof Basic||n instanceof ZigZag||n instanceof Boss){
						mine = new Location(0, (int)(g.getNumCols()*Math.random()));
						add(mine, n);
						MoverList.add(n);
						enemyCount++;
					}
					else if (n instanceof Twin){
						mine = new Location(0, g.getNumCols()/2); 
						add(mine,n);
						MoverList.add(n);
						enemyCount++;
					}
				}
		}else if (count>350){
			if(count%5 == 0 && enemyCount<temp.size()){
				Mover n = temp.get(enemyCount);
				if(n instanceof Basic||n instanceof ZigZag||n instanceof Boss){
					mine = new Location(0, (int)(g.getNumCols()*Math.random()));
					add(mine, n);
					MoverList.add(n);
					enemyCount++;
				}
				else if (n instanceof Twin){
					mine = new Location(0, g.getNumCols()/2); 
					add(mine,n);
					MoverList.add(n);
					enemyCount++;
				}
			}
		}
	}
	}
	
	
	@Override
	public boolean keyPressed(String s, Location loc){
		Grid<Actor> g = getGrid(); 
		if (s.equals("RIGHT")){
			if (g.isValid(myLoc.getAdjacentLocation(Location.EAST))){
				Actor removed = g.remove(myLoc.getAdjacentLocation(Location.EAST));
				if(removed instanceof Enemy){
					MoverList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				else if(removed instanceof EnemyMissile){
					MissileList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				p.moveTo(new Location(myLoc.getRow(), myLoc.getCol()+1));
				myLoc=p.getLocation();
			}
			return false;
		}
		else if (s.equals("LEFT")){
			if (g.isValid(myLoc.getAdjacentLocation(Location.WEST))){
				Actor removed = g.remove(myLoc.getAdjacentLocation(Location.WEST));
				if(removed instanceof Enemy){
					MoverList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				else if(removed instanceof EnemyMissile){
					MissileList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				p.moveTo(new Location(myLoc.getRow(), myLoc.getCol()-1));
				myLoc=p.getLocation();
			}
			return false;
		}
		else if (s.equals("UP")){
			if (g.isValid(myLoc.getAdjacentLocation(Location.NORTH))){
				Actor removed = g.remove(myLoc.getAdjacentLocation(Location.NORTH));
				if(removed instanceof Enemy){
					MoverList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				else if(removed instanceof EnemyMissile){
					MissileList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				p.moveTo(new Location(myLoc.getRow()-1, myLoc.getCol()));
				myLoc=p.getLocation();
			}
			return false;
		}
		else if (s.equals("DOWN")){
			if (g.isValid(myLoc.getAdjacentLocation(Location.SOUTH))){
				Actor removed = g.remove(myLoc.getAdjacentLocation(Location.SOUTH));
				if(removed instanceof Enemy){
					MoverList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				else if(removed instanceof EnemyMissile){
					MissileList.remove(removed);
					JOptionPane.showMessageDialog(panel, "You lose", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(1);
				}
				p.moveTo(new Location(myLoc.getRow()+1, myLoc.getCol()));
				myLoc=p.getLocation();
			}
			return false;
		}
		else if (s.equals("SPACE")){
			if (g.isValid(myLoc.getAdjacentLocation(Location.NORTH))){
				if (!(g.get(myLoc.getAdjacentLocation(Location.NORTH)) instanceof Missile)){
				g.remove(myLoc.getAdjacentLocation(Location.NORTH));
				Missile missile = new Missile();
				add(myLoc.getAdjacentLocation(Location.NORTH), missile);
				g.put(myLoc.getAdjacentLocation(Location.NORTH), missile);
				MissileList.add(missile);
				}
			}
		}
		return true;		
	}
	public void paint(Graphics g2) {
		Grid<Actor> g = getGrid();
		ArrayList<Location> move = g.getOccupiedLocations();
		for(int i = 0; i < move.size(); i++) {
			Mover m = (Mover) g.get(move.get(i));
			System.out.println("Painting: "+m);
			m.paint(g2);
		}
	}

	
	
	}
