package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.Grid;

public class Apple extends Entity{

	private boolean eaten;
	
	public Apple (Automate a,Grid g, Position p, Absolute_Orientation o) {
		super(a,g,p,o);
		eaten = false;
	}
	
	public Apple (Grid g, Position p, Absolute_Orientation o) {
		super(g,p,o);
		eaten = false;
	}
	
	public boolean eaten () {
		return eaten;
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		char r=  g.eval(dir, position.x, position.y, orientation);
		if(r != 'X') 
			return true; 
		return false;
	}


	/*
	 * an apple do not move, return false bc didn't move
	 */
	
	@Override
	public boolean do_move() {
		System.out.print(get_x() + "\n");
		position.SetPositionX(get_x()+1);
		System.out.print(get_x() + "\n");

		return true;
	}

	/*
	 * an apple do not egg
	 */
	@Override
	public boolean do_egg() {
		return false;
	}

	
}
