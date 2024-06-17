package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Hit implements Action {
	
	Entity e;
	Absolute_Orientation or;
	String type;
	int porte;
	
	public Hit(Entity e,Absolute_Orientation o,String t,int p) {
		this.e = e;
		or = o;
		type = t;
		porte = p;
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_hit(or, type, porte);
	}
	

}