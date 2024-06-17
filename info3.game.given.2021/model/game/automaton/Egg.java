package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Egg implements Action{

	int cat;
	
	public Egg(int c) {
		cat = c;
	}

	@Override
	public boolean exec(Entity e) {
		e.do_egg(cat);
		return true;
	}

}
