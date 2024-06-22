package game.automaton;

import game.entity.Entity;

public class Negation extends Condition {
	Condition c;
	
	public Negation(Condition c){
		this.c = c;
	}
	@Override
	boolean eval(Entity e) {
		return !c.eval(e);
	}

}
