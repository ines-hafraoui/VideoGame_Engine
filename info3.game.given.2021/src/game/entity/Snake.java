package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.*;

public abstract class Snake extends Entity{
	
	//Head head; 
	Block next;
	int team;
	
	
	public Snake(Automate aut,Orientation orientation, Grid g ,Block next, int t, int x, int y) {
		super(aut, g, x,y, orientation);
		//head = h; 
		this.next = next;
		team = t;
	}

	/* eval_cell call the method eval of Grid that needs
	 * the direction and category of the condition 
	 * and the coordinates + orientation of the snake
	 * 
	 * return value : boolean
	 */

}
