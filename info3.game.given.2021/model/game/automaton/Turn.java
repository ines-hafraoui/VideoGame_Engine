package game.automaton;

import game.entity.Entity;
import game.entity.Orientation;

public class Turn implements Action{
	
	Entity e;
	Orientation o; 
	
	public Turn(Entity e, Orientation or) {
		this.e = e;
		o = or;
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_turn(o);
	}	

}
