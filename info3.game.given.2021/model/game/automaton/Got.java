package game.automaton;

import game.entity.Entity;

public class Got implements Condition {
	
	Category cat; 
	boolean got;
	
	public Got (Category c) {
		cat = c;
		this.got = true;
	}
	
	public boolean eval (Entity e) {
		return e.eval_got(cat);
	}

}
