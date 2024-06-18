package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Wizz implements Action{

	int factor;
	
	public Wizz(int f) {
		factor =f;
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_wizz(factor);
	}

}
