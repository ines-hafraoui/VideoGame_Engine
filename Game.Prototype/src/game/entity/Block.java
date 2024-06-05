package game.entity;

import game.automaton.*;
import game.grid.*;

public class Block extends Entity {

	private Block previous;
	private Block next;
	
	public Block (Block prev, Block next, Automate aut, Grid grid) {
		this.previous=prev;
		this.next=next;
		this.aut=aut;
		this.g=grid;
	}
	
	boolean is_tail() {
		return (this.next==null);
	}
	
	public Block get_prev() {
		return previous;
	}
	
	public Block get_next() {
		return next;
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		return g.eval(dir, cat, x, y, orientation);
	}
}
