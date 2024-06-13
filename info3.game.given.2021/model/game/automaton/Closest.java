package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Closest implements Condition {
	
	Absolute_Orientation d;
	Category cat;
	
	public Closest (Absolute_Orientation d, Category c) {
		this.d = d;
		cat = c; 
	}
	
	public boolean eval (Entity e) {
		return e.eval_closest(d,cat);
	}

}
