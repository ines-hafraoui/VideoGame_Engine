package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public abstract class Entity {
	protected Automate aut;
	protected Grid g;
	public int x;
	public int y; 
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
	
	public abstract int getY(); 
	public abstract int getX();

	public abstract boolean do_move();

	public abstract boolean do_egg();
}
