package game.automaton;

import game.entity.Entity;

public abstract class Condition {
	public int percent = Integer.MAX_VALUE;

	abstract boolean eval (Entity e);
	
	
}
