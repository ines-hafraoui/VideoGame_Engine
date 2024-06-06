package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public abstract class Entity {
	protected Automate aut;
	protected Grid g;
	protected  int x;
	protected  int y; 
	protected Orientation orientation;
	
	public Entity(Automate a,Grid g, int x,int y, Orientation o) {
		aut = a;
		this.g = g;
		this.x = x; 
		this.y = y; 
		orientation = o;
	}
	
	public Entity(Grid g, int x,int y, Orientation o) {
		this.g = g;
		this.x = x; 
		this.y = y; 
		orientation = o;
	}
	
	public abstract boolean eval_cell(Direction dir, Category cat);
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}

	public abstract boolean do_move();

	public abstract boolean do_egg();
	
	public void tick(long elpased) {
		
		if (aut != null) {
			aut.step(this);
		}
	}
}
