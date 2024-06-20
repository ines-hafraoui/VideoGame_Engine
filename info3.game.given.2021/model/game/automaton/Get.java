package game.automaton;

import game.entity.Entity;

public class Get implements Action{
	
	public Get() {
		
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_get();
	}
	
}
