package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Block extends Entity {

	public Block(Grid g,Orientation orientation,int x, int y) {
		super(g,x,y,orientation);
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

	@Override
	public boolean do_move() {
		switch(orientation.getOrientation()) {
		case 'N': 
			y--;
			return true;
		case 'S': 
			y++;
			return true;
		case 'E' : 
			x++;
			return true;
		case 'W': 
			x--;
			return true;
		}
		return false;
	}

	@Override
	public boolean do_egg() {
		// TODO Auto-generated method stub
		return false;
	}
}
