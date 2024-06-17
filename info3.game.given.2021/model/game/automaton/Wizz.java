package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Wizz implements Action{
	
	Entity e;
	int factor;
	
	public Wizz(Entity e,int f) {
		this.e = e;
		factor =f;
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_wizz(factor);
	}

}
