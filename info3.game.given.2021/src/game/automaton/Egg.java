package game.automaton;

import game.entity.Entity;

public class Egg implements Action{

	@Override
	public boolean exec(Entity e) {
		return e.do_egg();
	}

}
