package game.automaton;

import game.entity.Entity;

public class Power implements Action {

	int increm;
	
	public Power(int p) {
		increm = p;
	}

	@Override
	public boolean exec(Entity e) {
		e.do_power(increm);
		return true;
	}

}
