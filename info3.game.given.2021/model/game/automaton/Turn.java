package game.automaton;

import game.entity.Entity;
import game.entity.Absolute_Orientation;

public class Turn implements Action{
	
	Entity e;
	Absolute_Orientation o; 
	
	public Turn(Entity e, Absolute_Orientation or) {
		this.e = e;
		o = or;
	}
	
	@Override
	public boolean exec(Entity e) {
		e.do_turn(o);
		return true;
	}	

}
