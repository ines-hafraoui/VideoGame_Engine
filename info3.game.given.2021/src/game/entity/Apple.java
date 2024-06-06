package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Apple extends Entity{

	private boolean eaten;
	
	public Apple (Automate a,Grid g, Position p, Orientation o) {
		super(a,g,p,o);
		eaten = false;
	}
	
	public Apple (Grid g, Position p, Orientation o) {
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
	 * la pomme ne bouge pas donc elle renvoie false car
	 * elle a pas bougé 
	 */
	
	@Override
	public boolean do_move() {
		return false;
	}

	@Override
	public boolean do_egg() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
