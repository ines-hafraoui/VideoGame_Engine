package game.automaton;

import game.entity.Entity;

public class Cell implements Condition {
	
	Direction d; 
	Category cat; 
	
	public Cell (Direction d, Category c ) {
		this.d = d; 
		cat = c; 
	}
	
	public boolean eval (Entity e) {
		return e.eval_cell(d,cat);
	}

}
