package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Wait implements Action {
	
	Entity e;
	int inc; 
	int select;
	
	
	public Wait(Entity e,int i, int s) {
		this.e = e;
		inc = i;
		select = s;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_wait(inc, select);
	}

}
