package game.automaton;

import game.entity.Entity;
import game.entity.Absolute_Orientation;

public class Turn extends Action{

	Absolute_Orientation o; 
	
	public Turn(Absolute_Orientation or) {
		o = or;
	}
	
	@Override
	public boolean exec(Entity e) {
		e.do_turn(o);
		return true;
	}	

}
