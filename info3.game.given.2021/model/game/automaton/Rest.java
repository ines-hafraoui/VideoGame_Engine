package game.automaton;

import game.entity.Entity;

public class Rest implements Action {

	int hp;
	
	public Rest(int n ) {
		hp = n;
	}

	@Override
	public boolean exec(Entity e) {
		e.do_rest(hp);
		return true;
	}

}
