package game.entity;

import game.automaton.*;
import game.grid.*;

public class Block extends Snake {

	public Block(Head h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	private Block previous;
	private Block next;
	
//	public Block (Block prev, Block next, Automate aut, Grid grid) {
//		this.previous=prev;
//		this.next=next;
//		this.aut=aut;
//		this.g=grid;
//	}
	
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
