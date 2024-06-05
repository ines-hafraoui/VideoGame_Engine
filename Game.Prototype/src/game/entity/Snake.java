package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.*;

public class Snake{
	
	Head head; 
	private List<Block> body;
	
	
	public Snake(Head h) {
		head = h; 
		body = new ArrayList<>();
	}

	/* eval_cell call the method eval of Grid that needs
	 * the direction and category of the condition 
	 * and the coordinates + orientation of the snake
	 * 
	 * return value : boolean
	 */

}
