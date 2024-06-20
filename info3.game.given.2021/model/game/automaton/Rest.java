package game.automaton;

import game.entity.Entity;

public class Rest implements Action{
	int value;
	
	public Rest(int v) {
		this.value = v;
	}
	@Override
	public boolean exec(Entity e) {
		return e.do_rest(this.value);
		
	}

}
