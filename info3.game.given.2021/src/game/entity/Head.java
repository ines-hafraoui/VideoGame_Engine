package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Head extends Entity{
	
	public Head(Automate aut,Orientation orientation, Grid g ,Position p) {
		super(aut, g,p,orientation);
		
	}
	public Head(Orientation orientation, Grid g ,Position p) {
		super(g,p,orientation);
		
	}
	public Orientation get_orientation() {
		return orientation;
	}
	


	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		
		char r=  g.eval(dir, position.x, position.y, orientation);
		if(r != 'X') 
			return true; 
		return false;
	}

	@Override
	public boolean do_move() {
		switch(orientation.getOrientation()) {
		case 'N': 
			position.y--;
			return true;
		case 'S': 
			position.y++;
			return true;
		case 'E' : 
			position.x++;
			return true;
		case 'W': 
			position.x--;
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
