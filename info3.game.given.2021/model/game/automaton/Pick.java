package game.automaton;

import game.entity.Entity;

public class Pick implements Action {
	int distance;
	
	public Pick(int d) {
		distance = d;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_pick(distance);
	}

}
