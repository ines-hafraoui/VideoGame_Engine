package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Head extends Entity{
	
	public Head(Automate aut,Orientation orientation, Grid g ,int x, int y) {
		super(aut,g,x,y,orientation);
		
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

	@Override
	public boolean do_move() {
		switch(orientation.getOrientation()) {
		case 'N': 
			y--;
			return true;
		case 'S': 
			y++;
			return true;
		case 'E' : 
			x++;
			return true;
		case 'W': 
			x--;
			return true;
		}
		return false;
	}

	@Override
	public boolean do_egg() {
		// TODO Auto-generated method stub
		return false;
	}
}
