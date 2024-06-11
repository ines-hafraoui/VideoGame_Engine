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
	
	public  boolean do_turn(Orientation o) {
		orientation = o;
		return true;
	}
	
	public void set_automate(Automate a) {
		aut = a;
	}
	
	public Automate get_automate() {
		return aut;
	}
	
	
	public Entity[] get_entity() {
		Entity[] eList = new Entity[1];
		eList[0] = this;
		
		return eList;
	}
	public void tick(long elpased) {
		
		System.out.print("TICK in " + this + "\n" );
		if (aut != null) {
			aut.step(this);
		}
	}
}
