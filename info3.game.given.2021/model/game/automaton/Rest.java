package game.automaton;

import game.entity.Entity;

public class Rest extends Action{
	int value;
	
	public Rest(int v) {
		this.value = v;
	}
	@Override
	public boolean exec(Entity e) {
		e.do_rest(this.value); //THIS MUST RETURN BOOLEAN
		return true;
	}

}
