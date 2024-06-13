package game.automaton;

import game.entity.Entity;

public class Key implements Condition {
	
	Touche touche; 
	
	public Key (Touche t) {
		touche = t; 
	}
	
	public boolean eval (Entity e) {
		return e.eval_got(touche);
	}

}
