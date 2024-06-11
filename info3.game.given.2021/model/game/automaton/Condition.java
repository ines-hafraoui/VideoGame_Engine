package game.automaton;

import game.entity.Entity;

public interface Condition {
	boolean eval (Entity e);
}
