package game.entity;

import game.entity.Position;
import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public abstract class Entity {
	protected Automate aut;
	protected Grid g;
	protected Orientation orientation;
	protected Automate automate;
	protected Position position;
	
	public Entity(Automate a,Grid g, Position p, Orientation o) {
		aut = a;
		this.g = g;
		position = p;
		orientation = o;
	}
	
	public Entity(Grid g,  Position p, Orientation o) {
		this.g = g;
		position = p;
		orientation = o;
	}
	
	public abstract boolean eval_cell(Direction dir, Category cat);
	
	public int get_x() {
		return position.x;
	}
	
	public int get_y() {
		return position.y;
	}

	public abstract boolean do_move();

	public abstract boolean do_egg();
	
	public void set_automate(Automate a) {
		automate = a;
	}
	
	public void tick(long elpased) {
		
		if (aut != null) {
			aut.step(this);
		}
	}
}
