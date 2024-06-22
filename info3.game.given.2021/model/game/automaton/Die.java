package game.automaton;

import game.entity.Entity;

public class Die extends Action {

	@Override
	public boolean exec(Entity e) {
		e.do_explode();
		return true;
	}

}
