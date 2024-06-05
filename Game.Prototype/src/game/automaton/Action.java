package game.automaton;

import game.entity.Entity;

public interface Action {
	boolean exec(Entity e);
}
