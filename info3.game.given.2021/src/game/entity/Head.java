package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.Grid;

public class Head extends Snake{

	Block next;
	
//	public Head (Orientation orientation, Automate aut, Grid grid) {
//		this.aut=aut;
//		this.g = grid;
//		this.orientation=orientation;
//	}
	
	public Head(Automate aut,Orientation orientation, Grid g ,Block next, int t, int x, int y) {
		super(aut, orientation,g,next, t,x,y);
	}

	public Orientation get_orientation() {
		return orientation;
	}
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		
		char r=  g.eval(dir, x, y, orientation);
		if(r != 'X') 
			return true; 
		return false;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getX() {
		return this.x;
	}
}
