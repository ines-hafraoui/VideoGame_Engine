package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Cell extends Condition {

	Absolute_Orientation a;
	Relative_Orientation d; 
	Category cat; 
	int portee;
	
	public Cell (Relative_Orientation d, Category c, int portee ) {
		this.d = d; 
		cat = c; 
		this.portee=portee;
	}
	
	public Cell (Absolute_Orientation a, Category c, int portee ) {
		this.a = a; 
		cat = c; 
		this.portee=portee;
	}
	
	public boolean eval (Entity e) {

		if (a != null) return e.eval_cell_abs(a, cat, portee);
		return e.eval_cell_rel(d,cat,portee);

	}

}
