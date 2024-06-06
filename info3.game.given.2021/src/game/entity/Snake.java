package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.State;
import info3.game.snake.Grid;

public class Snake {
	
	private Head head; 
	private Block corps;
	private Automate automate;
	private Grid g;
	
	
	public Snake(Automate aut, Grid g, Head h) {
		automate = aut;
		head = h;
	}
	public Snake( Grid g, Head h) {
		head = h;
	}
	
	public void grow() {
		
		
		if (corps == null) {
			corps = new Block(g, head.get_x(), head.get_y());
		}
		
		Block tail = get_tail();
		
		Block new_tail = new Block(g, tail.get_x(), tail.get_y());
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

}
