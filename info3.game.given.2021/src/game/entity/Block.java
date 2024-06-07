package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Block extends Entity {

	public Block(Head h, Grid g, Position p) {
		super(g,p,h.orientation);
	}

	private Entity previous;
	private Entity next;
	
//	public Block (Block prev, Block next, Automate aut, Grid grid) {
//		this.previous=prev;
//		this.next=next;
//		this.aut=aut;
//		this.g=grid;
//	}
	
	boolean is_tail() {
		return (this.next==null);
	}
	
	public Entity get_prev() {
		return previous;
	}
	
	public Entity get_next() {
		return next;
	}

	
	public void set_prev(Entity prev) {
		this.previous = prev;
	}
	
	public void set_next(Entity next) {
		this.next = next;
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
		
		Block prev = (Block)get_prev();
		if (prev != null) {
			this.position.SetPositionX(prev.position.getPositionX());
			this.position.SetPositionY(prev.position.getPositionY());
			return true;
		}
		return false;
	}

	@Override
	public boolean do_egg() {
		return false;
	}
}
