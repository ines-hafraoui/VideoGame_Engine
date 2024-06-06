package game.entity;

import game.automaton.Automate;
import game.automaton.Direction;
import game.automaton.Category;
import game.automaton.State;
import game.grid.*;

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
	
	public abstract boolean eval_cell(Direction dir, Category cat);
	
	public abstract int getY(); 
	public abstract int getX();
}
