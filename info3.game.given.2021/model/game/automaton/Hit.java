package game.automaton;

import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Hit implements Action {
	
	Absolute_Orientation or;
	Relative_Orientation ror;
	String type;
	int porte;
	
	public Hit(Absolute_Orientation o,String t,int p) {
		or = o;
		type = t;
		porte = p;
	}
	public Hit(Relative_Orientation o,String t,int p) {
		type = t;
		porte = p;
		ror = o;
	}

	@Override
	public boolean exec(Entity e) {
		return e.do_hit(e.get_abs_or(), type, porte);
	}
	

}