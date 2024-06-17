package game.automaton;

import game.entity.Entity;

public class Move implements Action{
	
	public Move() {
	}
	
	@Override
	public boolean exec(Entity e) {
		return e.do_move();
	}

}
