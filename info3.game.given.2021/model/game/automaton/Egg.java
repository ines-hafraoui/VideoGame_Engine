package game.automaton;

import game.entity.Entity;

public class Egg implements Action{

	public Egg() {
		
	}
	@Override
	public boolean exec(Entity e) {
		return e.do_egg();
	}

}
