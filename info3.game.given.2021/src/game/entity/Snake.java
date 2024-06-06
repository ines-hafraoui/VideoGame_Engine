package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.State;
import info3.game.snake.Grid;

public class Snake {
	
	Head head; 
	List<Block> blocks;
	Automate automate;
	Orientation orientation;
	Grid g;
	
	
	public Snake(Automate aut,Orientation o, Grid g, Head h) {
		orientation = o;
		automate = aut;
		head = h;
		blocks = new ArrayList<>();
	}
	
	public Snake(Orientation o, Grid g, Head h) {
		orientation = o;
		head = h;
		blocks = new ArrayList<>();
	}

	/* eval_cell call the method eval of Grid that needs
	 * the direction and category of the condition 
	 * and the coordinates + orientation of the snake
	 * 
	 * return value : boolean
	 */

}
