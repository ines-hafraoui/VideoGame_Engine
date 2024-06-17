package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Hit implements Action {
	
	Absolute_Orientation or;
	String type;
	int porte;
	
	public Hit(Absolute_Orientation o,String t,int p) {
		or = o;
		type = t;
		porte = p;
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_hit(or, type, porte);
	}
	

}