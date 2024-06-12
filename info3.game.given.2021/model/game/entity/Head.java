package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.Grid;

public class Head extends Entity{
	
	public Head(Automate aut,Absolute_Orientation orientation, Grid g ,Position p) {
		super(aut, g,p,orientation);
		
	}
	public Head(Absolute_Orientation orientation, Grid g ,Position p) {
		super(g,p,orientation);
		
	}
	public Absolute_Orientation get_orientation() {
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
			position.SetPositionX(get_y()-1);
			return true;
		case 'S': 
			position.SetPositionX(get_y()+1);
			return true;
		case 'E' : 
			position.SetPositionX(get_x()+1);
			return true;
		case 'W': 
			position.SetPositionX(get_x()-1);
			return true;
		}
		return false;
	}

	
	@Override
	public boolean do_egg() {
		return false;
	}
}
