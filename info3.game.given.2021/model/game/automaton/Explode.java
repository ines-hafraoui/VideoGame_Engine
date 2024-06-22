package game.automaton;

import game.entity.Entity;

public class Explode extends Action {
	
	public Explode() {
		
	}
	
	@Override
	public boolean exec(Entity e) {
		e.do_explode();
		return true;
	}

}
