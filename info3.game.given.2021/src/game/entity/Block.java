package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import info3.game.snake.Grid;

public class Block extends Entity {

	public Block(Grid g, int x, int y) {
		super(g,x,y,new Orientation('N'));
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
		char r=  g.eval(dir, x, y, orientation);
		if(r != 'X') 
			return true; 
		return false;
	}


	@Override
	public boolean do_move() {
		switch(orientation.getOrientation()) {
		case 'N': 
			this.y --;
			return true;
		case 'S': 
			super.y++;
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
