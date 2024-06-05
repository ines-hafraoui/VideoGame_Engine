package game.automaton;

import game.entity.Category;
import game.entity.Direction;
import game.entity.Entity;

public class Cell implements Condition {
	
	Direction d; 
	Category cat; 
	
	public Cell (Direction d, Category c ) {
		this.d = d; 
		cat = c; 
	}
	
	public boolean eval (Entity e) {
		// TODO;
		return false;
	}

}
