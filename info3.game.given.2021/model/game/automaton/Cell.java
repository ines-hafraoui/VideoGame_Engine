package game.automaton;

import game.entity.Entity;

public class Cell implements Condition {
	
	Relative_Orientation d; 
	Category cat; 
	
	public Cell (Relative_Orientation d, Category c ) {
		this.d = d; 
		cat = c; 
	}
	
	public boolean eval (Entity e) {
		return e.eval_cell_rel(d,cat,0);
	}

}
