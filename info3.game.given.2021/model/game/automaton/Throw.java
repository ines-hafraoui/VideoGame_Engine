package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Throw implements Action{
	
	Entity e;
	
	public Throw(Entity e) {
		this.e = e;
	}

	@Override
	public boolean exec(Entity e) {
		Entity entity  = e.do_throw();
		if(entity != null) return true;
		return false;
	}
}
