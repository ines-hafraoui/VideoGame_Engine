package game.automaton;

import game.entity.Entity;

public class Got implements Condition {
	
	Category cat; 
	
	public Got (Category c) {
		cat = c; 
	}
	
	public boolean eval (Entity e) {
		return e.eval_got(cat);
	}

}
