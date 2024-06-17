package game.automaton;

import game.entity.Entity;

public class Power implements Action {
	
	Entity e;
	int increm;
	
	public Power(Entity e, int p) {
		this.e = e;
		increm = p;
	}

	@Override
	public boolean exec(Entity e) {
		e.do_power(increm);
		return true;
	}

}
