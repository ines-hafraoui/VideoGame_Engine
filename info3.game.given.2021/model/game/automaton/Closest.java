package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Closest extends Condition {
	
	Absolute_Orientation d;
	Category cat;
	float portee = Integer.MAX_VALUE;
	
	public Closest (Absolute_Orientation d, Category c) {
		this.d = d;
		cat = c; 
	}
	
	public Closest (Absolute_Orientation d, Category c, float portee) {
		this.d = d;
		cat = c; 
		this.portee = portee;
	}
	
	public boolean eval (Entity e) {
		return e.eval_closest(d,cat,portee);
	}

}
