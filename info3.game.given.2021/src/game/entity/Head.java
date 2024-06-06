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
	


	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		
		char r=  g.eval(dir, x, y, orientation);
		if(r != 'X') 
			return true; 
		return false;
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
