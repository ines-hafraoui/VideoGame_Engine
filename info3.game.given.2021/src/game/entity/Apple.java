package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Apple extends Entity{

	private boolean eaten;
	
	public Apple (Automate a,Grid g, int x,int y, Orientation o) {
		super(a,g,x,y,o);
		eaten = false;
	}
	
	public boolean eaten () {
		return eaten;
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
