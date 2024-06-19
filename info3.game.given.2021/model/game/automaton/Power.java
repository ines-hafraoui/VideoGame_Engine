package game.automaton;

import game.entity.Entity;

public class Power implements Action {

	
	public Power() {
	}

	@Override
	public boolean exec(Entity e) {
		e.do_power(5);
		return true;
	}

}
