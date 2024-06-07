package game.automaton;

import game.entity.Entity;

public class TrueFalse implements Condition {

	public boolean value;
	
	public TrueFalse(boolean v){
		value = v;
	}
	
	@Override
	public boolean eval(Entity e) {
		return value;
	}

}
