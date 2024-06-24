package game.automaton;

import game.entity.Entity;

public abstract class Action {
	public int percent = Integer.MAX_VALUE;

	abstract boolean exec(Entity e);
}
