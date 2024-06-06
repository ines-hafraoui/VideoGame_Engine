package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.automaton.State;
import info3.game.snake.Grid;

public class Snake extends Entity{
	
	private Head head; 
	private Block corps;
	private Grid g;
	
	
	public Snake(Automate aut, Position p, Grid g, Orientation o) {
		super(aut,g,p,o);
		automate = aut;
		head = new Head(o,g,p);
	}
	
	public Snake( Position p, Grid g, Orientation o) {
		super(g,p,o);
		head = new Head(o,g,p);
	}
	
	public void grow() {
		
		
		if (corps == null) {
			corps = new Block(head, g,head.position);
		}
		
		Block tail = get_tail();
		
		Block new_tail = new Block(head, g,tail.position);
		tail.set_next(new_tail);
		new_tail.set_prev(tail);
	}
	



	public Block get_tail() {
		
		// i allow myself to cast since i'm in a snake
		//and i know it's corps is made of blocks only
		Block  c_tail = corps;

		if (c_tail != null) {
			while (c_tail.get_next() != null) {
				c_tail = (Block) c_tail.get_next();
			}		
		}
		
		return c_tail;
	}
	
	public void set_automate(Automate aut) {
		this.automate = aut;
	}
	
	
	/* eval_cell call the method eval of Grid that needs
	 * the direction and category of the condition 
	 * and the coordinates + orientation of the snake
	 * 
	 * return value : boolean
	 */
	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean do_move() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean do_egg() {
		// TODO Auto-generated method stub
		return false;
	}

}
