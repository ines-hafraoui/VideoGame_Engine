package game.automaton;

import game.entity.Entity;

public class Pick implements Action {
	String type;
	int distance;
	
	public Pick(String t, int d) {
		type = t;
		distance = d;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_pick(type, distance);
	}

}
