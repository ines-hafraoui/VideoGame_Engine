package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Wait implements Action {
	
	int inc; 
	int select;
	
	
	public Wait(int i, int s) {
		inc = i;
		select = s;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_wait(inc, select);
	}

}
