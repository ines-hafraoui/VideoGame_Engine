package game.automaton;

import game.entity.Entity;

public class Pick implements Action {

	Entity e;
	String type;
	int distance;
	
	public Pick(Entity e, String t, int d) {
		this.e = e;
		type = t;
		distance = d;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_pick(type, distance);
	}

}
